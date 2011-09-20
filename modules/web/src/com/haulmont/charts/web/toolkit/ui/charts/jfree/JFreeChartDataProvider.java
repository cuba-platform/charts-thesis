/*
 * Copyright (c) 2011 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */
package com.haulmont.charts.web.toolkit.ui.charts.jfree;

import com.haulmont.charts.web.toolkit.ui.charts.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class JFreeChartDataProvider implements VChartDataProvider<JFreeChart> {
    private static final long serialVersionUID = -8688971084440222503L;

    private Log log = LogFactory.getLog(JFreeChartDataProvider.class);

    public synchronized void handleDataRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            JFreeChart chart
    ) throws ChartException {
        org.jfree.chart.JFreeChart jFreeChart;

        if (chart instanceof VPieChart) {
            jFreeChart = createPieChart((JFreePieChart) chart);
        } else if (chart instanceof VBarChart) {
            jFreeChart = createBarChart((JFreeBarChart) chart);
        } else if (chart instanceof VLineChart) {
            jFreeChart = createLineChart((JFreeLineChart) chart);
        } else if (chart instanceof VXYLineChart) {
            jFreeChart = createXYLineChart((JFreeXYLineChart) chart);
        } else {
            log.warn(String.format("This data provider doesn't support a chart type for class: %s",
                    chart.getClass()));
            return;
        }

        try {
            OutputStream out = null;
            try {
                out = response.getOutputStream();

                if (jFreeChart != null) {
                    TextTitle chartTitle = jFreeChart.getTitle();
                    if (chartTitle != null) {
                        chartTitle.setFont(new Font(null, 0, 20));
                    }

                    response.setContentType("image/png");
                    ChartUtilities.writeChartAsPNG(out, jFreeChart, chart.getChartWidth(), chart.getChartHeight());
                }
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new ChartException(e);
        }
    }

    private org.jfree.chart.JFreeChart createPieChart(JFreePieChart chart) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Object valuePropertyId = chart.getCategoryPropertyIds().iterator().next();

        for (final Object itemId : chart.getRowIds()) {
            Number value = getNumberValue(chart.getValue(itemId, valuePropertyId));

            dataset.setValue(
                    chart.getRowCaption(itemId),
                    value
            );
        }

        org.jfree.chart.JFreeChart result;
        if (chart.is3D()) {
            result = ChartFactory.createPieChart3D(
                    getChartTitle(chart),
                    dataset,
                    chart.getHasLegend(),
                    false,
                    false
            );
        } else {
            result = ChartFactory.createPieChart(
                    getChartTitle(chart),
                    dataset,
                    chart.getHasLegend(),
                    false,
                    false
            );
        }

        PiePlot plot = (PiePlot) result.getPlot();
        plot.setIgnoreNullValues(chart.isIgnoreNullValues());
        plot.setIgnoreZeroValues(chart.isIgnoreZeroValues());
        plot.setOutlineVisible(false);

        return result;
    }

    private org.jfree.chart.JFreeChart createBarChart(JFreeBarChart chart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (final Object itemId : chart.getRowIds()) {
            for (Object categoryPropertyId : chart.getCategoryPropertyIds()) {
                Number value = getNumberValue(chart.getValue(itemId, categoryPropertyId));

                dataset.addValue(
                    value,
                    chart.getRowCaption(itemId),
                    chart.getCategoryCaption(categoryPropertyId)
                );
            }
        }

        org.jfree.chart.JFreeChart result;
        if (chart.is3D()) {
            result = ChartFactory.createBarChart3D(
                    getChartTitle(chart),
                    chart.getArgumentAxisLabel(),
                    chart.getValueAxisLabel(),
                    dataset,
                    convertChartOrientation(chart.getOrientation()),
                    chart.getHasLegend(),
                    false,
                    false
            );
        } else {
            result = ChartFactory.createBarChart(
                    getChartTitle(chart),
                    chart.getArgumentAxisLabel(),
                    chart.getValueAxisLabel(),
                    dataset,
                    convertChartOrientation(chart.getOrientation()),
                    chart.getHasLegend(),
                    false,
                    false
            );
        }

        ValueAxis rangeAxis =  getValueAxis(chart.getValueAxisType(), chart.is3D());
        if (rangeAxis != null) {
            result.getCategoryPlot().setRangeAxis(rangeAxis);
        }

        return result;
    }

    private org.jfree.chart.JFreeChart createLineChart(JFreeLineChart chart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (final Object itemId : chart.getRowIds()) {
            for (Object categoryPropertyId : chart.getCategoryPropertyIds()) {
                Number value = getNumberValue(chart.getValue(itemId, categoryPropertyId));

                dataset.addValue(
                    value,
                    chart.getRowCaption(itemId),
                    chart.getCategoryCaption(categoryPropertyId)
                );
            }
        }

        org.jfree.chart.JFreeChart result = ChartFactory.createLineChart(
                getChartTitle(chart),
                chart.getArgumentAxisLabel(),
                chart.getValueAxisLabel(),
                dataset,
                convertChartOrientation(chart.getOrientation()),
                chart.getHasLegend(),
                false,
                false
        );

        ValueAxis rangeAxis =  getValueAxis(chart.getValueAxisType(), false);
        if (rangeAxis != null) {
            result.getCategoryPlot().setRangeAxis(rangeAxis);
        }

        return result;
    }

    private org.jfree.chart.JFreeChart createXYLineChart(JFreeXYLineChart chart) {
        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (final VXYChartRow row : chart.getRows()) {
            XYSeries series = new XYSeries(getRowCaption(row));

            for (Object pointItemId : row.getPointIds()) {
                Number y = getNumberValue(row.getYValue(pointItemId));
                Number x = getNumberValue(row.getXValue(pointItemId));

                if (x != null) {
                    series.add(x, y);
                }
            }

            dataset.addSeries(series);
        }

        org.jfree.chart.JFreeChart result = ChartFactory.createXYLineChart(
                getChartTitle(chart),
                chart.getArgumentAxisLabel(),
                chart.getValueAxisLabel(),
                dataset,
                convertChartOrientation(chart.getOrientation()),
                chart.getHasLegend(),
                false,
                false
        );

        ValueAxis rangeAxis =  getValueAxis(chart.getValueAxisType(), false);
        if (rangeAxis != null) {
            result.getXYPlot().setRangeAxis(rangeAxis);
        }

        ValueAxis domainAxis = getValueAxis(chart.getArgumentAxisType(), false);
        if (domainAxis != null) {
            result.getXYPlot().setDomainAxis(domainAxis);
        }

        return result;
    }

    private ValueAxis getValueAxis(VChart.AxisType axisType, boolean is3D) {
        switch (axisType) {
            case NUMBER:
                if (is3D) {
                    return new NumberAxis3D();
                } else {
                    return new NumberAxis();
                }
            case DATE:
                return new DateAxis();
            default:
                return null;
        }
    }

    private Number getNumberValue(Object value) {
        if (value instanceof Number) {
            return (Number)value;
        } else if (value instanceof Boolean) {
            return (Boolean)value ? 1 : 0;
        } else if (value instanceof Date) {
            return ((Date)value).getTime();
        } else {
            return null;
        }
    }

    private String getRowCaption(VXYChartRow row) {
        return row.getCaption() == null ? "" : row.getCaption();
    }

    private String getChartTitle(JFreeChart chart) {
        return chart.getCaption() == null ? "" : chart.getCaption();
    }

    private static PlotOrientation convertChartOrientation(VChart.Orientation orientation) {
        return orientation == VChart.Orientation.VERTICAL
                ? PlotOrientation.VERTICAL : PlotOrientation.HORIZONTAL;
    }
}
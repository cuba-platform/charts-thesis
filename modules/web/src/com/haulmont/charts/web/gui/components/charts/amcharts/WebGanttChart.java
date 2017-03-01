/*
 * Copyright (c) 2008-2017 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.web.gui.components.charts.amcharts;

import com.haulmont.charts.gui.amcharts.model.DatePeriod;
import com.haulmont.charts.gui.amcharts.model.Graph;
import com.haulmont.charts.gui.amcharts.model.ValueAxis;
import com.haulmont.charts.gui.components.charts.GanttChart;
import com.haulmont.chile.core.datatypes.Datatype;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.chile.core.model.MetaProperty;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UuidProvider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class WebGanttChart extends WebSeriesBasedChart<GanttChart, com.haulmont.charts.gui.amcharts.model.charts.GanttChart>
        implements GanttChart {

    @Override
    protected com.haulmont.charts.gui.amcharts.model.charts.GanttChart createChartConfiguration() {
        return new com.haulmont.charts.gui.amcharts.model.charts.GanttChart();
    }

    @Override
    protected Entity getEventItem(String itemIdString) {
        if (datasource != null && StringUtils.isNotEmpty(itemIdString)) {
            return getGanttChartEventItem(itemIdString);
        }
        return null;
    }

    protected Entity getGanttChartEventItem(String itemIdString) {
        com.haulmont.charts.gui.amcharts.model.charts.GanttChart ganttChart = getModel();

        String[] ids = itemIdString.split(":");
        if (ids.length != 2) {
            return null;
        }

        Object categoryId = getItemId(datasource, ids[0]);
        if (categoryId == null) {
            return null;
        }

        //noinspection unchecked
        Entity category = datasource.getItem(categoryId);
        if (category == null) {
            return null;
        }

        Collection segments = category.getValue(ganttChart.getSegmentsField());
        if (segments == null) {
            return null;
        }

        Object segmentId = getItemId(segments, ids[1]);
        if (segmentId == null) {
            return null;
        }

        for (Object segment : segments) {
            if (segment instanceof Entity && segmentId.equals(((Entity) segment).getId())) {
                return (Entity) segment;
            }
        }

        return null;
    }

    @Nullable
    protected Object getItemId(Collection items, String itemIdString) {
        if (CollectionUtils.isNotEmpty(items)) {
            Object obj = items.iterator().next();
            if (obj instanceof Entity) {
                Entity entity = (Entity) obj;

                Metadata metadata = AppBeans.get(Metadata.class);
                MetaClass metaClass = metadata.getClassNN(entity.getClass());
                if (metadata.getTools().isTransient(metaClass)) {
                    return UuidProvider.fromString(itemIdString);
                }
                MetaProperty pkProp = metadata.getTools().getPrimaryKeyProperty(metaClass);
                if (pkProp != null) {
                    Datatype<Object> datatype = pkProp.getRange().asDatatype();
                    try {
                        return datatype.parse(itemIdString);
                    } catch (ParseException e) {
                        throw new RuntimeException("Error parsing item ID", e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Integer getBrightnessStep() {
        return getModel().getBrightnessStep();
    }

    @Override
    public GanttChart setBrightnessStep(Integer brightnessStep) {
        getModel().setBrightnessStep(brightnessStep);
        return this;
    }

    @Override
    public String getColorField() {
        return getModel().getColorField();
    }

    @Override
    public GanttChart setColorField(String colorField) {
        getModel().setColorField(colorField);
        return this;
    }

    @Override
    public String getColumnWidthField() {
        return getModel().getColumnWidthField();
    }

    @Override
    public GanttChart setColumnWidthField(String columnWidthField) {
        getModel().setColumnWidthField(columnWidthField);
        return this;
    }

    @Override
    public String getDurationField() {
        return getModel().getDurationField();
    }

    @Override
    public GanttChart setDurationField(String durationField) {
        getModel().setDurationField(durationField);
        return this;
    }

    @Override
    public String getEndDateField() {
        return getModel().getEndDateField();
    }

    @Override
    public GanttChart setEndDateField(String endDateField) {
        getModel().setEndDateField(endDateField);
        return this;
    }

    @Override
    public String getEndField() {
        return getModel().getEndField();
    }

    @Override
    public GanttChart setEndField(String endField) {
        getModel().setEndField(endField);
        return this;
    }

    @Override
    public Graph getGraph() {
        return getModel().getGraph();
    }

    @Override
    public GanttChart setGraph(Graph graph) {
        getModel().setGraph(graph);
        return this;
    }

    @Override
    public DatePeriod getPeriod() {
        return getModel().getPeriod();
    }

    @Override
    public GanttChart setPeriod(DatePeriod period) {
        getModel().setPeriod(period);
        return this;
    }

    @Override
    public String getSegmentsField() {
        return getModel().getSegmentsField();
    }

    @Override
    public GanttChart setSegmentsField(String segmentsField) {
        getModel().setSegmentsField(segmentsField);
        return this;
    }

    @Override
    public Date getStartDate() {
        return getModel().getStartDate();
    }

    @Override
    public GanttChart setStartDate(Date startDate) {
        getModel().setStartDate(startDate);
        return this;
    }

    @Override
    public String getStartDateField() {
        return getModel().getStartDateField();
    }

    @Override
    public GanttChart setStartDateField(String startDateField) {
        getModel().setStartDateField(startDateField);
        return this;
    }

    @Override
    public String getStartField() {
        return getModel().getStartField();
    }

    @Override
    public GanttChart setStartField(String startField) {
        getModel().setStartField(startField);
        return this;
    }

    @Override
    public ValueAxis getValueAxis() {
        return getModel().getValueAxis();
    }

    @Override
    public GanttChart setValueAxis(ValueAxis valueAxis) {
        getModel().setValueAxis(valueAxis);
        return this;
    }

    @Override
    public List<String> getAdditionalSegmentFields() {
        return getModel().getAdditionalSegmentFields();
    }

    @Override
    public GanttChart setAdditionalSegmentFields(List<String> additionalSegmentFields) {
        getModel().setAdditionalSegmentFields(additionalSegmentFields);
        return this;
    }

    @Override
    public GanttChart addAdditionalSegmentFields(String... fields) {
        getModel().addAdditionalSegmentFields(fields);
        return this;
    }
}
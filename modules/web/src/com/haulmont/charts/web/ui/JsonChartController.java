/*
 * Copyright (c) 2008-2015 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */
package com.haulmont.charts.web.ui;

import com.haulmont.charts.web.gui.components.charts.amcharts.WebChart;
import com.haulmont.charts.web.toolkit.ui.amcharts.CubaAmchartsScene;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractFrame;

import java.util.Map;

/**
 * @author degtyarjov
 */
public class JsonChartController extends AbstractFrame {
    @WindowParam
    protected String chartJson;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        WebChart webChart = new WebChart();
        webChart.setHeight("100%");
        webChart.setWidth("100%");
        CubaAmchartsScene cubaAmchartsScene = webChart.getComponent();
        cubaAmchartsScene.setJson(chartJson);
        cubaAmchartsScene.drawChart();
        add(webChart);
    }
}
/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.web.toolkit.ui.amcharts.events;

import com.haulmont.charts.web.toolkit.ui.amcharts.CubaAmchartsScene;

/**
 */
public class ChartRightClickEvent extends AbstractClickEvent {

    private static final long serialVersionUID = 5192245671928509489L;

    private final double xAxis;
    private final double yAxis;

    public ChartRightClickEvent(CubaAmchartsScene scene, int x, int y, int absoluteX, int absoluteY, double xAxis, double yAxis) {
        super(scene, x, y, absoluteX, absoluteY);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public double getXAxis() {
        return xAxis;
    }

    public double getYAxis() {
        return yAxis;
    }
}
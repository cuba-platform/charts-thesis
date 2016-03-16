/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.gui.amcharts.model;

/**
 */
public class DateFormat extends AbstractChartObject {

    private static final long serialVersionUID = 2962826142162852907L;

    private DatePeriod period;

    private String format;

    public String getFormat() {
        return format;
    }

    public DateFormat setFormat(String format) {
        this.format = format;
        return this;
    }

    public DatePeriod getPeriod() {
        return period;
    }

    public DateFormat setPeriod(DatePeriod period) {
        this.period = period;
        return this;
    }
}
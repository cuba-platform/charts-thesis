/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.gui.amcharts.model;

/**
 */
public enum StockEventType implements ChartEnum {
    FLAG("flag"),
    SIGN("sign"),
    PIN("pin"),
    TRIANGLE_UP("triangleUp"),
    TRIANGLE_DOWN("triangleDown"),
    TRIANGLE_LEFT("triangleLeft"),
    TRIANGLE_RIGHT("triangleRight"),
    TEXT("text"),
    ARROW_UP("arrowUp"),
    ARROW_DOWN("arrowDown");

    private String id;

    StockEventType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}

/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.web.toolkit.ui.amcharts.events;

import com.haulmont.charts.web.toolkit.ui.amcharts.CubaAmchartsScene;

/**
 */
public class GraphItemClickEvent extends com.vaadin.ui.Component.Event {

    private static final long serialVersionUID = 5059266635532077593L;

    private final String graphId;

    private final int x;
    private final int y;
    private final int absoluteX;
    private final int absoluteY;

    private final int itemIndex;
    private final String itemId;

    public GraphItemClickEvent(CubaAmchartsScene scene, String graphId, int itemIndex,
                               String itemId, int x, int y, int absoluteX, int absoluteY) {
        super(scene);
        this.itemIndex = itemIndex;
        this.itemId = itemId;
        this.absoluteY = absoluteY;
        this.absoluteX = absoluteX;
        this.graphId = graphId;
        this.x = x;
        this.y = y;
    }

    public int getAbsoluteX() {
        return absoluteX;
    }

    public int getAbsoluteY() {
        return absoluteY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getGraphId() {
        return graphId;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getItemId() {
        return itemId;
    }
}
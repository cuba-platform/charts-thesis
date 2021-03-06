/*
 * Copyright (c) 2008-2014 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.web.toolkit.ui.amcharts.events;

import com.haulmont.charts.web.toolkit.ui.amcharts.CubaAmchartsScene;

/**
 * @author artamonov
 * @version $Id$
 */
public class LegendMarkerClickEvent extends com.vaadin.ui.Component.Event {

    private static final long serialVersionUID = -536343761071370040L;

    private final String itemId;

    public LegendMarkerClickEvent(CubaAmchartsScene scene, String itemId) {
        super(scene);
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
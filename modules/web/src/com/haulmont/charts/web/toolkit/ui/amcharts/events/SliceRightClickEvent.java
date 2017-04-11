/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.web.toolkit.ui.amcharts.events;

import com.haulmont.charts.gui.data.DataItem;
import com.haulmont.charts.web.toolkit.ui.amcharts.CubaAmchartsScene;

public class SliceRightClickEvent extends AbstractClickEvent {

    private static final long serialVersionUID = -536343761071370040L;

    private final DataItem dataItem;

    public SliceRightClickEvent(CubaAmchartsScene scene, DataItem dataItem, int x, int y, int absoluteX, int absoluteY) {
        super(scene, x, y, absoluteX, absoluteY);
        this.dataItem = dataItem;
    }

    public DataItem getDataItem() {
        return dataItem;
    }
}
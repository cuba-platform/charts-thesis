/*
 * Copyright (c) 2008-2015 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.gui.amcharts.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gorelov
 * @version $Id$
 */
public class DataItemsChangeEvent implements Serializable {

    private final DataChangeOperation operation;
    private final List<DataItem> items;

    public DataItemsChangeEvent(DataChangeOperation operation, List<DataItem> items) {
        this.operation = operation;
        this.items = items;
    }

    public DataChangeOperation getOperation() {
        return operation;
    }

    public List<DataItem> getItems() {
        return items;
    }
}
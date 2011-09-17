/*
 * Copyright (c) 2011 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.charts.gui.components.charts;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.CollectionDatasource;

/**
 * <p>$Id$</p>
 *
 * @author zagumennikov
 */
public interface XYChartRow extends Component, Component.Wrapper,
        Component.HasXmlDescriptor, Component.HasCaption {

    String NAME = "xyChartRow";

    CollectionDatasource getCollectionDatasource();
    void setCollectionDatasource(CollectionDatasource datasource);

    Object getXProperty();
    void setXProperty(Object property);

    Object getYProperty();
    void setYProperty(Object property);
}
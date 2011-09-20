/*
 * Copyright (c) 2011 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */
package com.haulmont.charts.gui.xml.layout.loaders.charts;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.charts.BarChart;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.dom4j.Element;

public class BarChartLoader extends AbstractCategoryChartLoader {
    private static final long serialVersionUID = -8059950271995313942L;

    public BarChartLoader(Context context) {
        super(context);
    }

    @Override
    public BarChart loadComponent(ComponentsFactory factory, Element element, Component parent)
            throws InstantiationException, IllegalAccessException {

        BarChart component = (BarChart) super.loadComponent(factory, element, parent);

        load3D(component, element);
        loadOrientation(component, element);
        loadAxisLabels(component, element);
        loadValueAxisType(component, element);

        return component;
    }
}
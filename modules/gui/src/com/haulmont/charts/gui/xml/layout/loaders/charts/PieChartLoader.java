/*
 * Copyright (c) 2011 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */
package com.haulmont.charts.gui.xml.layout.loaders.charts;

import com.haulmont.charts.gui.components.charts.CategoryChart;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.chile.core.model.MetaProperty;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class PieChartLoader extends AbstractCategoryChartLoader {

    public PieChartLoader(Context context) {
        super(context);
    }

    @Override
    public PieChart loadComponent(ComponentsFactory factory, Element element, Component parent) {
        PieChart component = (PieChart) super.loadComponent(factory, element, parent);

        load3D(component, element);

        return component;
    }

    @Override
    protected void loadDatasource(CategoryChart component, Element element) {
        String datasource = element.attributeValue("datasource");
        if (!StringUtils.isEmpty(datasource)) {
            CollectionDatasource ds = context.getDsContext().get(datasource);
            if (ds == null) {
                throw new IllegalStateException("Cannot find data source by name: " + datasource);
            }

            String valueProperty = element.attributeValue("valueProperty");
            if (StringUtils.isEmpty(valueProperty)) {
                throw new IllegalAccessError("PieChart must contains non-empty 'valueProperty' attribute");
            }

            MetaProperty property = ds.getMetaClass().getProperty(valueProperty);
            if (property == null) {
                throw new IllegalStateException(String.format("Property '%s' not found in entity '%s'",
                        valueProperty, ds.getMetaClass().getName()));
            }

            component.addCategory(property, null);

            String captionPropertyString = element.attributeValue("captionProperty");
            if (!StringUtils.isEmpty(captionPropertyString)) {
                MetaProperty captionProperty = ds.getMetaClass().getProperty(captionPropertyString);
                if (captionProperty == null) {
                    throw new IllegalStateException(String.format("Property '%s' not found in entity '%s'",
                            captionProperty, ds.getMetaClass().getName()));
                }
                component.setRowCaptionProperty(captionProperty);
            }

            component.setCollectionDatasource(ds);
        }
    }
}

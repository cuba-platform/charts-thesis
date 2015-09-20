/*
 * Copyright (c) 2008-2015 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.gui.map.model;

/**
 * @author korotkov
 * @version $Id$
 */
public interface Circle {

    public GeoPoint getCenter();

    public void setCenter(GeoPoint center);

    public boolean isDraggable();

    public void setDraggable(boolean draggable);

    public boolean isEditable();

    public void setEditable(boolean editable);

    public boolean isVisible();

    public void setVisible(boolean visible);

    public boolean isClickable();

    public void setClickable(boolean clickable);

    public double getRadius();

    public void setRadius(double radius);

    public String getFillColor();

    public void setFillColor(String fillColor);

    public double getFillOpacity();

    public void setFillOpacity(double fillOpacity);

    public String getStrokeColor();

    public void setStrokeColor(String strokeColor);

    public double getStrokeOpacity();

    public void setStrokeOpacity(double strokeOpacity);

    public int getStrokeWeight();

    public void setStrokeWeight(int strokeWeight);

    public int getzIndex();

    public void setzIndex(int zIndex);
}

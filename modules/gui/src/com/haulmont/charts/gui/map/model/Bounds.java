/*
 * Copyright (c) 2008-2015 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.gui.map.model;

/**
 * @author korotkov
 * @version $Id$
 */
public interface Bounds {
    GeoPoint getSw();

    void setSw(GeoPoint sw);

    GeoPoint getNe();

    void setNe(GeoPoint ne);
}

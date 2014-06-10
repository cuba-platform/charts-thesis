/*
 * Copyright (c) 2008-2014 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.gui.map.model.listeners;

import com.haulmont.charts.gui.map.model.Polygon;

/**
 * @author korotkov
 * @version $Id$
 */
public interface PolygonCompleteListener {

    static class PolygonCompleteEvent {
        private Polygon polygon;

        public PolygonCompleteEvent(Polygon polygon) {
            this.polygon = polygon;
        }

        public Polygon getPolygon() {
            return polygon;
        }
    }

    void onComplete(PolygonCompleteEvent event);
}

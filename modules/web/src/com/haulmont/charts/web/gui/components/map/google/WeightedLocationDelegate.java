/*
 * Copyright (c) 2008-2014 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.web.gui.components.map.google;

import com.haulmont.charts.gui.map.model.GeoPoint;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.WeightedLocation;

import java.util.Objects;

/**
 * @author korotkov
 * @version $Id$
 */
public class WeightedLocationDelegate implements com.haulmont.charts.gui.map.model.WeightedLocation {
    private WeightedLocation location;

    public WeightedLocationDelegate(WeightedLocation location) {
        this.location = location;
    }

    @Override
    public GeoPoint getLocation() {
        return new GeoPointDelegate(location.getLocation());
    }

    @Override
    public void setLocation(GeoPoint location) {
        if (location == null) {
            this.location.setLocation(null);
        } else {
            this.location.setLocation(new LatLon(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    public Double getWeight() {
        return location.getWeight();
    }

    @Override
    public void setWeight(Double weight) {
        location.setWeight(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedLocationDelegate that = (WeightedLocationDelegate) o;

        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }

}

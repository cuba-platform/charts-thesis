/*
 * Copyright (c) 2008-2014 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.web.gui.components.map.google;

import com.haulmont.bali.util.Preconditions;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.InfoWindow;
import com.haulmont.charts.gui.map.model.Marker;
import com.vaadin.tapio.googlemaps.client.overlays.*;

/**
 * @author korotkov
 * @version $Id$
 */
public class InfoWindowDelegate implements InfoWindow {

    protected GoogleMapInfoWindow infoWindow;

    public InfoWindowDelegate() {
        this.infoWindow = new GoogleMapInfoWindow();
    }

    public InfoWindowDelegate(GoogleMapInfoWindow infoWindow) {
        Preconditions.checkNotNullArgument(infoWindow);
        this.infoWindow = infoWindow;
    }

    public GoogleMapInfoWindow getInfoWindow() {
        return infoWindow;
    }

    public void setInfoWindow(GoogleMapInfoWindow infoWindow) {
        this.infoWindow = infoWindow;
    }

    @Override
    public String getContent() {
        return infoWindow.getContent();
    }

    @Override
    public void setContent(String content) {
        infoWindow.setContent(content);
    }

    @Override
    public Integer getMaxWidth() {
        return null;
    }

    @Override
    public void setMaxWidth(Integer maxWidth) {
        infoWindow.setMaxWidth(maxWidth);
    }

    @Override
    public Integer getPixelOffsetWidth() {
        return infoWindow.getPixelOffsetWidth();
    }

    @Override
    public void setPixelOffsetWidth(Integer pixelOffsetWidth) {
        infoWindow.setPixelOffsetWidth(pixelOffsetWidth);
    }

    @Override
    public Integer getPixelOffsetHeight() {
        return infoWindow.getPixelOffsetHeight();
    }

    @Override
    public void setPixelOffsetHeight(Integer pixelOffsetHeight) {
        infoWindow.setPixelOffsetHeight(pixelOffsetHeight);
    }

    @Override
    public Integer getZIndex() {
        return infoWindow.getzIndex();
    }

    @Override
    public void setZIndex(Integer zIndex) {
        infoWindow.setzIndex(zIndex);
    }

    @Override
    public GeoPoint getPosition() {
        return GeoPointDelegate.fromLatLon(infoWindow.getPosition());
    }

    @Override
    public void setPosition(GeoPoint position) {
        infoWindow.setPosition(position != null ? ((GeoPointDelegate)position).getLatLon() : null);
    }

    @Override
    public Marker getAnchorMarker() {
        return new MarkerDelegate(infoWindow.getAnchorMarker());
    }

    @Override
    public void setAnchorMarker(Marker anchorMarker) {
        infoWindow.setAnchorMarker(anchorMarker != null ? ((MarkerDelegate)anchorMarker).getMarker() : null);
    }

    @Override
    public boolean isAutoPanDisabled() {
        return infoWindow.isAutoPanDisabled();
    }

    @Override
    public void setAutoPanDisabled(boolean autoPanDisabled) {
        infoWindow.setAutoPanDisabled(autoPanDisabled);
    }

    @Override
    public String getHeight() {
        return infoWindow.getHeight();
    }

    @Override
    public void setHeight(String height) {
        infoWindow.setHeight(height);
    }

    @Override
    public String getWidth() {
        return infoWindow.getWidth();
    }

    @Override
    public void setWidth(String width) {
        infoWindow.setWidth(width);
    }

    @Override
    public int hashCode() {
        return infoWindow.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InfoWindowDelegate other = (InfoWindowDelegate) obj;
        return infoWindow.equals(other.getInfoWindow());
    }
}

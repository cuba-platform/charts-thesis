/*
 * Copyright (c) 2008-2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.charts.gui.amcharts.model;

import com.haulmont.charts.gui.model.JsonEnum;

/**
 * The default series type for the chart.
 *
 */
public enum ChartType implements JsonEnum {
    SERIAL("serial"),
    PIE("pie"),
    XY("xy"),
    RADAR("radar"),
    FUNNEL("funnel"),
    GAUGE("gauge"),
    GANTT("gantt"),
    STOCK("stock");

    private String id;

    ChartType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
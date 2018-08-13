/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.charts.web.widgets.amcharts;

import com.haulmont.cuba.web.widgets.WebJarResource;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.UI;

@WebJarResource(value = "amcharts:responsive.min.js", overridePath = "amcharts/")
public class CubaAmchartsResponsiveIntegration extends AbstractExtension {

    public static CubaAmchartsResponsiveIntegration get(UI ui) {
        CubaAmchartsResponsiveIntegration optioner = null;

        // Search singleton optioner
        for (Extension extension : ui.getExtensions()) {
            if (extension instanceof CubaAmchartsResponsiveIntegration) {
                optioner = (CubaAmchartsResponsiveIntegration) extension;
                break;
            }
        }

        // Create new optioner if not found
        if (optioner == null) {
            optioner = new CubaAmchartsResponsiveIntegration();
            optioner.extend(ui);
        }

        return optioner;

    }

    public static CubaAmchartsResponsiveIntegration get() {
        UI ui = UI.getCurrent();

        if (ui == null) {
            throw new IllegalStateException(
                    "This method must be used from UI thread");
        }
        return get(ui);
    }
}
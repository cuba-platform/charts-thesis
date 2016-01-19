/*
 * Copyright (c) 2008-2015 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.charts.gui.amcharts.model.data;

import com.haulmont.chile.core.datatypes.impl.EnumClass;
import com.haulmont.chile.core.model.Instance;
import com.haulmont.chile.core.model.utils.InstanceUtils;
import com.haulmont.chile.core.model.utils.MethodsCache;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.UuidProvider;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gorelov
 * @version $Id$
 */
public class SimpleDataItem implements DataItem {

    private static transient Map<Class, MethodsCache> methodCacheMap = new ConcurrentHashMap<>();

    protected Messages messages = AppBeans.get(Messages.NAME);
    protected UUID id;
    protected Object item;

    public SimpleDataItem(Object item) {
        this.id = UuidProvider.createUuid();
        this.item = item;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    @Override
    public Object getValue(String property) {
        Object value;
        try {
            value = getMethodsCache().invokeGetter(item, property);
        } catch (IllegalArgumentException ex) {
            if ("id".equals(property)) {
                value = id;
            } else {
                throw ex;
            }
        }
        if (value instanceof Entity) {
            return InstanceUtils.getInstanceName((Instance) value);
        }
        if (value instanceof EnumClass) {
            return messages.getMessage((Enum) value);
        }
        if (value instanceof Collection) {
            List<DataItem> items = new ArrayList<>();

            for (Object item : (Collection) value) {
                items.add(new SimpleDataItem(item));
            }
            return items;
        }
        return value;
    }

    protected MethodsCache getMethodsCache() {
        Class cls = item.getClass();
        MethodsCache cache = methodCacheMap.get(cls);
        if (cache == null) {
            cache = new MethodsCache(cls);
            methodCacheMap.put(cls, cache);
        }
        return cache;
    }
}
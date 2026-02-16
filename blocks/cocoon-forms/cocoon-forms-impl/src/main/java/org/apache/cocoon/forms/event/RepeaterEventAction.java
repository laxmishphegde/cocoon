/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.forms.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Type-safe enumeration of the various repeater actions that triggers events.
 *
 * @version $Id$
 */
public enum RepeaterEventAction {

    /**
     * This event type is triggered after a row has been added.
     */
    ROW_ADDED("Row added", 0),

    /**
     * This event type is triggered before a row get's removed.
     */
    ROW_DELETING("Row deleting", 1),

    /**
     * This event type is triggered after a row has been removed.
     */
    ROW_DELETED("Row deleted", 2),

    /**
     * This event type is triggered after the order of one or more rows has been changed.
     */
    ROWS_REARRANGED("Rows rearranged", 3),

    /**
     * This event type is triggered before the repeater is cleared (aka before all rows are removed).
     */
    ROWS_CLEARING("Rows clearing", 4),

    /**
     * This event type is triggered after the repeater has been cleared (aka after all rows have been removed)
     */
    ROWS_CLEARED("Rows cleared", 5);

    public static final int ROW_ADDED_VALUE = 0;
    public static final int ROW_DELETING_VALUE = 1;
    public static final int ROW_DELETED_VALUE = 2;
    public static final int ROWS_REARRANGED_VALUE = 3;
    public static final int ROWS_CLEARING_VALUE = 4;
    public static final int ROWS_CLEARED_VALUE = 5;

    private final String name;
    private final int value;

    private RepeaterEventAction(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public static RepeaterEventAction getEnum(String name) {
        for (RepeaterEventAction action : values()) {
            if (action.getName().equals(name)) {
                return action;
            }
        }
        return null;
    }

    public static RepeaterEventAction getEnum(int value) {
        for (RepeaterEventAction action : values()) {
            if (action.getValue() == value) {
                return action;
            }
        }
        return null;
    }

    public static Map<String, RepeaterEventAction> getEnumMap() {
        Map<String, RepeaterEventAction> map = new HashMap<String, RepeaterEventAction>();
        for (RepeaterEventAction action : values()) {
            map.put(action.getName(), action);
        }
        return map;
    }

    public static List<RepeaterEventAction> getEnumList() {
        return Arrays.asList(values());
    }

    public static Iterator<RepeaterEventAction> iterator() {
        return Arrays.asList(values()).iterator();
    }
}

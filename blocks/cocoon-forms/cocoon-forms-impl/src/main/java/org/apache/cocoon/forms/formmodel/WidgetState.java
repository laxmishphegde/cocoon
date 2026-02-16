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
package org.apache.cocoon.forms.formmodel;

/**
 * The state of a widget. States are ordered from the most featured ("active")
 * to the most constrained ("invisible"), so that state combinations can be
 * computed: a widget's combined state is the strictest between the widget's own
 * state and its parent state.
 *
 * @version $Id$
 */
public enum WidgetState {

    /**
     * Active state. This is the default state, where widgets read their values
     * from the request and display them.
     */
    ACTIVE("active", 4),

    /**
     * Disabled state, value is displayed but user input is ignored. The widget should be
     * rendered in a manner that indicates that this widget could be active, but is currently not.
     */
    DISABLED("disabled", 3),

    /**
     * Output state, value is displayed but user input is ignored. The widget should be rendered
     * as plain text, giving no indication that it could be input.
     */
    OUTPUT("output", 2),

    /**
     * Invisible state. Values are not displayed and user input is ignored.
     */
    INVISIBLE("invisible", 1);

    private static final int ACTIVE_VALUE = 4;
    private static final int DISABLED_VALUE = 3;
    private static final int OUTPUT_VALUE = 2;
    private static final int INVISIBLE_VALUE = 1;

    private final String name;
    private final int value;

    private WidgetState(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Get a state given its name. Valid names are "active", "disabled",
     * "output", "invisible".
     *
     * @param name the state name
     * @return the state, or <code>null</code> if <code>name</code> doesn't
     *         denote a known state name
     */
    public static WidgetState stateForName(String name) {
        for (WidgetState state : values()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    /**
     * Determine the strictest of two states. "invisible" is stricter than
     * "disabled" which is stricter than "active"
     * 
     * @param one a state
     * @param two another state
     * @return the strictes of <code>one</code> and <code>two</code>
     */
    public static WidgetState strictest(WidgetState one, WidgetState two) {
        return (one.getValue() < two.getValue()) ? one : two;
    }

    /**
     * Test if the current state is stricter than another one.
     * 
     * @param other a state
     * @return <code>true</code> if <code>this</code> is stricter
     *         than <code>other</code>
     */
    public boolean stricterThan(WidgetState other) {
        return this.getValue() < other.getValue();
    }

    /**
     * Does this state accept user inputs?
     * 
     * @return <code>true</code> if this state accepts user inputs.
     */
    public boolean isAcceptingInputs() {
        return this.getValue() == ACTIVE_VALUE;
    }

    /**
     * Does this state display widget values?
     * 
     * @return <code>true</code> if this state displays widget values.
     */
    public boolean isDisplayingValues() {
        return this.getValue() > INVISIBLE_VALUE;
    }

    /**
     * Does this state validate widget values?
     * 
     * @return <code>true</code> if this state validates widget values.
     */
    public boolean isValidatingValues() {
        return this.getValue() == ACTIVE_VALUE;
    }

// Potential features provided by ValuedEnum that don't seem to be needed now
//
//    public static WidgetState stateForValue(int stateValue) {
//        return (WidgetState) getEnum(WidgetState.class, stateValue);
//    }
//
//    public static Map getEnumMap() {
//        return getEnumMap(WidgetState.class);
//    }
//
//    public static List getStateList() {
//        return getEnumList(WidgetState.class);
//    }
//
//    public static Iterator iterator() {
//        return iterator(WidgetState.class);
//    }

}

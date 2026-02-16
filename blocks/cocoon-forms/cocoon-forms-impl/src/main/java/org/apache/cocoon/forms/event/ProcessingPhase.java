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
 * Type-safe enumeration of the various form processing phases.
 *
 * @version $Id$
 */
public enum ProcessingPhase {

    PROCESSING_INITIALIZE("Processing initialize", 4),
    LOAD_MODEL("Load model", 0),
    READ_FROM_REQUEST("Read from request", 1),
    VALIDATE("Validate", 2),
    SAVE_MODEL("Save model", 3);

    public static final int PROCESSING_INITIALIZE_VALUE = 4;
    public static final int LOAD_MODEL_VALUE = 0;
    public static final int READ_FROM_REQUEST_VALUE = 1;
    public static final int VALIDATE_VALUE = 2;
    public static final int SAVE_MODEL_VALUE = 3;

    private final String name;
    private final int value;

    private ProcessingPhase(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public static ProcessingPhase getEnum(String name) {
        for (ProcessingPhase phase : values()) {
            if (phase.getName().equals(name)) {
                return phase;
            }
        }
        return null;
    }

    public static ProcessingPhase getEnum(int value) {
        for (ProcessingPhase phase : values()) {
            if (phase.getValue() == value) {
                return phase;
            }
        }
        return null;
    }

    public static Map<String, ProcessingPhase> getEnumMap() {
        Map<String, ProcessingPhase> map = new HashMap<String, ProcessingPhase>();
        for (ProcessingPhase phase : values()) {
            map.put(phase.getName(), phase);
        }
        return map;
    }

    public static List<ProcessingPhase> getEnumList() {
        return Arrays.asList(values());
    }

    public static Iterator<ProcessingPhase> iterator() {
        return Arrays.asList(values()).iterator();
    }
}

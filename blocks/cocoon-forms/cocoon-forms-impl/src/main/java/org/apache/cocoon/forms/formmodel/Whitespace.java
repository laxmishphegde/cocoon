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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Enumeration of whitespace-handling strategies.
 */
public enum Whitespace {

    PRESERVE("preserve"),
    TRIM_START("trim-start"),
    TRIM_END("trim-end"),
    TRIM("trim");

    private final String name;

    private Whitespace(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Whitespace getEnum(String name) {
        for (Whitespace ws : values()) {
            if (ws.getName().equals(name)) {
                return ws;
            }
        }
        return null;
    }

    public static Map<String, Whitespace> getEnumMap() {
        Map<String, Whitespace> map = new HashMap<String, Whitespace>();
        for (Whitespace ws : values()) {
            map.put(ws.getName(), ws);
        }
        return map;
    }

    public static List<Whitespace> getEnumList() {
        return Arrays.asList(values());
    }

    public static Iterator<Whitespace> iterator() {
        return Arrays.asList(values()).iterator();
    }
}

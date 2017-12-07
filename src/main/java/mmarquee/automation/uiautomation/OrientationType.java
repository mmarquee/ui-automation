/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.automation.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * The OrientationType.
 *
 * @author Mark Humphreys
 * Date 12/09/2016.
 */
public enum OrientationType {
    /**
     * No orientation.
     */
    None(0),

    /**
     * Horizontal orientation.
     */
    Horizontal(1),

    /**
     * Vertical orientation.
     */
    Vertical(2);

    /**
     * The value.
     */
    private int value;

    /**
     * Gets the value.
     * @return The value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for OrientationType.
     * @param inValue The value.
     */
    OrientationType (final int inValue) {
        this.value = inValue;
    }

    private static final Map<Integer, OrientationType> intToTypeMap = new HashMap<>();

    static {
        for (OrientationType type : OrientationType.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer
     * @param i The given integer
     * @return The value (as an OrientationType)
     */
    public static OrientationType fromInt(int i) {
        OrientationType type = intToTypeMap.get(i);
        if (type == null)
            return OrientationType.None;
        return type;
    }
}
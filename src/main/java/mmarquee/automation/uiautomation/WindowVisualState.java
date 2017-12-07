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
 * Mapping for the Windows Visual state.
 */
public enum WindowVisualState {
    /**
     * Normal state.
     */
    Normal(0),

    /**
     * Maximized state.
     */
    Maximized(1),

    /**
     * Minimized state.
     */
    Minimized(2);

    /**
     * Underlying value.
     */
    private int value;

    /**
     * Gets the state.
     * @return The visual state.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for WindowVisualState.
     * @param value The value of the state.
     */
    WindowVisualState(final int value) {
        this.value = value;
    }

    private static final Map<Integer, WindowVisualState> intToTypeMap = new HashMap<>();
    static {
        for (WindowVisualState type : WindowVisualState.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer
     * @param i The given integer
     * @return The value (as an WindowVisualState)
     */
    public static WindowVisualState fromInt(final int i) {
        WindowVisualState type = intToTypeMap.get(i);
        if (type == null)
            return WindowVisualState.Normal;
        return type;
    }
}

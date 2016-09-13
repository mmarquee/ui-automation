/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
 * Created by Mark Humphreys on 13/07/2016.
 */
public enum ToggleState {
    Off(0),
    On(1),
    Indeterminate(2);

    private int value;

    public int getValue() {
        return this.value;
    }

    ToggleState (int value) {
        this.value = value;
    }

    private static final Map<Integer, ToggleState> intToTypeMap = new HashMap<Integer, ToggleState>();
    static {
        for (ToggleState type : ToggleState.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer
     * @param i The given integer
     * @return The value (as an ToggleState)
     */
    public static ToggleState fromInt(int i) {
        ToggleState type = intToTypeMap.get(Integer.valueOf(i));
        if (type == null)
            return ToggleState.Off;
        return type;
    }
}
/*
 * Copyright 2018 inpwtepydjuf@gmail.com
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
 * Defines values that can be used to customize tree navigation order.
 *
 * @author Mark Humphreys
 * Date 27/12/2017
 */
public enum TreeTraversalOptions {
    /**
     * Pre-order, visit children from first to last.
     */
    Default(0),

    /**
     * Post-order.
     */
    PostOrder(1),

    /**
     * Visit children from last to first.
     */
    LastToFirstOrder(2);

    /**
     * The underlying value.
     */
    private int value;

    /**
     * Gets the value associated with this enumeration.
     * @return The associated value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for TreeTraversalOptions.
     * @param inValue The value of the object.
     */
    TreeTraversalOptions(final int inValue) {
        this.value = inValue;
    }

    private static final Map<Integer, TreeTraversalOptions> IntToTypeMap =
            new HashMap<>();

    static {
        for (TreeTraversalOptions type : TreeTraversalOptions.values()) {
            IntToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     * @param value The given integer.
     * @return The value (as an TreeTraversalOptions).
     */
    public static TreeTraversalOptions fromInt(final int value) {
        TreeTraversalOptions type = IntToTypeMap.get(value);
        if (type == null) {
            return TreeTraversalOptions.Default;
        }

        return type;
    }
}

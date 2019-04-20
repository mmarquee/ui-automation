/*
 * Copyright 2016-18 inpwtepydjuf@gmail.com
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
package mmarquee.uiautomation;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Collections;
import java.util.List;

/**
 * Contains values that specify the scope of various operations in the
 * Microsoft UI Automation tree.
 *
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public class TreeScope extends Structure {
    /**
     * ByReference version of TreeScope.
     */
    public static class ByReference
            extends TreeScope
            implements Structure.ByReference {
    }

    /**
     * The value.
     */
    public int value;

    /**
     * Accessor for the value.
     * @return The value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Default constructor.
     */
    public TreeScope() {
    }

    /**
     * Constructor for integers.
     * @param inValue The value
     */
    public TreeScope(final int inValue) {
        this.value = inValue;
    }

    /**
     * Constructor for pointer value.
     * @param pointer Pointer to value
     */
    public TreeScope(final Pointer pointer) {
        super(pointer);
        this.read();
    }

    /**
     * The scope excludes the subtree from the search.
     */
    public static final int NONE = 0;

    /**
     * The scope includes the element itself.
     */
    public static final int ELEMENT = 1;

    /**
     * The scope includes children of the element.
     */
    public static final int CHILDREN = 2;

    /**
     * The scope includes children and more distant descendants of the element.
     */
    public static final int DESCENDANTS = 4;

    /**
     * The scope includes the parent of the element.
     */
    public static final int PARENT = 8;

    /**
     * The scope includes the element and all its descendants.
     */
    public static final int SUBTREE = 7;

    /**
     * The scope includes the parent and more distant ancestors of the element.
     */
    public static final int ANCESTORS = 16;

    @Override
    protected List<String> getFieldOrder() {
        return Collections.singletonList("value");
    }
}
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
package mmarquee.automation.uiautomation;

import java.util.Collections;
import java.util.List;

import com.sun.jna.*;

/**
 * Contains values that specify the scope of various operations in the Microsoft UI Automation tree.
 *
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public class TreeScope extends Structure {
    public static class ByReference extends TreeScope implements
            Structure.ByReference {
    }

    public int value;

    public  TreeScope() {
    }

    public  TreeScope(int value) {
        this.value = value;
    }

    public  TreeScope(Pointer pointer) {
        super(pointer);
        this.read();
    }

    /**
     * The scope excludes the subtree from the search.
     */
    public static final int None = 0;

    /**
     * The scope includes the element itself.
     */
    public static final int Element = 1;

    /**
     * The scope includes children of the element.
     */
    public static final int Children = 2;

    /**
     * The scope includes children and more distant descendants of the element.
     */
    public static final int Descendants = 4;

    /**
     * The scope includes the parent of the element.
     */
    public static final int Parent = 8;

    /**
     * The scope includes the element and all its descendants.
     */
    public static final int Subtree = 7;

    /**
     * The scope includes the parent and more distant ancestors of the element.
     */
    public static final int Ancestors = 16;

    @Override
    protected List<String> getFieldOrder() {
        return Collections.singletonList("value");
    }
}
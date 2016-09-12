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

import java.util.List;
import java.util.Arrays;

import com.sun.jna.*;

/**
 * uuid({00000000-0000-0000-0000-000000000000})
 * helpstring()
 *
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

    //TreeScope_Element
    public static final int Element = 1;
    //TreeScope_Children
    public static final int Children = 2;
    //TreeScope_Descendants
    public static final int Descendants = 4;
    //TreeScope_Parent
    public static final int Parent = 8;
    //TreeScope_Subtree
    public static final int Subtree = 7;
    //TreeScope_Ancestors
    public static final int Ancestors = 16;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] { "value" });
    }
}
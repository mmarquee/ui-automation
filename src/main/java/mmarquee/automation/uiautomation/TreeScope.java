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
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinNT.*;
import com.sun.jna.platform.win32.OaIdl.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.Variant.VARIANT;
import com.sun.jna.platform.win32.WTypes.*;
import com.sun.jna.platform.win32.COM.*;
import com.sun.jna.platform.win32.Guid.*;

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
    public static final int TreeScope_Element = 1;
    //TreeScope_Children
    public static final int TreeScope_Children = 2;
    //TreeScope_Descendants
    public static final int TreeScope_Descendants = 4;
    //TreeScope_Parent
    public static final int TreeScope_Parent = 8;
    //TreeScope_Ancestors
    public static final int TreeScope_Ancestors = 16;
    //TreeScope_Subtree
    public static final int TreeScope_Subtree = 7;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] { "value" });
    }
}
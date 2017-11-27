/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Author: Radosław Nowak (RNowak4 on github)
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
package mmarquee.automation.structure;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

public class PointNativeLong extends Structure implements Structure.ByValue {
    public static final List<String> FIELDS = createFieldsOrder("x", "y");
    public NativeLong x;
    public NativeLong y;

    public PointNativeLong() {
    }

    public PointNativeLong(Pointer memory) {
        super(memory);
        this.read();
    }

    public PointNativeLong(double x, double y) {
        this.x = new NativeLong((long) x);
        this.y = new NativeLong((long) y);
    }

    protected List<String> getFieldOrder() {
        return FIELDS;
    }

    public class ByReference extends PointNativeLong implements Structure.ByReference {
        public ByReference() {
        }
    }
}

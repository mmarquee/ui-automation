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
package mmarquee.uiautomation;

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;

/**
 * Provides access to a control that presents a range of values.
 *
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationRangeValuePattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "{59213F4F-7346-49E5-B120-80555987A148}");

    /**
     * Sets the value of the control.
     *
     * @param val the value to set
     * @return Success or failure
     */
    int setValue(Double val);

    /**
     * Retrieves the value of the control.
     *
     * @param retVal The value to set
     * @return Success or failure
     */
    int getValue(DoubleByReference retVal);

    /**
     * Indicates whether the value of the element can be changed.
     *
     * @param retVal The return value
     * @return Success or failure
     */
    int getIsReadOnly(IntByReference retVal);
}
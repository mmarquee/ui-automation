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

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.DoubleByReference;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationRangeValuePattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "{59213F4F-7346-49E5-B120-80555987A148}");

    int setValue(Double val);
    int getValue(DoubleByReference retVal);
}
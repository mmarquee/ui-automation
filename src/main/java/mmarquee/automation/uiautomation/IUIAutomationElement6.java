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

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.PointerByReference;

/**
 * Wrapper around the IUIAutomation6 interface.
 *
 * This interface is supported in Windows 10, version 1703 [desktop apps only] upwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement6 extends IUIAutomationElement5 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{4780D450-8BCA-4977-AFA5-A4A517F555E3}");

    /**
     * Gets the current full description of the automation element.
     * @param sr The pointer to the full description
     * @return Error code
     */
    int getCurrentFullDescription(PointerByReference sr);

    /**
     * Gets the cached full description of the automation element.
     * @param sr The pointer to the full description
     * @return Error code
     */
    int getCachedFullDescription(PointerByReference sr);
}
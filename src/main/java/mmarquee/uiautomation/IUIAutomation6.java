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
package mmarquee.uiautomation;

import com.sun.jna.platform.win32.Guid;

/**
 * Wrapper for the IUIAutomation6 COM interface.
 *
 * Extends the IUIAutomation5 interface to expose additional methods for
 * controlling UI Automation functionality.
 *
 * @author Mark Humphreys
 * Date 04/10/2018
 */
public interface IUIAutomation6 extends IUIAutomation5 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{AAE072DA-29E3-413D-87A7-192DBF81ED10}");
}

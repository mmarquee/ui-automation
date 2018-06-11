/*
 * Copyright 2017 inpwtepydjuf@gmail.com
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

/**
 * Wrapper for the IUIAutomation4 COM interface.
 *
 * Extends the IUIAutomation3 interface to expose additional methods for controlling UI Automation functionality.
 *
 * Windows 10, version 1607 [desktop apps only]
 * Windows Server 2016 [desktop apps only]
 */
public interface IUIAutomation4 extends IUIAutomation3 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{1189C02A-05F8-4319-8E21-E817E3DB2860}");
}

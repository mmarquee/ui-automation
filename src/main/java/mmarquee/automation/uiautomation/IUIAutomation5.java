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
 * Wrapper for the IUIAutomation5 COM interface.
 *
 * Extends the IUIAutomation4 interface to expose additional methods for controlling UI Automation functionality.
 *
 * Windows 10, version 1607 [desktop apps only]
 * Windows Server, version 1709 [desktop apps only]
 */
public interface IUIAutomation5 extends IUIAutomation4 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{25F700C8-D816-4057-A9DC-3CBDEE77E256}");
}

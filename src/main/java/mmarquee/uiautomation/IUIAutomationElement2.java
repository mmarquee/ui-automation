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

import com.sun.jna.platform.win32.Guid;

/**
 * Wrapper around the IUIAutomation2 interface.
 *
 * This interface is supported fro Windows 8.1 desktop onwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement2 extends IUIAutomationElement {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{6749C683-F70D-4487-A698-5F79D55290D}");
}
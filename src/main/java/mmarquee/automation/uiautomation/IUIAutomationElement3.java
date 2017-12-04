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

/**
 * Wrapper around the IUIAutomation3 interface, only implementing the extra methods.
 *
 * This interface is supported for Windows 8.1 [desktop apps only] onwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement3 extends IUIAutomationElement2 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{8471DF34-AEE0-4A01-A7DE-7DB9AF12C296}");

    int showContextMenu();

    /*
    function Get_CurrentIsPeripheral(out retVal: Integer): HResult; stdcall;
    function Get_CachedIsPeripheral(out retVal: Integer): HResult; stdcall;
     */
}
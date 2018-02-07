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
import com.sun.jna.ptr.IntByReference;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationWindowPattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{0FAEF453-9208-43EF-BBB2-3B485177864F}");

    int close();
    int waitForInputIdle(Integer milliseconds, IntByReference success);
    int setWindowVisualState(Integer state);
    int getCurrentCanMaximize(IntByReference retVal);
    int getCurrentCanMinimize(IntByReference retVal);
    int getCurrentIsModal(IntByReference retVal);
    int getCurrentIsTopmost(IntByReference retVal);
}


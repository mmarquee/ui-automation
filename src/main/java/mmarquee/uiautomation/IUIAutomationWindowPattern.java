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
import com.sun.jna.ptr.IntByReference;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationWindowPattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{0FAEF453-9208-43EF-BBB2-3B485177864F}");

    /**
     * CLose the window element.
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int close();

    /**
     * Wait for the window to be idle for input.
     * @param milliseconds Timeout
     * @param success Success of operation
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int waitForInputIdle(Integer milliseconds, IntByReference success);

    /**
     * Set the visual state of the window.
     * @param state The state to set
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int setWindowVisualState(Integer state);

    /**
     * Gets the current value of whether the window can be maximized.
     * @param retVal Boolean value
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentCanMaximize(IntByReference retVal);

    /**
     * Gets the current value of whether the window can be minimized.
     * @param retVal Boolean value
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentCanMinimize(IntByReference retVal);

    /**
     * Gets whether the window is modal.
     * @param retVal Boolean value
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentIsModal(IntByReference retVal);

    /**
     * Gets whether the window is topmost window.
     * @param retVal Boolean value
     * @param retVal The topmost flag
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentIsTopmost(IntByReference retVal);
}


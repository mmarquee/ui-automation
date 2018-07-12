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
 * Wrapper around the IUIAutomation3 interface.
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

    /**
     * Programmatically invokes a context menu on the target element.
     * @return Error code
     */
    int showContextMenu();

    /**
     * Retrieves the current peripheral UI indicator for the element.
     *
     * Peripheral UI appears and supports user interaction, but does not take keyboard focus when it appears.
     * Examples of peripheral UI includes popups, flyouts, context menus, or floating notifications.
     *
     * When the IsPeripheral property is TRUE, a client application can't assume that focus was taken by
     * the element even if it's currently keyboard-interactive.
     *
     * Could apply to:
     * <ul>
     * <li>Group
     * <li>Menu
     * <li>Pane
     * <li>ToolBar
     * <li>ToolTip
     * <li>Window
     * <li>Custom
     * </ul>
     *
     * @param retVal The return value
     * @return Error code
     */
    int getCurrentIsPeripheral(Integer retVal);

    /**
     * Retrieves the cached peripheral UI indicator for the element.
     *
     * Peripheral UI appears and supports user interaction, but does not take keyboard focus when it appears.
     * Examples of peripheral UI includes popups, flyouts, context menus, or floating notifications.
     *
     * @param retVal The return value
     * @return Error code
     */
    int getCachedIsPeripheral(Integer retVal);
}
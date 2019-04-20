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
import com.sun.jna.platform.win32.WinDef;

/**
 * Wrapper for the IUIAutomation2 COM interface.
 *
 * Available from Windows 8 / Server 2012.
 *
 * @author Mark Humphreys
 * Date 12/09/2016.
 */
public interface IUIAutomation2 extends IUIAutomation {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}");

    /**
     * Specifies whether calls to UI Automation control pattern methods
     * automatically set focus to the target element.
     *
     * @param AutoSetFocus Value
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getAutoSetFocus(Integer AutoSetFocus);

    /**
     * Specifies whether calls to UI Automation control pattern methods
     * automatically set focus to the target element.
     *
     * @param AutoSetFocus Value to set
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int setAutoSetFocus(Integer AutoSetFocus);

    /**
     * Specifies the length of time that UI Automation will wait for a
     * provider to respond to a client request for an automation element.
     *
     * @param timeout The timeout
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getConnectionTimeout(WinDef.DWORD timeout);

    /**
     * Specifies the length of time that UI Automation will wait for a provider
     * to respond to a client request for an automation element.
     *
     * @param timeout the timeout
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int setConnectionTimeout(WinDef.DWORD timeout);

    /**
     * Specifies the length of time that UI Automation will wait for a
     * provider to respond to a client request for information about an
     * automation element.
     *
     * @param timeout The timeout
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getTransactionTimeout(WinDef.DWORD timeout);

    /**
     * Specifies the length of time that UI Automation will wait for a provider
     * to respond to a client request for information about an automation
     * element.
     *
     * @param timeout The timeout
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int setTransactionTimeout(WinDef.DWORD timeout);
}


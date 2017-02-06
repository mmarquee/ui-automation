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
package mmarquee.automation;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationTreeWalker;

/**
 * Created by Mark Humphreys on 02/02/2017.
 *
 * Wrapper for the AutomationTreeWalker.
 */
public class AutomationTreeWalker {
    IUIAutomationTreeWalker walker = null;

    public AutomationTreeWalker(IUIAutomationTreeWalker walker) {
        this.walker = walker;
    }

    public AutomationElement getNextSiblingElement (IUIAutomationElement3 element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        PointerByReference pElement = new PointerByReference();

        WinNT.HRESULT result1 = element.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pElement);
        if (!COMUtils.SUCCEEDED(result1)) {
            throw new AutomationException();
        }

        this.walker.getNextSiblingElement(pElement.getValue(), pChild);

        IUIAutomationElement3 childElement =
                IUIAutomationElement3.Converter.PointerToInterface(pChild);
        return new AutomationElement(childElement);
    }

    public AutomationElement getFirstChildElement(IUIAutomationElement3 element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        PointerByReference pElement = new PointerByReference();

        WinNT.HRESULT result1 = element.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pElement);
        if (!COMUtils.SUCCEEDED(result1)) {
            throw new AutomationException();
        }

        this.walker.getFirstChildElement(pElement.getValue(), pChild);

        IUIAutomationElement3 childElement =
                IUIAutomationElement3.Converter.PointerToInterface(pChild);
        return new AutomationElement(childElement);
    }
}

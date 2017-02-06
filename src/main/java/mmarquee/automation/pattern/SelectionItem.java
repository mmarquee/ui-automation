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
package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the SelectionItem pattern.
 */
public class SelectionItem extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public SelectionItem() {
        this.IID = IUIAutomationSelectionItemPattern.IID;
    }

    private IUIAutomationSelectionItemPattern rawPattern;

    /**
     * Constructor for the value pattern
     * @param rawPattern The raw pattern
     */
    public SelectionItem(IUIAutomationSelectionItemPattern rawPattern) {
        this.IID = IUIAutomationSelectionItemPattern.IID;

        this.rawPattern = rawPattern;
    }

    private IUIAutomationSelectionItemPattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return convertPointerToInterface(pbr);
            } else {
                throw new AutomationException();
            }
        }
    }

    /**
     * Selects the given item
     * @throws AutomationException Something has gone wrong
     */
    public void select() throws AutomationException {
        this.getPattern().select();
    }

    /**
     * Is the control selected
     * @return True if selected
     * @throws AutomationException Something has gone wrong
     */
    public boolean isSelected() throws AutomationException {
        IntByReference ibr = new IntByReference();
        if (this.getPattern().getCurrentIsSelected(ibr) != 0) {
            throw new AutomationException();
        }

        return (ibr.getValue() == 1);
    }

    /**
     * Adds the item to the current selection
     * @throws AutomationException Something went wrong in the automation library
     */
    public void addToSelection() throws AutomationException {
        this.getPattern().addToSelection();
    }

    /**
     * Gets the selection container
     * @return The selection container
     * @throws AutomationException Something has gone wrong in automation
     */
    public AutomationElement getSelectionContainer() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().getCurrentSelectionContainer(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            return new AutomationElement(convertPointerToElementInterface(pUnknownA));
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Removes the item from the current selection
     * @throws AutomationException Something went wrong in the automation library
     */
    public void removeFromSelection() throws AutomationException {
        this.getPattern().removeFromSelection();
    }

    public IUIAutomationSelectionItemPattern convertPointerToInterface(PointerByReference ref) {
        return IUIAutomationSelectionItemPattern.Converter.PointerToInterface(ref);
    }
}

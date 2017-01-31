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
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationSelectionPattern;
import mmarquee.automation.uiautomation.IUIAutomationTablePattern;

import java.util.List;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the Selection pattern.
 */
public class Selection extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Selection() {
        this.IID = IUIAutomationSelectionPattern.IID;
    }

    private IUIAutomationSelectionPattern rawPattern;

    /**
     * Constructor for the value pattern
     * @param rawPattern The raw pattern to use
     */
    public Selection(IUIAutomationSelectionPattern rawPattern) {
        this.IID = IUIAutomationSelectionPattern.IID;
        this.rawPattern = rawPattern;
    }

    /**
     * Gets the pointer
     * @return Underlying pointer
     * @throws AutomationException Automation has gone wrong
     */
    private IUIAutomationSelectionPattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return this.convertPointerToInterface(pbr);
            } else {
                throw new AutomationException();
            }
        }
    }

    /**
     * Gets the current selection
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentSelection() throws AutomationException {

        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().getCurrentSelection(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationElementArray collection = convertPointerToElementArray(pUnknownA);
            return this.collectionToList(collection);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Convert the unknown pointer to selection pattern
     *
     * @param pUnknown The unknown pointer
     * @return IUIAutomationSelectionPattern the converted pointer
     */
    public IUIAutomationSelectionPattern convertPointerToInterface(PointerByReference pUnknown) {
        return IUIAutomationSelectionPattern.Converter.PointerToInterface(pUnknown);
    }

    /**
     * Convert the unknown pointer to an array.
     *
     * @param pUnknown The unknown pointer
     * @return IUIAutomationSelectionPattern the converted pointer
     */
    public IUIAutomationElementArray convertPointerToElementArray(PointerByReference pUnknown) {
        return IUIAutomationElementArray.Converter.PointerToInterface(pUnknown);
    }

    /**
     * Gets the current selection
     * @return List of selected items
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getSelection() throws AutomationException {

        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().getCurrentSelection(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationElementArray collection =
                    this.convertPointerToElementArrayInterface(pUnknownA);

            return this.collectionToList(collection);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Returns whether the selection supports multiple selection
     * @return Value from automation
     * @throws AutomationException Something has gone wrong
     */
    public boolean canSelectMultiple() throws AutomationException {
        IntByReference ibr = new IntByReference();

        if (this.getPattern().getCurrentCanSelectMultiple(ibr) != 0) {
            throw new AutomationException();
        }

        return (ibr.getValue() == 1);
    }
}

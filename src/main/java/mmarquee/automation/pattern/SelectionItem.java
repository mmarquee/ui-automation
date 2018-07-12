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
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPatternConverter;

/**
 * Wrapper for the SelectionItem pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class SelectionItem extends BasePattern {

    /**
     * Constructor for the value pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public SelectionItem(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationSelectionItemPattern.IID;
        this.patternID = PatternID.SelectionItem;
        this.availabilityPropertyID = PropertyID.IsSelectionItemPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationSelectionItemPattern rawPattern;

    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Error in automation library
     */
    private IUIAutomationSelectionItemPattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Selects the given item.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void select() throws AutomationException {
        this.getPattern().select();
    }

    /**
     * Is the control selected.
     *
     * @return True if selected
     * @throws AutomationException Something has gone wrong
     */
    public boolean isSelected() throws AutomationException {
        IntByReference ibr = new IntByReference();
        final int res = this.getPattern().getCurrentIsSelected(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue() == 1);
    }

    /**
     * Adds the item to the current selection.
     *
     * @throws AutomationException Something went wrong in the automation library
     */
    public void addToSelection() throws AutomationException {
        this.getPattern().addToSelection();
    }

    /**
     * Gets the selection container.
     *
     * @return The selection container
     * @throws AutomationException Something has gone wrong in automation
     */
    public AutomationElement getSelectionContainer() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getCurrentSelectionContainer(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            return new AutomationElement(convertPointerToElementInterface(pUnknownA));
        } else {
            throw new AutomationException(resultA.intValue());
        }
    }

    /**
     * Removes the item from the current selection.
     *
     * @throws AutomationException Something went wrong in the automation library
     */
    public void removeFromSelection() throws AutomationException {
        this.getPattern().removeFromSelection();
    }

    /**
     * Converts the pointer.
     *
     * @param ref The raw pointer
     * @return The pattern
     */
    IUIAutomationSelectionItemPattern convertPointerToInterface(PointerByReference ref) {
        return IUIAutomationSelectionItemPatternConverter.pointerToInterface(ref);
    }
}

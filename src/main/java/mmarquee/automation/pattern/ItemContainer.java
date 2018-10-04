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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPatternConverter;

/**
 * Wrapper for the item container.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 *
 */
public class ItemContainer extends BasePattern {

    /**
     * Constructor for the ItemContainer pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     * @throws AutomationException API issue
     */
    public ItemContainer(final AutomationElement element) throws AutomationException {
        super(element);
        this.patternID = PatternID.ItemContainer;
        this.availabilityPropertyID = PropertyID.IsItemContainerPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    private IUIAutomationItemContainerPattern rawPattern;

    /**
     * Gets the pattern
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationItemContainerPattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return this.convertPointerToInterface(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }

    /**
     * Converts a pointer to the appropriate interface.
     * @param unknown The pointer
     * @return The converted interface
     */
    public IUIAutomationItemContainerPattern convertPointerToInterface(PointerByReference unknown) {
        return IUIAutomationItemContainerPatternConverter.pointerToInterface(unknown);
    }

    /**
     * Finds the first element that matches the property.
     * @param startAfter Element to start after
     * @param propertyId The property
     * @param value The value of the property
     * @return The found element.
     */
    public AutomationElement findItemByProperty(final Pointer startAfter,
                                                final int propertyId,
                                                final Variant.VARIANT.ByValue value)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().findItemByProperty(startAfter, propertyId, value, pbr);

        if (res == 0) {
            IUIAutomationElement element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }
}

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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.BaseAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationElementArrayConverter;
import mmarquee.automation.uiautomation.IUIAutomationElementConverter;

/**
 * Base for the pattern wrappers.
 *
 * @author Mark Humphreys
 * Date 29/02/2016.
 */
public abstract class BasePattern extends BaseAutomation implements Pattern {

    /**
     * The guid of the pattern.
     */
    protected Guid.IID IID;

    /**
     * The underlying automation pattern.
     */
    protected Pointer pattern;

    /**
     * Constructs a Pattern.
     */
    public BasePattern() {
        this.pattern = null;
    }

    /**
     * Sets the pattern.
     * @param pattern The pattern to set
     */
    public void setPattern(Pointer pattern) {
        this.pattern =  pattern;
    }

    /**
     * Is this pattern available.
     * @return True if available.
     */
    public boolean isAvailable() {
        return (pattern == null);
    }

    /**
     * Gets the raw pointer to the pattern.
     * @param pbr The raw pointer
     * @return Result of the call from the COM library
     */
    public WinNT.HRESULT getRawPatternPointer(
            final PointerByReference pbr) {
        Unknown uElement = makeUnknown(this.pattern);
        return uElement.QueryInterface(new Guid.REFIID(this.IID), pbr);
    }

    /**
     * Converts the unknown value to a IUIAutomationElement.
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElement convertPointerToElementInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationElementConverter.pointerToInterface(pUnknownA);
    }

    /**
     * Converts the unknown value to a IUIAutomationElementArray.
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElementArray convertPointerToElementArrayInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationElementArrayConverter.pointerToInterface(pUnknownA);
    }
}

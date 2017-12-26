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
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.patterns.IUIAutomationGridItemPattern;
import mmarquee.automation.uiautomation.patterns.IUIAutomationGridItemPatternConverter;

/**
 * @author Mark Humphreys
 * Date 27/01/2017.
 *
 * Wrapper for the GridItem pattern
 */
public class GridItem extends BasePattern {
    /**
     * Constructor for the pattern
     */
    public GridItem() {
        this.IID = IUIAutomationGridItemPattern.IID;
    }

    /**
     * The raw pattern.
     */
    private IUIAutomationGridItemPattern rawPattern;

    /**
     * Constructor for the pattern
     * @param rawPattern The raw pattern
     */
    public GridItem(IUIAutomationGridItemPattern rawPattern) {
        this.IID = IUIAutomationGridItemPattern.IID;
        this.rawPattern = rawPattern;
    }

    private IUIAutomationGridItemPattern getPattern() throws AutomationException {
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
     * Converts the pointer to the interface.
     * @param unknown The pointer
     * @return The converted interface
     */
    public IUIAutomationGridItemPattern convertPointerToInterface(PointerByReference unknown) {
        return IUIAutomationGridItemPatternConverter.PointerToInterface(unknown);
    }

    /**
     * Gets the current column for the cell
     * @return The column
     * @throws AutomationException Automation returned an error
     */
    public int getColumn() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentColumn(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the current row for the cell
     * @return The row
     * @throws AutomationException Automation returned an error
     */
    public int getRow() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentRow(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

}

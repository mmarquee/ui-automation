/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationGridPattern;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the Grid pattern
 */
public class Grid extends BasePattern {

    private IUIAutomationGridPattern getPattern() {
        Unknown uElement = new Unknown(this.pattern);

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationGridPattern.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationGridPattern.Converter.PointerToIUIAutomationGridPattern(pbr);
        } else {
            return null; // or throw exception?
        }
    }

    /**
     * Get the item associated with the given cell
     * @param x Cell X position
     * @param y Cell Y position
     * @return The item associated with the cell
     */
    public Pointer getItem(int x, int y) {
        PointerByReference pbr = new PointerByReference();

        this.getPattern().GetItem(x, y, pbr);

        return pbr.getValue();
    }

    /**
     * Gets the row count
     * @return The tow count
     */
    public int rowCount() {
        IntByReference ibr = new IntByReference();

        this.getPattern().Get_CurrentRowCount(ibr);

        return ibr.getValue();
    }

    /**
     * Gets the coloumn count
     * @return The column count
     */
    public int columnCount() {

        IntByReference ibr = new IntByReference();

        this.getPattern().Get_CurrentColumnCount(ibr);

        return ibr.getValue();
    }
}

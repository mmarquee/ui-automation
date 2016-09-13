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

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationTablePattern;
import mmarquee.automation.uiautomation.RowOrColumnMajor;

import java.util.List;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the table pattern
 */
public class Table extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Table() {
        this.IID = IUIAutomationTablePattern.IID;
    }

    private IUIAutomationTablePattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationTablePattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the column headers for the grid.
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentColumnHeaders() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().GetCurrentColumnHeaders(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = new Unknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationElementArray.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationElementArray collection =
                    IUIAutomationElementArray.Converter.PointerToInterface(pUnknownA);

            return this.collectionToList(collection);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the row or column major for the table
     * @return RowOrColumnMajor value of the table element
     * @throws AutomationException Error in automation library
     */
    public RowOrColumnMajor getRowOrColumnMajor() throws AutomationException {
        IntByReference ibr = new IntByReference();

        if (this.getPattern().Get_CurrentRowOrColumnMajor(ibr) != 0) {
            throw new AutomationException();
        }

        return RowOrColumnMajor.fromInt(ibr.getValue());
    }

    /**
     * Gets the row headers for the grid.
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentRowHeaders() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().GetCurrentRowHeaders(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = new Unknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationElementArray.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationElementArray collection =
                    IUIAutomationElementArray.Converter.PointerToInterface(pUnknownA);

            return this.collectionToList(collection);
        } else {
            throw new AutomationException();
        }
    }
}

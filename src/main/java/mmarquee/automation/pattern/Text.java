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
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.*;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the text pattern
 */
public class Text extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Text() {
        this.IID = IUIAutomationTextPattern.IID;
    }

    private IUIAutomationTextPattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationTextPattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the selection.
     *
     * @return String of the selection
     * @throws AutomationException Something has gone wrong
     */
    public String getSelection() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().GetSelection(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = new Unknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationTextRangeArray.IID);

        String selectionResult = "";

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationTextRangeArray selection =
                    IUIAutomationTextRangeArray.Converter.PointerToInterface(pUnknownA);

            // OK, now what?
            IntByReference ibr = new IntByReference();
            if (selection.Get_Length(ibr) != 0) {
                throw new AutomationException();
            }

            int count = ibr.getValue();

            for (int i = 0; i < count; i++) {
                PointerByReference pbr0 = new PointerByReference();

                if (selection.GetElement(i, pbr0) != 0) {
                    throw new AutomationException();
                }

                Unknown unknown = new Unknown(pbr0.getValue());
                PointerByReference pUnknown = new PointerByReference();

                Guid.REFIID refiid = new Guid.REFIID(IUIAutomationTextRange.IID);

                WinNT.HRESULT result = unknown.QueryInterface(refiid, pUnknown);
                if (COMUtils.SUCCEEDED(result)) {
                    IUIAutomationTextRange range =
                            IUIAutomationTextRange.Converter.PointerToInterface(pUnknown);

                    PointerByReference sr = new PointerByReference();

                    if (range.GetText(-1, sr) != 0) {
                        throw new AutomationException();
                    }

                    selectionResult = sr.getValue().getWideString(0);
                } else {
                    throw new AutomationException();
                }
            }
        }

        return selectionResult;
    }

    /**
     * Gets the text from the pattern.
     * @return The text.
     * @throws AutomationException Something has gone wrong
     */
    public String getText() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.getPattern().Get_DocumentRange(pbr) != 0) {
            throw new AutomationException();
        }

        Unknown unkConditionA = new Unknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationTextRange.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationTextRange range =
                    IUIAutomationTextRange.Converter.PointerToInterface(pUnknownA);

            PointerByReference sr = new PointerByReference();

            if (range.GetText(-1, sr) != 0) {
                throw new AutomationException();
            }

            return sr.getValue().getWideString(0);
        } else {
            throw new AutomationException();
        }
    }
}

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

    private IUIAutomationTextPattern rawPattern;

    public Text(IUIAutomationTextPattern rawPattern) {
        this.IID = IUIAutomationTextPattern.IID;
        this.rawPattern = rawPattern;
    }

    private IUIAutomationTextPattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return IUIAutomationTextPattern.Converter.PointerToInterface(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }

    public IUIAutomationTextRangeArray convertPointerToArrayInterface(PointerByReference pUnknownA) {
        return IUIAutomationTextRangeArray.Converter.PointerToInterface(pUnknownA);
    }

    /**
     * Gets the selection.
     *
     * @return String of the selection
     * @throws AutomationException Something has gone wrong
     */
    public String getSelection() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getSelection(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        String selectionResult = "";

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTextRangeArray.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationTextRangeArray selection =
                    convertPointerToArrayInterface(pUnknownA);

            // OK, now what?
            IntByReference ibr = new IntByReference();
            final int res1 = selection.getLength(ibr);
            if (res1 != 0) {
                throw new AutomationException(res1);
            }

            int count = ibr.getValue();

            for (int i = 0; i < count; i++) {
                PointerByReference pbr0 = new PointerByReference();

                final int res2 = selection.getElement(i, pbr0);
                if (res2 != 0) {
                    throw new AutomationException(res2);
                }

                Unknown unknown = makeUnknown(pbr0.getValue());
                PointerByReference pUnknown = new PointerByReference();

                WinNT.HRESULT result = unknown.QueryInterface(new Guid.REFIID(IUIAutomationTextRange.IID), pUnknown);
                if (COMUtils.SUCCEEDED(result)) {
                    IUIAutomationTextRange range =
                            convertPointerToInterface(pUnknown);

                    PointerByReference sr = new PointerByReference();

                    final int res3 = range.getText(-1, sr);
                    if (res3 != 0) {
                        throw new AutomationException(res3);
                    }

                    selectionResult = sr.getValue().getWideString(0);
                } else {
                    throw new AutomationException(result.intValue());
                }
            }
        } else {
            throw new AutomationException(resultA.intValue());
        }

        return selectionResult;
    }

    /**
     * Gets the text from the pattern.
     *
     * @return The text.
     * @throws AutomationException Something has gone wrong
     */
    public String getText() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getDocumentRange(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTextRange.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationTextRange range = convertPointerToInterface(pUnknownA);

            PointerByReference sr = new PointerByReference();

            final int res1 = range.getText(-1, sr);
            if (res1 != 0) {
                throw new AutomationException(res1);
            }

            return sr.getValue().getWideString(0);
        } else {
            throw new AutomationException(resultA.intValue());
        }
    }

    public IUIAutomationTextRange convertPointerToInterface(PointerByReference pUnknownA) {
        return IUIAutomationTextRange.Converter.PointerToInterface(pUnknownA);
    }
}

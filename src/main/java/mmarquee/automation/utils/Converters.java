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
package mmarquee.automation.utils;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 07/02/2017.
 *
 * Methods to convert references into the various automation elements
 */
public class Converters {

    /**
     * Convert a raw PointerByReference to a IUIAutomationElement3
     * @param pbr The raw pointer
     * @return The IUIAutomationElement3
     * @throws AutomationException Automation library has thrown an error.
     */
    public static IUIAutomationElement3 getAutomationElementFromReference(PointerByReference pbr)
            throws AutomationException {
        Unknown uElement = new Unknown(pbr.getValue());

        WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException();
        }

        return IUIAutomationElement3.Converter.PointerToInterface(pbr);
    }

    /**
     * Turns a collection (array) of automation elements, into a collection.
     *
     * @param collection The ElementArray.
     * @return The List
     * @throws AutomationException Error in the automation library
     */
    public static List<AutomationElement> collectionToList(IUIAutomationElementArray collection) throws AutomationException {

        IntByReference ibr = new IntByReference();

        if (collection.getLength(ibr) != 0) {
            throw new AutomationException();
        }

        List<AutomationElement> list = new ArrayList<AutomationElement>();

        for (int count = 0; count < ibr.getValue(); count++) {

            PointerByReference pbr = new PointerByReference();

            if (collection.getElement(count, pbr) != 0) {
                throw new AutomationException();
            }

            Unknown uElement = new Unknown(pbr.getValue());

            WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                IUIAutomationElement3 element =
                        IUIAutomationElement3.Converter.PointerToInterface(pbr);

                list.add(new AutomationElement(element));
            }
        }

        return list;
    }
}

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
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the itemcontainer pattern
 */
public class ItemContainer extends BasePattern {

    private IUIAutomationItemContainerPattern getPattern() {
        Unknown uElement = new Unknown(this.pattern);

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationItemContainerPattern.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationItemContainerPattern.Converter.PointerToInterface(pbr);
        } else {
            return null; // or throw exception?
        }
    }

    /**
     * Finds an item by property
     * @param pStartAfter Where to start in the tree of elements
     * @param propertyId The property id to find
     * @param value The value of the property
     * @return The item found
     */
    public Pointer findItemByProperty (Pointer pStartAfter, int propertyId, Variant.VARIANT.ByValue value) {

        PointerByReference pbr = new PointerByReference();

        int result = this.getPattern().FindItemByProperty(pStartAfter, propertyId, value, pbr);

        return pbr.getValue();
    }
}

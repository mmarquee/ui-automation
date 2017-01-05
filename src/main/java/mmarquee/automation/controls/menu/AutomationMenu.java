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

package mmarquee.automation.controls.menu;

import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.PropertyID;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 09/02/2016.
 *
 * * Wrapper for the Menu control element.
 */
public class AutomationMenu extends AutomationBase {
    protected Logger logger = Logger.getLogger(AutomationMenu.class.getName());

    /**
     * Construct the AutomationMenu
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationMenu(AutomationElement element)
            throws AutomationException {
        super(element);
    }

    public static ControlType controlType = ControlType.Menu;

    /**
     * Gets the item associated with the index
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (int index) throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll();

        return new AutomationMenuItem(items.get(index));
    }

    /**
     * Gets the item associated with the name
     * @param name The name to look for
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (String name) throws PatternNotFoundException, AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(name);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        AutomationElement item = null;

        try {
            PointerByReference pCondition = this.automation.createPropertyCondition(PropertyID.Name.getValue(), variant);

            item = this.findFirst(
                    new TreeScope(TreeScope.Children),
                    pCondition);
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }

        return new AutomationMenuItem(item);
    }
}

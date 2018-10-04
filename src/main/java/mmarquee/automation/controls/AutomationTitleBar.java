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
package mmarquee.automation.controls;

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Wrapper for the TitleBar element.
 *
 * @author Mark Humphreys
 * Date 04/03/2016.
 *
 */
public final class AutomationTitleBar extends AutomationContainer {
    /**
     * Constructor for the AutomationTitleBar.
     * @param builder The builder
     */
    public AutomationTitleBar(final ElementBuilder builder){
        super(builder);
    }

    /**
     * Gets the menu bar for this title-bar.
     * @return The Main menu
     * @throws AutomationException Automation library error
     */
    public AutomationMainMenu getMenuBar() throws AutomationException {
        PointerByReference condition = this.getAutomation()
                .createControlTypeCondition(ControlType.MenuBar);

        AutomationElement element =
                this.getElement().findFirst(
                        new TreeScope(TreeScope.Descendants),
                        condition);

        return new AutomationMainMenu(new ElementBuilder(element).parent(this.getElement()));
    }
}

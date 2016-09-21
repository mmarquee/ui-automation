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
package mmarquee.automation.controls;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.controls.mouse.AutomationMouse;

/**
 * Created by Mark Humphreys on 20/07/2016.
 *
 * For some reason the invoke pattern doesn't work for these buttons, even via Object Inspector - no error, just doesn't work, so have to manufacture the click.
 */
public class AutomationToolBarButton extends AutomationBase implements Clickable {

    /**
     * Constructor for the AutomationToolBarButton
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     */
    public AutomationToolBarButton(AutomationElement element)
            throws AutomationException {
        super (element);
        controlType = ControlType.Button;
    }

    /**
     * <p>
     * Invokes the click event for this control
     * </p>
     *
     * Actual manufactures the click, as the toolbar buttons do not seem behave properly
     * @throws AutomationException Automation library error
     */
    public void click() throws AutomationException {
        WinDef.POINT point = this.element.getClickablePoint();

        AutomationMouse mouse = AutomationMouse.getInstance();
        mouse.setLocation(point.x, point.y);
        mouse.leftClick();
    }

    /**
     * <p>
     * Sets the focus to this control
     * </p>
     */
    public void focus() {
        this.element.setFocus();
    }
}

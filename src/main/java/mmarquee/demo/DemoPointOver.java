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

package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;

import java.awt.*;

/**
 * Example for the PointOver functionality.
 *
 * @author Mark Humphreys
 * Date 12/02/2017.
 */
public class DemoPointOver extends TestBase {
    /**
     * Run the thing.
     */
    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        do {
            this.rest();

            Point p = MouseInfo.getPointerInfo().getLocation();

            logger.info(p.getX() + " - " + p.getY());

            WinDef.POINT point = new WinDef.POINT();
            point.x = (int) p.getX();
            point.y = (int) p.getY();

            try {
                AutomationElement elementUnder =
                        automation.getElementFromPoint(point);

                logger.info("From point = " + elementUnder.getName());

                AutomationElement elementFocus = automation.getFocusedElement();
                logger.info("From focus = " + elementFocus.getName());

            } catch (Exception ex) {
                logger.info(ex.getStackTrace());
            }
        } while (true);
    }
}

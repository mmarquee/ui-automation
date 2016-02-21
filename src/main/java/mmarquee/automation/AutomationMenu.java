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

package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

import static java.lang.Thread.sleep;

/**
 * Created by inpwt on 09/02/2016.
 */
public class AutomationMenu extends AutomationBase {
//    IUIAutomationExpandCollapsePattern expandCollpasePattern;

    public AutomationMenu(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

  //      this.expandCollpasePattern = this.getExpandCollapsePattern();
    }

    public AutomationMenuItem getMenuItem(String name) {

//        this.click();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

//        expandCollpasePattern.expand();

  //      try {
    //        this.element.wait(750);
      //  } catch (Exception ex) {
        //    // Not sure about this yet
      //  }


        IUIAutomationElementArray collection =
                this.element.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        boolean found = false;
        IUIAutomationElement foundElement = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement elem = collection.getElement(count);
            String eName = elem.currentName();

            if (eName.equals(name)) {
                found = true;
                foundElement = elem;
                break;
            }
        }

//        expandCollpasePattern.collapse();

        if (found) {
            return new AutomationMenuItem(foundElement, uiAuto);
        } else {
            return null;
        }
    }
}

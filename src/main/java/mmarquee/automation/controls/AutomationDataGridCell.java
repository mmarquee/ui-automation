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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 04/02/2016.
 *
 * Wrapper around the 'virtual' cell element in the automated Delphi string grid
 */
public class AutomationDataGridCell extends AutomationBase {

    /**
     * Construct the AutomationDataGridCell
     * @param element The element
     * @param automation The automation library
     */
    public AutomationDataGridCell(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

     //   try {
     //       this.valuePattern = this.getValuePattern();
     //   } catch (PatternNotFoundException ex) {
     //       logger.info("Value pattern not found");
     //   }
    }

    /**
     * Gets the text associated with this element
     * @return The current value
     */
 //   public String value() {
 //       return valuePattern.value();
 //   }

 //   public boolean isReadOnly() {
 //       int value = valuePattern.isReadOnly();
 //       return (value == 1);
 //   }

    /**
     * Sets the value
     * @param value The value to set
     */
 //   public void setValue(String value) {
 //       this.valuePattern.setValue(value);
 //   }
}

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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * @author Mark Humphreys
 * Date 28/01/2016.
 *
 * Wrapper for the TabItem element.
 */
public class AutomationTabItem extends AutomationContainer {

    private SelectionItem selectItemPattern;

//    /**
//     * Construct the AutomationTabItem.
//     *
//     * @param element The element.
//     * @throws AutomationException      Automation library error.
//     * @throws PatternNotFoundException Expected pattern not found.
//     */
//    public AutomationTabItem(final AutomationElement element)
//            throws PatternNotFoundException, AutomationException {
//        super(element);
//        //selectItemPattern = this.getSelectItemPattern();
//    }

//    /**
//     * Construct the AutomationTabItem.
//     *
//     * @param element The element.
//     * @param pattern The SelectionItem pattern.
//     * @throws AutomationException      Automation library error.
//     * @throws PatternNotFoundException Expected pattern not found.
//     */
//    public AutomationTabItem(final AutomationElement element,
//                             final SelectionItem pattern)
//            throws PatternNotFoundException, AutomationException {
//        super(element);
//        this.selectItemPattern = pattern;
//    }

//    /**
//     * Construct the AutomationTabItem.
//     * @param element The element.
//     * @param automation The automation instance
//     * @throws AutomationException Automation library error.
//     * @throws PatternNotFoundException Expected pattern not found.
//     */
    //   public AutomationTabItem(final AutomationElement element,
//                             final UIAutomation automation)
//            throws PatternNotFoundException, AutomationException {
//        super(element, automation);
//       // this.selectItemPattern = pattern;
//    }

    /**
     * Construct the AutomationTabItem.
     *
     * @param builder The builder.
     * @throws AutomationException      Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationTabItem(final ElementBuilder builder)
            throws PatternNotFoundException, AutomationException {
        super(builder);
        this.selectItemPattern = builder.getSelectItem();
    }

    /**
     * Selects this item.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public void selectItem()
            throws AutomationException, PatternNotFoundException  {
        if (this.selectItemPattern == null) {
            selectItemPattern = this.getSelectItemPattern();
        }

        this.selectItemPattern.select();
    }
}

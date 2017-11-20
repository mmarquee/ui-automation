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

import java.util.regex.Pattern;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper for the Toolbar element.
 * IDockProvider, IExpandCollapse,
 *
 * @author Mark Humphreys
 * Date 02/03/2016
 */
public class AutomationToolBar extends AutomationContainer {
//    /**
//     * Constructor for the AutomationToolBar.
//     *
//     * @param element The underlying automation element.
//     * @throws AutomationException Automation library error.
//     * @throws PatternNotFoundException Could not find pattern.
//     */
//    public AutomationToolBar(final AutomationElement element)
//            throws AutomationException, PatternNotFoundException {
 //       super(element);
//    }

    /**
     * Constructor for the AutomationToolBar.
     *
     * @param builder The builder
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Could not find pattern.
     */
    public AutomationToolBar(final ElementBuilder builder)
            throws AutomationException, PatternNotFoundException {
        super(builder);
    }

//    /**
//     * Constructor for the AutomationToolBar.
//     *
//     * @param element The underlying automation element.
//     * @param container The ItemContainer pattern.
//     * @param instance The automation instance.
//     * @throws AutomationException Automation library error.
//     * @throws PatternNotFoundException Could not find pattern.
//     */
//    public AutomationToolBar(final AutomationElement element,
//                             final ItemContainer container,
//                             final UIAutomation instance)
//            throws AutomationException, PatternNotFoundException {
//        super(element, container, instance);
//    }

    /**
     * Gets the button control associated with the given index.
     * @param index The index of the button.
     * @return The AutomationButton.
     * @throws AutomationException Something has gone wrong.
     */
    private AutomationToolBarButton getToolbarButton(final int index)
            throws AutomationException {
        return new AutomationToolBarButton(this.getElementByControlType(index, ControlType.Button));
    }

    /**
     * Gets the button control associated with the given name.
     * @param name The name of the button.
     * @return The AutomationButton.
     * @throws AutomationException Something has gone wrong.
     */
    private AutomationToolBarButton getToolbarButton(final String name)
            throws AutomationException {
        return new AutomationToolBarButton(this.getElementByControlType(name, ControlType.Button));
    }

    /**
     * Gets the button control matching the given name.
     * @param namePattern The namePattern of the button.
     * @return The AutomationButton.
     * @throws AutomationException Something has gone wrong.
     */
    private AutomationToolBarButton getToolbarButton(final Pattern namePattern)
            throws AutomationException {
        return new AutomationToolBarButton(this.getElementByControlType(namePattern, ControlType.Button));
    }

    /**
     * Gets the button control associated with the given automationId.
     * @param automationId The index of the button.
     * @return The AutomationButton.
     * @throws AutomationException Something has gone wrong.
     */
    private AutomationToolBarButton getToolbarButtonByAutomationId(final String automationId)
            throws AutomationException {
        return new AutomationToolBarButton(this.getElementByAutomationId(automationId));
    }

    /**
     * Get the toolbar button, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationToolBarButton getToolbarButton(final Search search) throws PatternNotFoundException, AutomationException {
        if (search.getHasPattern()) {
            return getToolbarButton(search.getPattern());
        } else if (search.getHasAutomationId()) {
            return getToolbarButtonByAutomationId(search.getAutomationId());
        } else if (search.getHasId()) {
            return getToolbarButton(search.getId());
        } else if (search.getHasName()) {
            return getToolbarButton(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }
}

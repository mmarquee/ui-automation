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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import mmarquee.automation.Element;
import org.junit.Test;
import org.mockito.Mockito;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.uiautomation.IUIAutomation;

/**
 * @author Mark Humphreys
 * Date 01/12/2016.
 */
public class AutomationToolbarTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_Returns_Name_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        ToolBar ctrl = new ToolBar(
                new ElementBuilder(element).automation(instance).addPattern(container));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }
}

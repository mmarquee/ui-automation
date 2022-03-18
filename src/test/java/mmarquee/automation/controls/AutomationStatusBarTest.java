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

import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.uiautomation.IUIAutomation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

/**
 * @author Mark Humphreys
 * Date 02/12/2016.
 */
public class AutomationStatusBarTest {
    protected Logger logger =
            Logger.getLogger(RadioButtonTest.class.getName());

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        StatusBar statusBar = new StatusBar(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = statusBar.getName();

        assertTrue(name.equals("NAME"));
    }
}

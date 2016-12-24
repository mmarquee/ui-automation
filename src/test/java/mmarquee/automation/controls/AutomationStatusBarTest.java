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
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Range;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 02/12/2016.
 */
public class AutomationStatusBarTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationRadioButtonTest.class.getName());

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationStatusBar statusBar = new AutomationStatusBar(element, pattern);

        String name = statusBar.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetTextBox() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationStatusBar sb = window.getStatusBar();

            AutomationTextBox tb1 = sb.getTextBox(1);

            logger.info(tb1.getValue());

            assertTrue(tb1.getValue().equals("Statusbar Text"));
        } finally {
            closeApplication();
        }
    }
}

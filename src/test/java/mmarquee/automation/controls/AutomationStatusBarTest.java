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
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 02/12/2016.
 */
public class AutomationStatusBarTest {
    protected Logger logger = Logger.getLogger(AutomationRadioButtonTest.class.getName());

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationStatusBar statusBar = new AutomationStatusBar(element, pattern);

        String name = statusBar.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTextBox_Throws_IndexOutOfBoundsException_When_Index_Out_Of_Bounds() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationStatusBar statusBar = new AutomationStatusBar(element, pattern);

        AutomationTextBox textBox = statusBar.getTextBox(0);

        verify(element, times(1)).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetTextBox_Calls_Find_All_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(anyObject(), anyObject())).thenReturn(result);

        AutomationStatusBar statusBar = new AutomationStatusBar(element, pattern);

        AutomationTextBox textBox = statusBar.getTextBox(0);

        verify(element, times(1)).findAll(anyObject(), anyObject());
    }
}

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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 25/09/2017
 */
public class AutomationStatusBarTest2 {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTextBox_Throws_IndexOutOfBoundsException_When_Index_Out_Of_Bounds() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

//        when(mocked_automation.createPropertyCondition(any(), any(), any())).thenReturn(1);

        AutomationStatusBar statusBar = new AutomationStatusBar(
                new ElementBuilder(element).addPattern(pattern));

        AutomationTextBox textBox = statusBar.getTextBox(Search.getBuilder(0).build());

        verify(element, times(1)).findAll(any(), any());
    }

    @Test
    public void testGetTextBox_Calls_Find_All_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer pattern = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomationElement listElement = Mockito.mock(IUIAutomationElement.class);

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(any(), any())).thenReturn(result);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

    //    when(mocked_automation.createPropertyCondition(any(), any(), any())).thenReturn(1);

        AutomationStatusBar statusBar = new AutomationStatusBar(
                new ElementBuilder(element).addPattern(pattern));

        AutomationTextBox textBox = statusBar.getTextBox(Search.getBuilder(0).build());

        verify(element, times(1)).findAll(any(), any());
    }
}

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
import mmarquee.automation.pattern.Range;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 01/12/2016.
 */
public class AutomationProgressBarTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range range = Mockito.mock(Range.class);

        when(range.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationProgressBar bar = new AutomationProgressBar(
                new ElementBuilder(element).addPattern(range).automation(instance));

        String name = bar.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetRangeValue_Gets_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range range = Mockito.mock(Range.class);

        when(range.isAvailable()).thenReturn(true);
        when(range.getValue()).thenReturn(99.9);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationProgressBar bar = new AutomationProgressBar(
                new ElementBuilder(element).addPattern(range).automation(instance));

        double value = bar.getRangeValue();

        assertTrue(value == 99.9);
    }

    @Test
    public void testSetRangeValue() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range range = Mockito.mock(Range.class);

        when(range.isAvailable()).thenReturn(true);
        
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationProgressBar bar = new AutomationProgressBar(
                new ElementBuilder(element).addPattern(range).automation(instance));

        bar.setRangeValue(18.99);

        verify(range, atLeast(1)).setValue(18.99);
    }
}

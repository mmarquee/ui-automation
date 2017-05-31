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
import mmarquee.automation.pattern.Range;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationSliderTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range pattern = Mockito.mock(Range.class);

        when(element.getName()).thenReturn("NAME");

        AutomationSlider slider = new AutomationSlider(element, pattern);

        String name = slider.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetRangeValue_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range pattern = Mockito.mock(Range.class);

        when(pattern.getValue()).thenReturn(79.0);

        AutomationSlider slider = new AutomationSlider(element, pattern);

        double value = slider.getRangeValue();

        assertTrue(value == 79.0);
    }

    @Test
    public void testSetRangeValue_Calls_setValue_From_Element_Once() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Range pattern = Mockito.mock(Range.class);

        AutomationSlider slider = new AutomationSlider(element, pattern);

        slider.setRangeValue(99.0);

        verify(pattern, times(1)).setValue(99.0);
    }
}

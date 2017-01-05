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
import mmarquee.automation.pattern.Value;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 28/12/2016.
 */
public class AutomationEditBoxTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        when(element.getName()).thenReturn("NAME");

        AutomationEditBox ctrl = new AutomationEditBox(element, value);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetValue_Gets_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        when(value.value()).thenReturn("NAME");

        AutomationEditBox ctrl = new AutomationEditBox(element, value);

        String name = ctrl.getValue();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testIsReadOnly_Gets_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        when(value.isReadOnly()).thenReturn(true);

        AutomationEditBox ctrl = new AutomationEditBox(element, value);

        boolean result = ctrl.isReadOnly();

        assertTrue(result);
    }

    @Test
    public void testIsReadOnly_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        when(element.currentIsPassword()).thenReturn(true);

        AutomationEditBox ctrl = new AutomationEditBox(element, value);

        boolean result = ctrl.isPassword();

        assertTrue(result);
    }

    @Test
    public void testSetValue() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        //when(value.setValue(anyString())).thenReturn(true);

        AutomationEditBox ctrl = new AutomationEditBox(element, value);

        ctrl.setValue("VALUE");

        verify(value, times(1)).setValue("VALUE");
    }
}

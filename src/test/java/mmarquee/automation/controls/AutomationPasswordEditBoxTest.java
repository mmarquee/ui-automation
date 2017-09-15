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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutomationPasswordEditBoxTest {
    @Test
    public void testGetValue_Gets_Value_From_Value_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        
        when(element.getClassName()).thenReturn(AutomationPasswordEditBox.CLASS_NAME);

        when(value.value()).thenReturn("VALUE");

        AutomationPasswordEditBox control = new AutomationPasswordEditBox(element, value);

        String val = control.getValue();

        assertTrue(val.equals("VALUE"));
    }

    @Test
    public void testSetValue() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        
        when(element.getClassName()).thenReturn(AutomationPasswordEditBox.CLASS_NAME);

        AutomationPasswordEditBox control = new AutomationPasswordEditBox(element, value);

        control.setValue("VALUE");

        verify(value, atLeast(1)).setValue("VALUE");
    }
}

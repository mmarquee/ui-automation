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
import mmarquee.automation.pattern.SelectionItem;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 30/11/2016.
 */
public class AutomationRadioButtonTest {

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);

        when(element.getName()).thenReturn("NAME");

        AutomationRadioButton rb1 = new AutomationRadioButton(element, selection);

        String name = rb1.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void test_isSelected_Gets_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);

        when(selection.isSelected()).thenReturn(true);

        AutomationRadioButton rb1 = new AutomationRadioButton(element, selection);

        boolean value = rb1.isSelected();

        assertTrue(value);
    }

    @Test
    public void testSelect() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);

        when(selection.isSelected()).thenReturn(true);

        AutomationRadioButton rb1 = new AutomationRadioButton(element, selection);

        rb1.selectItem();

        verify(selection, atLeastOnce()).select();
    }
}

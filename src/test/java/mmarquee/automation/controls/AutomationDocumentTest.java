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
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.Text;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 30/11/2016.
 */
public class AutomationDocumentTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Text pattern = Mockito.mock(Text.class);

        when(element.getName()).thenReturn("NAME");

        AutomationDocument document = new AutomationDocument(element, pattern);

        String name = document.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetSelection_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Text pattern = Mockito.mock(Text.class);

        when(pattern.getSelection()).thenReturn("SELECTION");

        AutomationDocument document = new AutomationDocument(element, pattern);

        String name = document.getSelection();

        assertTrue(name.equals("SELECTION"));
    }

    @Test
    public void testGetText_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Text pattern = Mockito.mock(Text.class);

        when(pattern.getText()).thenReturn("NAME");

        AutomationDocument document = new AutomationDocument(element, pattern);

        String name = document.getText();

        assertTrue(name.equals("NAME"));
    }
}

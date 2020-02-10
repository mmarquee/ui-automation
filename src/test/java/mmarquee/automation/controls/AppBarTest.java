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

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.uiautomation.IUIAutomation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

/**
 * Tests for the appbar.
 *
 * @author Mark Humphreys
 * Date 28/12/2016.
 */
public class AppBarTest {
    @Test
    public void testGetName_Gets_Name_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createTrueCondition(isA(PointerByReference.class))).thenReturn(0);

        UIAutomation instance = new UIAutomation(mocked_automation);

        when(element.getName()).thenReturn("NAME");

        AppBar ctrl = new AppBar(
                new ElementBuilder(element).automation(instance));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }
}

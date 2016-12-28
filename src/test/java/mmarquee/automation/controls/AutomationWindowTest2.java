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
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 28/12/2016.
 *
 * AutomationWindow tests that rely on mocking behaviour
 */
public class AutomationWindowTest2 {

    @Test
    public void testMaximize_Gets_Value_From_Pattern() throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.maximize();

        verify(window, atLeast(1)).maximize();
    }

    @Test
    public void testMinimize_Gets_Value_From_Pattern() throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.minimize();

        verify(window, atLeast(1)).minimize();
    }

    @Test
    public void testGetName_Gets_Name_From_Element() throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationWindow wndw = new AutomationWindow(element, window, container);

        String name = wndw.name();

        assertTrue(name.equals("NAME"));
    }
}

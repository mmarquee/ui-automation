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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.SelectionItem;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationToolbarButtonTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationToolbarButtonTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        AutomationToolBarButton ctrl = new AutomationToolBarButton(element);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testClick_Never_Calls_Invoke_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke invoke = Mockito.mock(Invoke.class);
        when(element.getClickablePoint()).thenReturn(new WinDef.POINT(0,0));

        AutomationToolBarButton ctrl = new AutomationToolBarButton(element, invoke);

        ctrl.click();

        verify(invoke, never()).invoke();
    }
}

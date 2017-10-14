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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.*;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 28/12/2016.
 *
 * AutomationWindow tests that rely on mocking behaviour
 */
public class AutomationWindowTest2 {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock
    AutomationElement element;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @Mock
    User32 user32;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMaximize_Gets_Maximize_From_Pattern() throws AutomationException, PatternNotFoundException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.maximize();

        verify(window, atLeast(1)).maximize();
    }

    @Test
    public void testMinimize_Calls_Minimize_From_Pattern() throws AutomationException, PatternNotFoundException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.minimize();

        verify(window, atLeast(1)).minimize();
    }

    @Test
    public void testClose_Gets_Close_From_Pattern() throws AutomationException, PatternNotFoundException {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.close();

        verify(window, atLeast(1)).close();
    }

    @Test
    public void testGetName_Gets_Name_From_Element() throws AutomationException, PatternNotFoundException {
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        String name = wndw.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void test_focus_Calls_setFocus_From_element() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.focus();

        verify(element, atLeastOnce()).setFocus();
    }

    @Test
    public void test_WaitForIdleInput_Calls_waitForInputIdle_From_Window() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.waitForInputIdle(100);

        verify(window, atLeastOnce()).waitForInputIdle(anyInt());
    }

    @Test
    public void test_isTopMost_Calls_isTopMost_From_Window() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.isTopMost();

        verify(window, atLeastOnce()).isTopMost();
    }

    @Test
    public void test_isModal_Calls_isModal_From_Window() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.isModal();

        verify(window, atLeastOnce()).isModal();
    }

    @Test(expected= Win32Exception.class)
    public void test_setTransparency_Throws_Exception_When_Win32_Calls_Throw_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1234;
            }
        }).when(element).getPropertyValue(anyInt());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        wndw.setTransparency(100);

        verify(element, atLeastOnce()).getPropertyValue(anyInt());
    }

    @Test
    public void test_windowHandle_Calls_currentPropertyValue_From_Window() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1234;
            }
        }).when(element).getPropertyValue(anyInt());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        WinDef.HWND handle = wndw.getNativeWindowHandle();

        verify(element, atLeastOnce()).getPropertyValue(anyInt());
    }

    @Test(expected=AutomationException.class)
    @Ignore("Needs further work to make meaningful")
    public void test_windowHandle_Throws_Exception_When_currentPropertyValue_Returns_Error() throws Exception {

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1;
            }
        }).when(elem).getCurrentPropertyValue(anyInt(), any());

        AutomationElement localElement = Mockito.mock(AutomationElement.class);

        localElement.setElement(elem);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow wndw = new AutomationWindow(element, window, container, instance);

        WinDef.HWND handle = wndw.getNativeWindowHandle();

        verify(elem, atLeastOnce()).getCurrentPropertyValue(anyInt(), any());
    }

    @Test
    public void test_setTransparency_Calls_currentPropertyValue_From_Window() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 1;
            }
        }).when(user32).SetWindowLong(any(), anyInt(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return true;
            }
        }).when(user32).SetLayeredWindowAttributes(any(), anyInt(), anyByte(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1234;
            }
        }).when(element).getPropertyValue(anyInt());

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        wndw.setTransparency(100);

        verify(element, atLeastOnce()).getPropertyValue(anyInt());
    }

    @Test(expected = Win32Exception.class)
    public void test_setTransparency_Throws_Exception_When_SetWindowLong_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        }).when(user32).SetWindowLong(any(), anyInt(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return true;
            }
        }).when(user32).SetLayeredWindowAttributes(any(), anyInt(), anyByte(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1234;
            }
        }).when(element).getPropertyValue(anyInt());

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        wndw.setTransparency(100);

        verify(element, atLeastOnce()).getPropertyValue(anyInt());
    }

    @Test(expected = Win32Exception.class)
    public void test_setTransparency_Throws_Exception_When_SetLayeredWindowAttributes_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 1;
            }
        }).when(user32).SetWindowLong(any(), anyInt(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return false;
            }
        }).when(user32).SetLayeredWindowAttributes(any(), anyInt(), anyByte(), anyInt());

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object reference = (Object)args[0];

                reference = 1245;

                return 1234;
            }
        }).when(element).getPropertyValue(anyInt());

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        wndw.setTransparency(100);

        verify(element, atLeastOnce()).getPropertyValue(anyInt());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRebar_By_Index_Throws_Exception_When_Not_Found() throws Exception {

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        AutomationReBar rebar = wndw.getReBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetStatusBar_By_Index() throws Exception {

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        AutomationStatusBar bar = wndw.getStatusBar();

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetStatusBar_By_Index_Check_ControlType_When() throws Exception {

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.StatusBar.getValue());

                return 0;
            }
        }).when(listElement).getCurrentControlType(any());

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(any(), any())).thenReturn(result);

        AutomationStatusBar bar = wndw.getStatusBar();

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetAppBar_By_Index() throws Exception {

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.AppBar.getValue());

                return 0;
            }
        }).when(listElement).getCurrentControlType(any());

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(any(), any())).thenReturn(result);

        AutomationAppBar bar = wndw.getAppBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetTitleBar_By_Index() throws Exception {

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.TitleBar.getValue());

                return 0;
            }
        }).when(listElement).getCurrentControlType(any());

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(any(), any())).thenReturn(result);

        AutomationTitleBar bar = wndw.getTitleBar();

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected= ElementNotFoundException.class)
    public void testGetWindow_Throws_Exception_When_Not_Found() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        AutomationWindow w = wndw.getWindow("WINDOW-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetWindow_Calls_FindFirst_10_Times_When_Not_Found() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        try {
            AutomationWindow w = wndw.getWindow("WINDOW-01");
        } catch (Throwable t) {
            // Catch exception to allow verify to work
        }

        verify(element, times(10)).findFirst(any(), any());
    }

    @Test
    public void testGetWindow() throws Exception {
        doAnswer(new Answer() {
            @Override
            public AutomationElement answer(InvocationOnMock invocation) throws Throwable {
                IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

                return new AutomationElement(elem);
            }
        }).when(element).findFirst(any(), any());

        AutomationWindow wndw = new AutomationWindow(element, window, container, user32);

        try {
            AutomationWindow w = wndw.getWindow("WINDOW-01");
        } catch (Throwable t) {
            // Catch exception so test can continue
        }

        verify(element, times(1)).findFirst(any(), any());
    }
}

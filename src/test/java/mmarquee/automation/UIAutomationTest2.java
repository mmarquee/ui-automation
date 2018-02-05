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
package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static mmarquee.automation.BaseAutomationTest.getLocal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 22/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class UIAutomationTest2 {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IOException.class)
    public void testLaunch_Throws_Exception_When_startProcess_Fails() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation local_instance = new UIAutomation(mocked_automation);

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        local_instance.launch("notepad99.exe");
    }

    @Test(expected = AutomationException.class)
    public void testCreateFalseCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createFalseCondition(isA(PointerByReference.class))).thenReturn(-1);

        UIAutomation local_instance = new UIAutomation(mocked_automation);

        local_instance.createFalseCondition();
    }

    @Test
    public void testGetDesktopWindow_Succeeds_When_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
        IUIAutomation mocked = Mockito.mock(IUIAutomation.class);

        Unknown mockUnknown = Mockito.mock(Unknown.class);

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(), any());

        when(mocked.createAndCondition(any(Pointer.class), any(Pointer.class), any(PointerByReference.class))).thenReturn(0);
        when(mocked.createPropertyCondition(any(Integer.class), any(Variant.VARIANT.ByValue.class), any(PointerByReference.class))).thenReturn(0);

        UIAutomation local_instance = Mockito.mock(UIAutomation.class);

        doReturn(mockUnknown)
                .when(local_instance)
                .makeUnknown(any(Pointer.class));

        local_instance.getDesktopWindow(getLocal("notepad.title"));
    }

    @Test
    public void testLaunch_Does_Not_Throw_Exception_When_startProcess_Succeeds() throws Exception {
        UIAutomation instance = Mockito.mock(UIAutomation.class);

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        AutomationApplication app = instance.launch("notepad.exe");
    }

    @Test
    public void testLaunchOrAttach_Does_Not_Throw_Exception_When_Launching_startProcess_Succeeds() throws Exception {
        UIAutomation instance = Mockito.mock(UIAutomation.class);

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any(String[].class))).thenReturn(true);

        instance.launchOrAttach("notepad.exe");
    }

    @Test
    public void test_LaunchWithDirectoryOrAttach_Does_Not_Throw_Exception_When_Launching_startProcess_Succeeds() throws Exception {
        UIAutomation instance = Mockito.mock(UIAutomation.class);

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any(String[].class))).thenReturn(true);

        instance.launchWithDirectoryOrAttach("notepad.exe");
    }

    @Test
    public void testLaunchOrAttach_Succeeds_When_Already_Running() throws Exception {
        UIAutomation instance = Mockito.mock(UIAutomation.class);

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any(String[].class))).thenReturn(true);
        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);
        PowerMockito.when(Utils.getHandleFromProcessEntry(any())).thenReturn(new WinNT.HANDLE());

        instance.launchOrAttach("notepad.exe");
    }
}

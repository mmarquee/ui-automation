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

import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.utils.Utils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Mark Humphreys on 22/05/2017.
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
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        instance.launch("notepad99.exe");
    }

    @Test
    @Ignore("Something is wrong here")
    public void testLaunch_Does_Not_Throw_Exception_When_startProcess_Succeeds() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        AutomationApplication app = instance.launch("notepad.exe");
    }

    @Test(expected = IOException.class)
    public void testLaunchOrAttach_Fails_When_Launching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        instance.launchOrAttach("notepad99.exe");
    }

    @Test
    public void testLaunchOrAttach_Does_Not_Throw_Exception_When_Launching_startProcess_Succeeds() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any())).thenReturn(true);

        instance.launchOrAttach("notepad.exe");
    }

    @Test(expected = IOException.class)
    public void testLaunchOrAttach_Fails_When_Attaching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any())).thenReturn(false);
        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        instance.launchOrAttach("notepad99.exe");
    }

    @Test(expected = IOException.class)
    public void test_LaunchWithWorkingDirectoryOrAttach_Fails_When_Launching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcessWithWorkingDirectory(anyString())).thenThrow(java.io.IOException.class);

        instance.launchWithWorkingDirectoryOrAttach("notepad99.exe");
    }

    @Test
    public void test_LaunchWithWorkingDirectoryOrAttach_Does_Not_Throw_Exception_When_Launching_startProcess_Succeeds() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any())).thenReturn(true);

        instance.launchWithWorkingDirectoryOrAttach("notepad.exe");
    }

    @Test(expected = IOException.class)
    public void test_LaunchWithWorkingDirectoryOrAttach_Fails_When_Attaching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any())).thenReturn(false);
        PowerMockito.when(Utils.startProcessWithWorkingDirectory(anyString())).thenThrow(java.io.IOException.class);

        instance.launchWithWorkingDirectoryOrAttach("notepad99.exe");
    }

    @Test
    public void testLaunchOrAttach_Succeeds_When_Already_Running() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any())).thenReturn(true);
        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);
        PowerMockito.when(Utils.getHandleFromProcessEntry(any())).thenReturn(new WinNT.HANDLE());

        instance.launchOrAttach("notepad.exe");
    }
}

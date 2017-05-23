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

import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyString;

/**
 * Created by Mark Humphreys on 22/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class UIAutomationTest2 {

    @Test(expected = IOException.class)
    public void testLaunch_Throws_Exception_When_startProcess_Fails() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        Mockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        instance.launch("notepad99.exe");
    }

    @Test
    public void testLaunch_Does_Not_Throw_Exception_When_startProcess_Succeeds() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        AutomationApplication app = instance.launch("notepad.exe");
    }

    @Test(expected = IOException.class)
    public void testLaunchOrAttach_Fails_When_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        Mockito.when(Utils.startProcess(anyString())).thenThrow(java.io.IOException.class);

        instance.launchOrAttach("notepad99.exe");
    }

    @Test
    public void testLaunchOrAttach_Does_Not_Throw_Exception_When_startProcess_Succeeds() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        instance.launchOrAttach("notepad.exe");
    }
}

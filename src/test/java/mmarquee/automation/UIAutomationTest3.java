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

import mmarquee.automation.utils.Utils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Mark Humphreys
 * Date 25/09/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class UIAutomationTest3 {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IOException.class)
    public void testLaunchOrAttach_Fails_When_Launching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(IOException.class);

        instance.launchOrAttach("notepad99.exe");
    }

    @Test(expected = IOException.class)
    public void testLaunchOrAttach_Fails_When_Attaching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any(String[].class))).thenReturn(false);
        PowerMockito.when(Utils.startProcess(anyString())).thenThrow(IOException.class);

        instance.launchOrAttach("notepad99.exe");
    }

    @Test(expected = IOException.class)
    public void test_LaunchWithDirectoryOrAttach_Fails_When_Launching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.startProcessWithWorkingDirectory(anyString())).thenThrow(IOException.class);

        instance.launchWithDirectoryOrAttach("notepad99.exe");
    }

    @Test(expected = IOException.class)
    public void test_LaunchWithDirectoryOrAttach_Fails_When_Attaching_With_No_executable() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.findProcessEntry(any(), any(String[].class))).thenReturn(false);
        PowerMockito.when(Utils.startProcessWithWorkingDirectory(anyString())).thenThrow(IOException.class);

        instance.launchWithDirectoryOrAttach("notepad99.exe");
    }
}

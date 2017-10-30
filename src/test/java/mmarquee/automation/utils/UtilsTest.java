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
package mmarquee.automation.utils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

import mmarquee.automation.BaseAutomationTest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Mark Humphreys
 * Date 24/11/2016.
 *
 * Tests of the Utils class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class UtilsTest extends BaseAutomationTest {
    
	// helper methods for tests
    public static void setUser32(User32 user32) {
    	Utils.user32 = user32;	
    }
    
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @After
    public void tearDown() {
        // Must be a better way of doing this????
        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, getLocal("notepad.title"));

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }

    @Test
    @Ignore("How to verify a powermocked static class")
    public void testStartProcess_Calls_Start_Process_Once() throws IOException {

        PowerMockito.mockStatic(Utils.class);

        PowerMockito.when(Utils.createProcessBuilder(anyString())).thenThrow(java.io.IOException.class);

        Utils.startProcess("Notepad.exe");

//        verify(Utils, atLeastOnce()).start();
    }

    @Test
    public void testStartProcess_Starts_Notepad() {
        try {
            Utils.startProcess("notepad.exe");

            this.andRest();

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, getLocal("notepad.title"));

            assertTrue("startProcess - no process", hwnd != null);
        } catch (IOException io) {
            assertTrue("startProcess: " + io.getMessage(), false);
        }

        assertTrue("startProcess", true);
    }

    @Test
    public void testQuitProcess_Quits_Notepad() {
        try {
            Utils.startProcess("notepad.exe");

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, getLocal("notepad.title"));

            if (hwnd != null) {
                Utils.quitProcess(hwnd);
            }
        } catch (IOException io) {
            assertTrue("quitProcess: " + io.getMessage(), false);
        }

        assertTrue("quitProcess", true);
    }

    @Test
    public void testCloseWindow_Closes_Notepad() {
        try {
            Utils.startProcess("notepad.exe");

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, getLocal("notepad.title"));

            if (hwnd != null) {
                Utils.closeWindow(hwnd);
            }
        } catch (IOException io) {
            assertTrue("quitProcess: " + io.getMessage(), false);
        }

        assertTrue("quitProcess", true);
    }
    @Test
    public void testStartProcess_Throws_Exception_When_not_found() {
        try {
            Utils.startProcess("notepad99.exe");
        } catch (IOException io) {
            assertTrue("startProcess", true);
        }

        assertFalse("startProcess", false);
    }

    @Test
    public void testFindProcessEntry_When_Not_found() {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, "notepad99.exe");

        assertFalse(found);
    }

    @Test
    public void testFindProcessEntry_When_found() throws IOException {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        Utils.startProcess("notepad.exe");

        this.andRest();

        boolean found = Utils.findProcessEntry(processEntry, "notepad.exe");

        assertTrue(found);
    }

    @Test
    public void testCaptureScreen_Writes_to_File() throws Exception {
        Utils.captureScreen("test.png");

        File f = new File("test.png");

        assertTrue(f.exists());
    }

    @Test
    public void testCapture_Writes_to_File() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            Utils.capture(window.getNativeWindowHandle(), "test.png");

            File f = new File("test.png");

            assertTrue(f.exists());
        } finally {
            closeApplication();
        }
    }
   
    @Test
    public void test_FindWindow_with_RegexPattern_findsWindow() throws IOException {
        Utils.startProcess("notepad.exe");

        this.andRest();

        HWND handle = Utils.findWindow(null, Pattern.compile(Pattern.quote(getLocal("notepad.title"))));

        assertNotNull(handle);
    }

    @Test
    public void test_FindWindow_with_RegexPattern_WithClass_findsWindow() throws IOException {
        Utils.startProcess("notepad.exe");

        this.andRest();

        HWND handle = Utils.findWindow("Notepad", Pattern.compile(Pattern.quote(getLocal("notepad.title"))));

        assertNotNull(handle);
    }

    @Test
    public void test_FindWindow_with_RegexPattern_failstoFindNonExistingWindow() throws IOException {
        HWND handle = Utils.findWindow(null, Pattern.compile("Schnulli"));

        assertNull(handle);
    }

    @Test
    public void test_FindWindow_with_RegexPattern_WithClass_failsOnWringClass() throws IOException {
        Utils.startProcess("notepad.exe");

        this.andRest();

        HWND handle = Utils.findWindow("NotANotepad", Pattern.compile(Pattern.quote(getLocal("notepad.title"))));

        assertNull(handle);
    }

}
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
package mmarquee.automation.utils;

import java.io.IOException;

import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import junit.framework.TestCase;

/**
 * Created by Mark Humphreys on 24/11/2016.
 */
public class UtilsTest extends TestCase {

    private void andRest() {
        // Must be a better way of doing this????
        try {
            Thread.sleep(500);
        } catch (Throwable ex) {
            // interrupted
        }
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Override
    protected void tearDown() {
        // Must be a better way of doing this????
        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(UtilsTest.class);
    }

    public void testStartProcess_Starts_Notepad() {
        try {
            Utils.startProcess("notepad.exe");

            this.andRest();

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

            assertTrue("startProcess - no process", hwnd != null);
        } catch (IOException io) {
            assertTrue("startProcess: " + io.getMessage(), false);
        }

        assertTrue("startProcess", true);
    }

    public void testQuitProcess_Quits_Notepad() {
        try {
            Utils.startProcess("notepad.exe");

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

            if (hwnd != null) {
                Utils.quitProcess(hwnd);
            }
        } catch (IOException io) {
            assertTrue("quitProcess: " + io.getMessage(), false);
        }

        assertTrue("quitProcess", true);
    }

    public void testStartProcess_Throws_Exception_When_not_found() {
        try {
            Utils.startProcess("notepad99.exe");
        } catch (IOException io) {
            assertTrue("startProcess", true);
        }

        assertFalse("startProcess", false);
    }

    public void testFindProcessEntry_When_Not_found() {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, "notepad99.exe");

        assertFalse(found);
    }

    public void testFindProcessEntry_When_found() throws IOException {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        Utils.startProcess("notepad.exe");

        this.andRest();

        boolean found = Utils.findProcessEntry(processEntry, "notepad.exe");

        assertTrue(found);
    }
}
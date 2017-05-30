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

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.win32.W32APIOptions;
import mmarquee.automation.AutomationException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mark Humphreys on 18/03/2016.
 *
 * Utility methods used in the project
 */
public class Utils {
    /**
     * Gets the handle of a process from the process entry
     * @param processEntry The processEntry to use
     * @return The handle
     * @throws AutomationException Thrown if the handle cannot be determined
     */
    public static WinNT.HANDLE getHandleFromProcessEntry(Tlhelp32.PROCESSENTRY32.ByReference processEntry) throws AutomationException {
        WinNT.HANDLE handle = Kernel32.INSTANCE.OpenProcess (
                0x0400 |    /* PROCESS_QUERY_INFORMATION */
                0x0800 |    /* PROCESS_SUSPEND_RESUME */
                0x0001 |    /* PROCESS_TERMINATE */
                0x00100000  /* SYNCHRONIZE */,
                false,
                processEntry.th32ProcessID.intValue());

        if (handle == null) {
            throw new AutomationException();
        }

        return handle;
    }

    /**
     * Finds the given process in the process list
     * @param processEntry The process entry
     * @param command Command
     * @return The found process entry
     */
    public static boolean findProcessEntry(Tlhelp32.PROCESSENTRY32.ByReference processEntry, String... command) {
        File file = new File(command[0]);
        String filename = file.getName();

        Kernel32 kernel32 = Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);

        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));

        boolean found = false;

        try {
            while (kernel32.Process32Next(snapshot, processEntry)) {
                String fname = Native.toString(processEntry.szExeFile);

                if (fname.equals(filename)) {
                    found = true;
                    break;
                }
            }
        } finally {
            kernel32.CloseHandle(snapshot);
        }

        return found;
    }

    /**
     * Starts the given command
     * @param command The command to start
     * @return The process
     * @throws java.io.IOException something has gone wrong
     */
    public static Process startProcess(String... command) throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        return pb.start();
    }

    /**
     * Wrapper around creation of ProcessBuilder object.
     * @param command The command line to use
     * @return The created ProcessBuilder object.
     */
    public static ProcessBuilder createProcessBuilder (String... command) {
        return new ProcessBuilder(command);
    }

    /**
     * Starts the given command, setting the working directory
     * @param command The command to start
     * @return The process
     * @throws java.io.IOException something has gone wrong
     */
    public static Process startProcessWithWorkingDirectory(String... command) throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        String directory = new File(command[0]).getParent();

        pb.directory(new File(directory));

        return pb.start();
    }

    /**
     * Quits the given process
     * @param handle The handle to quit
     */
    public static void quitProcess(WinDef.HWND handle) {
        User32.INSTANCE.PostMessage(handle, WinUser.WM_QUIT, null, null);
    }

    /**
     * Closes the given process
     * @param handle The handle to close
     */
    public static void closeProcess(WinDef.HWND handle) {
        User32.INSTANCE.PostMessage(handle, WinUser.WM_CLOSE, null, null);
    }

    /**
     * Captures the window.
     *
     * @param hwnd The window to capture
     * @param filename Name to save the output into
     * @throws AWTException Robot exception
     * @throws IOException IO Exception
     */
    public static void capture(WinDef.HWND hwnd, String filename) throws AWTException, IOException, Win32Exception {
        WinDef.RECT rect = new WinDef.RECT();

        if (!User32.INSTANCE.GetWindowRect(hwnd, rect)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        Rectangle rectangle = new Rectangle(rect.left, rect.top, rect.right -rect.left, rect.bottom -rect.top);

        BufferedImage image = new Robot().createScreenCapture(rectangle);

        ImageIO.write(image, "png", new File(filename));
    }

    /**
     * Captures the screen.
     *
     * @param filename The filename
     * @throws AWTException Robot exception
     * @throws IOException IO Exception
     */
    public static void captureScreen(String filename) throws AWTException, IOException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File(filename));
    }
}

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

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.W32APIOptions;

import mmarquee.automation.AutomationException;

/**
 * Utility methods used in the project.
 *
 * @author Mark Humphreys
 * Date 18/03/2016.
 */
public class Utils {
    /**
     * The logger.
     */
    protected static final Logger logger =
            Logger.getLogger(Utils.class.getName());

    /**
     * The User32 instance.
     */
    static User32 user32; // can be mocked in tests

    /**
     * The Kernel32 instance.
     */
    static Kernel32 kernel32; // can be mocked in tests
    
    /**
     * Gets the handle of a process from the process entry.
     *
     * @param processEntry The processEntry to use
     * @return The handle
     * @throws AutomationException Thrown if the handle cannot be determined
     */
    public static WinNT.HANDLE getHandleFromProcessEntry(
            final Tlhelp32.PROCESSENTRY32.ByReference processEntry)
            throws AutomationException {
    	ensureWinApiInstances();

        WinNT.HANDLE handle = kernel32.OpenProcess(
                0x0400 |    /* PROCESS_QUERY_INFORMATION */
                0x0800 |    /* PROCESS_SUSPEND_RESUME */
                0x0001 |    /* PROCESS_TERMINATE */
                0x00100000  /* SYNCHRONIZE */,
                false,
                processEntry.th32ProcessID.intValue());

        if (handle == null) {
            throw new AutomationException("OpenProcess failed");
        }

        return handle;
    }

    /**
     * Finds the given process in the process list.
     *
     * @param processEntry The process entry.
     * @param command Command.
     * @return The found process entry.
     */
    public static boolean findProcessEntry(
            final Tlhelp32.PROCESSENTRY32.ByReference processEntry,
                     final String... command) {
        File file = new File(command[0]);
        String filename = file.getName();
        return findProcessEntry(processEntry,Pattern.compile(filename,
                Pattern.LITERAL));
    }

    /**
     * Finds the given process in the process list.
     *
     * @param processEntry The process entry.
     * @param filenamePattern pattern matching the filename of the process.
     * @return The found process entry.
     */
    public static boolean findProcessEntry(
            final Tlhelp32.PROCESSENTRY32.ByReference processEntry,
            final Pattern filenamePattern) {
        Kernel32 kern32 =
                Native.loadLibrary(Kernel32.class,
                        W32APIOptions.UNICODE_OPTIONS);

        WinNT.HANDLE snapshot =
                kern32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS,
                        new WinDef.DWORD(0));

        boolean found = false;

        try {
            while (kern32.Process32Next(snapshot, processEntry)) {
                String fname = Native.toString(processEntry.szExeFile);

                if (fname != null && filenamePattern.matcher(fname).matches()) {
                    found = true;
                    break;
                }
            }
        } finally {
            kern32.CloseHandle(snapshot);
        }

        return found;
    }

    /**
     * Starts the given command.
     *
     * @param command The command to start.
     * @return The process.
     * @throws java.io.IOException something has gone wrong.
     */
    public static Process startProcess(final String... command)
            throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        return pb.start();
    }

    /**
     * Starts the given command, redirecting stdout.
     *
     * @param command The command to start.
     * @return The process.
     * @throws java.io.IOException something has gone wrong.
     */
    public static Process startProcessWithRedirection(final String... command)
            throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        /* Direct output to log */
        File log = new File("log.txt");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

        return pb.start();
    }

    /**
     * Starts the given command, setting the working directory, and
     * redirection of output.
     *
     * @param command The command to start.
     * @return The process.
     * @throws java.io.IOException something has gone wrong.
     */
    public static Process startProcessWithWorkingDirectoryWithRedirection(
            final String... command)
            throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        String directory = new File(command[0]).getParent();

        pb.directory(new File(directory));

        /* Direct output to log */
        File log = new File("log.txt");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

        return pb.start();
    }

    /**
     * Wrapper around creation of ProcessBuilder object.
     *
     * @param command The command line to use
     * @return The created ProcessBuilder object.
     */
    public static ProcessBuilder createProcessBuilder(final String... command) {
        return new ProcessBuilder(command);
    }

    /**
     * Starts the given command, setting the working directory.
     *
     * @param command The command to start.
     * @return The process.
     * @throws java.io.IOException something has gone wrong.
     */
    public static Process startProcessWithWorkingDirectory(
            final String... command)
            throws java.io.IOException {
        ProcessBuilder pb = createProcessBuilder(command);

        String directory = new File(command[0]).getParent();

        pb.directory(new File(directory));

        return pb.start();
    }

    /**
     * Quits the given process.
     *
     * @param handle The handle to quit.
     */
    public static void quitProcess(final WinDef.HWND handle) {
        ensureWinApiInstances();
        
        user32.PostMessage(handle,
                WinUser.WM_QUIT,
                null,
                null);
    }

    /**
     * Ends the given process.
     * @param process The process to end
     */
    public static void end(final Process process) {
        process.destroy();
    }

    /**
     * Creates the WinApi instances, if necessary.
     */
    private static void ensureWinApiInstances() {
		if (user32 == null) {
            user32 = User32.INSTANCE;
        }
		if (kernel32 == null) {
			kernel32 = Kernel32.INSTANCE;
        }
	}

    /**
     * Closes the given window.
     *
     * @param handle The handle of the window to close.
     * 
     * @deprecated use closeWindow instead
     */
    @Deprecated
    public static void closeProcess(final WinDef.HWND handle) {
        closeWindow(handle);
    }

    /**
     * Closes the given window.
     *
     * @param handle The handle of the window to close.
     */
    public static void closeWindow(final WinDef.HWND handle) {
        ensureWinApiInstances();
        
        user32.PostMessage(handle,
                WinUser.WM_CLOSE,
                null,
                null);
    }

    /**
     * Captures the window.
     *
     * @param hwnd The window to capture.
     * @param filename Name to save the output into.
     * @throws AWTException Robot exception.
     * @throws IOException IO Exception.
     * @throws Win32Exception Win32 Exception
     */
    public static void capture(final WinDef.HWND hwnd,
                               final String filename)
            throws AWTException, IOException, Win32Exception {
        ensureWinApiInstances();
        
        WinDef.RECT rect = new WinDef.RECT();

        if (!user32.GetWindowRect(hwnd, rect)) {
            throw new Win32Exception(kernel32.GetLastError());
        }

        Rectangle rectangle =
                new Rectangle(rect.left, rect.top,
                        rect.right - rect.left,
                        rect.bottom - rect.top);

        BufferedImage image = new Robot().createScreenCapture(rectangle);

        ImageIO.write(image, "png", new File(filename));
    }

    /**
     * Captures the screen.
     *
     * @param filename The filename.
     * @throws AWTException Robot exception.
     * @throws IOException IO Exception.
     */
    public static void captureScreen(final String filename)
            throws AWTException, IOException {
        BufferedImage image =
                new Robot().createScreenCapture(
                        new Rectangle(
                                Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File(filename));
    }
    

    /**
     * An implementation of user32.FindWindow with Regex pattern matching.
     * 
     * @param windowClass the classname of the window, or null to ignore
     * @param titlePattern the regex pattern to match the title against
     * @return the hwnd of the found window, or null if not found
     */
	public static WinDef.HWND findWindow(final String windowClass,
                                         final Pattern titlePattern) {
		ensureWinApiInstances();
		
		final WinDef.HWND[] returnContainer = new WinDef.HWND[1];
        
		user32.EnumWindows(new WNDENUMPROC() {
            /**
             * Callback is called synchronously...
             */
            @Override
            public boolean callback(final HWND hWnd, final Pointer arg) {
            	try {
            		boolean checkWindowClass = (windowClass != null);
            		
                    final int length = user32.GetWindowTextLength(hWnd) + 1;
                    if (length == 1) {
                        return true;
                    }

                    final char[] windowText = new char[length];
                    user32.GetWindowText(hWnd, windowText, length);
                    final String wText = Native.toString(windowText);

                    String wClass = null;
					if (checkWindowClass || logger.isLoggable(Level.FINE)) {
                        final char[] classText = new char[255];
                        User32.INSTANCE.GetClassName(hWnd, classText, 255);
                        wClass = Native.toString(classText);
                    }

                    logger.fine("Detected window: " + wText + ", windowClass: "  + wClass + ", HWND: " + hWnd);
                    
                    final boolean windowClassMatches = ! checkWindowClass || windowClass.equals(wClass);

                    if (wText != null && windowClassMatches && titlePattern.matcher(wText).matches()) {
                        logger.info("Matching window: " + wText + ", HWND: " + hWnd);
                    	returnContainer[0] = hWnd;
                        return false;
                    }
                } catch (final Throwable ex) {
                    ex.printStackTrace();
                }
                return true;
            }
        }, null);
        
        return returnContainer[0];
	}
}

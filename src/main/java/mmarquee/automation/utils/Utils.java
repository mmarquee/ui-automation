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

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.win32.W32APIOptions;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by inpwt on 18/03/2016.
 */
public class Utils {
    /**
     * Gets the handle of a process from the process entry
     * @param processEntry The processEntry to use
     * @return The handle
     * @throws Exception Thrown if the handle cannot be determined
     */
    public static WinNT.HANDLE getHandleFromProcessEntry(Tlhelp32.PROCESSENTRY32.ByReference processEntry) throws Exception {

        WinNT.HANDLE handle = Kernel32.INSTANCE.OpenProcess (
                0x0400 |    /* PROCESS_QUERY_INFORMATION */
                0x0800 |    /* PROCESS_SUSPEND_RESUME */
                0x0001 |    /* PROCESS_TERMINATE */
                0x00100000  /* SYNCHRONIZE */,
                false,
                processEntry.th32ProcessID.intValue());

        if (handle == null) {
            throw new Exception(Kernel32Util.formatMessageFromLastErrorCode(Kernel32.INSTANCE.GetLastError()));
        }

        return handle;
    }

    /**
     * Finds the given process in the process list
     * @param command Command
     * @return The found process entry
     */
    public static boolean findProcessEntry(Tlhelp32.PROCESSENTRY32.ByReference processEntry, String... command) {
        File file = new File(command[0]);
        String filename = file.getName();

        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);

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
        ProcessBuilder pb = new ProcessBuilder(command);

        return pb.start();
    }

    /*
    public BufferedImage capture(WinDef.HWND hwnd) {
        BufferedImage image = new BufferedImage();

        return image;
    }
    */
}

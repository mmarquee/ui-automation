/*
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

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.VerRsrc;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * See https://stackoverflow.com/questions/6918022/get-version-info-for-exe
 */
public class ExecutableFileInfo {

    /**
     * Major version.
     */
    public static final int MAJOR_VERSION = 0;

    /**
     * Minor version number.
     */
    public static final int MINOR_VERSION = 1;

    /**
     * Release number.
     */
    public static final int RELEASE = 2;

    /**
     * Build number.
     */
    public static final int BUILD = 3;

    /**
     * Gets the version info (if present) from the file in the path.
     * @param path Pathname to file
     * @return The version info array (loads of integers)
     */
    public static int[] getVersionInfo(final String path) {
        IntByReference dwDummy = new IntByReference();
        dwDummy.setValue(0);

        int versionlength = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfoSize(path, dwDummy);

        byte[] bufferarray = new byte[versionlength];
        Pointer lpData = new Memory(bufferarray.length);
        PointerByReference lplpBuffer = new PointerByReference();
        IntByReference puLen = new IntByReference();
        com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfo(path, 0, versionlength, lpData);
        com.sun.jna.platform.win32.Version.INSTANCE.VerQueryValue(lpData, "\\", lplpBuffer, puLen);

        VerRsrc.VS_FIXEDFILEINFO lplpBufStructure = new VerRsrc.VS_FIXEDFILEINFO(lplpBuffer.getValue());
        lplpBufStructure.read();

        int v1 = (lplpBufStructure.dwFileVersionMS).intValue() >> 16;
        int v2 = (lplpBufStructure.dwFileVersionMS).intValue() & 0xffff;
        int v3 = (lplpBufStructure.dwFileVersionLS).intValue() >> 16;
        int v4 = (lplpBufStructure.dwFileVersionLS).intValue() & 0xffff;
        System.out.println("Version: " + v1 + "." + v2 + "." + v3 + "." + v4);
        return new int[]{v1, v2, v3, v4};
    }
}
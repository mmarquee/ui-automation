package mmarquee.automation.utils;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.VerRsrc;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * See https://stackoverflow.com/questions/6918022/get-version-info-for-exe
 */
public class EXEFileInfo {
    public static int MAJOR = 0;
    public static int MINOR = 1;
    public static int BUILD = 2;
    public static int REVISION = 3;

    public static int getMajorVersionOfProgram(String path) {
        return getVersionInfo(path)[MAJOR];
    }

    public static int getMinorVersionOfProgram(String path) {
        return getVersionInfo(path)[MINOR];
    }

    public static int getBuildOfProgram(String path) {
        return getVersionInfo(path)[BUILD];
    }

    public static int getRevisionOfProgram(String path) {
        return getVersionInfo(path)[REVISION];
    }

    public static int[] getVersionInfo(String path) {
        IntByReference dwDummy = new IntByReference();
        dwDummy.setValue(0);

        int versionlength = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfoSize(path, dwDummy);

        byte[] bufferarray = new byte[versionlength];
        Pointer lpData = new Memory(bufferarray.length);
        PointerByReference lplpBuffer = new PointerByReference();
        IntByReference puLen = new IntByReference();
        boolean fileInfoResult = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfo(path, 0, versionlength, lpData);
        boolean verQueryVal = com.sun.jna.platform.win32.Version.INSTANCE.VerQueryValue(lpData, "\\", lplpBuffer, puLen);

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

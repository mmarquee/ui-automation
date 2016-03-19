package mmarquee.automation.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by inpwt on 19/03/2016.
 */
public interface User32Ext extends User32 {
    WinDef.HWND GetParent(WinDef.HWND hwnd);

    User32Ext INSTANCE =
            (User32Ext) Native.loadLibrary("user32", User32Ext.class, W32APIOptions.DEFAULT_OPTIONS);
}
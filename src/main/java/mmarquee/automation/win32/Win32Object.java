package mmarquee.automation.win32;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import mmarquee.automation.utils.User32Ext;
import mmarquee.automation.utils.Utils;

import java.awt.*;
import java.util.*;

/**
 * Created by inpwt on 19/03/2016.
 */
public class Win32Object extends AutomationObject {
    WinDef.HWND handle;

    public Win32Object(WinDef.HWND hwnd) {
        this.handle = hwnd;
    }


    public String getWndClass() {
        final User32 usr = User32.INSTANCE;
        char[] iname = new char[1000];
        usr.GetClassName(handle, iname, 1000);
        return Utils.stripName(iname);
    }

    public Rectangle getRectangle() {
        final User32 usr = User32.INSTANCE;
        WinDef.RECT rect = new WinDef.RECT();
        usr.GetWindowRect(handle, rect);

        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.top - rect.bottom);
    }

    public AutomationObject getParent() {
        final User32Ext usr32Ext = User32Ext.INSTANCE;

        return new Win32Object(usr32Ext.GetParent(handle));
    }

    public java.util.List<AutomationObject> getChildItems() {

        final ArrayList<AutomationObject> objects = new ArrayList<AutomationObject>();

        final User32 usr = User32.INSTANCE;

        if (handle != null) {
            usr.EnumChildWindows(handle, new WinUser.WNDENUMPROC() {

                public boolean callback(WinDef.HWND hwnd, Pointer pntr) {
                    objects.add(new Win32Object(hwnd));
                    return true;
                }
            }, null);
        } else {
            usr.EnumWindows(new WinUser.WNDENUMPROC() {

                public boolean callback(WinDef.HWND hwnd, Pointer pntr) {
                    objects.add(new Win32Object(hwnd));
                    return true;
                }
            }, null);
        }

        return objects;
    }
}

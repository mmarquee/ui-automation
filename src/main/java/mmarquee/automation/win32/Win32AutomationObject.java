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
package mmarquee.automation.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import mmarquee.automation.utils.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 19/03/2016.
 */
public class Win32AutomationObject implements AutomationObject {
    public WinDef.HWND handle;

    public Win32AutomationObject(WinDef.HWND hwnd) {
        this.handle = hwnd;
    }

    /**
     * Get the window class of the automation object
     * @return The window class string
     */
    public String getWindowClass() {
        final User32 usr = User32.INSTANCE;
        char[] iname = new char[1000];
        usr.GetClassName(handle, iname, 1000);
        return Native.toString(iname);
    }

    /**
     * Get the text associated with the automation object
     * @return The text
     */
    public String getWindowText() {
        final User32 usr = User32.INSTANCE;
        char[] iname = new char[1000];
        usr.GetWindowText(handle, iname, 1000);

        return Native.toString(iname);
    }

    /**
     * Get the rectangle of the automation object
     * @return The rectangle of the window
     */
    public Rectangle getRectangle() {
        final User32 usr = User32.INSTANCE;
        WinDef.RECT rect = new WinDef.RECT();
        usr.GetWindowRect(handle, rect);

        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.top - rect.bottom);
    }

    /**
     * Get the list of child items for the automation object
     * @return List of automation objects
     */
    public List<AutomationObject> getChildItems() {

        final ArrayList<AutomationObject> objects = new ArrayList<AutomationObject>();

        final User32 usr = User32.INSTANCE;

        if (handle != null) {
            usr.EnumChildWindows(handle, new WinUser.WNDENUMPROC() {

                public boolean callback(WinDef.HWND hwnd, Pointer pntr) {
                    objects.add(new Win32AutomationObject(hwnd));
                    return true;
                }
            }, null);
        } else {
            usr.EnumWindows(new WinUser.WNDENUMPROC() {

                public boolean callback(WinDef.HWND hwnd, Pointer pntr) {
                    objects.add(new Win32AutomationObject(hwnd));
                    return true;
                }
            }, null);
        }

        return objects;
    }

    /**
     * Get the parent of the automation object.
     * @return The parent automation object
     */
    public AutomationObject getParent() {
        final User32Ext usr32Ext = User32Ext.INSTANCE;
        return new Win32AutomationObject(usr32Ext.GetParent(handle));
    }
}

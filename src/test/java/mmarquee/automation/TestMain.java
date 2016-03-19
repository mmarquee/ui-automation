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
package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.stringgrid.AutomationStringGrid;
import mmarquee.automation.controls.stringgrid.AutomationStringGridCell;
import mmarquee.automation.uiautomation.ToggleState;
import mmarquee.automation.utils.User32Ext;
import mmarquee.automation.win32.AutomationObject;
import mmarquee.automation.win32.Win32Object;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestMain {

    private static String stripName(char[] name) {
        int i = 0;
        while (i < name.length && name[i] != '\0') {
            i++;
        }

        char[] name1 = new char[i];
        System.arraycopy(name, 0, name1, 0, i);
        return new String(name1);
    }

    public void dumpWindows() {
        final User32 usr = User32.INSTANCE;

        usr.EnumWindows(new WinUser.WNDENUMPROC() {
            public boolean callback(WinDef.HWND hwnd, Pointer data) {
                char[] name = new char[1000];
                char[] text = new char[1000];

                usr.GetClassName(hwnd, name, 1000);

                int len = usr.GetWindowTextLength(hwnd);

                usr.GetWindowText(hwnd, text, 1000);

                System.err.println("WndClass: "
                        + stripName(name)
                        + " = '"
                        + stripName(text)
                        + "'");

                usr.EnumChildWindows(hwnd, new WinUser.WNDENUMPROC() {

                    public boolean callback(WinDef.HWND hwnd, Pointer pntr) {
                        char[] iname = new char[1000];
                        char[] itext = new char[1000];

                        WinDef.RECT rect = new WinDef.RECT();

                        usr.GetWindowRect(hwnd, rect);

                        usr.GetClassName(hwnd, iname, 1000);
                        usr.GetWindowText(hwnd, itext, 1000);

                        System.err.println("  WndClass: "
                                + stripName(iname)
                                + " = '"
                                + stripName(itext)
                                + "'");

                        return true;
                    }
                }, null);

                return true;
            }
        }, null);

    }

    public void run () {
        UIAutomation automation = new UIAutomation();

        Logger logger = Logger.getLogger(AutomationBase.class.getName());

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            // Smother????
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        try {
            AutomationWindow window = automation.getDesktopWindow("Form1");
            String name = window.name();
            logger.info(name);

            boolean val = window.isModal();

            java.lang.Object rect = window.getBoundingRectangle();

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu();
//		List<AutomationMenuItem> items = menu.getItems();

//		AutomationMenuItem item = items.get(0);
//		item.expand();

//		String name1 = item.name();

            try {
                AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
                exit.click();

                AutomationWindow popup = window.getWindow("Project1");
                Object val111 = popup.getBoundingRectangle();

                AutomationButton btn = popup.getButton("OK");
                Object val11 = btn.getBoundingRectangle();

                boolean val1 = popup.isModal();

                btn.click();
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find menu");
            }

            AutomationTab tab = window.getTab(0);
            tab.selectTabPage("Last Tab");
            //	String tabName = tab.name();

            String text = tab.getEditBox(0).getValue();
            logger.info("Text for editBox1 is " + text);

            AutomationCheckbox check = window.getCheckbox(0);
            check.toggle();

            ToggleState state = check.getToggleState();

            AutomationRadioButton radio = window.getRadioButton(1);
            radio.selectItem();

            AutomationStatusBar statusbar = window.getStatusBar();

            // AutomationTextBox tb0 = statusbar.getTextBox(0);
            AutomationTextBox tb1 = statusbar.getTextBox(1);

            // String eb0Text = statusbar.getTextBox(0).getValue();
            String eb1Text = tb1.getValue();

            logger.info("Statusbar text = " + eb1Text);

            try {
                AutomationComboBox cb1 = window.getCombobox("AutomatedCombobox1");
                cb1.setText("Replacements");
                String txt = cb1.text();

                cb1.getClickablePoint();

                logger.info("Text for AutomatedCombobox1 is " + txt);
            } catch (ElementNotFoundException ex) {

            }

            try {
                AutomationComboBox cb0 = window.getCombobox("AutomatedCombobox2");
                cb0.expand();
                try {
                    cb0.wait(750);
                } catch (Exception ex) {
                    // Time out
                }
                List<AutomationListItem> litems = cb0.getList();
            } catch (ElementNotFoundException ex) {

            }

            // Now string grids
            AutomationStringGrid grid = window.getStringGrid(0, "TAutomationStringGrid");

            AutomationStringGridCell cell1 = grid.getItem(0, 0);

            String itemName = cell1.name();
            logger.info(itemName);
            cell1.setName("This");
            logger.info(cell1.name());

            AutomationTreeView tree = window.getTreeView(0);
            try {
                AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
                treeItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
            } catch (ElementNotFoundException ex) {
                // Not found
            }

            AutomationList list = window.getListItem(0);
            try {
                AutomationListItem listItem = list.getItem("First (List)");
                listItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
            } catch (ElementNotFoundException ex) {
                // Not found
            }

            AutomationHyperlink link = window.getHyperlink(0);
            link.click();
            AutomationWindow popup1 = window.getWindow("Project1");
            try {
                AutomationButton btn1 = popup1.getButton("OK");
                btn1.click();
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find button");
            }

            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                logger.info("Interrupted");
            }

        /* This doesn't seem to work */
            AutomationToolBar toolbar = window.getToolBar(0);
            logger.info(toolbar.name());

            // Looks like the button is a problem with Delphi
            AutomationButton btn0 = toolbar.getButton(0);

            if (btn0.isEnabled()) {
                logger.info("btn0 Enabled");
                btn0.click();
            }

            AutomationButton btn1 = toolbar.getButton(1);

            if (btn1.isEnabled()) {
                logger.info("btn1 Enabled");
                btn1.click();
            }

            AutomationButton btn2 = toolbar.getButton(2);

            if (btn2.isEnabled()) {
                logger.info("btn2 Enabled");
                btn2.click();
            }

            AutomationButton btn3 = toolbar.getButton(3);

            if (btn3.isEnabled()) {
                logger.info("btn3 Enabled");
                btn3.click();
            }

        } catch (ElementNotFoundException ex) {
            logger.info("Element Not Found ");
        }

    }

    public void run2() {

        final User32Ext usr32Ext = User32Ext.INSTANCE;

        // Get the root object (i.e. null)
        Win32Object obj = new Win32Object(null);

        getDesktopWindowsALT(obj);
    }

    public void getDesktopWindowsALT(AutomationObject obj) {
        List<AutomationObject> chld = obj.getChildItems();

        for (AutomationObject ch: chld) {
            System.err.println(((Win32Object)ch).getWndClass());

            List<AutomationObject> chld2 = ch.getChildItems();

            for (AutomationObject ch2: chld2) {

                String cname = ((Win32Object) ch2).getWndClass();

                try {
                    UIAObject uia = ((Win32Object) ch2).toUIAObject();

                    String name = uia.getName();
                    java.awt.Rectangle rect = uia.getBoundingRectangle();

                    System.err.println(cname + " - '" + name + "'");

                } catch (Exception ex) {
                    // Something went wrong
                    System.err.println("Ooops");
                }
            }
        }
    }
}

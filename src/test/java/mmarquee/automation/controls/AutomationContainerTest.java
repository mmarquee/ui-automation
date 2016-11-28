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
package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.ribbon.AutomationRibbonBar;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationContainerTest {
    protected Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }

    private AutomationApplication application = null;
    private AutomationWindow applicationWindow = null;

    private void loadApplication(String appName, String windowName) throws Exception {
        this.rest();

        UIAutomation automation = UIAutomation.getInstance();

        application = automation.launchOrAttach(appName);

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        // Sleep for WPF, to address above issue
        this.rest();

        applicationWindow = automation.getDesktopWindow(windowName);
    }

    private void closeApplication() throws PatternNotFoundException, AutomationException {
        application.quit("Form1");
    }

    @Test
    public void testGetTab() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = applicationWindow.getTab(0);

            String m = tab.name();

            assertTrue(m.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationEditBox tab = applicationWindow.getEditBox(0);

            String m = tab.getValue();

            assertTrue(m.equals("Edit1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore
    public void testGetEditBox_By_Name() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationEditBox tab = applicationWindow.getEditBox("Edit1");

            String m = tab.getValue();

            assertTrue(m.equals("Edit1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = applicationWindow.getDataGrid(0);

            String name = grid.name();
            assertTrue(name.equals("AutomationStringGrid1"));
        } finally {
            closeApplication();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetDataGrid_By_Index_Fails_When_No_Grid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = applicationWindow.getDataGrid(1);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar toolbar = applicationWindow.getToolBar(0);

            String name = toolbar.name();
            assertTrue(name.equals("ToolBar1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRibbonBar() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = applicationWindow.getRibbonBar();

            String name = ribbon.name();

            assertTrue(name.equals("UIRibbonDockTop"));
        } finally {
            closeApplication();
        }
    }
}

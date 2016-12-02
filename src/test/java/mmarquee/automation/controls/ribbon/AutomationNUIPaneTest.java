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
package mmarquee.automation.controls.ribbon;

import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationNUIPaneTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationRibbonWorkPaneTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Is_Blank() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = window.getRibbonBar();

            AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();

            AutomationRibbonWorkPane workPane = commandBar.getRibbonWorkPane();

            AutomationNUIPane uiPane = workPane.getNUIPane(0);

            String name = uiPane.name();

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetNetUIHWND() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = window.getRibbonBar();

            AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();

            AutomationRibbonWorkPane workPane = commandBar.getRibbonWorkPane();

            AutomationNUIPane uiPane = workPane.getNUIPane(0);

            AutomationNetUIHWND uiHWND = uiPane.getNetUIHWND(0);

            logger.info(uiHWND.name());

            assertTrue(uiHWND.name().equals("Ribbon"));
        } finally {
            closeApplication();
        }
    }

}

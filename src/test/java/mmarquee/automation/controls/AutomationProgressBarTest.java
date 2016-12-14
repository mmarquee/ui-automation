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
import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationProgressBarTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationProgressBarTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
//    @Ignore // Fails for some reason
    public void testName_Equals_Blank() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationProgressBar progressBar = window.getProgressBar(0);

            String name = progressBar.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore // Failed to find element
    public void testGetRangeValue() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationProgressBar progressBar = window.getProgressBar(0);

            double value = progressBar.getRangeValue();

            logger.info(value);

            assertTrue(value == 75);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testSetRangeValue_Throws_Exception() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationProgressBar progressBar = window.getProgressBar(0);

            progressBar.setRangeValue(76);

            double value = progressBar.getRangeValue();

            logger.info(value);

            assertTrue(value == 76);
        } finally {
            closeApplication();
        }
    }
}

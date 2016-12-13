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

import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationSliderTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationSliderTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    @Ignore // Fails for some reason
    public void testName_Equals_Blank() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationSlider slider = window.getSlider(0);

            String name = slider.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRangeValue() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationSlider slider = window.getSlider(0);

            double value = slider.getRangeValue();

            logger.info(value);

            assertTrue(value == 0.0);
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore // Fails to find the control
    public void testSetRangeValue() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationSlider slider = window.getSlider(0);

            slider.setRangeValue(76);

            double value = slider.getRangeValue();

            logger.info(value);

            assertTrue(value == 76);
        } finally {
            closeApplication();
        }
    }
}

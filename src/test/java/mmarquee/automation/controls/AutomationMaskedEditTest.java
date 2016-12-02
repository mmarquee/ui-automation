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
 * Created by Mark Humphreys on 02/12/2016.
 */
public class AutomationMaskedEditTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationMaskedEdit.class.getName());

    @Test
    public void testGetValue() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationMaskedEdit me0 = window.getMaskedEdit("AutomatedMaskEdit1");

            String value = me0.getValue();

            logger.info(value);

            assertTrue(value.equals("  /  /  "));
        } finally {
            this.closeApplication();
        }
    }

    @Test
    public void testSetValue() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationMaskedEdit me0 = window.getMaskedEdit("AutomatedMaskEdit1");

            me0.setValue("12/12/99");

            String value = me0.getValue();

            assertTrue(value.equals("12/12/99"));
        } finally {
            this.closeApplication();
        }
    }

}

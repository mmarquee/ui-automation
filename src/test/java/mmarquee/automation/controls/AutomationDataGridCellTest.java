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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Value;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationDataGridCellTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationDataGridCellTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testValue() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);

        when(value.value()).thenReturn("VALUE");

        AutomationDataGridCell cell = new AutomationDataGridCell(element, value);

        String val = cell.value();

        assertTrue(val.equals("VALUE"));
    }

    @Test
    public void testGetCellName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationDataGridCell cell = new AutomationDataGridCell(element, value);

        String val = cell.name();

        assertTrue(val.equals("NAME"));
    }
}
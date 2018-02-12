/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.automation.pattern;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationGridItemPattern;

/**
 * @author Mark Humphreys
 * Date 20/05/2017.
 *
 * Mocked tests for the GridItem pattern
 */
public class GridItemPatternTest {
    @Mock
    AutomationElement element;
    
    @Mock
    IUIAutomationGridItemPattern rawPattern;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.GridItem, PropertyID.IsGridItemPatternAvailable);
    }

    @Test
    public void test_GetColumn_Calls_GetColumn_From_Pattern() throws Exception {
        GridItem pattern = new GridItem(element);
        pattern.rawPattern = rawPattern;

        pattern.getColumn();

        verify(rawPattern, atLeastOnce()).getCurrentColumn(any());
    }

    @Test
    public void test_GetRow_Calls_GetRow_From_Pattern() throws Exception {
        GridItem pattern = new GridItem(element);
        pattern.rawPattern = rawPattern;

        pattern.getRow();

        verify(rawPattern, atLeastOnce()).getCurrentRow(any());
    }

    @Test(expected=AutomationException.class)
    public void test_GetColumn_Throws_Exception_When_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).getCurrentColumn(any());

        GridItem pattern = new GridItem(element);
        pattern.rawPattern = rawPattern;

        Integer value = pattern.getColumn();

        verify(rawPattern, atLeastOnce()).getCurrentColumn(any());
    }

    @Test(expected=AutomationException.class)
    public void test_GetRow_Throws_Exception_When_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).getCurrentRow(any());

        GridItem pattern = new GridItem(element);
        pattern.rawPattern = rawPattern;

        Integer value = pattern.getRow();

        verify(rawPattern, atLeastOnce()).getCurrentRow(any());
    }

}

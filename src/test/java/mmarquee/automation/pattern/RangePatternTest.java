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

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;

/**
 * @author Mark Humphreys
 * Date 11/01/2017.
 *
 * Tests for the Range pattern
 */
@RunWith(MockitoJUnitRunner.class)
public class RangePatternTest {
    @Mock
    IUIAutomationRangeValuePattern rawPattern;
    @Mock
    AutomationElement element;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.RangeValue);
    }
    
    @Test
    public void testsetValue_Calls_setValue_From_Pattern() throws Exception {
        Range pattern = new Range(element);
        pattern.rawPattern = rawPattern;

        pattern.setValue(10.0);

        verify(rawPattern, atLeastOnce()).setValue(10.0);
    }

    @Test(expected=AutomationException.class)
    public void test_setValue_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).setValue(any());

        Range pattern = new Range(element);
        pattern.rawPattern = rawPattern;

        pattern.setValue(10.0);

        verify(rawPattern, atLeastOnce()).setValue(10.0);
    }

    @Test
    public void test_GetValue_Calls_getValue_From_Pattern() throws Exception {
        Range pattern = new Range(element);
        pattern.rawPattern = rawPattern;

        double value = pattern.getValue();

        verify(rawPattern, atLeastOnce()).getValue(any());
    }

    @Test(expected= AutomationException.class)
    public void test_GetValue_Throws_Exception_When_COM_Call_fails() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            DoubleByReference reference = (DoubleByReference)args[0];

            reference.setValue(4.0);

            return 1;
        }).when(rawPattern).getValue(any());

        Range pattern = new Range(element);
        pattern.rawPattern = rawPattern;

        double count = pattern.getValue();

        assertTrue(count == 4);
    }

    @Test
    public void test_GetValue_Gets_Value_From_Pattern() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            DoubleByReference reference = (DoubleByReference)args[0];

            reference.setValue(4.0);

            return 0;
        }).when(rawPattern).getValue(any());

        Range pattern = new Range(element);
        pattern.rawPattern = rawPattern;

        double count = pattern.getValue();

        assertTrue(count == 4);
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Range pattern = new Range(element);

        Range spyPattern = Mockito.spy(pattern);

        IUIAutomationRangeValuePattern mockRange = Mockito.mock(IUIAutomationRangeValuePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        spyPattern.getValue();
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Range pattern = new Range(element);

        Range spyPattern = Mockito.spy(pattern);

        IUIAutomationRangeValuePattern mockRange = Mockito.mock(IUIAutomationRangeValuePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        spyPattern.getValue();

        verify(mockRange, atLeastOnce()).getValue(any());
    }
}

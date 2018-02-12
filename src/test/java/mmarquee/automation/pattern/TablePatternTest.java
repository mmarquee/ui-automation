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

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationTablePattern;
import mmarquee.automation.uiautomation.RowOrColumnMajor;

/**
 * @author Mark Humphreys
 * Date 11/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TablePatternTest {
    @Mock
    AutomationElement element;
    
    @Mock
    IUIAutomationTablePattern rawPattern;

    @Spy
    private Unknown mockUnknown;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.Table);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_ColumnMajor_When_Pattern_Returns_One() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(1);

            return 0;
        }).when(rawPattern).getCurrentRowOrColumnMajor(any());

        Table item = new Table(element);
        item.rawPattern = rawPattern;

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.ColumnMajor);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_RowMajor_When_Pattern_Returns_Zero() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return 0;
        }).when(rawPattern).getCurrentRowOrColumnMajor(any());

        Table item = new Table(element);
        item.rawPattern = rawPattern;

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.RowMajor);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_Indeterminate_When_Pattern_Returns_Three() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(2);

            return 0;
        }).when(rawPattern).getCurrentRowOrColumnMajor(any());

        Table item = new Table(element);
        item.rawPattern = rawPattern;

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.Indeterminate);
    }

    @Test(expected= AutomationException.class)
    public void test_getRowOrColumnMajor_Throws_Exception_When_Pattern_Returns_An_Error() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(2);

            return 1;
        }).when(rawPattern).getCurrentRowOrColumnMajor(any());

        Table item = new Table(element);
        item.rawPattern = rawPattern;

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.Indeterminate);
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getCurrentColumnHeaders_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getCurrentColumnHeaders(any());

        Table pattern = new Table(element);
        pattern.rawPattern = rawPattern;

        List<AutomationElement> elements = pattern.getCurrentColumnHeaders();
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getCurrentRowHeaders_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getCurrentRowHeaders(any());

        Table pattern = new Table(element);
        pattern.rawPattern = rawPattern;

        List<AutomationElement> elements = pattern.getCurrentRowHeaders();
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Table pattern = new Table(element);

        Table spyPattern = Mockito.spy(pattern);

        IUIAutomationTablePattern mockRange = Mockito.mock(IUIAutomationTablePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        spyPattern.getRowOrColumnMajor();

        verify(mockRange, atLeastOnce()).getCurrentRowOrColumnMajor(any());
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Table pattern = new Table(element);

        Table spyPattern = Mockito.spy(pattern);

        IUIAutomationTablePattern mockTable = Mockito.mock(IUIAutomationTablePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockTable)
                .when(spyPattern)
                .convertPointerToInterface(any());

        spyPattern.getRowOrColumnMajor();

        verify(mockTable, atLeastOnce()).getCurrentRowOrColumnMajor(any());
    }

    @Test
    @Ignore("Needs better tests")
    public void test_getColumnsHeaders_Calls_getColumnHeaders_From_Pattern() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Table pattern = new Table(element);

        Table spyPattern = Mockito.spy(pattern);

        IUIAutomationTablePattern mockRange = Mockito.mock(IUIAutomationTablePattern.class);

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArrayInterface(any());

        spyPattern.getCurrentColumnHeaders();

        verify(mockRange, atLeastOnce()).getCurrentColumnHeaders(any());
    }

    @Test
    @Ignore("Needs better tests")
    public void test_getRowHeaders_Calls_getColumnHeaders_From_Pattern() throws Exception {

        doAnswer(invocation ->
                new WinNT.HRESULT(1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Table pattern = new Table(element);

        Table spyPattern = Mockito.spy(pattern);

        IUIAutomationTablePattern mockRange = Mockito.mock(IUIAutomationTablePattern.class);

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArrayInterface(any());

        spyPattern.getCurrentRowHeaders();

        verify(mockRange, atLeastOnce()).getCurrentRowHeaders(any());
    }
}

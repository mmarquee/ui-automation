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

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 09/01/2017.
 *
 * Test for the SelectionItem pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionItemPatternTest {

    @Mock
    IUIAutomationSelectionItemPattern rawPattern;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSelect_Calls_Select_From_Pattern() throws Exception {
        SelectionItem item = new SelectionItem(rawPattern);

        item.select();

        verify(rawPattern, atLeastOnce()).select();
    }

    @Test
    public void testIsSelected_Returns_False_When_COM_Returns_One() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 0;
            }
        }).when(rawPattern).getCurrentIsSelected(anyObject());

        SelectionItem item = new SelectionItem(rawPattern);

        boolean selected = item.isSelected();

        assertFalse(selected);
    }

    @Test
    public void testIsSelected_Returns_True_When_COM_Returns_Zero() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(1);

                return 0;
            }
        }).when(rawPattern).getCurrentIsSelected(anyObject());

        SelectionItem item = new SelectionItem(rawPattern);

        boolean selected = item.isSelected();

        assertTrue(selected);
    }

    @Test(expected=AutomationException.class)
    public void testIsSelected_Throws_Exception_When_COM_Returns_One() throws Exception {
        when(rawPattern.getCurrentIsSelected(anyObject())).thenReturn(1);

        SelectionItem item = new SelectionItem(rawPattern);

        boolean selected = item.isSelected();

        assertFalse(selected);
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        SelectionItem pattern = new SelectionItem();

        SelectionItem spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.select();

        verify(spyPattern, atLeastOnce()).select();
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        SelectionItem pattern = new SelectionItem();

        SelectionItem spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.select();

        verify(spyPattern, atLeastOnce()).select();
    }

    @Test
    public void test_addToSelection_Adds_To_Selection() throws Exception {
        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        SelectionItem pattern = new SelectionItem();

        SelectionItem spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.addToSelection();

        verify(mockPattern, atLeastOnce()).addToSelection();
    }

    @Test
    public void test_removeFromSelection_Calls_Method_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        SelectionItem pattern = new SelectionItem();

        SelectionItem spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.removeFromSelection();

        verify(mockPattern, atLeastOnce()).removeFromSelection();
    }
}

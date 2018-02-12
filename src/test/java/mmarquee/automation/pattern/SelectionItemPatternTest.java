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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;

/**
 * @author Mark Humphreys
 * Date 09/01/2017.
 *
 * Test for the SelectionItem pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionItemPatternTest {

    @Mock
    IUIAutomationSelectionItemPattern rawPattern;

    @Mock
    AutomationElement element;
    
    @Mock
    private Unknown mockUnknown;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSelect_Calls_Select_From_Pattern() throws Exception {
        SelectionItem item = new SelectionItem(element);
        item.rawPattern = rawPattern;

        item.select();

        verify(rawPattern, atLeastOnce()).select();
    }

    @Test
    public void testIsSelected_Returns_False_When_COM_Returns_One() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return 0;
        }).when(rawPattern).getCurrentIsSelected(any(IntByReference.class));

        SelectionItem item = new SelectionItem(element);
        item.rawPattern = rawPattern;

        boolean selected = item.isSelected();

        assertFalse(selected);
    }

    @Test
    public void testIsSelected_Returns_True_When_COM_Returns_Zero() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(1);

            return 0;
        }).when(rawPattern).getCurrentIsSelected(any(IntByReference.class));

        SelectionItem item = new SelectionItem(element);
        item.rawPattern = rawPattern;

        boolean selected = item.isSelected();

        assertTrue(selected);
    }

    @Test(expected=AutomationException.class)
    public void testIsSelected_Throws_Exception_When_COM_Returns_One() throws Exception {
        when(rawPattern.getCurrentIsSelected(any())).thenReturn(1);

        SelectionItem item = new SelectionItem(element);
        item.rawPattern = rawPattern;

        boolean selected = item.isSelected();

        assertFalse(selected);
    }

    @Test(expected=AutomationException.class)
    @Ignore("Fails after mockito upgrade")
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        SelectionItem pattern = new SelectionItem(element);
        pattern.rawPattern = rawPattern;

        SelectionItem spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any(Pointer.class));

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(any(PointerByReference.class));

        spyPattern.select();

        verify(spyPattern, atLeastOnce()).select();
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {
        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        SelectionItem pattern = new SelectionItem(element);
        pattern.rawPattern = mockPattern;

        pattern.select();

        verify(mockPattern, atLeastOnce()).select();
    }

    @Test
    public void test_addToSelection_Adds_To_Selection() throws Exception {
        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        mockPattern.addToSelection();

        verify(mockPattern, atLeastOnce()).addToSelection();
    }

    @Test
    public void test_removeFromSelection_Calls_Method_From_Pattern() throws Exception {
        IUIAutomationSelectionItemPattern mockPattern = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        SelectionItem pattern = new SelectionItem(element);
        pattern.rawPattern = mockPattern;

        pattern.removeFromSelection();

        verify(mockPattern, atLeastOnce()).removeFromSelection();
    }
}

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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationTextPattern;
import mmarquee.automation.uiautomation.IUIAutomationTextRange;
import mmarquee.automation.uiautomation.IUIAutomationTextRangeArray;

/**
 * @author Mark Humphreys
 * Date 11/01/2017.
 *
 * Test for the Text pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class TextPatternTest {
    @Mock
    AutomationElement element;
    
    @Mock
    IUIAutomationTextPattern rawPattern;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getDocumentRange_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getDocumentRange(any());

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        String text = pattern.getText();

        assertTrue(text.equals(""));
    }

    @Test(expected= AutomationException.class)
    public void test_GetSelection_Throws_Exception_When_getSelection_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getSelection(any());

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        String text = pattern.getSelection();

        assertTrue(text.equals(""));
    }

    @Test(expected=AutomationException.class)
    @Ignore("Failures after Mockito upgrade")
    public void test_GetSelection_Throws_Exception_When_Error_Returned() throws Exception {
        doAnswer(invocation -> 0).when(rawPattern).getSelection(any(PointerByReference.class));

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        String text = pattern.getSelection();

        assertTrue(text.equals(""));
    }

    @Test(expected=AutomationException.class)
    public void test_GetSelection_Throws_Exception_When_GetRange_Length_Fails() throws Exception {
        doAnswer(invocation -> 0).when(rawPattern).getSelection(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        Text spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationTextRangeArray mockRangeArray = Mockito.mock(IUIAutomationTextRangeArray.class);

        doAnswer(invocation -> 1).when(mockRangeArray).getLength(any());

        doReturn(mockRangeArray)
                .when(spyPattern)
                .convertPointerToArrayInterface(any());

        String text = spyPattern.getSelection();

        assertTrue(text.equals(""));
    }

    @Test
    public void test_GetSelection_When_() throws Exception {
        doAnswer(invocation -> 0).when(rawPattern).getSelection(any());

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        Text spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationTextRangeArray mockRangeArray = Mockito.mock(IUIAutomationTextRangeArray.class);

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(1);

            return 0;
        }).when(mockRangeArray).getLength(any());

        IUIAutomationTextRange mockRange = Mockito.mock(IUIAutomationTextRange.class);

        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            PointerByReference reference = (PointerByReference)args[1];

            String value = "Hello";
            Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
            pointer.setWideString(0, value);

            reference.setValue(pointer);

            return 0;
        }).when(mockRange).getText(any(), any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        doReturn(mockRangeArray)
                .when(spyPattern)
                .convertPointerToArrayInterface(any());

        String text = spyPattern.getSelection();

        assertTrue(text.equals("Hello"));
    }

    @Test(expected=AutomationException.class)
    public void test_GetText_Throws_Exception_When_QueryInterface_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        Text spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationTextRange mockRange = Mockito.mock(IUIAutomationTextRange.class);

        String text = spyPattern.getText();

        assertTrue(text.equals(""));
    }

    @Test
    public void test_GetText_Calls_getText_From_Pattern() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        Text spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationTextRange mockRange = Mockito.mock(IUIAutomationTextRange.class);

        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            PointerByReference reference = (PointerByReference)args[1];

            String value = "Hello";
            Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
            pointer.setWideString(0, value);

            reference.setValue(pointer);

            return 0;
        }).when(mockRange).getText(any(), any());

//        doReturn(0)
//                .when(mockRange)
//                .getText(any(), any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        String text = spyPattern.getText();

        assertTrue(text.equals("Hello"));
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_GetText_Returns_Error_From_Range() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Text pattern = new Text(element);
        pattern.rawPattern = rawPattern;

        Text spyPattern = Mockito.spy(pattern);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationTextRange mockRange = Mockito.mock(IUIAutomationTextRange.class);

        doReturn(1)
                .when(mockRange)
                .getText(any(), any());

        doReturn(mockRange)
                .when(spyPattern)
                .convertPointerToInterface(any());

        String text = spyPattern.getText();

        assertTrue(text.equals(""));
    }
}

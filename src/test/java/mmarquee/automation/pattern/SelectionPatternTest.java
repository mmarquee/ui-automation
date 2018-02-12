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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPatternConverter;
import mmarquee.automation.uiautomation.IUIAutomationSelectionPattern;

/**
 * @author Mark Humphreys
 * Date 12/01/2017.
 *
 * Tests for the Selection pattern.
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(IUIAutomationSelectionItemPatternConverter.class)
public class SelectionPatternTest {
    @Mock
    IUIAutomationSelectionPattern rawPattern;
    @Mock
    AutomationElement element;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.Selection);
    }

/*
    Mockito.when(rawPattern.getCurrentExpandCollapseState(any())).thenAnswer(
           invocation -> {
               Object[] args = invocation.getArguments();
               IntByReference reference = (IntByReference) args[0];

               reference.setValue(0);

               return 0;
           }
        );
     */

    @Test(expected=AutomationException.class)
    public void test_getCurrentSelection_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getCurrentSelection(any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        pattern.getCurrentSelection();
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Selection pattern = new Selection(element);

        Selection spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionPattern mockPattern = Mockito.mock(IUIAutomationSelectionPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        spyPattern.getCurrentSelection();

        verify(spyPattern, atLeastOnce()).getCurrentSelection();
    }

    @Test
    @Ignore("Needs better tests")
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {
        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Selection pattern = new Selection(element);

        Selection spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionPattern mockPattern = Mockito.mock(IUIAutomationSelectionPattern.class);

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(any());

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArray(any());

        spyPattern.getCurrentSelection();

        verify(spyPattern, atLeastOnce()).getCurrentSelection();
    }

    @Test(expected = AutomationException.class)
    public void test_getSelection_Calls_getCurrentSelection_From_rawPattern_Throws_Exception_When_Error_Returned() throws AutomationException {
        doAnswer(invocation -> -1).when(rawPattern).getCurrentSelection(any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;

        pattern.getSelection();

        Mockito.verify(rawPattern, atLeastOnce()).getCurrentSelection(any());
    }

    @Test
    @Ignore("More a work in progress")
    public void test_getSelection_Calls_getCurrentSelection_From_rawPattern() throws AutomationException {
        Mockito.when(rawPattern.getCurrentSelection(any())).thenAnswer(
                invocation -> 0
        );

        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(element);
        pattern.rawPattern = rawPattern;
		Selection spyPattern = Mockito.spy(pattern);

//        Unknown unk = Mockito.mock(Unknown.class);

  //      Mockito.when(unk.QueryInterface(any(), any())).thenReturn(new WinNT.HRESULT(0));
//
  //      doReturn(unk)
    //            .when(spyPattern)
      //          .makeUnknown(any());

//        List<AutomationElement> list = new ArrayList<>();

  //      doReturn(list)
    //        .when(spyPattern)
      //      .collectionToList(any());

        PowerMockito.mockStatic(IUIAutomationSelectionItemPatternConverter.class);

        IUIAutomationSelectionItemPattern rawPattern
                = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        Mockito.when(IUIAutomationSelectionItemPatternConverter.pointerToInterface(any()))
                .thenReturn(rawPattern);

        spyPattern.getSelection();

//        Mockito.verify(rawPattern, atLeastOnce()).getCurrentSelection(any());
    }
}

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
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 12/01/2017.
 *
 * Tests for the Selection pattern.
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(IUIAutomationSelectionItemPatternConverter.class)
public class SelectionPatternTest {
    @Mock
    IUIAutomationSelectionPattern rawPattern;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

/*
    Mockito.when(rawPattern.getCurrentExpandCollapseState(anyObject())).thenAnswer(
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
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentSelection(anyObject());

        Selection pattern = new Selection(rawPattern);

        pattern.getCurrentSelection();
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Selection pattern = new Selection();

        Selection spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionPattern mockPattern = Mockito.mock(IUIAutomationSelectionPattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.getCurrentSelection();

        verify(spyPattern, atLeastOnce()).getCurrentSelection();
    }

    @Test
    @Ignore("Needs better tests")
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {
        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Selection pattern = new Selection();

        Selection spyPattern = Mockito.spy(pattern);

        IUIAutomationSelectionPattern mockPattern = Mockito.mock(IUIAutomationSelectionPattern.class);

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArray(anyObject());

        spyPattern.getCurrentSelection();

        verify(spyPattern, atLeastOnce()).getCurrentSelection();
    }

    @Test(expected = AutomationException.class)
    public void test_getSelection_Calls_getCurrentSelection_From_rawPattern_Throws_Exception_When_Error_Returned() throws AutomationException {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return -1;
            }
        }).when(rawPattern).getCurrentSelection(anyObject());

        Selection pattern = new Selection(rawPattern);

        pattern.getSelection();

        Mockito.verify(rawPattern, atLeastOnce()).getCurrentSelection(anyObject());
    }

    @Test
    public void test_getSelection_Calls_getCurrentSelection_From_rawPattern() throws AutomationException {
        Mockito.when(rawPattern.getCurrentSelection(anyObject())).thenAnswer(
                invocation -> {
                    return 0;
                }
        );

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

//        Unknown unk = Mockito.mock(Unknown.class);

  //      Mockito.when(unk.QueryInterface(anyObject(), anyObject())).thenReturn(new WinNT.HRESULT(0));
//
  //      doReturn(unk)
    //            .when(spyPattern)
      //          .makeUnknown(anyObject());

//        List<AutomationElement> list = new ArrayList<>();

  //      doReturn(list)
    //        .when(spyPattern)
      //      .collectionToList(anyObject());

        PowerMockito.mockStatic(IUIAutomationSelectionItemPatternConverter.class);

        IUIAutomationSelectionItemPattern rawPattern
                = Mockito.mock(IUIAutomationSelectionItemPattern.class);

        Mockito.when(IUIAutomationSelectionItemPatternConverter.PointerToInterface(anyObject()))
                .thenReturn(rawPattern);

        spyPattern.getSelection();

//        Mockito.verify(rawPattern, atLeastOnce()).getCurrentSelection(anyObject());
    }
}

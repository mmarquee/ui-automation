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

import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationTextPattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by Mark Humphreys on 11/01/2017.
 *
 * Test for the Text pattern.
 */
public class TextPatternTest {
    @Mock
    IUIAutomationTextPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore("Needs more work")
    public void test_GetText_Calls_Toggle_From_Pattern() throws Exception {
        Text pattern = new Text(rawPattern);

        String text = pattern.getText();

        assertTrue(text.equals(""));
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getDocumentRange_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getDocumentRange(anyObject());

        Text pattern = new Text(rawPattern);

        String text = pattern.getText();

        assertTrue(text.equals(""));
    }

    @Test(expected= AutomationException.class)
    public void test_GetSelection_Throws_Exception_When_getSelection_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getSelection(anyObject());

        Text pattern = new Text(rawPattern);

        String text = pattern.getSelection();

        assertTrue(text.equals(""));
    }

}

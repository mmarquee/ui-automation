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

import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class WindowPatternTest {
    @Mock
    IUIAutomationWindowPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_isTopMost_Calls_getCurrentIsTopmost_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        boolean value = pattern.isTopMost();

        verify(rawPattern, atLeastOnce()).getCurrentIsTopmost(anyObject());
    }

    @Test
    public void test_isModal_Calls_getCurrentIsModal_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        boolean value = pattern.isModal();

        verify(rawPattern, atLeastOnce()).getCurrentIsModal(anyObject());
    }
}

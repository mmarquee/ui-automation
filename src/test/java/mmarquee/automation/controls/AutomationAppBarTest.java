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
package mmarquee.automation.controls;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.Ole32Wrapper;
import mmarquee.automation.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

/**
 * Created by Mark Humphreys on 28/12/2016.
 *
 * Tests for the appbar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( { Ole32Wrapper.class })
public class AutomationAppBarTest {
    @Spy
    private Unknown mockUnknown;

    @Test
    public void testGetName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        suppress(method(Ole32Wrapper.class, "createWrapper"));

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(Ole32Wrapper.class);

     //   expect(Ole32Wrapper.()).andReturn(expectedId);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        when(element.getName()).thenReturn("NAME");

        AutomationAppBar ctrl = new AutomationAppBar(element);

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }
}

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

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 25/09/2017
 *
 * Tests for RibbonCommandBar
 */
public class AutomationRibbonCommandBarTest2 {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Test (expected = ElementNotFoundException.class)
    public void testGetRibbonCommandBar_Throws_Exception_When_No_WorkPane_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        when(element.getClassName()).thenReturn(AutomationRibbonCommandBar.CLASS_NAME);
        when(element.findAll(any(), any())).thenReturn(collection);

        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationRibbonCommandBar commandBar = new AutomationRibbonCommandBar(
                new ElementBuilder(element).addPattern(container));

        AutomationRibbonWorkPane workPane = commandBar.getRibbonWorkPane();

        Mockito.verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetRibbonCommandBar_When_WorkPane_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference) args[0];

                    String value = "UIRibbonWorkPane";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() + 1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                }
        );

        collection.add(new AutomationElement(elem));

        when(element.getClassName()).thenReturn(AutomationRibbonCommandBar.CLASS_NAME);
        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationRibbonCommandBar commandBar = new AutomationRibbonCommandBar(
                new ElementBuilder(element));

        AutomationRibbonWorkPane workPane = commandBar.getRibbonWorkPane();

        Mockito.verify(element, atLeastOnce()).findAll(any(), any());
    }
}

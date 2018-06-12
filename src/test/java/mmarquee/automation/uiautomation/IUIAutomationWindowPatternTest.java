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
package mmarquee.automation.uiautomation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import org.apache.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * @author Mark Humphreys
 * Date 20/11/2016.
 *
 * Tests for the IUIAutomationWindowPattern code.
 *
 * Currently all of these tests require to run on Windows.
 */
public class IUIAutomationWindowPatternTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    protected Logger logger = Logger.getLogger(IUIAutomationWindowPatternTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private IUIAutomation automation;

    private IUIAutomationElement getRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            return IUIAutomationElementConverter.pointerToInterface(root);
        } else {
            throw new Exception("Failed to get root element");
        }
    }

    @Before
    public void setUp() throws Exception {
        // Initialise COM
        Ole32.INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_MULTITHREADED);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT hr = Ole32.INSTANCE.CoCreateInstance(
                IUIAutomation.CLSID,
                null,
                WTypes.CLSCTX_SERVER,
                IUIAutomation.IID,
                pbr);

        COMUtils.checkRC(hr);

        Unknown unk = new Unknown(pbr.getValue());

        PointerByReference pbr1 = new PointerByReference();

        WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomation.IID), pbr1);
        if (COMUtils.SUCCEEDED(result)) {
            this.automation = IUIAutomationConverter.pointerToInterface(pbr1);
        }
    }

    private IUIAutomationElement getWindowChildOfRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            IUIAutomationElement rootElement = IUIAutomationElementConverter.pointerToInterface(root);

            Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
            variant.setValue(Variant.VT_INT, ControlType.Window.getValue());

            // Get first descendant for the root element, that is a window
            PointerByReference pCondition = new PointerByReference();
            automation.createPropertyCondition(PropertyID.ControlType.getValue(), variant, pCondition);
            PointerByReference first = new PointerByReference();

            rootElement.findFirst(new TreeScope(TreeScope.Children), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            WinNT.HRESULT res = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), element);

            return IUIAutomationElementConverter.pointerToInterface(element);
        } else {
            throw new Exception("Failed to get root element");
        }
    }

    @Test
    public void testGetWindowPatternFailsForRootElement() throws Exception {
        // Get the pattern
        IUIAutomationElement element = this.getRootElement();

        PointerByReference pbr = new PointerByReference();

        assertFalse("Successfully failed to get window pattern for element",
                element.getCurrentPattern(ControlType.Window.getValue(), pbr) == 0);
    }

    @Test
    @Ignore("This fails for some reason")
    public void testGetWindowPatternSucceedsForWindowElement() throws Exception {
        // Get the pattern
        IUIAutomationElement element = this.getWindowChildOfRootElement();

        PointerByReference pbr = new PointerByReference();

        if (element.getCurrentPattern(ControlType.Window.getValue(), pbr) == 0) {
            fail("Failed to get current pattern");
        }

        Unknown unkConditionA = new Unknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        logger.info("About to query interface");

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationWindowPattern.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationWindowPattern pattern =
                    IUIAutomationWindowPatternConverter.pointerToInterface(pUnknownA);
        } else {
            fail("Failed to get WindowPattern");
        }
    }
}
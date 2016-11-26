/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.ControlType;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 18/10/2016.
 */
public class IUIAutomationElementTest {

    protected Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private IUIAutomation automation;

    private IUIAutomationElement getRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.GetRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            return IUIAutomationElement.Converter.PointerToInterface(root);
        } else {
            throw new Exception("Failed to get root element");
        }
    }

    private IUIAutomationElement getChildOfRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.GetRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            IUIAutomationElement rootElement = IUIAutomationElement.Converter.PointerToInterface(root);

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.CreateTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            rootElement.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            WinNT.HRESULT res = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), element);

            return IUIAutomationElement.Converter.PointerToInterface(element);
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
            this.automation = IUIAutomation.Converter.PointerToInterface(pbr1);
        }
    }

    @Test
    public void testClassNameForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            PointerByReference sr = new PointerByReference();

            if (root.get_CurrentClassName(sr) != 0) {
                assertTrue("Failed to get_CurrentClassName", false);
            }

            String name = sr.getValue().getWideString(0);

            assertTrue(name.equals("#32769"));

        } catch (Throwable error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testNameForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            PointerByReference sr = new PointerByReference();

            if (root.get_CurrentName(sr) != 0) {
                assertTrue("Failed to get_CurrentName", false);
            }

            String name = sr.getValue().getWideString(0);

            assertTrue("CurrentName", name.equals("Desktop"));

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsPasswordForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentIsPassword(ir) != 0) {
                assertTrue("Failed to get_CurrentIsPassword", false);
            }

            int isPassword = ir.getValue();

            assertTrue("CurrentIsPassword", isPassword == 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testGetControlTypeForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentControlType(ir) != 0) {
                assertTrue("Failed to get_CurrentControlType", false);
            }

            int controlType = ir.getValue();

            assertTrue("get_CurrentControlType", controlType == ControlType.Pane.getValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsOffScreenForRootElement() {

        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsOffscreen(br) != 0) {
                assertTrue("Failed to get_CurrentIsOffscreen", false);
            }

            WinDef.BOOL isOffScreen = br.getValue();

            assertFalse("CurrentIsPassword", isOffScreen.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsEnabledForRootElement() {

        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsEnabled(br) != 0) {
                assertTrue("Failed to get_CurrentIsEnabled", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("CurrentIsEnabled", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsContentElementForRootElement() {

        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsContentElement(br) != 0) {
                assertTrue("Failed to get_CurrentIsContentElement", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("get_CurrentIsContentElement", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsControlElementForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsControlElement(br) != 0) {
                assertTrue("Failed to get_CurrentIsControlElement", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("get_CurrentIsControlElement", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testClassNameForNonRootElement() {
        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            PointerByReference sr = new PointerByReference();

            if (root.get_CurrentClassName(sr) != 0) {
                assertTrue("Failed to get_CurrentClassName", false);
            }

            String name = sr.getValue().getWideString(0);

            // Actual name will vary depending on the environment
            assertTrue(!name.isEmpty());

        } catch (Throwable error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testNameForNonRootElementDoesntReturnError() {
        try {
            IUIAutomationElement element = this.getChildOfRootElement();

            PointerByReference sr = new PointerByReference();

            assertTrue("CurrentName", element.get_CurrentName(sr) == 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsPasswordForNonRootElement() {
        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentIsPassword(ir) != 0) {
                assertTrue("Failed to get_CurrentIsPassword", false);
            }

            int isPassword = ir.getValue();

            assertTrue("CurrentIsPassword", isPassword == 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testGetControlTypeForNonRootElement() {
        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentControlType(ir) != 0) {
                assertTrue("Failed to get_CurrentControlType", false);
            }

            int controlType = ir.getValue();

            assertTrue("get_CurrentControlType", controlType == ControlType.Pane.getValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsOffScreenForNonRootElement() {

        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsOffscreen(br) != 0) {
                assertTrue("Failed to get_CurrentIsOffscreen", false);
            }

            WinDef.BOOL isOffScreen = br.getValue();

            assertFalse("CurrentIsPassword", isOffScreen.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsEnabledForNonRootElement() {

        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsEnabled(br) != 0) {
                assertTrue("Failed to get_CurrentIsEnabled", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("CurrentIsEnabled", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsContentElementForNonRootElement() {

        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsContentElement(br) != 0) {
                assertTrue("Failed to get_CurrentIsContentElement", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("get_CurrentIsContentElement", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testIsControlElementForNonRootElement() {
        try {
            IUIAutomationElement root = this.getChildOfRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsControlElement(br) != 0) {
                assertTrue("Failed to get_CurrentIsControlElement", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("get_CurrentIsControlElement", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testFindFirst() {
        try {
            IUIAutomationElement root = this.getRootElement();

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.CreateTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            root.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            WinNT.HRESULT res = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), element);

            IUIAutomationElement elem = IUIAutomationElement.Converter.PointerToInterface(element);

            PointerByReference sr = new PointerByReference();

            if (elem.get_CurrentName(sr) != 0) {
                assertTrue("Failed to get_CurrentName", false);
            }

            String name = sr.getValue().getWideString(0);

            assertTrue("findFirst", !name.equals("Desktop"));

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testFindAllDoes_Not_ReturnError() {
        try {
            IUIAutomationElement root = this.getRootElement();

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.CreateTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            root.findAll(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            WinNT.HRESULT res = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), element);

            IUIAutomationElementArray elements = IUIAutomationElementArray.Converter.PointerToInterface(element);

            IntByReference ibr = new IntByReference();

            assertTrue("findAll", elements.get_Length(ibr) == 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    @Test
    public void testFindAllGetValidList() {
        try {
            IUIAutomationElement root = this.getRootElement();

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.CreateTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            root.findAll(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            WinNT.HRESULT res = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), element);

            IUIAutomationElementArray elements = IUIAutomationElementArray.Converter.PointerToInterface(element);

            IntByReference ibr = new IntByReference();

            elements.get_Length(ibr);

            assertTrue("findAll", ibr.getValue() != 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }
}
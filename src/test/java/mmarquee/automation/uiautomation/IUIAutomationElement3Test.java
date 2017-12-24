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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import org.apache.log4j.Logger;
import org.junit.*;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 01/06/2017.
 *
 * Tests for the IUIAutomationElement3 class
 *
 * Currently all of these tests require to run on Windows.
 */
public class IUIAutomationElement3Test {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    private Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private IUIAutomation automation;

    private IUIAutomationElement3 getRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            return IUIAutomationElement3Converter.pointerToInterface(root);
        } else {
            throw new Exception("Failed to get root element");
        }
    }

    private IUIAutomationElement3 getChildOfRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            IUIAutomationElement rootElement = IUIAutomationElement3Converter.pointerToInterface(root);

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.createTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            rootElement.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            Unknown uElement = new Unknown(first.getValue());

            PointerByReference element = new PointerByReference();

            uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), element);

            return IUIAutomationElement3Converter.pointerToInterface(element);
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

    @Test
    public void testClassNameForRootElement() throws Exception {

        IUIAutomationElement3 root = this.getRootElement();

        PointerByReference sr = new PointerByReference();

        if (root.getCurrentClassName(sr) != 0) {
            fail("Failed to get_CurrentClassName");
        }

        String name = sr.getValue().getWideString(0);

        assertTrue(name.equals("#32769"));
    }

    @Test
    public void testNameForRootElement() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        PointerByReference sr = new PointerByReference();

        if (root.getCurrentName(sr) != 0) {
            fail("Failed to get_CurrentName");
        }

        String name = sr.getValue().getWideString(0);

        logger.info(name);

        assertTrue("CurrentName", name.startsWith("Desktop"));
    }

    @Test
    public void testIsPasswordForRootElement() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        IntByReference ir = new IntByReference();

        if (root.getCurrentIsPassword(ir) != 0) {
            fail("Failed to get_CurrentIsPassword");
        }

        int isPassword = ir.getValue();

        assertTrue("CurrentIsPassword", isPassword == 0);
    }

    @Test
    public void testGetControlTypeForRootElement() throws Exception {

        IUIAutomationElement3 root = this.getRootElement();

        IntByReference ir = new IntByReference();

        if (root.getCurrentControlType(ir) != 0) {
            fail("Failed to get_CurrentControlType");
        }

        int controlType = ir.getValue();

        assertTrue("get_CurrentControlType", controlType == ControlType.Pane.getValue());
    }

    @Test
    public void testIsOffScreenForRootElement() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsOffscreen(br) != 0) {
            fail("Failed to get_CurrentIsOffscreen");
        }

        WinDef.BOOL isOffScreen = br.getValue();

        assertFalse("CurrentIsPassword", isOffScreen.booleanValue());
    }

    @Test
    public void testIsEnabledForRootElement() throws Exception {

        IUIAutomationElement3 root = this.getRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsEnabled(br) != 0) {
            fail("Failed to get_CurrentIsEnabled");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("CurrentIsEnabled", isEnabled.booleanValue());
    }

    @Test
    public void testIsContentElementForRootElement() throws Exception {

        IUIAutomationElement3 root = this.getRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsContentElement(br) != 0) {
            fail("Failed to get_CurrentIsContentElement");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("get_CurrentIsContentElement", isEnabled.booleanValue());
    }

    @Test
    public void testIsControlElementForRootElement() throws Exception {

        IUIAutomationElement3 root = this.getRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsControlElement(br) != 0) {
            fail("Failed to get_CurrentIsControlElement");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("get_CurrentIsControlElement", isEnabled.booleanValue());
    }

    @Test
    public void testClassNameForNonRootElement() throws Exception {
        IUIAutomationElement root = this.getChildOfRootElement();

        PointerByReference sr = new PointerByReference();

        if (root.getCurrentClassName(sr) != 0) {
            fail("Failed to get_CurrentClassName");
        }

        String name = sr.getValue().getWideString(0);

        // Actual name will vary depending on the environment
        assertTrue(!name.isEmpty());
    }

    @Test
    public void testNameForNonRootElementDoesntReturnError() throws Exception {
        IUIAutomationElement3 element = this.getChildOfRootElement();

        PointerByReference sr = new PointerByReference();

        assertTrue("CurrentName", element.getCurrentName(sr) == 0);
    }

    @Test
    public void testIsPasswordForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        IntByReference ir = new IntByReference();

        if (root.getCurrentIsPassword(ir) != 0) {
            fail("Failed to get_CurrentIsPassword");
        }

        int isPassword = ir.getValue();

        assertTrue("CurrentIsPassword", isPassword == 0);
    }

    @Test
    @Ignore("Should probably be mocked")
    public void testGetControlTypeForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        IntByReference ir = new IntByReference();

        if (root.getCurrentControlType(ir) != 0) {
            fail("Failed to get_CurrentControlType");
        }

        int controlType = ir.getValue();

        assertEquals("get_CurrentControlType", ControlType.Pane.getValue(), controlType);
    }

    @Test
    public void testIsOffScreenForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsOffscreen(br) != 0) {
            fail("Failed to get_CurrentIsOffscreen");
        }

        WinDef.BOOL isOffScreen = br.getValue();

        assertFalse("CurrentIsPassword", isOffScreen.booleanValue());
    }

    @Test
    public void testIsEnabledForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsEnabled(br) != 0) {
            fail("Failed to get_CurrentIsEnabled");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("CurrentIsEnabled", isEnabled.booleanValue());
    }

    @Test
    public void testIsContentElementForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsContentElement(br) != 0) {
            fail("Failed to get_CurrentIsContentElement");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("get_CurrentIsContentElement", isEnabled.booleanValue());
    }

    @Test
    public void testIsControlElementForNonRootElement() throws Exception {
        IUIAutomationElement3 root = this.getChildOfRootElement();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        if (root.getCurrentIsControlElement(br) != 0) {
            fail("Failed to get_CurrentIsControlElement");
        }

        WinDef.BOOL isEnabled = br.getValue();

        assertTrue("get_CurrentIsControlElement", isEnabled.booleanValue());
    }

    @Test
    public void testFindFirst() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        // Get first descendant for the root element
        PointerByReference pCondition = new PointerByReference();
        automation.createTrueCondition(pCondition);
        PointerByReference first = new PointerByReference();

        root.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

        Unknown uElement = new Unknown(first.getValue());

        PointerByReference element = new PointerByReference();

        uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), element);

        IUIAutomationElement3 elem = IUIAutomationElement3Converter.pointerToInterface(element);

        PointerByReference sr = new PointerByReference();

        if (elem.getCurrentName(sr) != 0) {
            fail("Failed to get_CurrentName");
        }

        String name = sr.getValue().getWideString(0);

        assertTrue("findFirst", !name.equals("Desktop"));
    }

    @Test
    public void testFindAllDoes_Not_ReturnError() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        // Get first descendant for the root element
        PointerByReference pCondition = new PointerByReference();
        automation.createTrueCondition(pCondition);
        PointerByReference first = new PointerByReference();

        root.findAll(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

        Unknown uElement = new Unknown(first.getValue());

        PointerByReference element = new PointerByReference();

        uElement.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), element);

        IUIAutomationElementArray elements = IUIAutomationElementArrayConverter.pointerToInterface(element);

        IntByReference ibr = new IntByReference();

        assertTrue("findAll", elements.getLength(ibr) == 0);
    }

    @Test
    public void testFindAllGetValidList() throws Exception {
        IUIAutomationElement3 root = this.getRootElement();

        // Get first descendant for the root element
        PointerByReference pCondition = new PointerByReference();
        automation.createTrueCondition(pCondition);
        PointerByReference first = new PointerByReference();

        root.findAll(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

        Unknown uElement = new Unknown(first.getValue());

        PointerByReference element = new PointerByReference();

        uElement.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), element);

        IUIAutomationElementArray elements = IUIAutomationElementArrayConverter.pointerToInterface(element);

        IntByReference ibr = new IntByReference();

        elements.getLength(ibr);

        assertTrue("findAll", ibr.getValue() != 0);
    }

    @Test (expected=AutomationException.class)
    public void testcurrentControlType_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement mockedElement = Mockito.mock(IUIAutomationElement.class);

        when(mockedElement.getCurrentControlType(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getControlType();
    }

    @Test (expected=AutomationException.class)
    public void testcurrentPropertyValue_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentPropertyValue(anyInt(), any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getPropertyValue(PropertyID.ProcessId.getValue());
    }

    @Test (expected=AutomationException.class)
    public void testGetAcceleratorKey_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentAcceleratorKey(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getAcceleratorKey();
    }

    @Test (expected=AutomationException.class)
    public void testGetCurrentProcessId_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentProcessId(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getProcessId();
    }

    @Test (expected=AutomationException.class)
    public void testGetAriaRole_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentAriaRole(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getAriaRole();
    }

    @Test (expected=AutomationException.class)
    public void testCurrentClassName_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement mockedElement = Mockito.mock(IUIAutomationElement.class);

        when(mockedElement.getCurrentClassName(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getClassName();
    }

    @Test (expected=AutomationException.class)
    public void testCurrentIsPassword_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentIsPassword(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.isPassword();
    }

    @Test (expected=AutomationException.class)
    public void testLocalizedControlType_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentLocalizedControlType(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.localizedControlType();
    }

    @Test (expected=AutomationException.class)
    public void testGetOrientationType_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentOrientation(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getOrientation();
    }

    @Test (expected=AutomationException.class)
    public void testGetProviderDescription_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentProviderDescription(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getProviderDescription();
    }

    @Test (expected=AutomationException.class)
    public void testGetCulture_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentCulture(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getCulture();
    }

    @Test (expected=AutomationException.class)
    public void testGetFrameworkId_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentFrameworkId(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getFrameworkId();
    }

    @Test (expected=AutomationException.class)
    public void testGetItemStatus_Fails_When_Element_Call_Fails() throws AutomationException {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);

        when(mockedElement.getCurrentItemStatus(any())).thenReturn(-1);

        AutomationElement element = new AutomationElement(mockedElement);

        element.getItemStatus();
    }
}

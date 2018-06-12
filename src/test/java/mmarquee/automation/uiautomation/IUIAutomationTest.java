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
import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Mark Humphreys
 * Date 13/10/2016.
 *
 * Currently all of these tests require to run on Windows.
 */
public class IUIAutomationTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    protected Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private IUIAutomation automation;

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
    public void testGetRootElement() {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);

        assertTrue("RootElement", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testCompareElementsWhenElementsAreTheSame() {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        IntByReference same = new IntByReference();

        automation.compareElements(root.getValue(), root.getValue(), same);

        assertTrue("Compare", same.getValue() != 0);
    }

    @Test
    public void testCreateTrueCondition() {
        PointerByReference pbr = new PointerByReference();
        automation.createTrueCondition(pbr);

        Unknown uRoot = new Unknown(pbr.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);

        assertTrue("TrueCondition", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testCreateFalseCondition() {
        PointerByReference pbr = new PointerByReference();
        automation.createFalseCondition(pbr);

        Unknown uRoot = new Unknown(pbr.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);

        assertTrue("FalseCondition", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testGetPatternProgrammaticNameForInvoke () {
        PointerByReference sr = new PointerByReference();

        automation.getPatternProgrammaticName(10000, sr);

        String name = sr.getValue().getWideString(0);

        assertTrue("GetPatternProgrammticName", name.equals("InvokePattern"));
    }

    @Test
    public void testGetPatternProgrammaticNameForCustomNavigation () {
        PointerByReference sr = new PointerByReference();

        automation.getPatternProgrammaticName(10033, sr);

        String name = sr.getValue().getWideString(0);

        assertTrue("GetPatternProgrammticName", name.equals("CustomNavigationPattern"));
    }

    @Test
    public void testOrCondition() {
        PointerByReference condition1 = new PointerByReference();
        PointerByReference condition2 = new PointerByReference();
        PointerByReference pbr = new PointerByReference();

        automation.createTrueCondition(condition1);
        automation.createTrueCondition(condition2);

        automation.createOrCondition(condition1.getValue(), condition2.getValue(), pbr);
        Unknown uCondition = new Unknown(pbr.getValue());

        WinNT.HRESULT result = uCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);

        assertTrue("OrCondition", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testAndCondition() {
        PointerByReference condition1 = new PointerByReference();
        PointerByReference condition2 = new PointerByReference();
        PointerByReference pbr = new PointerByReference();

        automation.createTrueCondition(condition1);
        automation.createTrueCondition(condition2);

        automation.createAndCondition(condition1.getValue(), condition2.getValue(), pbr);
        Unknown uCondition = new Unknown(pbr.getValue());

        WinNT.HRESULT result = uCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);

        assertTrue("AndCondition", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testCreateNotCondition() {
        PointerByReference condition = new PointerByReference();
        PointerByReference pbr = new PointerByReference();

        automation.createTrueCondition(condition);

        automation.createNotCondition(condition.getValue(), pbr);
        Unknown uCondition = new Unknown(pbr.getValue());

        WinNT.HRESULT result = uCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);

        assertTrue("NotCondition", COMUtils.SUCCEEDED(result));
    }

    @Test
    public void testElementFromHandle() {
        PointerByReference pbr = new PointerByReference();

        WinDef.HWND hwnd = new WinDef.HWND();

        automation.getElementFromHandle(hwnd, pbr);

        assertTrue("ElementFromHandle", pbr != null);
    }

    @Test
    public void testElementFromHandleGivesRootElement() {
        PointerByReference pbr = new PointerByReference();
        PointerByReference root = new PointerByReference();

        WinDef.HWND hwnd = new WinDef.HWND();

        automation.getElementFromHandle(hwnd, pbr);
        automation.getRootElement(root);

        IntByReference same = new IntByReference();

        automation.compareElements(root.getValue(), pbr.getValue(), same);

        assertTrue("Compare", same.getValue() == 0);
    }

    @Test
    public void testCompareElementsWhenElementsAreDifferent() {
        PointerByReference root = new PointerByReference();
        automation.getRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        IUIAutomationElement rootElement = null;

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            rootElement = IUIAutomationElementConverter.pointerToInterface(root);

            // Get first descendant for the root element
            PointerByReference pCondition = new PointerByReference();
            automation.createTrueCondition(pCondition);
            PointerByReference first = new PointerByReference();

            rootElement.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

            IntByReference same = new IntByReference();

            automation.compareElements(root.getValue(), first.getValue(), same);

            assertTrue("Compare", same.getValue() == 0);
        } else {
            fail("Compare");
        }
    }

    @Test
    public void testGetFocusedElement() {
        PointerByReference pbr = new PointerByReference();

        this.automation.getFocusedElement(pbr);

        assertTrue("GetFocusedElement", pbr.getValue() != null);
    }

/*    This test is wrong, needs fixing
    public void testCreatePropertyCondition() {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("ID");

        try {
            variant.setValue(Variant.VT_BSTR, sysAllocated);

            PointerByReference pbr = new PointerByReference();

            automation.CreatePropertyCondition(0,
                    variant,
                    pbr);

            Unknown uRoot = new Unknown(pbr.getValue());

            WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pbr);
            assertTrue("FalseCondition", COMUtils.SUCCEEDED(result));
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }
    */
}

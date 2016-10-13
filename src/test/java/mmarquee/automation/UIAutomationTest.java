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
package mmarquee.automation;

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import junit.framework.TestCase;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationCondition;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by Mark Humphreys on 19/07/2016.
 */
public class UIAutomationTest extends TestCase {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public void testGetInstance() {
        UIAutomation instance = UIAutomation.getInstance();

        assertTrue("instance:" + instance.toString(), instance != null);
    }

    public void testGetRootElement() throws AutomationException {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentName(), root.currentName().equals("Desktop"));
    }

    public void testCompareElementsWhenTheSame() {
        UIAutomation instance = UIAutomation.getInstance();

        IntByReference same = new IntByReference();

        // The root element
        PointerByReference pRoot = new PointerByReference();

        instance.getRootElement(pRoot);

        instance.compareElements(pRoot.getValue(), pRoot.getValue(), same);

        int value = same.getValue();

        assertTrue("Compare Element:" + value, value != 0);
    }

    public void testCreateFalseCondtion() {
        UIAutomation instance = UIAutomation.getInstance();

        try {
            PointerByReference condition = instance.createFalseCondition();
            PointerByReference first = new PointerByReference();

            Unknown unk = new Unknown(condition.getValue());
            PointerByReference pUnk = new PointerByReference();

            PointerByReference pUnknown1 = new PointerByReference();

            WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

            assertTrue("Create FalseCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
        } catch (AutomationException ex) {
            assertTrue("Ouch", false);
        }
    }

    public void testGetDesktopWindows() throws PatternNotFoundException, AutomationException {
        UIAutomation instance = UIAutomation.getInstance();

        List<AutomationWindow> windows = instance.getDesktopWindows();

        assertFalse("DesktopWindows" + windows.size(), windows.size() == 0);
    }

    public void testCreatePropertyCondition() {
        UIAutomation instance = UIAutomation.getInstance();

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            try {
                PointerByReference pCondition = instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);

                Unknown unk = new Unknown(pCondition.getValue());

                PointerByReference pUnknown1 = new PointerByReference();

                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

                assertTrue("CreatePropertyCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
            } catch (AutomationException ex) {
                assertTrue("Exception", false);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    public void testCreateNotCondition() {
        UIAutomation instance = UIAutomation.getInstance();

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            try {
                // Create first condition to use
                PointerByReference pCondition =
                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);

                // Create the actual condition
                PointerByReference notCondition =
                        instance.createNotCondition(pCondition.getValue());

                // Checking
                Unknown unk = new Unknown(notCondition.getValue());

                PointerByReference pUnknown1 = new PointerByReference();

                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

                assertTrue("CreateNotCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
            } catch (AutomationException ex) {
                assertTrue("Exception", false);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    public void testCreateAndCondition() {
        UIAutomation instance = UIAutomation.getInstance();

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            try {
                // Create first condition to use
                PointerByReference pCondition0 =
                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);

                // Create first condition to use
                PointerByReference pCondition1 =
                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);

                // Create the actual condition
                PointerByReference andCondition =
                        instance.createAndCondition(pCondition0.getValue(), pCondition1.getValue());

                // Checking
                Unknown unk = new Unknown(andCondition.getValue());
                PointerByReference pUnk = new PointerByReference();

                PointerByReference pUnknown1 = new PointerByReference();

                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

                assertTrue("CreateNotCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
            } catch (AutomationException ex) {
                assertTrue("Exception", false);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    public void testCreateTrueCondtion() {
        UIAutomation instance = UIAutomation.getInstance();

        try {
            PointerByReference pCondition = instance.createTrueCondition();
            PointerByReference first = new PointerByReference();

            // Check whether it is a condition

            Unknown unk = new Unknown(pCondition.getValue());
            PointerByReference pUnk = new PointerByReference();

            Guid.REFIID refiid3 = new Guid.REFIID(IUIAutomationCondition.IID);

            PointerByReference pUnknown1 = new PointerByReference();

            WinNT.HRESULT result = unk.QueryInterface(refiid3, pUnknown1);

            assertTrue("Create TrueCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
        } catch (AutomationException ex) {
            assertTrue("Exception", false);
        }
    }

    public void testCompareElementsWhenNotTheSame() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        IntByReference same = new IntByReference();

        // The root element
        PointerByReference pRoot = new PointerByReference();

        instance.getRootElement(pRoot);

        // Get the first descendant of the root element
        AutomationElement root = instance.getRootElement();

        PointerByReference pCondition = instance.createTrueCondition();
        PointerByReference first = new PointerByReference();

        root.element.findFirst(new TreeScope(TreeScope.Descendants), pCondition.getValue(), first);

        instance.compareElements(pRoot.getValue(), first.getValue(), same);

        int value = same.getValue();

        assertTrue("Compare Element:" + value, value != -1);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(UIAutomationTest.class);
    }
}

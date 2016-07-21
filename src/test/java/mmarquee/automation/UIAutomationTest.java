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
import mmarquee.automation.uiautomation.IUIAutomationCondition;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by inpwt on 19/07/2016.
 */
public class UIAutomationTest extends TestCase {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public void testGetInstance() {
        UIAutomation instance = UIAutomation.getInstance();

        assertTrue("instance:" + instance.toString(), instance != null);
    }

    public void testGetRootElement() {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentName(), root.currentName().equals("Desktop"));
    }

    public void testCompareElementsWhenTheSame() {
        UIAutomation instance = UIAutomation.getInstance();

        IntByReference same = new IntByReference();

        // The root element
        PointerByReference pRoot = new PointerByReference();

        instance.automation.GetRootElement(pRoot);

        instance.automation.CompareElements(pRoot.getValue(), pRoot.getValue(), same);

        int value = same.getValue();

        assertTrue("Compare Element:" + value, value != 0);
    }

    public void testCreateFalseCondtion() {
        UIAutomation instance = UIAutomation.getInstance();

        PointerByReference pCondition = new PointerByReference();
        PointerByReference first = new PointerByReference();

        instance.automation.CreateFalseCondition(pCondition);

        Unknown unk = new Unknown(pCondition.getValue());
        PointerByReference pUnk = new PointerByReference();

        Guid.REFIID refiid3 = new Guid.REFIID(IUIAutomationCondition.IID);

        PointerByReference pUnknown1 = new PointerByReference();

        WinNT.HRESULT result = unk.QueryInterface(refiid3, pUnknown1);

        assertTrue("Create FalseCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
    }

    public void testGetDesktopWindows() throws AutomationException {
        UIAutomation instance = UIAutomation.getInstance();

        List<AutomationWindow> windows = instance.getDesktopWindows();

        assertFalse("DesktopWindows" + windows.size(), windows.size() == 0);
    }

    public void testCreatePropertyCondition() {
        UIAutomation instance = UIAutomation.getInstance();

        PointerByReference pCondition = new PointerByReference();

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            instance.automation.CreatePropertyCondition(PropertyID.AutomationId.getValue(), variant, pCondition);

            Unknown unk = new Unknown(pCondition.getValue());
            PointerByReference pUnk = new PointerByReference();

            Guid.REFIID refiid3 = new Guid.REFIID(IUIAutomationCondition.IID);

            PointerByReference pUnknown1 = new PointerByReference();

            WinNT.HRESULT result = unk.QueryInterface(refiid3, pUnknown1);

            assertTrue("Create FalseCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    public void testCreateTrueCondtion() {
        UIAutomation instance = UIAutomation.getInstance();

        PointerByReference pCondition = new PointerByReference();
        PointerByReference first = new PointerByReference();

        instance.automation.CreateTrueCondition(pCondition);

        // Check whether it is a condition

        Unknown unk = new Unknown(pCondition.getValue());
        PointerByReference pUnk = new PointerByReference();

        Guid.REFIID refiid3 = new Guid.REFIID(IUIAutomationCondition.IID);

        PointerByReference pUnknown1 = new PointerByReference();

        WinNT.HRESULT result = unk.QueryInterface(refiid3, pUnknown1);

        assertTrue("Create TrueCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
    }

    public void testCompareElementsWhenNotTheSame() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        IntByReference same = new IntByReference();

        // The root element
        PointerByReference pRoot = new PointerByReference();

        instance.automation.GetRootElement(pRoot);

        // Get the first descendant of the root element
        AutomationElement root = instance.getRootElement();

        PointerByReference pCondition = new PointerByReference();
        PointerByReference first = new PointerByReference();

        instance.automation.CreateTrueCondition(pCondition);

        root.element.findFirst(new TreeScope(TreeScope.TreeScope_Descendants), pCondition.getValue(), first);

        instance.automation.CompareElements(pRoot.getValue(), first.getValue(), same);

        int value = same.getValue();

        assertTrue("Compare Element:" + value, value != -1);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(UIAutomationTest.class);
    }
}

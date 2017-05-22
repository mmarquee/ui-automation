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
package mmarquee.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import mmarquee.automation.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationCondition;
import mmarquee.automation.uiautomation.TreeScope;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Mark Humphreys on 19/07/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Utils.class })
public class UIAutomationTest extends BaseAutomationTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
	}

    @Test
    public void testGetInstance() {
        UIAutomation instance = UIAutomation.getInstance();

        assertTrue("instance:" + instance.toString(), instance != null);
    }

    @Test
    public void testGetRootElement() throws AutomationException {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentName(), root.currentName().startsWith("Desktop"));
    }

    @Test
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

    @Test
    public void testCreateFalseCondtion() {
        UIAutomation instance = UIAutomation.getInstance();

        try {
            PointerByReference condition = instance.createFalseCondition();

            Unknown unk = new Unknown(condition.getValue());

            PointerByReference pUnknown1 = new PointerByReference();

            WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

            assertTrue("Create FalseCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
        } catch (AutomationException ex) {
            assertTrue("Ouch", false);
        }
    }

    @Test
    public void testGetDesktopWindows() throws PatternNotFoundException, AutomationException {
        UIAutomation instance = UIAutomation.getInstance();

        List<AutomationWindow> windows = instance.getDesktopWindows();

        assertFalse("DesktopWindows" + windows.size(), windows.size() == 0);
    }

    @Test
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

    @Test
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

    @Test
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

                assertTrue("CreateAndCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
            } catch (AutomationException ex) {
                assertTrue("Exception", false);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    @Test
    public void testCreateOrCondition() {
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
                PointerByReference condition =
                        instance.createOrCondition(pCondition0.getValue(), pCondition1.getValue());

                // Checking
                Unknown unk = new Unknown(condition.getValue());
                PointerByReference pUnk = new PointerByReference();

                PointerByReference pUnknown1 = new PointerByReference();

                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);

                assertTrue("CreateOrCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
            } catch (AutomationException ex) {
                assertTrue("Exception", false);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    @Test
    public void testCreateTrueCondition() {
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

    @Test
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

    @Test
    public void testLaunch_Fails_When_No_executable() throws IOException {
        UIAutomation instance = UIAutomation.getInstance();

        PowerMockito.mockStatic(Utils.class);

        Mockito.when(Utils.startProcess("notepad99.exe")).thenThrow(java.io.IOException.class);

        try {
            instance.launch("notepad99.exe");
        } catch (Throwable ex) {
            assertTrue("textLaunch succeeded somehow", true);
        }

        assertFalse("textLaunch succeeded somehow", false);
    }

    @Test
    public void testLaunch_Succeeds_When_executable() {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationApplication app = null;

        try {
            try {
                app = instance.launch("notepad.exe");
            } catch (Throwable ex) {
                assertTrue("textLaunch succeeded somehow", true);
            }

            assertFalse("textLaunch succeeded somehow", false);
        } finally {
            app.quit(getLocal("notepad.title"));
        }
    }

    @Test
    public void testCreateNamePropertyCondition() {
        UIAutomation instance = UIAutomation.getInstance();

        try {
            // Create first condition to use
            PointerByReference condition =
                    instance.createNamePropertyCondition("ID");
        } catch (AutomationException ex) {
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public void testCreateAutomationIdPropertyCondition_Does_Not_Throw_Exception() {
        UIAutomation instance = UIAutomation.getInstance();

        try {
            // Create first condition to use
            PointerByReference condition =
                    instance.createAutomationIdPropertyCondition("ID");
        } catch (AutomationException ex) {
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public void testCreateControlTypeCondition_Does_Not_Throw_Exception() {
        UIAutomation instance = UIAutomation.getInstance();;

        try {
            // Create first condition to use
            PointerByReference condition =
                    instance.createControlTypeCondition(ControlType.Button);
        } catch (AutomationException ex) {
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public void testLaunchOrAttach_Fails_When_No_executable() {
        UIAutomation instance = UIAutomation.getInstance();

        boolean failure = false;

        try {
            instance.launchOrAttach("notepad99.exe");
        } catch (Throwable e) {
            failure = true;
        }

        assertTrue("Should have failed", failure);
    }

    @Test
    public void testLaunchOrAttach_Succeeds_When_Not_Running() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        instance.launchOrAttach("notepad.exe");
    }

    @Test
    public void testLaunchOrAttach_Succeeds_When_Already_Running() throws Exception {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationApplication app = instance.launch("notepad.exe");

        try {
            this.andRest();

            AutomationApplication launched = instance.launchOrAttach("notepad.exe");

            assertTrue("Should be the same name", launched.name().equals(app.name()));

        } finally {
            app.quit(getLocal("notepad.title"));
        }
    }

    @Test
    public void testCreateTrueCondition_Succeeds_When_Automation_Returns_True()
            throws AutomationException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createTrueCondition(isA(PointerByReference.class))).thenReturn(0);

        UIAutomation instance = new UIAutomation(mocked_automation);

        instance.createTrueCondition();
    }

    @Test(expected = AutomationException.class)
    public void testCreateTrueCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createTrueCondition(isA(PointerByReference.class))).thenReturn(-1);

        UIAutomation local_instance = new UIAutomation(mocked_automation);

        local_instance.createTrueCondition();
    }

    @Test(expected = AutomationException.class)
    public void testCreateNotCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createNotCondition(anyObject(), anyObject())).thenReturn(-1);

        UIAutomation local_instance = new UIAutomation(mocked_automation);

        PointerByReference pbr = new PointerByReference();

        local_instance.createNotCondition(pbr.getValue());
    }

    @Test(expected = AutomationException.class)
    public void testCreateFalseCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        when(mocked_automation.createFalseCondition(isA(PointerByReference.class))).thenReturn(-1);

        UIAutomation local_instance = new UIAutomation(mocked_automation);

        local_instance.createFalseCondition();
    }

    @Test(expected = AutomationException.class)
    public void testCreateAndCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked = Mockito.mock(IUIAutomation.class);

        when(mocked.createAndCondition(any(Pointer.class), any(Pointer.class), any(PointerByReference.class))).thenReturn(-1);

        UIAutomation instanceWithMocking = new UIAutomation(mocked);

        instanceWithMocking.createAndCondition(new PointerByReference().getValue(),
                new PointerByReference().getValue());
    }

    @Test(expected = AutomationException.class)
    public void testCreateOrCondition_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomation mocked = Mockito.mock(IUIAutomation.class);

        when(mocked.createOrCondition(any(Pointer.class), any(Pointer.class), any(PointerByReference.class))).thenReturn(-1);

        UIAutomation instanceWithMocking = new UIAutomation(mocked);

        instanceWithMocking.createOrCondition(new PointerByReference().getValue(),
                new PointerByReference().getValue());
    }

    @Test
    public void test_GetDesktopWindows()
            throws IOException, AutomationException, PatternNotFoundException {
        UIAutomation instance = UIAutomation.getInstance();

        List<AutomationWindow> items = instance.getDesktopWindows();

        assertTrue(items.size() != 0);
    }

    @Test (expected = ItemNotFoundException.class)
    public void test_GetDesktopMenu_Throws_Exception_When_Not_Found()
            throws IOException, AutomationException, PatternNotFoundException {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationApplication app = instance.launch("notepad.exe");

        try {
            AutomationMenu menu = instance.getDesktopMenu("Menu");
        } finally {
            app.quit(getLocal("notepad.title"));
        }
    }

    @Test
    public void testGetDesktopWindow_Succeeds_When_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationApplication app = instance.launch("notepad.exe");

        try {
            AutomationWindow window = instance.getDesktopWindow(getLocal("notepad.title"));

            assertTrue("Should be the same name", window.name().equals(getLocal("notepad.title")));
        } finally {
            app.quit(getLocal("notepad.title"));
        }
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetDesktopWindow_Fails_When_Not_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
        UIAutomation instance = UIAutomation.getInstance();
        instance.getDesktopWindow("Untitled - Notepad99");
    }

    @Test(expected=AutomationException.class)
    public void testGetDesktopObject_Fails_When_Window_Not_Present() throws IOException, AutomationException, PatternNotFoundException {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationApplication app = instance.launch("notepad.exe");

        try {
            AutomationWindow window = instance.getDesktopObject("xxUntitled - Notepad");

            assertTrue("Should be the same name", window.name().equals(getLocal("notepad.title")));
        } finally {
            app.quit(getLocal("notepad.title"));
        }
    }
}

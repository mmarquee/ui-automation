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

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

/**
 * Created by Mark Humphreys on 20/07/2016.
 */
public class AutomationElementTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private UIAutomation instance;

    @Before
    public void setUp() throws Exception {
        instance = UIAutomation.getInstance();
    }

    @Test
    public void testGetCurrentName() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentName(), root.currentName().equals("Desktop"));
    }

    @Test
    public void testGetClassName() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentClassName(), root.currentClassName().equals("#32769"));
    }

    @Test
    public void testIsPassword() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsPassword(), !root.currentIsPassword());
    }

    @Test
    public void testIsControlElement() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsControlElement(), root.currentIsControlElement().booleanValue());
    }

    @Test
    public void testIsContentElement() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsContentElement(), root.currentIsContentElement().booleanValue());
    }

    @Test
    public void testIsOffScreen() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentOffScreen(), !root.currentOffScreen().booleanValue());
    }

    @Test
    public void testIsEnabled() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsEnabled(), root.currentIsEnabled().booleanValue());
    }

    @Test
    public void testLocalizedControlType() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.localizedControlType(), root.localizedControlType().equals("pane"));
    }

    @Test
    public void testCurrentControlType() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentControlType(), root.currentControlType() == ControlType.Pane.getValue());
    }

    @Test
    public void testFrameworkID() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.getFrameworkId(), root.getFrameworkId().equals("Win32"));
    }

    @Test
    public void testNameForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getName(), root.getName().equals("Desktop"));
    }

    @Test
    public void testAriaRoleForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getAriaRole(), root.getAriaRole().equals(""));
    }

    @Test
    public void testOrientationForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getOrientation(), root.getOrientation() == OrientationType.None);
    }

    @Test
    public void testItemStatusForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.getItemStatus(), root.getItemStatus().equals(""));
    }

    @Test
    public void testFindFirst() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        PointerByReference condition = instance.createTrueCondition();

        AutomationElement element = root.findFirst(new TreeScope(TreeScope.Descendants), condition);

        assertTrue("root:" + element.currentName(), !element.currentName().equals(root.currentName()));
    }

    @Test
    public void testFindAll() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        PointerByReference condition = instance.createTrueCondition();

        List<AutomationElement> elements = root.findAll(new TreeScope(TreeScope.Descendants), condition.getValue());

        assertTrue("findAll:" + elements.size(), elements.size() != 0);
    }

    @Test
    public void testProviderDescriptionForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getProviderDescription(), !root.getProviderDescription().equals(""));
    }

    @Test
    public void testgetProcessIdForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getProcessId(), root.getProcessId() != -1);
    }

    @Test
    public void testgetAcceleratorKeyForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.getAcceleratorKey(), root.getAcceleratorKey().equals(""));
    }

    @Test
    public void testGetClickablePointForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        WinDef.POINT empty = new WinDef.POINT(0, 0);

        assertTrue("root:" + root.getClickablePoint(), root.getClickablePoint().dataEquals(empty));
    }

    @Test
    public void testGetCurrentBoundingRectangleForDesktop() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        WinDef.RECT empty = new WinDef.RECT();

        assertTrue("root:" + root.getCurrentBoundingRectangle(), !root.getCurrentBoundingRectangle().dataEquals(empty));
    }

    @Test
    public void testCurrentPropertyValue_Suceeds_When_No_Error() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        Object value = root.currentPropertyValue(PropertyID.FrameworkId.getValue());

        assertTrue(!value.toString().isEmpty());
    }

    /*
    currentPropertyValue
    getPattern
    setFocus
    showContextMenu
     */

    @Test(expected = AutomationException.class)
    public void testGetClickablePoint_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

        when(mocked.getClickablePoint(isA(WinDef.POINT.ByReference.class),isA(WinDef.BOOLByReference.class))).thenReturn(-1);

        WinDef.POINT point = new WinDef.POINT();

        AutomationElement element = new AutomationElement(mocked);

        point = element.getClickablePoint();
    }

    @Test(expected = AutomationException.class)
    public void testGetCurrentBoundingRectangle_Throws_Exception_When_Automation_Returns_False()
            throws AutomationException {
        IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

        when(mocked.getCurrentBoundingRectangle(isA(WinDef.RECT.class))).thenReturn(-1);

        WinDef.RECT rect = new WinDef.RECT();

        AutomationElement element = new AutomationElement(mocked);

        rect = element.getCurrentBoundingRectangle();
    }

}
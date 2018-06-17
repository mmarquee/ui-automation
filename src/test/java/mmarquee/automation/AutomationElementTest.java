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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 20/07/2016.
 *
 * Tests for the AutomationElement class behaviour.
 *
 * Currently all of these tests require to run on Windows.
 */
public class AutomationElementTest extends BaseAutomationTest {
	static {
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
	}

	private UIAutomation instance;

	@BeforeClass
	public static void checkOs() throws Exception {
		Assume.assumeTrue(isWindows());
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}

	@Before
	public void setUp() throws Exception {
		instance = UIAutomation.getInstance();
    }

	@Test
	public void testGetCurrentName() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.currentName(), root.currentName().startsWith("Desktop"));
	}

	@Test
	public void testGetFullDescription() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.getFullDescription(), root.getFullDescription().equals("Not set"));
	}

	@Test
	public void testGetClassName() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.getClassName(), root.getClassName().equals("#32769"));
	}

    @Test
    @Ignore("Needs better mocking")
    public void testGetFullDescription_Mocked() throws AutomationException {
        // Using mock since desktop does not provide an automation ID
        AutomationElement element = getMocketAutomationElement6();

        when(element.getElement6().getCurrentFullDescription(any()))
                .thenAnswer(answerWithSetPointerReferenceToWideString("MyCurrentFullDescription"));

        assertEquals("MyCurrentFullDescription", element.getFullDescription());
    }

	@Test
	public void testGetAutomationId() throws AutomationException {
		// Using mock since desktop does not provide an automation ID
		AutomationElement element = getMocketAutomationElement();
		
        when(element.getElement().getCurrentAutomationId(any()))
        .thenAnswer(answerWithSetPointerReferenceToWideString("myAutomationId"));

        assertEquals("myAutomationId", element.getAutomationId());
	}
	
	@Test
	public void testIsPassword() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.isPassword(), !root.isPassword());
	}

	@Test
	public void testIsControlElement() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.isControlElement(), root.isControlElement().booleanValue());
	}

	@Test
	public void testIsContentElement() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.isContentElement(), root.isContentElement().booleanValue());
	}

	@Test
	public void testIsOffScreen() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.offScreen(), !root.offScreen().booleanValue());
	}

	@Test
	public void testIsEnabled() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.isEnabled(), root.isEnabled().booleanValue());
	}

	@Test
	public void testLocalizedControlType() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertEquals(getLocal("pane"), root.localizedControlType());
	}

	@Test
	public void testCurrentControlType() throws AutomationException {
		AutomationElement root = instance.getRootElement();

		assertTrue("root:" + root.getControlType(), root.getControlType() == ControlType.Pane.getValue());
	}

	@Test
	public void testFrameworkID() throws AutomationException {
		AutomationElement root = instance.getRootElement();

		assertTrue("root:" + root.getFrameworkId(), root.getFrameworkId().equals("Win32"));
	}

	@Test
	public void testNameForDesktop() throws AutomationException {
		AutomationElement root = instance.getRootElement();
		assertTrue("root:" + root.getName(), root.getName().startsWith("Desktop"));
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

		List<AutomationElement> elements = root.findAll(new TreeScope(TreeScope.Descendants), condition);

		assertTrue("findAll:" + elements.size(), elements.size() != 0);
	}

	@Test
	public void testFindAllDescendants() throws AutomationException {
		AutomationElement root = instance.getRootElement();

		PointerByReference condition = instance.createTrueCondition();

		List<AutomationElement> elements = root.findAllDescendants(condition);

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

		assertTrue("root:" + root.getBoundingRectangle(), !root.getBoundingRectangle().dataEquals(empty));
	}

	@Test
	public void testCurrentPropertyValue_Succeeds_When_No_Error() throws AutomationException {
		AutomationElement root = instance.getRootElement();

		Object value = root.getPropertyValue(PropertyID.FrameworkId.getValue());

		assertTrue(!value.toString().isEmpty());
	}

	/*
	 * currentPropertyValue getPattern setFocus showContextMenu
	 */

	@Test(expected = AutomationException.class)
	public void testGetClickablePoint_Throws_Exception_When_Automation_Returns_False() throws AutomationException {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getClickablePoint(isA(WinDef.POINT.ByReference.class), isA(WinDef.BOOLByReference.class)))
				.thenReturn(-1);

		WinDef.POINT point = new WinDef.POINT();

		AutomationElement element = new AutomationElement(mocked);

		element.getClickablePoint();
	}

	@Test(expected = AutomationException.class)
	public void testGetCurrentBoundingRectangle_Throws_Exception_When_Automation_Returns_False()
			throws AutomationException {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentBoundingRectangle(isA(WinDef.RECT.class))).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.getBoundingRectangle();
	}

	@Test
	public void test_SetFocus_Calls_SetFocus_From_Element() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);
		AutomationElement element = new AutomationElement(mocked);

		element.setFocus();

		verify(mocked, atLeastOnce()).setFocus();
	}

	@Test(expected = AutomationException.class)
	public void test_GetPattern_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentPattern(any(), any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.getPattern(1);

		verify(mocked, atLeastOnce()).getCurrentPattern(any(), any());
	}

	@Test
	public void test_GetPattern_Calls_GetCurrentPattern_From_ELement() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);
		AutomationElement element = new AutomationElement(mocked);

		element.getPattern(1);

		verify(mocked, atLeastOnce()).getCurrentPattern(any(), any());
	}

	@Test(expected = AutomationException.class)
	public void test_GetName_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentName(any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.getName();

		verify(mocked, atLeastOnce()).getCurrentName(any());
	}

	@Test(expected = AutomationException.class)
	public void test_currentIsContentElement_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentIsContentElement(any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.isContentElement();

		verify(mocked, atLeastOnce()).getCurrentIsContentElement(any());
	}

	@Test(expected = AutomationException.class)
	public void test_currentIsControlElement_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentIsControlElement(any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.isControlElement();

		verify(mocked, atLeastOnce()).getCurrentIsControlElement(any());
	}

	@Test(expected = AutomationException.class)
	public void test_currentOffScreen_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentIsOffscreen(any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.offScreen();

		verify(mocked, atLeastOnce()).getCurrentIsOffscreen(any());
	}

	@Test(expected = AutomationException.class)
	public void test_currentIsEnabled_Throws_Exception_When_Element_Returns_Error() throws Exception {
		IUIAutomationElement mocked = Mockito.mock(IUIAutomationElement.class);

		when(mocked.getCurrentIsEnabled(any())).thenReturn(-1);

		AutomationElement element = new AutomationElement(mocked);

		element.isEnabled();

		verify(mocked, atLeastOnce()).getCurrentIsEnabled(any());
	}
}
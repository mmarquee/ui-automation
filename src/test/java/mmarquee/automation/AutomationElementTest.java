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

import junit.framework.TestCase;

/**
 * Created by inpwt on 20/07/2016.
 */
public class AutomationElementTest extends TestCase {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private UIAutomation instance;

    protected void setUp() throws Exception {
        instance = UIAutomation.getInstance();
    }

    public void testGetCurrentName() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentName(), root.currentName().equals("Desktop"));
    }

    public void testGetClassName() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentClassName(), root.currentClassName().equals("#32769"));
    }

    public void testIsPassword() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsPassword(), !root.currentIsPassword());
    }

    public void testIsControlElement() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsControlElement(), root.currentIsControlElement().booleanValue());
    }

    public void testIsContentElement() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsContentElement(), root.currentIsContentElement().booleanValue());
    }

    public void testIsOffScreen() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentOffscreen(), !root.currentOffscreen().booleanValue());
    }

    public void testIsEnabled() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.currentIsEnabled(), root.currentIsEnabled().booleanValue());
    }

    public void testLocalizedControlType() throws AutomationException {
        AutomationElement root = instance.getRootElement();
        assertTrue("root:" + root.localizedControlType(), root.localizedControlType().equals("pane"));
    }

    public void testCurrentControlType() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentControlType(), root.currentControlType() == ControlType.Pane.getValue());
    }

    public void testFrameworkID() throws AutomationException {
        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.getFrameworkId(), root.getFrameworkId().equals("Win32"));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AutomationElementTest.class);
    }
}
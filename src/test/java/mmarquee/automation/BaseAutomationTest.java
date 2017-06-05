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

import java.util.ResourceBundle;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;

/**
 * Created by Mark Humphreys on 29/11/2016.
 *
 * Tests for the BaseAutomation claess.
 */
public class BaseAutomationTest {
	
	private static ResourceBundle locals = ResourceBundle.getBundle("locals");
	static {
		UIAutomation.FIND_DESKTOP_ATTEMPTS = 2; // speed up tests
	}

    private AutomationApplication application;
    protected AutomationWindow window;
    private String windowName;

    protected void andRest() {
        // Must be a better way of doing this????
        try {
            Thread.sleep(500);
        } catch (Throwable ex) {
            // interrupted
        }
    }

    protected void loadApplication(String appName, String windowName) throws Exception {
        this.andRest();

        this.windowName = windowName;

        UIAutomation automation = UIAutomation.getInstance();

        application = automation.launchOrAttach(appName);

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        // Sleep for WPF, to address above issue
        this.andRest();

        window = automation.getDesktopWindow(windowName);
    }

    protected void closeApplication() throws PatternNotFoundException, AutomationException {
        application.close(this.windowName);
    }

	/**
	 * Get the localized String for a given key
	 * @param key the key to get the string for
	 * @return the localized string;
	 */
	protected static String getLocal(String key) {
		return locals.getString(key);
	}

	protected AutomationElement getMocketAutomationElement() {
        IUIAutomationElement3 mockedElement = Mockito.mock(IUIAutomationElement3.class);
        return new AutomationElement(mockedElement);
	}
	
	protected Answer<Integer> answerWithSetPointerReferenceToWideString(final String expectedString) {
		return new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
              PointerByReference pr = invocation.getArgumentAt(0, PointerByReference.class);
              Pointer m = new Memory(Native.WCHAR_SIZE * (expectedString.length() + 1));
              m.setWideString(0, expectedString);
              pr.setValue(m);
              return 0;
            }
          };
	}

}

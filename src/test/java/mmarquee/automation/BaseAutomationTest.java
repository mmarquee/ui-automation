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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ResourceBundle;

import org.junit.After;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.BasePattern;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;
import mmarquee.automation.utils.Utils;
import mmarquee.automation.utils.providers.PatternProvider;

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
              Object[] args = invocation.getArguments();
              PointerByReference pr = (PointerByReference)args[0];

              Pointer m = new Memory(Native.WCHAR_SIZE * (expectedString.length() + 1));
              m.setWideString(0, expectedString);
              pr.setValue(m);
              return 0;
            }
          };
	}


    @After
    public void cleanUp() {
    	
    	while (true) {
    		WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, getLocal("notepad.title"));
    		if (hwnd == null) {
    			break;
    		}
            Utils.closeProcess(hwnd);
    	}
    }
    

    /***************************************
     * Special Matchers & Helpers
     ***************************************/

    public static void setElementClassName(IUIAutomationElement3 elem, String className) {
		answerStringByReference(className).when(elem).getCurrentClassName(any());
	}

	public static void setElementCurrentName(IUIAutomationElement3 elem, String name) {
		answerStringByReference(name).when(elem).getCurrentName(any());
	}

	private static Stubber answerStringByReference(String value) {
		return doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        });
	}
	
	public static void setElementPropertyValue(IUIAutomationElement3 elem, PropertyID property, int vartype, Object propertyValue) {
		doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                Variant.VARIANT.ByReference reference = (Variant.VARIANT.ByReference)args[1];

				reference.setValue(vartype, propertyValue);

                return 0;
            }
        })
        .when(elem)
        .getCurrentPropertyValue(eq(property.getValue()),any());
	}
	
	public static TreeScope isTreeScope(int expectedValue) {
		return argThat(new TreeScopeMatcher(expectedValue));
	}
	
    static class TreeScopeMatcher implements ArgumentMatcher<TreeScope> {
    	final int expectedValue;
    	
    	TreeScopeMatcher(int expectedValue) {
    		this.expectedValue = expectedValue;
    	}
    	
        public boolean matches(TreeScope list) {
            return list.value == expectedValue;
        }
    }

    static abstract class PointerByReferenceWithPattern extends PointerByReference implements PatternProvider { 	
    }

	static public void setPatternForElementMock(AutomationElement automationElementMock, PatternID patternId,
			PropertyID patternAvailablePropertyID, BasePattern pattern) throws AutomationException {
		when(automationElementMock.getPropertyValue(patternAvailablePropertyID.getValue())).thenReturn(1);
        
		when(automationElementMock.getPattern(patternId.getValue())).thenReturn(new PointerByReferenceWithPattern(){
			@Override
			public BasePattern getPattern() {
				return pattern;
			}});
	}
	
	static public ExpandCollapse mockExpandCollapsePattern(AutomationElement automationElementMock) throws AutomationException {
		 PatternID patternId = PatternID.ExpandCollapse;
	     PropertyID patternAvailablePropertyID = PropertyID.IsExpandCollapsePatternAvailable;
	     ExpandCollapse expandCollapsePattern = Mockito.mock(ExpandCollapse.class);
	     setPatternForElementMock(automationElementMock, patternId, patternAvailablePropertyID, expandCollapsePattern);
	     
	     return expandCollapsePattern;
	}
    
}

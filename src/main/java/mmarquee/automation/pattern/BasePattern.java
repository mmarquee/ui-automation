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
package mmarquee.automation.pattern;

import java.util.function.Function;

import org.apache.log4j.Logger;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomation;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationElementArrayConverter;
import mmarquee.automation.uiautomation.IUIAutomationElementConverter;

// Temporary intermediate class
public abstract class BasePattern extends BaseAutomation implements Pattern
{

    /**
     * The logger.
     */
    final Logger logger = Logger.getLogger(BasePattern.class.getName());

    /**
     * The guid of the pattern.
     */
    protected Guid.IID IID;

    /**
     * The associated automation element.
     */
    final protected AutomationElement element;
    protected PatternID patternID;
    protected PropertyID availabilityPropertyID;


    public BasePattern(AutomationElement element) throws AutomationException
    {
        super();
        this.element = element;
    }

    public PatternID getPatternID() {
    	return patternID;
    }

    /**
     * Is this pattern available.
     * @return True if available.
     */
    public boolean isAvailable () {
        try {
            return !this.element.getPropertyValue(availabilityPropertyID.getValue()).equals(0);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Gets the raw pointer to the pattern.
     * @param pbr The raw pointer
     * @return Result of the call from the COM library
     * @throws PatternNotFoundException
     */
    public WinNT.HRESULT getRawPatternPointer(
            final PointerByReference pbr) throws PatternNotFoundException {
    	PointerByReference unknown;
		try {
			unknown = this.element.getPattern(patternID.getValue());
		} catch (AutomationException e) {
        	throw new PatternNotFoundException(e);
		}

    	if (unknown == null) {
        	logger.warn("Failed to find pattern");
        	throw new PatternNotFoundException("Failed to find pattern");
    	}

        Unknown uElement = makeUnknown(unknown.getValue());
        return uElement.QueryInterface(new Guid.REFIID(this.IID), pbr);
    }

    /**
     * Returns the Class of the unmocked pattern class
     * @return the class without mocking
     */
	@SuppressWarnings("unchecked")
	public final Class<? extends BasePattern> getPatternClass() {
		Class<? extends BasePattern> patternClass = this.getClass();
        while (patternClass.getSimpleName().contains("Mockito")) { // Support for Pattern mocks
            patternClass = (Class<? extends BasePattern>) patternClass.getSuperclass();
        }
		return patternClass;
	}

	/**
	 * Gets a pattern from the raw pattern pointer, or returns the override pattern
	 *
	 * @param overridePattern the pattern to use if to
	 * @param convertPointerToInterface the method to convert the result pointer
	 * @return the pattern interface
	 * @throws AutomationException if something goes wrong
	 */
    protected <T> T getPattern(T overridePattern, Function<PointerByReference,T> convertPointerToInterface)
    		throws AutomationException {
    	if (overridePattern != null) {
            return overridePattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = null;
			result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return (T) convertPointerToInterface.apply(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }
	 /**
     * Converts the unknown value to a IUIAutomationElement.
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElement convertPointerToElementInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationElementConverter.pointerToInterface(pUnknownA);
    }

    /**
     * Converts the unknown value to a IUIAutomationElementArray.
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElementArray convertPointerToElementArrayInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationElementArrayConverter.pointerToInterface(pUnknownA);
    }
}

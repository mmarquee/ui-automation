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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationTreeWalker;

import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 02/02/2017.
 *
 * Wrapper for the AutomationTreeWalker.
 */
public class AutomationTreeWalker extends BaseAutomation {
    IUIAutomationTreeWalker walker = null;

    public AutomationTreeWalker(IUIAutomationTreeWalker walker) {
        this.walker = walker;
    }

    /**
     * Gets the next sibling element
     * @param element The element
     * @return The sibling element, or null if not found
     * @throws AutomationException Something is up in automation
     */
    public AutomationElement getNextSiblingElement (AutomationElement element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element.element);

        this.walker.getNextSiblingElement(pElement, pChild);

        try {
	        IUIAutomationElement3 childElement =
	                IUIAutomationElement3.Converter.PointerToInterface(pChild);
	        return new AutomationElement(childElement);
        } catch (NullPointerException ex) {
        	return null;
        }
    }

    /**
     * Gets the previous sibling element
     * @param element The element
     * @return The previous sibling element, or null if not found
     * @throws AutomationException Something is up in automation
     */
    public AutomationElement getPreviousSiblingElement (AutomationElement element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element.element);

        this.walker.getPreviousSiblingElement(pElement, pChild);

        try {
	        IUIAutomationElement3 childElement =
	                IUIAutomationElement3.Converter.PointerToInterface(pChild);
	        return new AutomationElement(childElement);
        } catch (NullPointerException ex) {
        	return null;
        }
    }

    /**
     * Gets the last child element of the supplied element
     * @param element The element
     * @return The last child of the element, or null if not found
     * @throws AutomationException Automation has returned an error
     */
    public AutomationElement getLastChildElement(AutomationElement element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element.element);

        this.walker.getLastChildElement(pElement, pChild);

        try {
	        IUIAutomationElement3 childElement =
	                IUIAutomationElement3.Converter.PointerToInterface(pChild);
	        return new AutomationElement(childElement);
        } catch (NullPointerException ex) {
        	return null;
        }
    }

    /**
     * Gets the first child element of the supplied element
     * @param element The element
     * @return The first child of the element, or null if not found
     * @throws AutomationException Automation has returned an error
     */
    public AutomationElement getFirstChildElement(AutomationElement element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element.element);
        this.walker.getFirstChildElement(pElement, pChild);
        
        try {
	        IUIAutomationElement3 childElement =
	                IUIAutomationElement3.Converter.PointerToInterface(pChild);
	        return new AutomationElement(childElement);
        } catch (NullPointerException ex) {
        	return null;
        }
    }

    protected Logger logger = Logger.getLogger(UIAutomation.class.getName());

    private IUIAutomationElement3 getRawFirstChildElement(IUIAutomationElement3 element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element);

        this.walker.getFirstChildElement(pElement, pChild);

        return IUIAutomationElement3.Converter.PointerToInterface(pChild);
    }

    private IUIAutomationElement3 getRawNextSiblingElement(IUIAutomationElement3 element)
            throws AutomationException {
        PointerByReference pChild = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element);

        this.walker.getNextSiblingElement(pElement, pChild);

        try {
            return IUIAutomationElement3.Converter.PointerToInterface(pChild);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * Sample walker implementation
     * @param root The root element
     * @throws AutomationException Exception in the automation library
     */
    public void walk(IUIAutomationElement3 root) throws AutomationException {

        IUIAutomationElement3 child = this.getRawFirstChildElement(root);

        AutomationElement childElement = new AutomationElement(child);

        logger.info(childElement.getName());
        logger.info(childElement.getClassName());

        while (child != null) {
            try {
                child = this.getRawNextSiblingElement(child);

                AutomationElement childElem = new AutomationElement(child);

                logger.info(childElem.getName());
                logger.info(childElem.getClassName());
            } catch (Throwable ex) {
                logger.info("++DONE?++");
            }
        }
    }
}

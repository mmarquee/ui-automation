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
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationElement3Converter;
import mmarquee.automation.uiautomation.IUIAutomationTreeWalker;

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
	                IUIAutomationElement3Converter.PointerToInterface(pChild);
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
	                IUIAutomationElement3Converter.PointerToInterface(pChild);
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
	                IUIAutomationElement3Converter.PointerToInterface(pChild);
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
	                IUIAutomationElement3Converter.PointerToInterface(pChild);
	        return new AutomationElement(childElement);
        } catch (NullPointerException ex) {
        	return null;
        }
    }

    /**
     * Gets the parent child element of the supplied element
     *
     * @param element The element
     * @return The parent of the element, or null if not found
     * @throws AutomationException Automation has returned an error
     */
    public AutomationElement getParentElement(AutomationElement element)
            throws AutomationException {
        PointerByReference pParent = new PointerByReference();

        Pointer pElement = this.getPointerFromElement(element.element);
        this.walker.getParentElement(pElement, pParent);

        try {
            IUIAutomationElement3 parentElement =
                    IUIAutomationElement3Converter.PointerToInterface(pParent);
            return new AutomationElement(parentElement);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * A generic walker algorithm
     * @param visitor The visitor to call on each element
     * @param root The root element (of which the children are walked)
     * @throws AutomationException Exception in the automation library
     */
    public void walk(AutomationElementVisitor visitor, AutomationElement root) throws AutomationException {

    	assert visitor != null;
    	assert root != null;
    	
        AutomationElement childElement = this.getFirstChildElement(root);

        while (childElement != null) {
        	boolean cont = visitor.visit(this, childElement);
        	if (! cont) break;
        	
        	childElement = this.getNextSiblingElement(childElement);
        }
    }
    
    /**
     * A visitor as used by {@link AutomationTreeWalker#walk(AutomationElementVisitor, AutomationElement)}
     *
     */
    public static interface AutomationElementVisitor {
    	/**
    	 * Visits an element during an 
    	 * {@link AutomationTreeWalker#walk(AutomationElementVisitor, AutomationElement)} run
    	 * @param walker The walker to use
    	 * @param element The currently visited element
    	 * @return true to continue walking the elements siblings, false otherwise
    	 * @throws AutomationException if something goes wrong
    	 */
    	boolean visit(AutomationTreeWalker walker, AutomationElement element) throws AutomationException;
    }
}

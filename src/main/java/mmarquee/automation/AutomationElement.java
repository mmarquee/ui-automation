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

import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Humphreys
 * Date 06/03/2016.
 * <p>
 * Wrapper for the underlying automation element.
 * </p>
 */
public class AutomationElement extends BaseAutomation {
    /**
     * <p>
     * The underlying automation element.
     * </p>
     */
    private IUIAutomationElement element;

    /**
     * Gets the underlying automation element.
     *
     * @return IUIAutomationElement3 The automation element.
     */
    public final IUIAutomationElement getElement() {
        return element;
    }

    /**
     * Sets the underlying automation element.
     *
     * @param inValue The new value.
     */
    public final void setElement(final IUIAutomationElement inValue) {
        this.element = inValue;
    }

    /**
     * Constructor of AutomationElement.
     *
     * @param inElement The element.
     */
    public AutomationElement(final IUIAutomationElement inElement) {
        this.element = inElement;
    }

    /**
     * Gets the property associated with the passed in id.
     *
     * @param propertyId The property ID to get.
     * @return The property ID.
     * @throws AutomationException Call to Automation API failed.
     */
    public Object getPropertyValue(final int propertyId)
            throws AutomationException {
        Variant.VARIANT.ByReference value = new Variant.VARIANT.ByReference();

        final int res = this.element.getCurrentPropertyValue(propertyId, value);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return value.getValue();
    }

    /**
     * Gets the current control type.
     *
     * @return The current control type.
     * @throws AutomationException Call to Automation API failed.
     */
    public int getControlType() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.element.getCurrentControlType(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the current class name of the element.
     *
     * @return The current class name.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getClassName() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentClassName(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current automation id of the element.
     *
     * @return The current automation id.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getAutomationId() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentAutomationId(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current localized control type of the element.
     *
     * @return The current class name.
     * @throws AutomationException Call to Automation API failed.
     */
    public String localizedControlType() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentLocalizedControlType(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current IsPassword value.
     *
     * @return True if IsPassword.
     * @throws AutomationException Call to Automation API failed.
     */
    public Boolean isPassword() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.element.getCurrentIsPassword(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue() == 1;
    }

    /**
     * Returns whether the element is off screen.
     * @return True if off screen.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.BOOL offScreen() throws AutomationException {
        WinDef.BOOLByReference bbr = new WinDef.BOOLByReference();

        final int res = this.element.getCurrentIsOffscreen(bbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return bbr.getValue();
    }

    /**
     * Returns whether the element is a content element.
     * @return True if content element.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.BOOL isContentElement() throws AutomationException {
        WinDef.BOOLByReference bbr = new WinDef.BOOLByReference();

        final int res = this.element.getCurrentIsContentElement(bbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return bbr.getValue();
    }

    /**
     * Returns whether the element is a control element.
     * @return True if control element.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.BOOL isControlElement() throws AutomationException {
        WinDef.BOOLByReference bbr = new WinDef.BOOLByReference();

        final int res = this.element.getCurrentIsControlElement(bbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return bbr.getValue();
    }

    /**
     * Returns whether the element is enabled.
     * @return True if enabled.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.BOOL isEnabled() throws AutomationException {
        WinDef.BOOLByReference bbr = new WinDef.BOOLByReference();

        final int res = this.element.getCurrentIsEnabled(bbr);
        if (res  != 0) {
            throw new AutomationException(res);
        }

        return bbr.getValue();
    }

    /**
     * Gets the name, either from the current ot cache property.
     *
     * @return The name (either cached or current).
     * @throws AutomationException Call to Automation API failed.
     */
    public String getName() throws AutomationException {
        return this.currentName();
    }

    /**
     * Gets the current name of the control.
     *
     * @return The name of the element.
     * @throws AutomationException Call to Automation API failed.
     */
    protected String currentName() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentName(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        if (sr.getValue() == null) {
            return "";
        } else {
            return sr.getValue().getWideString(0);
        }
    }

//    /**
//     * Sets the name of the element
//     * @param name The name to use
//     */
//    public void setName(String name) {
//        this.element.setName(name);
// Not sure how this worked
//    }

    /**
     * Finds the first element that matches the raw condition.
     *
     * @param scope Tree scope.
     * @param pCondition The raw condition.
     * @return The first matching element.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationElement findFirst(final TreeScope scope,
                                       final PointerByReference pCondition)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        this.element.findFirst(scope, pCondition.getValue(), pbr);

        try {
            IUIAutomationElement elem =
                    getAutomationElementFromReference(pbr);
            return new AutomationElement(elem);
        } catch (NullPointerException npe) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Get the current pattern that matches the patternId.
     *
     * @param patternId What pattern to get.
     * @return The pattern.
     * @throws AutomationException Call to Automation API failed.
     */
    public PointerByReference getPattern(final int patternId)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.element.getCurrentPattern(patternId, pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return pbr;
    }

    /**
     * Sets focus to the element.
     */
    public void setFocus() {
        this.element.setFocus();
    }

    /**
     * Gets all of the descendant elements that match the condition.
     *
     * @param pCondition The condition.
     * @return List of matching elements.
     * @throws AutomationException Call to Automation API failed.
     */
    public List<AutomationElement> findAllDescendants(
            final PointerByReference pCondition)
            throws AutomationException {
        return this.findAll(new TreeScope(TreeScope.Descendants), pCondition);
    }

    /**
     * Gets all of the elements that match the condition and scope.
     *
     * @param scope The scope in the element tree.
     * @param pCondition The condition.
     * @return List of matching elements.
     * @throws AutomationException Call to Automation API failed.
     */
    public List<AutomationElement> findAll(final TreeScope scope,
                                           final PointerByReference pCondition)
            throws AutomationException {
        List<AutomationElement> items = new ArrayList<AutomationElement>();

        PointerByReference pAll = new PointerByReference();

        final int res =
                this.element.findAll(scope, pCondition.getValue(), pAll);
        if (res != 0) {
            throw new AutomationException(res);
        }

        IUIAutomationElementArray collection =
                getAutomationElementArrayFromReference(pAll);

        IntByReference ibr = new IntByReference();

        collection.getLength(ibr);

        int counter = ibr.getValue();

        for (int a = 0; a < counter; a++) {
            PointerByReference pbr = new PointerByReference();

            collection.getElement(a, pbr);

            IUIAutomationElement elem =
                    getAutomationElementFromReference(pbr);

            items.add(new AutomationElement(elem));
        }

        return items;
    }

    /**
     * Gets the current ARIA role.
     *
     * @return String representing the ARIA role.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getAriaRole() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentAriaRole(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current orientation.
     *
     * @return The orientation.
     * @throws AutomationException Something has gone wrong.
     */
    public OrientationType getOrientation() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.element.getCurrentOrientation(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return OrientationType.fromInt(ibr.getValue());
    }

    /**
     * Gets the current culture.
     *
     * @return The culture.
     * @throws AutomationException Something has gone wrong.
     */
    public Integer getCulture() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.element.getCurrentCulture(ibr);

        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the framework ID.
     *
     * @return The framework ID.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getFrameworkId() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentFrameworkId(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the provider description.
     *
     * @return The provider description.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getProviderDescription() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentProviderDescription(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

//    /**
//     * Get the runtime Id
//     * @return The runtime ID
//     */
//    //   public int[] getRuntimeId() {
//   //       return element.getRuntimeId();
//    //   }

    /**
     * Gets the process ID.
     *
     * @return The process ID.
     * @throws AutomationException Call to Automation API failed.
     */
    public Integer getProcessId() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.element.getCurrentProcessId(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the current item status.
     *
     * @return The status.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getItemStatus() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentItemStatus(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current accelerator key associated with the element.
     *
     * @return The accelerator key.
     * @throws AutomationException Call to Automation API failed.
     */
    public String getAcceleratorKey() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.element.getCurrentAcceleratorKey(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the clickable point for the control.
     *
     * @return The clickable point.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.POINT getClickablePoint() throws AutomationException {
        WinDef.POINT.ByReference pbr = new WinDef.POINT.ByReference();

        WinDef.BOOLByReference br = new WinDef.BOOLByReference();

        final int res = this.element.getClickablePoint(pbr, br);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return new WinDef.POINT(pbr.x, pbr.y);
    }

    /**
     * Gets the bounding rectangle of the control.
     *
     * @return The bounding rectangle.
     * @throws AutomationException Call to Automation API failed.
     */
    public WinDef.RECT getBoundingRectangle() throws AutomationException {
        WinDef.RECT rect = new WinDef.RECT();

        final int res = this.element.getCurrentBoundingRectangle(rect);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return rect;
    }

    /**
     * Shows the context menu for the element.
     *
     * @throws AutomationException Failed to get the correct interface.
     */
    public void showContextMenu() throws AutomationException {
        final int res = this.element.showContextMenu();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }
}

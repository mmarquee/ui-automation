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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.uiautomation.*;
import mmarquee.automation.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * The base automation wrapper.
 */
public class UIAutomation {

    protected Logger logger = Logger.getLogger(UIAutomation.class.getName());

    private static UIAutomation INSTANCE = null;

    private AutomationElement rootElement;

    private IUIAutomation automation;

    private final static int FIND_DESKTOP_ATTEMPTS = 25;

    /**
     * Constructor for UIAutomation library
     */
    protected UIAutomation() {
        Ole32.INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_APARTMENTTHREADED);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT hr = Ole32.INSTANCE.CoCreateInstance(
                IUIAutomation.CLSID,
                null,
                WTypes.CLSCTX_SERVER,
                IUIAutomation.IID,
                pbr);

        COMUtils.checkRC(hr);

        Unknown unk = new Unknown(pbr.getValue());

        PointerByReference pbr1 = new PointerByReference();

        Guid.REFIID refiid = new Guid.REFIID(IUIAutomation.IID);

        WinNT.HRESULT result = unk.QueryInterface(refiid, pbr1);
        if (COMUtils.SUCCEEDED(result)) {
            this.automation = IUIAutomation.Converter.PointerToInterface(pbr1);
        }

        PointerByReference pRoot = new PointerByReference();

        this.getRootElement(pRoot);

        Unknown uRoot = new Unknown(pRoot.getValue());

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID);

        WinNT.HRESULT result0 = uRoot.QueryInterface(refiidElement, pRoot);

        if (COMUtils.SUCCEEDED(result0)) {
            this.rootElement = new AutomationElement(IUIAutomationElement.Converter.PointerToInterface(pRoot));
        }
    }

    /**
     * Gets the root element for automation
     * @param element Pointer to the element
     * @return Error status
     */
    public int getRootElement(PointerByReference element) {
        return this.automation.GetRootElement(element);
    }

    /**
     * Compares 2 elements
     * @param element1 First element
     * @param element2 Second element
     * @param same Are they the same?
     * @return Error status
     */
    public int compareElements(Pointer element1, Pointer element2, IntByReference same) {
        return this.automation.CompareElements(element1, element2, same);
    }

    /**
     * Gets the instance
     *
     * @return the instance of the ui automation library
     */
    public static UIAutomation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIAutomation();
        }

        return INSTANCE;
    }

    /**
     * Launches the application
     *
     * @param command The command to be called
     * @return AutomationApplication that represents the application
     * @throws java.io.IOException Cannot start application?
     * @throws AutomationException Automation library error
     */
    public AutomationApplication launch(String... command) throws java.io.IOException, AutomationException {
        Process process = Utils.startProcess(command);
        return new AutomationApplication(rootElement, process, false);
    }

    /**
     * Attaches to the application process
     *
     * @param process Process to attach to
     * @return AutomationApplication that represents the application
     * @throws AutomationException Automation library error
     */
    public AutomationApplication attach(Process process) throws AutomationException {
        return new AutomationApplication(rootElement, process, true);
    }

    /**
     * Finds the given process
     *
     * @param command Command to look for
     * @return The Application
     * @throws AutomationException If findProcessEntry throws an exception.
     */
    public AutomationApplication findProcess(String... command) throws AutomationException {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, command);

        if (!found) {
            throw new AutomationException();
        } else {
            WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
            return new AutomationApplication(rootElement, handle, true);
        }
    }

    /**
     * Attaches or launches the application
     *
     * @param command Command to be started
     * @return AutomationApplication that represents the application
     * @throws java.lang.Exception Unable to find process
     */
    public AutomationApplication launchOrAttach(String... command) throws Exception {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, command);

        if (!found) {
            return this.launch(command);
        } else {
            WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
            return new AutomationApplication(rootElement, handle, true);
        }
    }

    /**
     * Gets the desktop window associated with the title
     *
     * @param title Title to search for
     * @return AutomationWindow The found window
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationWindow getDesktopWindow(String title) throws AutomationException {
        AutomationElement element = null;

        // Look for a window
        Variant.VARIANT.ByValue variant1 = new Variant.VARIANT.ByValue();
        variant1.setValue(Variant.VT_INT, ControlType.Window.getValue());

        // Look for a specific title
        Variant.VARIANT.ByValue variant2 = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(title);
        variant2.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            // First condition
            PointerByReference pCondition1 = this.createPropertyCondition(PropertyID.Name.getValue(), variant2);

            // Second condition
            PointerByReference pCondition2 = this.createPropertyCondition(PropertyID.ControlType.getValue(), variant1);

            // And Condition
            PointerByReference pAndCondition = this.createAndCondition(pCondition1.getValue(), pCondition2.getValue());

            for (int loop = 0; loop < FIND_DESKTOP_ATTEMPTS; loop++) {

                try {
                    element = this.rootElement.findFirst(new TreeScope(TreeScope.Descendants), pAndCondition);
                } catch (AutomationException ex) {
                    logger.info("Not found, retrying " + title);
                }

                if (element != null) {
                    break;
                }
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }

        if (element == null) {
            logger.warning("Failed to find desktop window `" + title + "`");
            throw new ItemNotFoundException();
        }

        return new AutomationWindow(element);
    }

    /**
     * Compares 2 elements
     * @param element1 First element
     * @param element2 Second element
     * @return Are the elememts the same
     * @throws AutomationException Automation library error
     */
    public boolean compareElement(Pointer element1, Pointer element2) throws AutomationException {
        IntByReference ibr = new IntByReference();

        if (this.automation.CompareElements(element1, element2, ibr) != 0) {
            throw new AutomationException();
        } else {
            return ibr.getValue() == 1;
        }
    }

    /**
     * Create an and condition
     * @param pCondition1 First condition
     * @param pCondition2 Second condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createAndCondition (Pointer pCondition1, Pointer pCondition2) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.automation.CreateAndCondition(pCondition1, pCondition2, pbr) == 0) {
            return pbr;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Create an or condition
     * @param pCondition1 First condition
     * @param pCondition2 Second condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createOrCondition (Pointer pCondition1, Pointer pCondition2) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.automation.CreateOrCondition(pCondition1, pCondition2, pbr) == 0) {
            return pbr;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Creates a condition, based on control id
     * @param id The control id
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference CreateControlTypeCondition(ControlType id) throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, id.getValue());

        return this.createPropertyCondition(PropertyID.ControlType.getValue(), variant);
    }

    /**
     * Creates a condition, based on automation id
     * @param automationId The automation id
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference CreateAutomationIdPropertyCondition(String automationId) throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(automationId);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            return this.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    /**
     * Creates a condition, based on element name
     * @param name The name
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference CreateNamePropertyCondition(String name) throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(name);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            return this.createPropertyCondition(PropertyID.Name.getValue(), variant);
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    /**
     * Creates a property condition
     * @param id Which property to check for
     * @param value The value of the property
     * @return The nre condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createPropertyCondition(int id, Variant.VARIANT.ByValue value) throws AutomationException {
        PointerByReference pCondition = new PointerByReference();

        if (this.automation.CreatePropertyCondition(id, value, pCondition) == 0) {
            Guid.REFIID refiid1 = new Guid.REFIID(IUIAutomationCondition.IID);

            Unknown unkCondition = new Unknown(pCondition.getValue());
            PointerByReference pUnknown = new PointerByReference();

            WinNT.HRESULT result1 = unkCondition.QueryInterface(refiid1, pUnknown);
            if (COMUtils.SUCCEEDED(result1)) {
                return pCondition;
            } else {
                throw new AutomationException();
            }
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the desktop object associated with the title
     *
     * @param title Title to search for
     * @return AutomationWindow The found window
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationWindow getDesktopObject(String title) throws AutomationException {
        AutomationElement element = null;

        // Look for a specific title
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(title);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            PointerByReference pCondition1 = this.createPropertyCondition(PropertyID.Name.getValue(), variant);

            for (int loop = 0; loop < FIND_DESKTOP_ATTEMPTS; loop++) {

                try {
                    element = this.rootElement.findFirst(new TreeScope(TreeScope.Descendants),
                            pCondition1);
                } catch (AutomationException ex) {
                    logger.info("Not found, retrying " + title);
                }

                if (element != null) {
                    break;
                }
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }

        if (element == null) {
            logger.warning("Failed to find desktop object `" + title + "`");
            throw new ItemNotFoundException();
        }

        return new AutomationWindow(element);
    }

    /**
     * Gets the desktop object associated with the title
     *
     * @param title Title of the menu to search for
     * @return AutomationMenu The found menu
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationMenu getDesktopMenu(String title) throws AutomationException {
        AutomationElement element = null;

        // Look for a specific title
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(title);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            PointerByReference pCondition1 = this.createPropertyCondition(PropertyID.Name.getValue(), variant);

            for (int loop = 0; loop < FIND_DESKTOP_ATTEMPTS; loop++) {

                try {
                    element = this.rootElement.findFirst(new TreeScope(TreeScope.Descendants),
                            pCondition1);
                } catch (AutomationException ex) {
                    logger.info("Not found, retrying " + title);
                }

                if (element != null) {
                    break;
                }
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }

        if (element == null) {
            logger.info("Failed to find desktop menu `" + title + "`");
            throw new ItemNotFoundException();
        }

        return new AutomationMenu(element);
    }

    /**
     * Gets the list of desktop windows
     *
     * @return List of desktop windows
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationWindow> getDesktopWindows() throws AutomationException {
        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        PointerByReference pTrueCondition = this.createTrueCondition();

        Unknown unkConditionA = new Unknown(pTrueCondition.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationCondition.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            List<AutomationElement> collection =
                    this.rootElement.findAll(new TreeScope(TreeScope.Children), pTrueCondition.getValue());

            for (AutomationElement element : collection) {
                result.add(new AutomationWindow(element));
            }

            return result;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Creates a false Condition
     * @return The condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference CreateFalseCondition () throws AutomationException{
        PointerByReference pCondition = new PointerByReference();

        if (this.automation.CreateFalseCondition(pCondition) == 0) {
            return pCondition;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Creates a true Condition
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createTrueCondition() throws AutomationException {
        PointerByReference pTrueCondition = new PointerByReference();

        if (this.automation.CreateTrueCondition(pTrueCondition) == 0) {
            return pTrueCondition;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Creates a false Condition
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createFalseCondition() throws AutomationException {
        PointerByReference condition = new PointerByReference();

        if (this.automation.CreateFalseCondition(condition) == 0) {
            return condition;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Create a NOT condition
     * @param condition The condition condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createNotCondition (Pointer condition) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        if (this.automation.CreateNotCondition(condition, pbr) == 0) {
            return pbr;
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the root automation element
     * @return The root element
     */
    AutomationElement getRootElement() {
        return this.rootElement;
    }
}

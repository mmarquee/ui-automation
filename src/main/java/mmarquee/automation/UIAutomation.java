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
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationPanel;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.*;
import mmarquee.automation.utils.Canalizer;
import mmarquee.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * The base automation wrapper.
 */
public class UIAutomation extends BaseAutomation {

    protected Logger logger = Logger.getLogger(UIAutomation.class.getName());

    protected static UIAutomation INSTANCE = null;
    private static final Ole32 CANALIZED_OLE32_INSTANCE = Canalizer.canalize(com.sun.jna.platform.win32.Ole32.INSTANCE);


    protected AutomationElement rootElement;

    /**
     * Main automation interface
     */
    private IUIAutomation automation;

    /*final*/ static int FIND_DESKTOP_ATTEMPTS = 25; // not final to be set in tests

    /**
     * Created for test, to allow mocking
     *
     * @param automation The automation object to use.
     */
    public UIAutomation(IUIAutomation automation) {
        this.automation = automation;
    }

    /**
     * Constructor for UIAutomation library
     */
    protected UIAutomation() {
        CANALIZED_OLE32_INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_APARTMENTTHREADED);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT hr = CANALIZED_OLE32_INSTANCE.CoCreateInstance(
                IUIAutomation.CLSID,
                null,
                WTypes.CLSCTX_SERVER,
                IUIAutomation.IID,
                pbr);

        COMUtils.checkRC(hr);

        Unknown unk = new Unknown(pbr.getValue());

        PointerByReference pbr1 = new PointerByReference();

        WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomation.IID), pbr1);
        if (COMUtils.SUCCEEDED(result)) {
            this.automation = IUIAutomationConverter.PointerToInterface(pbr1);
        }

        PointerByReference pRoot = new PointerByReference();

        this.getRootElement(pRoot);

        Unknown uRoot = new Unknown(pRoot.getValue());

        WinNT.HRESULT result0 = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pRoot);

        if (COMUtils.SUCCEEDED(result0)) {
            this.rootElement = new AutomationElement(IUIAutomationElement3Converter.PointerToInterface(pRoot));
        }
    }

    /**
     * Gets the root element for automation
     *
     * @param element Pointer to the element
     * @return Error status
     */
    public int getRootElement(PointerByReference element) {
        return this.automation.getRootElement(element);
    }

    /**
     * Compares 2 elements
     *
     * @param element1 First element
     * @param element2 Second element
     * @param same     Are they the same?
     * @return Error status
     */
    public int compareElements(Pointer element1, Pointer element2, IntByReference same) {
        return this.automation.compareElements(element1, element2, same);
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
     * Launches the application, from a given directory.
     *
     * @param command The command to be called
     * @return AutomationApplication that represents the application
     * @throws java.io.IOException Cannot start application?
     * @throws AutomationException Automation library error
     */
    public AutomationApplication launchWithDirectory(String... command) throws java.io.IOException, AutomationException {
        Process process = Utils.startProcessWithWorkingDirectory(command);
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
     * Attaches or launches the application
     *
     * @param command Command to be started
     * @return AutomationApplication that represents the application
     * @throws java.lang.Exception Unable to find process
     */
    public AutomationApplication launchWithWorkingDirectoryOrAttach(String... command) throws Exception {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, command);

        if (!found) {
            return this.launchWithDirectory(command);
        } else {
            WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
            return new AutomationApplication(rootElement, handle, true);
        }
    }

    /**
     * Gets the desktop object associated with the title
     *
     * @param title Title to search for
     * @return AutomationWindow The found 'element'
     * @throws ElementNotFoundException Element is not found
     */
    private AutomationElement get(ControlType controlType, String title, int numberOfRetries)
            throws AutomationException {
        AutomationElement element = null;

        // Look for a control type
        Variant.VARIANT.ByValue variant1 = new Variant.VARIANT.ByValue();
        variant1.setValue(Variant.VT_INT, controlType.getValue());

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
            PointerByReference pAndCondition = this.createAndCondition(pCondition1, pCondition2);

            for (int loop = 0; loop < numberOfRetries; loop++) {

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
            throw new ItemNotFoundException(title);
        }

        return element;
    }

    /**
     * Gets the desktop 'window' associated with the title
     *
     * @param title Title to search for
     * @return AutomationWindow The found window
     * @throws ElementNotFoundException Element is not found
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getDesktopWindow(String title)
            throws PatternNotFoundException, AutomationException {
        return new AutomationWindow(this.get(ControlType.Window, title, FIND_DESKTOP_ATTEMPTS));
    }

    /**
     * Gets the desktop 'window' associated with the title, with a variable
     * number of retries.
     *
     * @param title Title to search for
     * @param retries Number of retries
     * @return AutomationWindow The found window
     * @throws ElementNotFoundException Element is not found
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getDesktopWindow(String title, int retries)
            throws PatternNotFoundException, AutomationException {
        return new AutomationWindow(this.get(ControlType.Window, title, retries));
    }

    /**
     * Create an 'and' condition
     *
     * @param pCondition1 First condition
     * @param pCondition2 Second condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createAndCondition(PointerByReference pCondition1, PointerByReference pCondition2) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createAndCondition(pCondition1.getValue(), pCondition2.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Create an 'or' condition
     *
     * @param pCondition1 First condition
     * @param pCondition2 Second condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createOrCondition(PointerByReference pCondition1, PointerByReference pCondition2) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createOrCondition(pCondition1.getValue(), pCondition2.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Creates a condition, based on control id
     *
     * @param id The control id
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference createControlTypeCondition(ControlType id) throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, id.getValue());

        return this.createPropertyCondition(PropertyID.ControlType.getValue(), variant);
    }

    /**
     * Creates a condition, based on automation id
     *
     * @param automationId The automation id
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference createAutomationIdPropertyCondition(String automationId) throws AutomationException {
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
     *
     * @param name The name
     * @return The condition
     * @throws AutomationException Something went wrong
     */
    public PointerByReference createNamePropertyCondition(String name) throws AutomationException {
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
     *
     * @param id    Which property to check for
     * @param value The value of the property
     * @return The nre condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createPropertyCondition(int id, Variant.VARIANT.ByValue value) throws AutomationException {
        PointerByReference pCondition = new PointerByReference();

        final int res = this.automation.createPropertyCondition(id, value, pCondition);
        if (res == 0) {
            Unknown unkCondition = new Unknown(pCondition.getValue());
            PointerByReference pUnknown = new PointerByReference();

            WinNT.HRESULT result1 = unkCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown);
            if (COMUtils.SUCCEEDED(result1)) {
                return pCondition;
            } else {
                throw new AutomationException(result1.intValue());
            }
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the desktop object associated with the title
     *
    /**
     * Gets the desktop object associated with the title
     *
     * @param title Title to search for
     * @return AutomationPanel The found object
     * @throws ElementNotFoundException Element is not found
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPanel getDesktopObject(String title)
            throws PatternNotFoundException, AutomationException {
        return new AutomationPanel(this.get(ControlType.Pane, title, FIND_DESKTOP_ATTEMPTS));
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
            throw new ItemNotFoundException(title);
        }

        return new AutomationMenu(element);
    }

    /**
     * Gets the list of desktop windows
     *
     * @return List of desktop windows
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationWindow> getDesktopWindows()
            throws PatternNotFoundException, AutomationException {
        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        List<AutomationElement> collection = getRootChildren(ControlType.Window);

        for (AutomationElement element : collection) {
            result.add(new AutomationWindow(element));
        }

        return result;
    }

    /**
     * Creates a true Condition
     *
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createTrueCondition() throws AutomationException {
        PointerByReference pTrueCondition = new PointerByReference();

        final int res = this.automation.createTrueCondition(pTrueCondition);
        if (res == 0) {
            return pTrueCondition;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Creates a false Condition
     *
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    public PointerByReference createFalseCondition() throws AutomationException {
        PointerByReference condition = new PointerByReference();

        final int res = this.automation.createFalseCondition(condition);
        if (res == 0) {
            return condition;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Create a NOT condition
     *
     * @param condition The condition condition
     * @return The new condition
     * @throws AutomationException Something is wrong
     */
    public PointerByReference createNotCondition(PointerByReference condition) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createNotCondition(condition.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the element from the supplied tag
     * @param pt The point
     * @return The actual element under the tag
     * @throws AutomationException The automation library returned an error
     */
    public AutomationElement getElementFromPoint(WinDef.POINT pt) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.elementFromPoint(pt, pbr);
        if (res == 0) {
            IUIAutomationElement3 element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the element from the native handle
     *
     * @param hwnd Native Handle
     * @return The actual element under the handle
     * @throws AutomationException The automation library returned an error
     */
    public AutomationElement getElementFromHandle(WinDef.HWND hwnd) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.getElementFromHandle(hwnd, pbr);
        if (res == 0) {
            IUIAutomationElement3 element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the focused element
     * @return The focused element
     * @throws AutomationException Automation returned an error
     */
    public AutomationElement getFocusedElement() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.getFocusedElement(pbr);
        if (res == 0) {
            IUIAutomationElement3 element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the root automation element
     *
     * @return The root element
     */
    public AutomationElement getRootElement() {
        return this.rootElement;
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
            throw new AutomationException("Process " + command + " not found.");
        } else {
            WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
            return new AutomationApplication(rootElement, handle, true);
        }
    }

    /**
     * Gets the control view walker
     * @return The tree walker object
     */
    public AutomationTreeWalker getControlViewWalker() throws AutomationException {
        PointerByReference pbrWalker = new PointerByReference();

        this.automation.getControlViewWalker(pbrWalker);

        Unknown unkConditionA = new Unknown(pbrWalker.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTreeWalker.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {

            IUIAutomationTreeWalker walker =
                    IUIAutomationTreeWalkerConverter.PointerToInterface(pUnknownA);

            return new AutomationTreeWalker(walker);
        } else {
            throw new AutomationException(resultA.intValue());
        }
    }

    /*
     * Adds an automation event handler.
     *
     IntByReference eventId,
     TreeScope scope,
     Pointer element,
     PointerByReference cacheRequest,
     PointerByReference handler
     *
     * @param event The identifier of the event that the method handles.
     * @param scope The scope of events to be handled; that is, whether they are on the element itself, or on its ancestors and descendants.
     * @param element The UI Automation element to associate with the event handler.
     * @param handler The object that handles the event.
     * @throws AutomationException
     */
/*
    public void addAutomationEventHandler(EventID event,
                                          TreeScope scope,
                                          AutomationElement element,
                                          AutomationHandler handler) throws AutomationException {

        IntByReference ibr = new IntByReference();
        ibr.setValue(event.getValue());

        PointerByReference handlerRef = new PointerByReference();

        PointerByReference pElement = new PointerByReference();

        WinNT.HRESULT resultA = element.element.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pElement);
        if (COMUtils.SUCCEEDED(resultA)) {
            throw new AutomationException();
        }

        if (automation.addAutomationEventHandler(ibr, scope, pElement.getValue(), null, handler) != 0) {
            throw new AutomationException();
        }
    }
*/
          /*
        IntByReference eventId, PointerByReference element, PointerByReference handler
         */
  /*
    public void removeAutomationEventHandler(EventID event) {

        IntByReference ibr = new IntByReference();
        ibr.setValue(event.getValue());


        automation.removeAutomationEventHandler(ibr);
    }
*/
}

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
import mmarquee.automation.uiautomation.*;
import mmarquee.automation.utils.Utils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HumphreysM on 26/01/2016.
 *
 * The base automation wrapper.
 */
public class UIAutomation {

    private static UIAutomation INSTANCE = null;

    private AutomationElement rootElement;

    // TODO: Fix this (changed for caching for now)
    protected IUIAutomation automation;

    /**
     * Constructor for UIAutomation library
     */
    protected UIAutomation() {
//        automation = ClassFactory.createCUIAutomation();

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
            this.automation = IUIAutomation.Converter.PointerToIUIAutomation(pbr1);
        }

        // rootElement = new AutomationElement(this.automation.getRootElement());
        PointerByReference pRoot = new PointerByReference();

        this.automation.GetRootElement(pRoot);

        Unknown uRoot = new Unknown(pRoot.getValue());

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID);

        WinNT.HRESULT result0 = uRoot.QueryInterface(refiidElement, pRoot);

        if (COMUtils.SUCCEEDED(result0)) {
            this.rootElement = new AutomationElement(IUIAutomationElement.Converter.PointerToIUIAutomationElement(pRoot));
        }
    }

    /**
     * Gets the instance
     *
     * @return the instance of the ui automation library
     */
    public final static UIAutomation getInstance() {
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
     */
    public AutomationApplication launch(String... command) throws java.io.IOException {
        Process process = Utils.startProcess(command);
        return new AutomationApplication(rootElement, process, false);
    }

    /**
     * Attaches to the application process
     *
     * @param process Process to attach to
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication attach(Process process) {
        return new AutomationApplication(rootElement, process, true);
    }

    /**
     * Finds the given process
     *
     * @param command Command to look for
     * @return The Application
     * @throws Exception If findProcessEntry throws an exception.
     */
    public AutomationApplication findProcess(String... command) throws Exception {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, command);

        if (!found) {
            return null;
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
    public AutomationWindow getDesktopWindow(String title) throws ElementNotFoundException {
        AutomationElement element = null;

        // Look for a window
        Variant.VARIANT.ByValue variant1 = new Variant.VARIANT.ByValue();
        variant1.setValue(Variant.VT_INT, ControlType.Window);

        // Look for a specific title
        Variant.VARIANT.ByValue variant2 = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(title);
        variant2.setValue(Variant.VT_BSTR, sysAllocated);

        // Create the properties outside of the loop

        Guid.REFIID refiid1 = new Guid.REFIID(IUIAutomationCondition.IID);

        // First condition
        PointerByReference pCondition1 = this.createPropertyCondition(PropertyID.Name, variant2);

        // Second condition
        PointerByReference pCondition2 = this.createPropertyCondition(PropertyID.ControlType, variant1);

        // And Condition
        PointerByReference pAndCondition = this.createAndCondition(pCondition1.getValue(), pCondition2.getValue());

        for (int loop = 0; loop < 15; loop++) {

            element = this.rootElement.findFirst(new TreeScope(TreeScope.TreeScope_Descendants), pAndCondition);

            if (element != null) {
                break;
            }
        }

        return new AutomationWindow(element);
    }

    /**
     * Compares 2 elements
     * @param element1 First element
     * @param element2 Second element
     * @return Are the elememts the same
     */
    public boolean compareElement(Pointer element1, Pointer element2) {
        IntByReference ibr = new IntByReference();

        int result = this.automation.CompareElements(element1, element2, ibr);

        return ibr.getValue() == 1;
    }

    private PointerByReference createAndCondition (Pointer pCondition1, Pointer pCondition2) {
        PointerByReference pbr = new PointerByReference();

        this.automation.CreateAndCondition(pCondition1, pCondition2, pbr);

        // Checks ?
        return pbr;
    }

    public Pointer CreateControlTypeCondition(int id) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, id);

        return this.createPropertyCondition(PropertyID.ControlType, variant).getValue();
    }

    public Pointer CreateAutomationIdPropertyCondition(String automationId) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(automationId);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        return this.createPropertyCondition(PropertyID.AutomationId, variant).getValue();
    }

    public Pointer CreateNamePropertyCondition(String name) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(name);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        return this.createPropertyCondition(PropertyID.Name, variant).getValue();
    }

    private PointerByReference createPropertyCondition(PropertyID id, Variant.VARIANT.ByValue value) {
        PointerByReference pCondition = new PointerByReference();

        int result = this.automation.CreatePropertyCondition(id.getValue(), value, pCondition);

        Guid.REFIID refiid1 = new Guid.REFIID(IUIAutomationCondition.IID);

        Unknown unkCondition = new Unknown(pCondition.getValue());
        PointerByReference pUnknown = new PointerByReference();

        WinNT.HRESULT result1 = unkCondition.QueryInterface(refiid1, pUnknown);
        if (COMUtils.SUCCEEDED(result1)) {
            return pCondition;
        } else {
            // Or perhaps throw an exception?
            return null;
        }
    }

    /**
     * Gets the desktop window associated with the title
     *
     * @param title Title to search for
     * @return AutomationWindow The found window
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationWindow getDesktopObject(String title) throws ElementNotFoundException {
        AutomationElement element = null;

        // Look for a specific title
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(title);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        PointerByReference pbr = this.createPropertyCondition(PropertyID.ControlType, variant);

        for (int loop = 0; loop < 15; loop++) {
            element = this.rootElement.findFirst(new TreeScope(TreeScope.TreeScope_Descendants),
                    pbr);

            if (element != null) {
                break;
            }
        }

        return new AutomationWindow(element);
    }

    /**
     * Gets the list of desktop windows
     *
     * @return List of desktop windows
     */
    public List<AutomationWindow> getDesktopWindows() {
        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        PointerByReference pAll = new PointerByReference();

        PointerByReference pTrueCondition = new PointerByReference();

        this.automation.CreateTrueCondition(pTrueCondition);

        Unknown unkConditionA = new Unknown(pTrueCondition.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationCondition.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationCondition condition =
                    IUIAutomationCondition.Converter.PointerToIUIAutomationCondition(pUnknownA);

            List<AutomationElement> collection =
                    this.rootElement.findAll(new TreeScope(TreeScope.TreeScope_Children), pTrueCondition.getValue());

            for (AutomationElement element : collection) {
                result.add(new AutomationWindow(element));
            }

            return result;
        } else {
            return null; // or throw exception
        }
    }

    /**
     * Does this automation object support IUIAutomation2
     * i.e. is it Windows 8 or above?
     *
     * @return supports IUIAutomation2
     */
    //public boolean supportsAutomation2() {
    //    return this.automation instanceof IUIAutomation2;
   // }

    /**
     * Does this automation object support IUIAutomation3
     * i.e. is it Windows 8.1 or above?
     *
     * @return supports IUIAutomation3
     */
    //public boolean supportsAutomation3() {
    //    return this.automation instanceof IUIAutomation3;
    //}

    /**
     * Captures the screen.
     *
     * @param filename The filename
     * @throws AWTException Robot exception
     * @throws IOException  IO Exception
     */

    public void captureScreen(String filename) throws AWTException, IOException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File(filename));
    }

    /**
     * Adds an event handler for the given event.
     *
     * @param sender       The sender
     * @param eventId      The event id
     * @param treeScope    The treeScope
     * @param eventHandler The EventHandler to add
     */
    /*
    public void addAutomationEventHandler(IUIAutomationElement sender,
                                   int eventId,
                                   TreeScope treeScope,
                                   EventHandler eventHandler) {
        this.automation.addAutomationEventHandler(eventId, sender, treeScope, null, eventHandler);
    }
*/
    /**
     * Removed the event handler from the element
     * @param element The element
     * @param eventId The event
     * @param eventHandler The handler
     */
/*    public void removeAutomationEventHandler(IUIAutomationElement element,
                                             int eventId,
                                             EventHandler eventHandler) {
        this.automation.removeAutomationEventHandler(eventId, element, eventHandler);
    }
*/
    /**
     * Creates the raw and condition
     * @param condition0 First condition
     * @param condition1 Second condition
     * @return The AndCondition
     */
//    public IUIAutomationCondition CreateAndCondition (IUIAutomationCondition condition0,IUIAutomationCondition condition1) {
//        return automation.createAndCondition(
//                condition0, condition1);
//    }

    /**
     * Creates a false Condition
     * @return The condition
     */
    public Pointer CreateFalseCondition () {
        PointerByReference pCondition = new PointerByReference();

        this.automation.CreateFalseCondition(pCondition);

/*        Unknown unkConditionA = new Unknown(pTrueCondition.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationCondition.IID_IUIAUTOMATION_CONDITION);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            return IUIAutomationCondition.Converter.PointerToIUIAutomationCondition(pUnknownA);
        } else {
            return null; // or throw excption
        }
*/
        return pCondition.getValue();
    }

    /**
     * Creates a true Condition
     * @return The condition
     */
    public Pointer CreateTrueCondition () {
        PointerByReference pTrueCondition = new PointerByReference();

        this.automation.CreateTrueCondition(pTrueCondition);

/*        Unknown unkConditionA = new Unknown(pTrueCondition.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationCondition.IID_IUIAUTOMATION_CONDITION);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            return IUIAutomationCondition.Converter.PointerToIUIAutomationCondition(pUnknownA);
        } else {
            return null; // or throw excption
        }
*/
        return pTrueCondition.getValue();

    }

    /**
     * Getst the raw condition
     * @return the underlying IUIAutomationCondition
     */
//    public IUIAutomationCondition CreateOrCondition (IUIAutomationCondition condition0,IUIAutomationCondition condition1) {
//        return automation.createOrCondition(
//                condition0,
//                condition1);
//    }

    /**
     * Creates a property condition
     * @param property The property to check
     * @param value The value of the property
     * @return The property condition
     */
 //   public IUIAutomationCondition CreatePropertyCondition (int property, java.lang.Object value) {
 //       return this.createPropertyCondition(property, value);
 //   }
}

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
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.uiautomation.*;
import mmarquee.automation.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * The base automation wrapper.
 *
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public class UIAutomation extends BaseAutomation {

    /**
     * The logger instance.
     */
    private Logger logger = Logger.getLogger(UIAutomation.class.getName());

    /**
     * The automation instance.
     */
    protected static UIAutomation INSTANCE = null;

    /**
     * The wrapper for the Ole32 library.
     */
    private static Ole32Wrapper Ole32 = null;

    /**
     * The root element.
     */
    private AutomationElement rootElement;

    /**
     * Main automation interface.
     */
    private IUIAutomation automation;

    /*final*/ static int FIND_DESKTOP_ATTEMPTS = 25; // not final to be set in tests

    /**
     * Created for test, to allow mocking.
     *
     * @param automation The automation object to use.
     */
    public UIAutomation(final IUIAutomation automation) {
        this.automation = automation;
    }

    /**
     * Constructor for UIAutomation library.
     */
    protected UIAutomation() {
        Ole32 = new Ole32Wrapper();

        PointerByReference pbr1 = new PointerByReference();

        WinNT.HRESULT result = getOle32Unknown().QueryInterface(new Guid.REFIID(IUIAutomation.IID), pbr1);
        if (COMUtils.SUCCEEDED(result)) {
            this.automation = IUIAutomationConverter.pointerToInterface(pbr1);
        }

        PointerByReference pRoot = new PointerByReference();

        this.getRootElement(pRoot);

        Unknown uRoot = new Unknown(pRoot.getValue());

        WinNT.HRESULT result0 = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), pRoot);

        if (COMUtils.SUCCEEDED(result0)) {
            this.rootElement = new AutomationElement(IUIAutomationElementConverter.pointerToInterface(pRoot));
        }
    }

    /**
     * Gets the underlying unknown value of Ole32.
     * @return Unknown The COM Unknown value.
     */
    Unknown getOle32Unknown() {
        return Ole32.getUnknown();
    }

    /**
     * Gets the root element for automation.
     *
     * @param element Pointer to the element.
     * @return Error status.
     */
    public int getRootElement(final PointerByReference element) {
        return this.automation.getRootElement(element);
    }

    /**
     * Compares 2 elements.
     *
     * @param element1 First element.
     * @param element2 Second element.
     * @param same     Are they the same.
     * @return Error status.
     */
    public int compareElements(final Pointer element1,
                               final Pointer element2,
                               final IntByReference same) {
        return this.automation.compareElements(element1, element2, same);
    }

    /**
     * Gets the instance.
     *
     * @return the instance of the ui automation library.
     */
    public static UIAutomation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIAutomation();
        }

        return INSTANCE;
    }

    /**
     * Launches the application.
     *
     * @param command The command to be called.
     * @return AutomationApplication that represents the application.
     * @throws java.io.IOException Cannot start application.
     */
    public AutomationApplication launch(final String... command)
            throws java.io.IOException {
        Process process = Utils.startProcess(command);
        return new AutomationApplication(
                new ElementBuilder(rootElement).process(process).attached(false));
    }

    public AutomationApplication launchWithRedirect(final String... command)
            throws java.io.IOException {
        Process process = Utils.startProcessWithRedirection(command);
        return new AutomationApplication(
                new ElementBuilder(rootElement).process(process).attached(false));
    }
    /**
     * Launches the application, from a given directory, with setting its working directory set to the
     * commands parent folder.
     *
     * @param command The command to be called.
     * @return AutomationApplication that represents the application.
     * @throws java.io.IOException Cannot start application.
     */
    public AutomationApplication launchWithDirectory(final String... command)
            throws java.io.IOException {
        Process process = Utils.startProcessWithWorkingDirectory(command);
        return new AutomationApplication(
                new ElementBuilder(rootElement).process(process).attached(false));
    }

    /**
     * Attaches to the application process.
     *
     * @param process Process to attach to.
     * @return AutomationApplication that represents the application.
     */
    public AutomationApplication attach(final Process process) {
        return new AutomationApplication(
                new ElementBuilder(rootElement).process(process).attached(false));
    }

    /**
     * Attaches or launches the application.
     *
     * @param command Command to be started.
     * @return AutomationApplication that represents the application.
     * @throws java.lang.Exception Unable to find process.
     */
    public AutomationApplication launchOrAttach(final String... command)
            throws Exception {
    	try {
    		return findProcess(command);
    	} catch (AutomationException ex) {
    		return this.launch(command);
    	}
    }

    /**
     * Attaches or launches the application.
     *
     * @param command Command to be started.
     * @return AutomationApplication that represents the application.
     * @throws java.lang.Exception Unable to find process.
     *
     * @deprecated Use {@link #launchWithDirectoryOrAttach(String...)} instead
     */
    @Deprecated
    public AutomationApplication launchWithWorkingDirectoryOrAttach(final String... command)
            throws Exception {
    	return launchWithDirectoryOrAttach(command);
    }

    /**
     * Attaches or launches the application, with setting its working directory set to the
     * commands parent folder.
     *
     * @param command Command to be started.
     * @return AutomationApplication that represents the application.
     * @throws java.lang.Exception Unable to find process.
     */
    public AutomationApplication launchWithDirectoryOrAttach(final String... command)
            throws Exception {
        try {
                return findProcess(command);
        } catch (AutomationException ex) {
                return this.launchWithDirectory(command);
        }
    }

    /**
     * Finds the given process.
     *
     * @param command Command to look for.
     * @return The Application.
     * @throws AutomationException If findProcessEntry throws an exception.
     */
    public AutomationApplication findProcess(final String... command)
            throws AutomationException {

        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
            new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, command);

        if (!found) {
            throw new AutomationException("Process " + Arrays.toString(command) + " not found.");
        } else {
            WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
            return new AutomationApplication(
                    new ElementBuilder(rootElement).handle(handle).attached(true));
        }
    }

    /**
     * Finds the given process.
     *
     * @param filenamePattern A pattern which matches the filename of the application
     * @return AutomationApplication that represents the application.
     * @throws java.lang.Exception Unable to find process.
     *
     */
    public AutomationApplication findProcess(final Pattern filenamePattern)
            throws Exception {
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry =
                new Tlhelp32.PROCESSENTRY32.ByReference();

        boolean found = Utils.findProcessEntry(processEntry, filenamePattern);

        if (!found) {
            throw new AutomationException("No process found matching " + filenamePattern);
        }

        WinNT.HANDLE handle = Utils.getHandleFromProcessEntry(processEntry);
        return new AutomationApplication(
                new ElementBuilder(rootElement).handle(handle).attached(true));
    }

    /**
     * Gets the desktop object associated with the title.
     *
     * @param controlType the ControlType to search for
     * @param title Title to search for.
     * @param treeScopeConstant the treescope to search
     * @param numberOfRetries the number of tries to find the window
     * @return AutomationWindow The found 'element'.
     * @throws ElementNotFoundException Element is not found.
     */
    private AutomationElement get(final ControlType controlType,
                                  final String title,
                                  final int treeScopeConstant,
                                  final int numberOfRetries)
            throws AutomationException {
        AutomationElement foundElement = null;

        // And Condition
        PointerByReference pAndCondition = this.createAndCondition(
        		this.createNamePropertyCondition(title),
        		this.createControlTypeCondition(controlType));

        for (int loop = 0; loop < numberOfRetries; loop++) {

            try {
            	foundElement = this.rootElement.findFirst(new TreeScope(treeScopeConstant), pAndCondition);
            } catch (AutomationException ex) {
                logger.info("Not found, retrying " + title);
            }

            if (foundElement != null) {
                break;
            }

            // Wait for it
            try {
                Thread.sleep(AutomationWindow.SLEEP_DURATION);
            } catch (InterruptedException e) {
                // interrupted
            }
        }

        if (foundElement == null) {
            logger.warning("Failed to find desktop window `" + title + "`");
            throw new ItemNotFoundException(title);
        }

        return foundElement;
    }

    /**
     * Gets the desktop object matching the title pattern.
     *
     * @param controlType the ControlType to search for
     * @param titlePattern the pattern to match the title.
     * @param treeScopeConstant the treescope to search
     * @param numberOfRetries the number of tries to find the window
     * @return AutomationWindow The found 'element'.
     * @throws ElementNotFoundException Element is not found.
     */
    private AutomationElement get(final ControlType controlType,
                                  final Pattern titlePattern,
                                  final int treeScopeConstant,
                                  final int numberOfRetries)
            throws AutomationException {

        AutomationElement foundElement = null;

        // And Condition
        PointerByReference condition = this.createControlTypeCondition(controlType);

        retry_loop: for (int loop = 0; loop < numberOfRetries; loop++) {

            try {
                List<AutomationElement> collection =
                		this.rootElement.findAll(new TreeScope(treeScopeConstant), condition);

                for (AutomationElement element : collection) {
                    String name = element.getName();

                    if (name != null && titlePattern.matcher(name).matches()) {
                        foundElement = element;
                        break retry_loop;
                    }
                }

            } catch (AutomationException ex) {
                logger.info("Failed");
            }

            logger.info("Not found, retrying matching " + titlePattern);

            // Wait for it
            try {
                Thread.sleep(AutomationWindow.SLEEP_DURATION);
            } catch (InterruptedException e) {
                // interrupted
                logger.info("interrupted");
            }
        }

        if (foundElement == null) {
            logger.warning("Failed to find desktop window matching `" + titlePattern + "`");
            throw new ItemNotFoundException(titlePattern.toString());
        }

        return foundElement;
    }

    /**
     * Gets the desktop 'window' associated with the title.
     *
     * @param title Title to search for.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getDesktopWindow(final String title)
            throws AutomationException {
        return getDesktopWindow(title, FIND_DESKTOP_ATTEMPTS);
    }

    /**
     * Gets the desktop 'window' associated with the title, with a variable
     * number of retries.
     *
     * @param title Title to search for.
     * @param retries Number of retries.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getDesktopWindow(final String title, final int retries)
            throws AutomationException {
        return new AutomationWindow(new ElementBuilder(this.get(ControlType.Window, title, TreeScope.Children, retries)));
    }

    /**
     * Gets the desktop 'window' matching the title pattern
     *
     * @param titlePattern a pattern matching the title.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getDesktopWindow(final Pattern titlePattern)
            throws AutomationException {
        return getDesktopWindow(titlePattern, FIND_DESKTOP_ATTEMPTS);
    }

    /**
     * Gets the desktop 'window' matching the title pattern, with a variable
     * number of retries.
     *
     * @param titlePattern a pattern matching the title.
     * @param retries Number of retries.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationWindow getDesktopWindow(final Pattern titlePattern, final int retries)
            throws AutomationException {
        return new AutomationWindow(new ElementBuilder(this.get(ControlType.Window, titlePattern, TreeScope.Children, retries)));
    }

    /**
     * Gets the 'window' associated with the title. Searches all windows currently opened windows.
     *
     * @param title Title to search for.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getWindow(final String title)
            throws AutomationException {
        return getWindow(title, FIND_DESKTOP_ATTEMPTS);
    }

    /**
     * Gets the 'window' associated with the title, with a variable
     * number of retries. Searches all windows currently opened windows.
     *
     * @param title Title to search for.
     * @param retries Number of retries.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getWindow(final String title, final int retries)
            throws AutomationException {
        return new AutomationWindow(new ElementBuilder(this.get(ControlType.Window, title, TreeScope.Descendants, retries)));
    }

    /**
     * Gets the 'window' matching the title pattern. Searches all windows currently opened windows.
     *
     * @param titlePattern a pattern matching the title.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationWindow getWindow(final Pattern titlePattern)
            throws AutomationException {
        return getWindow(titlePattern, FIND_DESKTOP_ATTEMPTS);
    }

    /**
     * Gets the 'window' matching the title pattern, with a variable
     * number of retries. Searches all windows currently opened windows.
     *
     * @param titlePattern a pattern matching the title.
     * @param retries Number of retries.
     * @return AutomationWindow The found window.
     * @throws ElementNotFoundException Element is not found
     */
    public AutomationWindow getWindow(final Pattern titlePattern, final int retries)
            throws AutomationException {
        return new AutomationWindow(new ElementBuilder(this.get(ControlType.Window, titlePattern, TreeScope.Descendants, retries)));
    }

    /**
     * Create an 'and' condition.
     *
     * @param pCondition1 First condition.
     * @param pCondition2 Second condition.
     * @return The new condition.
     * @throws AutomationException Something is wrong.
     */
    public PointerByReference createAndCondition(final PointerByReference pCondition1,
                                                 final PointerByReference pCondition2)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createAndCondition(pCondition1.getValue(), pCondition2.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Create an 'or' condition.
     *
     * @param pCondition1 First condition.
     * @param pCondition2 Second condition.
     * @return The new condition.
     * @throws AutomationException Something is wrong.
     */
    public PointerByReference createOrCondition(final PointerByReference pCondition1,
                                                final PointerByReference pCondition2)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createOrCondition(pCondition1.getValue(), pCondition2.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Creates a condition, based on control id.
     *
     * @param id The control id.
     * @return The condition.
     * @throws AutomationException Something went wrong.
     */
    public PointerByReference createControlTypeCondition(final ControlType id)
            throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, id.getValue());

        return this.createPropertyCondition(PropertyID.ControlType.getValue(), variant);
    }

    /**
     * Creates a condition, based on automation id.
     *
     * @param automationId The automation id.
     * @return The condition.
     * @throws AutomationException Something went wrong.
     */
    public PointerByReference createAutomationIdPropertyCondition(final String automationId)
            throws AutomationException {
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
     * Creates a condition, based on element name.
     *
     * @param name The name.
     * @return The condition.
     * @throws AutomationException Something went wrong.
     */
    public PointerByReference createNamePropertyCondition(final String name)
            throws AutomationException {
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
     * Creates a condition, based on element class name.
     *
     * @param className The class name.
     * @return The condition.
     * @throws AutomationException Something went wrong.
     */
	public PointerByReference createClassNamePropertyCondition(final String className)
            throws AutomationException {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(className);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            return this.createPropertyCondition(PropertyID.ClassName.getValue(), variant);
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    /**
     * Creates a property condition.
     *
     * @param id Which property to check for.
     * @param value The value of the property.
     * @return The nre condition.
     * @throws AutomationException Something has gone wrong.
     */
    public PointerByReference createPropertyCondition(final int id,
                                                      final Variant.VARIANT.ByValue value)
            throws AutomationException {
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
     * Gets the main desktop object.
     *
     * @return AutomationPanel The found object.
     */
    public AutomationPanel getDesktop() {
        return new AutomationPanel(new ElementBuilder(this.rootElement));
    }

    /**
     * Gets the desktop object (a pane) associated with the title.
     *
     * @param title Title to search for.
     * @return AutomationPanel The found object.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationPanel getDesktopObject(final String title)
            throws AutomationException {
        return getDesktopObject(title, FIND_DESKTOP_ATTEMPTS);
    }


    /**
     * Gets the desktop object (a pane) associated with the title, with a variable
     * number of retries.
     *
     * @param title Title to search for.
     * @param retries Number of retries.
     * @return AutomationPanel The found object.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationPanel getDesktopObject(final String title, final int retries)
            throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.get(ControlType.Pane, title, TreeScope.Children, retries)));
    }

    /**
     * Gets the desktop object (a pane) matching the title pattern.
     *
     * @param titlePattern a pattern matching the title.
     * @return AutomationPanel The found object.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationPanel getDesktopObject(final Pattern titlePattern)
            throws AutomationException {
        return getDesktopObject(titlePattern, FIND_DESKTOP_ATTEMPTS);
    }


    /**
     * Gets the desktop object (a pane) matching the title pattern, with a variable
     * number of retries.
     *
     * @param titlePattern a pattern matching the title.
     * @param retries Number of retries.
     * @return AutomationPanel The found object.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationPanel getDesktopObject(final Pattern titlePattern, final int retries)
            throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.get(ControlType.Pane, titlePattern, TreeScope.Children, retries)));
    }

    /**
     * Gets the desktop menu associated with the title.
     *
     * @param title Title of the menu to search for.
     * @return AutomationMenu The found menu.
     * @throws ElementNotFoundException Element is not found.
     */
    public AutomationMenu getDesktopMenu(final String title)
            throws AutomationException {
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

        return new AutomationMenu(new ElementBuilder(element));
    }

    /**
     * Gets the list of desktop windows.
     *
     * @return List of desktop windows.
     * @throws AutomationException Something has gone wrong.
     */
    public List<AutomationWindow> getDesktopWindows()
            throws AutomationException {
        List<AutomationWindow> result = new ArrayList<>();

        List<AutomationElement> collection = getRootChildren(ControlType.Window);

        for (AutomationElement element : collection) {
            result.add(new AutomationWindow(new ElementBuilder(element)));
        }

        return result;
    }

    /**
     * Gets the list of desktop objects.
     *
     * @return List of desktop object.
     * @throws AutomationException Something has gone wrong.
     */
    public List<AutomationPanel> getDesktopObjects()
            throws AutomationException {
        List<AutomationPanel> result = new ArrayList<>();

        List<AutomationElement> collection = getRootChildren(ControlType.Pane);

        for (AutomationElement element : collection) {
            result.add(new AutomationPanel(new ElementBuilder(element)));
        }

        return result;
    }

    /**
     * Gets the children of the root element.
     * @param controlType The control type to get
     * @return List of Elements
     * @throws AutomationException Something went wrong
     */
	private List<AutomationElement> getRootChildren(final ControlType controlType)
            throws AutomationException {
        PointerByReference pCondition = this.createControlTypeCondition(controlType);

		return this.rootElement.findAll(new TreeScope(TreeScope.Children), pCondition);
	}

    /**
     * Creates a true Condition.
     *
     * @return The condition.
     * @throws AutomationException Something has gone wrong.
     */
    public PointerByReference createTrueCondition()
            throws AutomationException {
        PointerByReference pTrueCondition = new PointerByReference();

        final int res = this.automation.createTrueCondition(pTrueCondition);
        if (res == 0) {
            return pTrueCondition;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Creates a false Condition.
     *
     * @return The condition.
     * @throws AutomationException Something has gone wrong.
     */
    public PointerByReference createFalseCondition()
            throws AutomationException {
        PointerByReference condition = new PointerByReference();

        final int res = this.automation.createFalseCondition(condition);
        if (res == 0) {
            return condition;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Create a NOT condition.
     *
     * @param condition The condition condition.
     * @return The new condition.
     * @throws AutomationException Something is wrong.
     */
    public PointerByReference createNotCondition(final PointerByReference condition)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.createNotCondition(condition.getValue(), pbr);
        if (res == 0) {
            return pbr;
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the element from the supplied tag.
     * @param pt The point.
     * @return The actual element under the tag.
     * @throws AutomationException The automation library returned an error
     */
    public AutomationElement getElementFromPoint(final WinDef.POINT pt)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.elementFromPoint(pt, pbr);
        if (res == 0) {
            IUIAutomationElement element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the element from the native handle.
     *
     * @param hwnd Native Handle.
     * @return The actual element under the handle.
     * @throws AutomationException The automation library returned an error.
     */
    public AutomationElement getElementFromHandle(final WinDef.HWND hwnd)
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.getElementFromHandle(hwnd, pbr);
        if (res == 0) {
            IUIAutomationElement element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the focused element.
     * @return The focused element.
     * @throws AutomationException Automation returned an error.
     */
    public AutomationElement getFocusedElement()
            throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.automation.getFocusedElement(pbr);
        if (res == 0) {
            IUIAutomationElement element = getAutomationElementFromReference(pbr);

            return new AutomationElement(element);
        } else {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the root automation element.
     *
     * @return The root element.
     */
    public AutomationElement getRootElement() {
        return this.rootElement;
    }

    /**
     * Gets the control view walker.
     * @return The tree walker object.
     * @throws AutomationException if something goes wrong.
     */
    public AutomationTreeWalker getControlViewWalker()
            throws AutomationException {
        PointerByReference pbrWalker = new PointerByReference();

        this.automation.getControlViewWalker(pbrWalker);

        Unknown unkConditionA = new Unknown(pbrWalker.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTreeWalker.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {

            IUIAutomationTreeWalker walker =
                    IUIAutomationTreeWalkerConverter.pointerToInterface(pUnknownA);

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

    /**
     * Creates a cache request.
     *
     * @return The cache request.
     * @throws AutomationException Something has gone wrong.
     */
    public PointerByReference createCacheRequest()
            throws AutomationException {
        PointerByReference request = new PointerByReference();

        final int res = this.automation.createCacheRequest(request);
        if (res == 0) {
            return request;
        } else {
            throw new AutomationException(res);
        }
    }
}

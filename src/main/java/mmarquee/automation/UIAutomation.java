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

import com.sun.jna.platform.win32.*;
import mmarquee.automation.cache.CacheRequest;
import mmarquee.automation.condition.raw.IUIAutomationCondition;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.eventhandlers.EventHandler;
import mmarquee.automation.eventhandlers.FocusChange;
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
        automation = ClassFactory.createCUIAutomation();
        rootElement = new AutomationElement(automation.getRootElement());
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
     * Create a cache request
     *
     * @return The created mmarquee.automation.cache request
     */
    public CacheRequest createCacheRequest() {
        return new CacheRequest();
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

        for (int loop = 0; loop < 25; loop++) {

            element = this.rootElement.findFirstFromRawCondition(TreeScope.TreeScope_Descendants,
                    this.automation.createAndCondition(
                            this.automation.createPropertyCondition(PropertyID.Name.getValue(), title),
                            this.automation.createPropertyCondition(PropertyID.ControlType.getValue(), ControlType.Window)));

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
        IUIAutomationCondition condition = automation.createTrueCondition();
        List<AutomationElement> collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        for (AutomationElement element : collection) {
            result.add(new AutomationWindow(element));
        }

        return result;
    }

    /**
     * Does this automation object support IUIAutomation2
     * i.e. is it Windows 8 or above?
     *
     * @return supports IUIAutomation2
     */
    public boolean supportsAutomation2() {
        return this.automation instanceof IUIAutomation2;
    }

    /**
     * Does this automation object support IUIAutomation3
     * i.e. is it Windows 8.1 or above?
     *
     * @return supports IUIAutomation3
     */
    public boolean supportsAutomation3() {
        return this.automation instanceof IUIAutomation3;
    }

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
     * @param treeScope    The treescope
     * @param eventHandler The EventHandler to add
     */
    public void addAutomationEventHandler(IUIAutomationElement sender,
                                   int eventId,
                                   TreeScope treeScope,
                                   EventHandler eventHandler) {
        this.automation.addAutomationEventHandler(eventId, sender, treeScope, null, eventHandler.getEventHandler());
    }

    /**
     * Creates the raw and condition
     * @param condition0 First condition
     * @param condition1 Second condition
     * @return The AndCondition
     */
    public IUIAutomationCondition CreateAndCondition (IUIAutomationCondition condition0,IUIAutomationCondition condition1) {
        return automation.createAndCondition(
                condition0, condition1);
    }

    public IUIAutomationCondition CreateFalseCondition () {
        return this.automation.createFalseCondition();
    }

    public IUIAutomationCondition CreateTrueCondition () {
        return this.automation.createTrueCondition();
    }

    /**
     * Getst the raw condition
     * @return the underlying IUIAutomationCondition
     */
    public IUIAutomationCondition CreateOrCondition (IUIAutomationCondition condition0,IUIAutomationCondition condition1) {
        return automation.createOrCondition(
                condition0,
                condition1);
    }

    public IUIAutomationCondition CreatePropertyCondition (int property, java.lang.Object value) {
        return automation.createPropertyCondition(property, value);
    }

    public IUIAutomationCacheRequest CreateCacheRequest() {
        return this.automation.createCacheRequest();
    }
}

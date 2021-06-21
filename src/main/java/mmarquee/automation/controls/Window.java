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
package mmarquee.automation.controls;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.uiautomation.TreeScope;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Encapsulates the 'window' element.
 *
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public class Window
        extends Container
        implements ImplementsFocus, ImplementsWindow {
    /**
     * The user32 instance.
     */
    private User32 user32;

    /**
     * The sleep duration.
     */
    public static final int SLEEP_DURATION = 500;

    /**
     * Focuses this control.
     */
    public void focus() {
        this.getElement().setFocus();
    }

    /**
     * Constructor for the Window.
     * @param builder The builder
     */
    public Window(ElementBuilder builder) {
        super(builder);

        if (builder.getHasUser32()) {
            this.user32 = builder.getUser32();
        } else {
            this.user32 = User32.INSTANCE;
        }
    }

    /**
     * Gets the status bar associated with this window.
     * @return The status bar.
     * @throws AutomationException Automation issue.
     */
    public StatusBar getStatusBar() throws AutomationException {
        PointerByReference condition = this.createTrueCondition();

        List<Element> collection =
                this.findAll(
                        new TreeScope(TreeScope.DESCENDANTS), condition);

        StatusBar found = null;

        for (Element element: collection) {
            int retVal = element.getControlType();

            if (retVal == ControlType.StatusBar.getValue()) {
                found = new StatusBar(new ElementBuilder(element));
                break;
            }
        }

        return found;
    }

    /**
     * Gets the system menu associated with this window.
     * @return The system menu.
     * @throws AutomationException Something has gone wrong.
     */
    public SystemMenu getSystemMenu() throws AutomationException {
        return (
                new SystemMenu(
                        this.getElementByControlType(
                                0, ControlType.MenuBar)));
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @return The main menu.
     * @throws AutomationException Something has gone wrong.
     */
    public MainMenu getMainMenu() throws AutomationException {
        return getMainMenu(1);
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @param offset The menu offset to get.
     * @return The main menu.
     * @throws AutomationException Something has gone wrong.
     */
    public MainMenu getMainMenu(final int offset)
            throws AutomationException {
        return (new MainMenu(
                new ElementBuilder(
                        this.getElementByControlType(offset,
                            ControlType.MenuBar)).parent(this.getElement())));
    }

    /**
     * Gets the menu associated with this window.
     * @return The menu.
     * @throws AutomationException Something has gone wrong.
     */
    public MainMenu getMenu() throws AutomationException {
        return (
                new MainMenu(
                        new ElementBuilder(
                                this.getElementByControlType(
                                        0, ControlType.Menu)).parent(
                                                this.getElement())));
    }

    /**
     * Finds the child window, using the search criteria.
     * @param search The search criteria
     * @return The child window.
     * @throws AutomationException Something has gone wrong.
     */
    public Window getWindow(final Search search)
            throws AutomationException {
        if (search.getHasName()) {
            return getWindow(search.getName());
        } else if (search.getHasNamePattern()) {
            return getWindow(search.getNamePattern());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Number of retries.
     */
    private static final int WINDOW_RETRIES = 10;

    /**
     * Finds the child window with the given title.
     * @param title Title to search for.
     * @return The child window.
     * @throws AutomationException Something has gone wrong.
     */
    public Window getWindow(final String title)
            throws AutomationException {
        Element item = null;

        for (int count = 0; count < WINDOW_RETRIES; count++) {
            try {
                item = this.findFirst(new TreeScope(TreeScope.DESCENDANTS),
                        this.createAndCondition(
                                this.createNamePropertyCondition(title),
                                this.createControlTypeCondition(
                                        ControlType.Window)));
            } catch (ElementNotFoundException ex) {
                getLogger().warn("Failed to find `" + title + "` window");
            }

            if (item != null) {
                getLogger().warn("Found window");
                break;
            } else {
                try {
                    getLogger().warn(
                            "Did not find `" + title + "` window, retrying");
                    // Wait for it
                    Thread.sleep(SLEEP_DURATION);
                } catch (InterruptedException ex) {
                    // interrupted
                }
            }
        }

        if (item == null) {
            throw new ElementNotFoundException(title);
        }

        return new Window(new ElementBuilder(item));
    }

    /**
     * Finds the child window matching the given title.
     * @param titlePattern Title to match against for.
     * @return The child window.
     * @throws AutomationException Something has gone wrong.
     */
    public Window getWindow(Pattern titlePattern)
            throws AutomationException {
        Element item = null;

        retry_loop: for (int loop = 0; loop < WINDOW_RETRIES; loop++) {
            try {
                List<Element> collection =
                		this.findAll(new TreeScope(TreeScope.DESCENDANTS),
                				this.createControlTypeCondition(
                				        ControlType.Window));
                
                for (Element element : collection) {
                    String name = element.getName();

                    if (name != null && titlePattern.matcher(name).matches()) {
                    	item = element;
                        break retry_loop;
                    }
                }
                
            } catch (AutomationException ex) {
                getLogger().info("Failed");
            }

            getLogger().warn(
                    "Did not find window matching `" + titlePattern + "`, retrying");
            // Wait for it
            try {
				Thread.sleep(SLEEP_DURATION);
			} catch (InterruptedException e) {
                // interrupted
                getLogger().info("interrupted");
			}
        }
        
        if (item == null) {
            throw new ElementNotFoundException(
                    "matching " + titlePattern.toString());
        }

        return new Window(new ElementBuilder(item));
    }

    /**
     * Get the TitleBar associated with the given name.
     * @return The TitleBar.
     * @throws AutomationException Something has gone wrong.
     */
    public TitleBar getTitleBar() throws AutomationException {
        return new TitleBar(
                new ElementBuilder(
                        this.getElementByControlType(0,
                                                     ControlType.TitleBar)));
    }

    /**
     * Sets transparency of the window.
     * @param alpha 0..255 alpha attribute.
     * @throws Win32Exception WIN32 call has failed.
     * @throws AutomationException Something is wrong in automation.
     */
    public void setTransparency(final int alpha)
            throws Win32Exception, AutomationException {
        WinDef.HWND hwnd = this.getNativeWindowHandle();

        if (user32.SetWindowLong(
                hwnd, User32.GWL_EXSTYLE, User32.WS_EX_LAYERED) == 0) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        if (!user32.SetLayeredWindowAttributes(
                hwnd, 0, (byte) alpha, User32.LWA_ALPHA)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
    }
}


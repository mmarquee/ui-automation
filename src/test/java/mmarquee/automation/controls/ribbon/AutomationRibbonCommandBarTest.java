package mmarquee.automation.controls.ribbon;

import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.PatternNotFoundException;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationRibbonCommandBarTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationRibbonCommandBarTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetRibbonCommandBar_Gets_Correct_Name() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = window.getRibbonBar();

            AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();

            String name = commandBar.name();

            assertTrue(name.equals("Ribbon"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRibbonCommandBar_Gets_WorkPane() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = window.getRibbonBar();

            AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();

            AutomationRibbonWorkPane workPane = commandBar.getRibbonWorkPane();

            String name = workPane.name();

            assertTrue(name.equals("Ribbon"));
        } finally {
            closeApplication();
        }
    }
}

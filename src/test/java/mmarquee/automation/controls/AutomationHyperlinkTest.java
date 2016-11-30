package mmarquee.automation.controls;

import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by inpwt on 30/11/2016.
 */
public class AutomationHyperlinkTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationHyperlinkTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationHyperlink hl1 = window.getHyperlink(0);

            String name = hl1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore
    public void testClick() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationHyperlink hl1 = window.getHyperlink(0);

            hl1.click();

            this.andRest(); // Wait for it??

            // This should show a popup dialog
            AutomationWindow popup1 = window.getWindow("Project1");
            AutomationButton btn1 = popup1.getButton("OK");
            btn1.click();
        } finally {
            closeApplication();
        }
    }
}

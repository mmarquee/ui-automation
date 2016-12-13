package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 11/12/2016.
 */
public class AutomationTextBoxTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationTextBoxTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTextBox cb1 = window.getTextBox(0);

            String name = cb1.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }

    }

    @Test
    public void testGetValue() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTextBox cb1 = window.getTextBox(0);

            String value = cb1.getValue();

            logger.info(value);

            assertTrue(value.equals(""));
        } finally {
            closeApplication();
        }

    }

}

package mmarquee.automation.controls;

import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark on 30/11/2016.
 */
public class AutomationTreeViewTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationTabText.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView tv1 = window.getTreeView(0);

            String name = tv1.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetItem_When_Item_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView tv1 = window.getTreeView(0);

            AutomationTreeViewItem treeItem = tv1.getItem("Sub-SubItem");

            String name = treeItem.name();

            logger.info(name);

            assertTrue(name.equals("Sub-SubItem"));
        } finally {
            closeApplication();
        }
    }

    ;
}

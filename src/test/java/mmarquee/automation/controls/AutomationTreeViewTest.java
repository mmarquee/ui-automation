package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        AutomationTreeView ctrl = new AutomationTreeView(element);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));    }

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
}

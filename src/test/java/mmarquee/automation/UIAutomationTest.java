package mmarquee.automation;

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.PointerByReference;
import junit.framework.TestCase;

/**
 * Created by inpwt on 19/07/2016.
 */
public class UIAutomationTest extends TestCase {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public void testGetInstance() {
        UIAutomation instance = UIAutomation.getInstance();

        assertTrue("instance:" + instance.toString(), instance != null);
    }

    public void testGetRootElement() {
        UIAutomation instance = UIAutomation.getInstance();

        AutomationElement root = instance.getRootElement();

        assertTrue("root:" + root.currentName(), root.currentName().equals("Desktop"));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(UIAutomationTest.class);
    }
}

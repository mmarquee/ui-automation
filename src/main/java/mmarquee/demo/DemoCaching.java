package mmarquee.demo;

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.uiautomation.*;

import java.util.List;

public class DemoCaching extends TestBase {


    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            assert application != null;
            application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);
        } catch (Throwable ex) {
            logger.error("Failed to wait properly");
        }

        try {
            // Now do some caching!!!

            AutomationWindow window = automation.getDesktopWindow("Form1");

            PointerByReference condition = automation.createTrueCondition();

            CacheRequest cacheRequest = new CacheRequest(automation);

            cacheRequest.addProperty(PropertyID.Name.getValue());

            List<AutomationElement> all =
                    window.getElement().findAll(new TreeScope(TreeScope.Children), condition, cacheRequest);

            logger.info(all.size());

            for(AutomationElement item: all) {
                logger.info(" *" +item.getCachedName());
            }

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }

}

package mmarquee.automation;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * Created by inpwt on 15/03/2016.
 */
public class TestJacob {
    public void run() {

        ActiveXComponent msui =
                new ActiveXComponent("CLSID:{944DE083-8FB8-45CF-BCB7-C477ACB2F897}");
  //              new ActiveXComponent("UIAutomationClient");
        Dispatch uiautomation = msui.getObject();

        Object root[] = new Object[1];

        Dispatch.call(uiautomation, "GetRootElement", root).toDispatch();

    }
}

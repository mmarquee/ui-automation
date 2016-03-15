package mmarquee.automation;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * Created by inpwt on 15/03/2016.
 */
public class TestJacob {
    public void run() {

        ActiveXComponent msui = new ActiveXComponent("{30CBE57D-D9D0-452A-AB13-7AC5AC4825EE}");
        Dispatch uiautomation = msui.getObject();

        Object root[] = new Object[1];

        Dispatch.call(uiautomation, "GetRootElement", root).toDispatch();

    }
}

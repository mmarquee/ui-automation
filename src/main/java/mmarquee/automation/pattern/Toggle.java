package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;
import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the toggle pattern.
 */
public class Toggle extends BasePattern {

    private IUIAutomationTogglePattern getPattern() {
        Unknown uElement = new Unknown(this.pattern);

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationTogglePattern.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationTogglePattern.Converter.PointerToIUIAutomationTogglePattern(pbr);
        } else {
            return null; // or throw exception?
        }
    }

    /**
     * Toggles the control
     */
    public void toggle () {
        ((IUIAutomationTogglePattern)(this.pattern)).toggle();
    }

    /**
     * Gets the toggled state of the control
     * @return The toggled state
     */
    public ToggleState currentToggleState() {
        return ((IUIAutomationTogglePattern)(this.pattern)).currentToggleState();
    }
}

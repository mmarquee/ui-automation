package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;
import mmarquee.automation.uiautomation.IUIAutomationTogglePatternConverter;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * @author Mark Humphreys
 * Date 25/02/2016.
 *
 * Wrapper for the toggle pattern.
 */
public class Toggle extends BasePattern {
    /**
     * Constructor for the value pattern.
     */
    public Toggle() {
        this.IID = IUIAutomationTogglePattern.IID;
    }

    /**
     * The raw pattern.
     */
    private IUIAutomationTogglePattern rawPattern;

    /**
     * Constructor for the toggle pattern.
     *
     * @param rawPattern The raw pattern.
     */
    public Toggle(final IUIAutomationTogglePattern rawPattern) {
        this.IID = IUIAutomationTogglePattern.IID;
        this.rawPattern = rawPattern;
    }

    /**
     * Gets the pattern interface.
     *
     * @return The toggle pattern interface
     * @throws AutomationException Something went wrong with the automation library.
     */
    private IUIAutomationTogglePattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return this.convertPointerToInterface(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }

    /**
     * Toggles the control.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void toggle() throws AutomationException {
        final int res = this.getPattern().toggle();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the toggled state of the control.
     *
     * @return The toggled state
     * @throws AutomationException Something has gone wrong
     */
    public ToggleState currentToggleState() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentToggleState(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ToggleState.fromInt(ibr.getValue());
    }

    /**
     * Converts the pointer to the correct interface.
     *
     * @param pUnknown The unknown pointer.
     * @return The interface from the pointer.
     */
    public IUIAutomationTogglePattern convertPointerToInterface(final PointerByReference pUnknown) {
        return IUIAutomationTogglePatternConverter.pointerToInterface(pUnknown);
    }
}

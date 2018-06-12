package mmarquee.automation.pattern;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
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
     * Constructor for the toggle pattern.
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Toggle(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationTogglePattern.IID;
        this.patternID = PatternID.Toggle;
        this.availabilityPropertyID = PropertyID.IsTogglePatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationTogglePattern rawPattern;

    /**
     * Gets the pattern interface.
     *
     * @return The toggle pattern interface
     * @throws AutomationException Something went wrong with the automation library.
     */
    private IUIAutomationTogglePattern getPattern() throws AutomationException {
        return getPattern(rawPattern,this::convertPointerToInterface);
    }

    /**
     * Toggles the control.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void toggle () throws AutomationException {
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
    IUIAutomationTogglePattern convertPointerToInterface(final PointerByReference pUnknown) {
        return IUIAutomationTogglePatternConverter.pointerToInterface(pUnknown);
    }
}

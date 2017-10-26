package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationPanel;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * @author Mark Humphreys
 * Date 02/03/2016.
 *
 * Specialised type of pane, with a specific control name
 */
public class AutomationReBar extends AutomationPanel {
	
	public final static String CLASS_NAME = "ReBarWindow32";
	
    /**
     * Construct the AutomationReBar
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationReBar(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationReBar
     * @param element The element
     * @param containerPattern The container Pattern
     * @param instance Automation instance
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationReBar(AutomationElement element, ItemContainer containerPattern, UIAutomation instance) throws PatternNotFoundException, AutomationException {
        super(element, containerPattern, instance);
        assertClassName(CLASS_NAME);
    }
}

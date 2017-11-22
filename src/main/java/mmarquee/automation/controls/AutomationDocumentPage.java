package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Represents a page in a document - i.e. a Word document.
 *
 * @author Mark Humphreys
 * Date 28/01/2017.
 *
 * It seems to be a specific set of custom elements.
 */
public class AutomationDocumentPage extends AutomationCustom {
    /**
     * Constructor for the AutomationDocumentPage.
     *
     * @param builder The builder
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationDocumentPage(ElementBuilder builder) throws PatternNotFoundException, AutomationException {
        super(builder);
    }
}

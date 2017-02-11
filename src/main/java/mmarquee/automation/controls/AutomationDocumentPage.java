package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 28/01/2017.
 *
 * Represents a page in a document - i.e. a Word document.
 *
 * It seems to be a specific set of custom elements.
 */
public class AutomationDocumentPage extends AutomationCustom {
    /**
     * Constructor for the AutomationDocumentPage
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationDocumentPage (AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationDocumentPage
     * @param element The element
     * @param container ItemContainer pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationDocumentPage (AutomationElement element, ItemContainer container) throws PatternNotFoundException, AutomationException {
        super(element, container);
    }
}

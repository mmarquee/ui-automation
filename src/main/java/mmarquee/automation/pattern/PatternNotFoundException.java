package mmarquee.automation.pattern;

import mmarquee.automation.AutomationException;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Exception to indicate that a pattern has not been found.
 */
public class PatternNotFoundException extends AutomationException {

	private static final long serialVersionUID = -6758087543897914809L;

	public PatternNotFoundException()
    {
		super("Pattern not found");
    }
	
	public PatternNotFoundException(String message)
    {
        super(message);
    }

	public PatternNotFoundException(Throwable cause)
    {
        super(cause);
    }
}

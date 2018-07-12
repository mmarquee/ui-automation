package mmarquee.automation.pattern;

import mmarquee.automation.AutomationException;

/**
 * Exception to indicate that a pattern has not been found.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 */
public class PatternNotFoundException extends AutomationException {

    /**
     * The serial version id.
     */
	private static final long serialVersionUID = -6758087543897914809L;

    /**
     * Constructor for class.
     */
	public PatternNotFoundException()
    {
		super("Pattern not found");
    }

    /**
     * Constructor for class.
     *
     * @param message The message
     */
	public PatternNotFoundException(String message)
    {
        super(message);
    }

    /**
     *  Constructor for class.
     *
     * @param cause The throwable cause
     */
	public PatternNotFoundException(Throwable cause)
    {
        super(cause);
    }
}

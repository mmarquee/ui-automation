package mmarquee.automation.pattern;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Exception to indicate that a pattern has not been found.
 */
public class PatternNotFoundException extends Exception {

	private static final long serialVersionUID = -6758087543897914809L;

	public PatternNotFoundException()
    {
		super();
    }
	
	public PatternNotFoundException(String message)
    {
        super(message);
    }
}

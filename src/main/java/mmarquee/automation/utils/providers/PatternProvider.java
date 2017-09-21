package mmarquee.automation.utils.providers;

import mmarquee.automation.pattern.BasePattern;

/** 
 * Helper interface to allow testing with mocked Pattern

 */
public interface PatternProvider {
	BasePattern getPattern();
}

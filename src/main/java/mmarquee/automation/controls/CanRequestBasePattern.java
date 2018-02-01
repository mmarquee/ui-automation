package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.BasePattern;
import mmarquee.automation.pattern.PatternNotFoundException;

public interface CanRequestBasePattern {
	public <T extends BasePattern> T requestAutomationPattern(final Class<T> automationPatternClass) throws PatternNotFoundException, AutomationException;
}

package mmarquee.scala.automation.pattern

import com.sun.jna.platform.win32.Guid
import mmarquee.automation.{PatternID, PropertyID}
import mmarquee.uiautomation.{IUIAutomationValuePattern}

class Value extends Pattern {
  override val iid: Guid.IID = IUIAutomationValuePattern.IID
  override val patternID: PatternID = PatternID.Value
  override val availabilityPropertyID = PropertyID.IsValuePatternAvailable
}

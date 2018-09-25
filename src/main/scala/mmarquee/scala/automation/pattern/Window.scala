package mmarquee.scala.automation.pattern

import com.sun.jna.platform.win32.Guid
import mmarquee.automation.{AutomationException, PatternID, PropertyID}
import mmarquee.uiautomation.{IUIAutomationWindowPattern, WindowVisualState}

class Window extends Pattern {
  override val iid: Guid.IID = IUIAutomationWindowPattern.IID
  override val patternID: PatternID = PatternID.Window
  override val availabilityPropertyID = PropertyID.IsWindowPatternAvailable

  def maximize(): Unit = {
    this.setWindowState(WindowVisualState.Maximized)
  }

  def minimize(): Unit = {
    this.setWindowState(WindowVisualState.Minimized)
  }

  def setWindowState(state: WindowVisualState): Unit = {
    val res = this.getPattern.setWindowVisualState(state.getValue)
    if (res != 0) throw new AutomationException(res)
  }
}
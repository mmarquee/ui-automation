package mmarquee.scala.automation.pattern

import java.util.function.Function

import com.sun.jna.platform.win32.COM.{COMUtils, Unknown}
import com.sun.jna.platform.win32.{Guid, WinNT}
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation.pattern.PatternNotFoundException
import mmarquee.automation.{AutomationException, BaseAutomation, PatternID, PropertyID}
import mmarquee.scala.automation.{AutomationBase, Element}

trait Pattern extends AutomationBase {
  var element: Element = _

  /**
    * The associated property id.
    */
  var availabilityPropertyID: PropertyID = _
  var iid: Guid.IID = _
  var patternID: PatternID = _

  def isAvailable: Boolean = {
    try {
      val propertyValue = this.element.getPropertyValue(availabilityPropertyID.getValue)
      BaseAutomation.isPropertyValueTrue(propertyValue)
    } catch {
      case ex: AutomationException =>
        false
    }
  }

  def getPattern(patternId: Int): PointerByReference = {
    val pbr = new PointerByReference
    val res = this.element.element.getCurrentPattern(patternId, pbr)
    if (res != 0) throw new AutomationException(res)
    pbr
  }

  def getPatternID: PatternID = this.patternID

  protected def getPattern[T](overridePattern: T,
                              convertPointerToInterface: Function[PointerByReference, T]): T = {
    if (overridePattern != null) overridePattern
    else {
      val pbr = new PointerByReference
      val result0 = this.getRawPatternPointer(pbr)
      if (COMUtils.SUCCEEDED(result0)) convertPointerToInterface.apply(pbr).asInstanceOf[T]
      else throw new AutomationException(result0.intValue)
    }
  }

  def getRawPatternPointer (pbr: PointerByReference): WinNT.HRESULT = {
    var unknown: PointerByReference = null
    try {
      unknown = this.element.getPattern(patternID.getValue).get
    } catch {
      case e: AutomationException =>
        throw new PatternNotFoundException (e)
    }
  //    	if (unknown == null) {
  //        	logger.warn("Failed to find pattern");
  //        	throw new PatternNotFoundException("Failed to find pattern");
  //    	}
    val uElement: Unknown = makeUnknown (unknown.getValue)
    uElement.QueryInterface (new Guid.REFIID (this.iid), pbr)
  }
}

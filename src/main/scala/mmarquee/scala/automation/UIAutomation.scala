package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.{COMUtils, Unknown}
import com.sun.jna.platform.win32.{Guid, WinNT}
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation.Ole32Wrapper
import mmarquee.uiautomation.{IUIAutomation, IUIAutomationConverter, IUIAutomationElement, IUIAutomationElementConverter}

class UIAutomation {

  /**
    * The wrapper for the Ole32 library.
    */
  private var ole32: Ole32Wrapper = _

  /**
    * Main automation interface.
    */
  private var automation: IUIAutomation = _

  /**
    * Initialize the automation library.
    * */
  this.initialize()

//  private var rootElement = this.getRootElement

  def initialize(): Unit = {
    this.ole32 = new Ole32Wrapper

    val pbr1: PointerByReference = new PointerByReference

    val result: WinNT.HRESULT =
      getOle32Unknown.QueryInterface(
        new Guid.REFIID(IUIAutomation.IID), pbr1)
   // if (COMUtils.SUCCEEDED(result))
      this.automation = IUIAutomationConverter.pointerToInterface(pbr1)
//    else
//      throw new Exception // Handle a bad situation
  }

  def getOle32Unknown: Unknown = {
    return ole32.getUnknown
  }

  def getRootElement(element: PointerByReference): Int =
    this.automation.getRootElement(element)

  def getRootElement: Element = {
    val pRoot = new PointerByReference

    this.getRootElement(pRoot)

    val uRoot = new Unknown(pRoot.getValue)

    val result0 =
      uRoot.QueryInterface(
        new Guid.REFIID(IUIAutomationElement.IID), pRoot)

  //  if (COMUtils.SUCCEEDED(result0))
      new Element(IUIAutomationElementConverter.pointerToInterface(pRoot))
  //  else
    //  throw new Exception // Handle a bad situation
  }

}

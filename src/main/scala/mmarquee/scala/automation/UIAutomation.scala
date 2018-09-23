package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.{COMUtils, Unknown}
import com.sun.jna.platform.win32.{Guid, WinNT}
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation._
import mmarquee.automation.controls.{AutomationWindow, ElementBuilder}
import mmarquee.scala.automation.control.Window
import mmarquee.uiautomation._

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
    if (COMUtils.SUCCEEDED(result))
      this.automation = IUIAutomationConverter.pointerToInterface(pbr1)
    else
      throw new Exception // Handle a bad situation
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

    if (COMUtils.SUCCEEDED(result0))
      new Element(IUIAutomationElementConverter.pointerToInterface(pRoot))
    else
      throw new Exception // Handle a bad situation
  }

  private val FIND_DESKTOP_ATTEMPTS = 25 // not final to be set in tests

  def getDesktopWindow(title: String) : Window =
    return getDesktopWindow(title, FIND_DESKTOP_ATTEMPTS)

  private def getDesktopWindow(title: String, retries: Integer): Window =
    new Window(this.get(ControlType.Window, title, TreeScope.Children, retries))

  @throws[AutomationException]
  private def get(controlType: ControlType, title: String,
                  treeScopeConstant: Int, numberOfRetries: Int) : Element = {
    var foundElement = null

    // And Condition
    val pAndCondition =
      this.createAndCondition(
        this.createNamePropertyCondition(title),
        this.createControlTypeCondition(controlType))
    var loop = 0
    while ( {
      loop < numberOfRetries
    }) {
//      try
      foundElement = this.getRootElement.findFirst(
        new TreeScope(treeScopeConstant), pAndCondition)
//      catch {
//        case ex: AutomationException =>
//          logger.info("Not found, retrying " + title)
//      }
//      if (foundElement != null) break //todo: break is not supported
      // Wait for it
      try
        Thread.sleep(AutomationWindow.SLEEP_DURATION)
      catch {
        case e: InterruptedException =>
        // interrupted
      }

      {
        loop += 1; loop - 1
      }
    }

    if (foundElement == null) {
      throw new ItemNotFoundException(title)
    }

    foundElement
  }


}

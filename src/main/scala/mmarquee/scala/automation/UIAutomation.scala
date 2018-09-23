package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.{COMUtils, Unknown}
import com.sun.jna.platform.win32.Variant.VARIANT
import com.sun.jna.platform.win32.{Guid, OleAuto, Variant, WinNT}
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation._
import mmarquee.scala.automation.control.Window
import mmarquee.uiautomation._

class UIAutomation {

  /**
    * The wrapper for the Ole32 library.
    */
  private var ole32: Ole32Wrapper = _

  val SLEEP_DURATION = 500

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

  def createAndCondition(pCondition1: PointerByReference, pCondition2: PointerByReference): PointerByReference = {
    val pbr = new PointerByReference
    val res = this.automation.createAndCondition(pCondition1.getValue, pCondition2.getValue, pbr)
    if (res == 0) pbr
    else throw new AutomationException(res)
  }

  def createPropertyCondition(id: Int, value: VARIANT.ByValue): PointerByReference = {
    val pCondition = new PointerByReference
    val res = this.automation.createPropertyCondition(id, value, pCondition)
    if (res == 0) {
      val unkCondition = new Unknown(pCondition.getValue)
      val pUnknown = new PointerByReference
      val result1 = unkCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown)
      if (COMUtils.SUCCEEDED(result1)) pCondition
      else throw new AutomationException(result1.intValue)
    }
    else throw new AutomationException(res)
  }

  def createNamePropertyCondition(name: String): PointerByReference = {
    val variant = new VARIANT.ByValue
    val sysAllocated = OleAuto.INSTANCE.SysAllocString(name)
    variant.setValue(Variant.VT_BSTR, sysAllocated)
    try
      this.createPropertyCondition(PropertyID.Name.getValue, variant)
    finally OleAuto.INSTANCE.SysFreeString(sysAllocated)
  }

  def createControlTypeCondition(id: ControlType): PointerByReference = {
    val variant = new VARIANT.ByValue
    variant.setValue(Variant.VT_INT, id.getValue)
    this.createPropertyCondition(PropertyID.ControlType.getValue, variant)
  }

  private def get(controlType: ControlType, title: String,
                  treeScopeConstant: Int, numberOfRetries: Int) = {
    var foundElement: Element = _

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
        Thread.sleep(SLEEP_DURATION)
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

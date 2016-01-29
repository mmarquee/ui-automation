package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{4042C624-389C-4AFC-A630-9DF854A541FC}")
public interface IUIAutomationTreeWalker extends Com4jObject {
  // Methods:
  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElement getParentElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElement getFirstChildElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationElement getLastChildElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElement getNextSiblingElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElement getPreviousSiblingElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(8)
  mmarquee.automation.uiautomation.IUIAutomationElement normalizeElement(
    mmarquee.automation.uiautomation.IUIAutomationElement element);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationElement getParentElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(10)
  mmarquee.automation.uiautomation.IUIAutomationElement getFirstChildElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(11)
  mmarquee.automation.uiautomation.IUIAutomationElement getLastChildElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(12)
  mmarquee.automation.uiautomation.IUIAutomationElement getNextSiblingElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(13)
  mmarquee.automation.uiautomation.IUIAutomationElement getPreviousSiblingElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(14)
  mmarquee.automation.uiautomation.IUIAutomationElement normalizeElementBuildCache(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * <p>
   * Getter method for the COM property "condition"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
   */

  @VTID(15)
  mmarquee.automation.uiautomation.IUIAutomationCondition condition();


  // Properties:
}

package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{73D768DA-9B51-4B89-936E-C209290973E7}")
public interface IUIAutomation3 extends mmarquee.automation.uiautomation.IUIAutomation2 {
  // Methods:
  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param textEditChangeType Mandatory mmarquee.automation.uiautomation.TextEditChangeType parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler parameter.
   */

  @VTID(64)
  void addTextEditTextChangedEventHandler(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.TextEditChangeType textEditChangeType,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
    mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler handler);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler parameter.
   */

  @VTID(65)
  void removeTextEditTextChangedEventHandler(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler handler);


  // Properties:
}

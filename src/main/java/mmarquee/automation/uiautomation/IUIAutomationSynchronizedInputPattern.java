package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{2233BE0B-AFB7-448B-9FDA-3B378AA5EAE1}")
public interface IUIAutomationSynchronizedInputPattern extends Com4jObject {
  // Methods:
  /**
   * @param inputType Mandatory mmarquee.automation.uiautomation.SynchronizedInputType parameter.
   */

  @VTID(3)
  void startListening(
    mmarquee.automation.uiautomation.SynchronizedInputType inputType);


  /**
   */

  @VTID(4)
  void cancel();


  // Properties:
}

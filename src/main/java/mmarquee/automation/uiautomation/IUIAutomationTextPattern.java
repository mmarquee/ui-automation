package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{32EBA289-3583-42C9-9C59-3B6D9A1E9B6A}")
public interface IUIAutomationTextPattern extends Com4jObject {
  // Methods:
    /**
     * @param child Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
     */

    @VTID(4)
    mmarquee.automation.uiautomation.IUIAutomationTextRange rangeFromChild(
      mmarquee.automation.uiautomation.IUIAutomationElement child);


    /**
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRangeArray
     */

    @VTID(5)
    mmarquee.automation.uiautomation.IUIAutomationTextRangeArray getSelection();


    /**
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRangeArray
     */

    @VTID(6)
    mmarquee.automation.uiautomation.IUIAutomationTextRangeArray getVisibleRanges();


    /**
     * <p>
     * Getter method for the COM property "DocumentRange"
     * </p>
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
     */

    @VTID(7)
    mmarquee.automation.uiautomation.IUIAutomationTextRange documentRange();


    /**
     * <p>
     * Getter method for the COM property "SupportedTextSelection"
     * </p>
     * @return  Returns a value of type mmarquee.automation.uiautomation.SupportedTextSelection
     */

    @VTID(8)
    mmarquee.automation.uiautomation.SupportedTextSelection supportedTextSelection();


    // Properties:
  }

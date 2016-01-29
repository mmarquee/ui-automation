package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{A543CC6A-F4AE-494B-8239-C814481187A8}")
public interface IUIAutomationTextRange extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationTextRange clone();


  /**
   * @param range Mandatory mmarquee.automation.uiautomation.IUIAutomationTextRange parameter.
   * @return  Returns a value of type int
   */

  @VTID(4)
  int compare(
    mmarquee.automation.uiautomation.IUIAutomationTextRange range);


  /**
   * @param srcEndPoint Mandatory mmarquee.automation.uiautomation.TextPatternRangeEndpoint parameter.
   * @param range Mandatory mmarquee.automation.uiautomation.IUIAutomationTextRange parameter.
   * @param targetEndPoint Mandatory mmarquee.automation.uiautomation.TextPatternRangeEndpoint parameter.
   * @return  Returns a value of type int
   */

  @VTID(5)
  int compareEndpoints(
    mmarquee.automation.uiautomation.TextPatternRangeEndpoint srcEndPoint,
    mmarquee.automation.uiautomation.IUIAutomationTextRange range,
    mmarquee.automation.uiautomation.TextPatternRangeEndpoint targetEndPoint);


  /**
   * @param textUnit Mandatory mmarquee.automation.uiautomation.TextUnit parameter.
   */

  @VTID(6)
  void expandToEnclosingUnit(
    mmarquee.automation.uiautomation.TextUnit textUnit);


  /**
   * @param attr Mandatory int parameter.
   * @param val Mandatory java.lang.Object parameter.
   * @param backward Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationTextRange findAttribute(
    int attr,
    @MarshalAs(NativeType.VARIANT) java.lang.Object val,
    int backward);


  /**
   * @param text Mandatory java.lang.String parameter.
   * @param backward Mandatory int parameter.
   * @param ignoreCase Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(8)
  mmarquee.automation.uiautomation.IUIAutomationTextRange findText(
    java.lang.String text,
    int backward,
    int ignoreCase);


  /**
   * @param attr Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(9)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getAttributeValue(
    int attr);


  /**
   * @return  Returns a value of type double[]
   */

  @VTID(10)
  double[] getBoundingRectangles();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(11)
  mmarquee.automation.uiautomation.IUIAutomationElement getEnclosingElement();


  /**
   * @param maxLength Mandatory int parameter.
   * @return  Returns a value of type java.lang.String
   */

  @VTID(12)
  java.lang.String getText(
    int maxLength);


  /**
   * @param unit Mandatory mmarquee.automation.uiautomation.TextUnit parameter.
   * @param count Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @VTID(13)
  int move(
    mmarquee.automation.uiautomation.TextUnit unit,
    int count);


  /**
   * @param endpoint Mandatory mmarquee.automation.uiautomation.TextPatternRangeEndpoint parameter.
   * @param unit Mandatory mmarquee.automation.uiautomation.TextUnit parameter.
   * @param count Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @VTID(14)
  int moveEndpointByUnit(
    mmarquee.automation.uiautomation.TextPatternRangeEndpoint endpoint,
    mmarquee.automation.uiautomation.TextUnit unit,
    int count);


  /**
   * @param srcEndPoint Mandatory mmarquee.automation.uiautomation.TextPatternRangeEndpoint parameter.
   * @param range Mandatory mmarquee.automation.uiautomation.IUIAutomationTextRange parameter.
   * @param targetEndPoint Mandatory mmarquee.automation.uiautomation.TextPatternRangeEndpoint parameter.
   */

  @VTID(15)
  void moveEndpointByRange(
    mmarquee.automation.uiautomation.TextPatternRangeEndpoint srcEndPoint,
    mmarquee.automation.uiautomation.IUIAutomationTextRange range,
    mmarquee.automation.uiautomation.TextPatternRangeEndpoint targetEndPoint);


  /**
   */

  @VTID(16)
  void select();


  /**
   */

  @VTID(17)
  void addToSelection();


  /**
   */

  @VTID(18)
  void removeFromSelection();


  /**
   * @param alignToTop Mandatory int parameter.
   */

  @VTID(19)
  void scrollIntoView(
    int alignToTop);


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(20)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getChildren();


  // Properties:
}

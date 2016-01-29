package mmarquee.automation.uiautomation;

import com4j.*;

/**
 */
public enum TreeScope implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  TreeScope_Element(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  TreeScope_Children(2),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  TreeScope_Descendants(4),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  TreeScope_Parent(8),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  TreeScope_Ancestors(16),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  TreeScope_Subtree(7),
  ;

  private final int value;
  TreeScope(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}

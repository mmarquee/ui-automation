package mmarquee.automation.uiautomation;

import com4j.*;

/**
 */
public enum SynchronizedInputType implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  SynchronizedInputType_KeyUp(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  SynchronizedInputType_KeyDown(2),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  SynchronizedInputType_LeftMouseUp(4),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  SynchronizedInputType_LeftMouseDown(8),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  SynchronizedInputType_RightMouseUp(16),
  /**
   * <p>
   * The value of this constant is 32
   * </p>
   */
  SynchronizedInputType_RightMouseDown(32),
  ;

  private final int value;
  SynchronizedInputType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}

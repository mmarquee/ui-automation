package mmarquee.automation.uiautomation;

import com4j.*;

/**
 */
public enum ProviderOptions implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  ProviderOptions_ClientSideProvider(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  ProviderOptions_ServerSideProvider(2),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  ProviderOptions_NonClientAreaProvider(4),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  ProviderOptions_OverrideProvider(8),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  ProviderOptions_ProviderOwnsSetFocus(16),
  /**
   * <p>
   * The value of this constant is 32
   * </p>
   */
  ProviderOptions_UseComThreading(32),
  /**
   * <p>
   * The value of this constant is 64
   * </p>
   */
  ProviderOptions_RefuseNonClientSupport(64),
  /**
   * <p>
   * The value of this constant is 128
   * </p>
   */
  ProviderOptions_HasNativeIAccessible(128),
  /**
   * <p>
   * The value of this constant is 256
   * </p>
   */
  ProviderOptions_UseClientCoordinates(256),
  ;

  private final int value;
  ProviderOptions(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}

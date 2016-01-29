package mmarquee.automation.uiautomation;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * The Central Class for UIAutomation
   */
  public static mmarquee.automation.uiautomation.IUIAutomation createCUIAutomation() {
    return COM4J.createInstance( mmarquee.automation.uiautomation.IUIAutomation.class, "{FF48DBA4-60EF-4201-AA87-54103EEF594E}" );
  }

  /**
   * The Central Class for UIAutomation8
   */
  public static mmarquee.automation.uiautomation.IUIAutomation2 createCUIAutomation8() {
    return COM4J.createInstance( mmarquee.automation.uiautomation.IUIAutomation2.class, "{E22AD333-B25F-460C-83D0-0581107395C9}" );
  }
}

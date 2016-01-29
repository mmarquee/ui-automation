package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{09E31E18-872D-4873-93D1-1E541EC133FD}")
public interface IUIAutomationProxyFactoryMapping extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int count();


  /**
   * @param index Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry getEntry(
    int index);


      /**
       * @param before Mandatory int parameter.
       * @param factory Mandatory mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry parameter.
       */

      @VTID(8)
      void insertEntry(
        int before,
        mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry factory);


      /**
       * @param index Mandatory int parameter.
       */

      @VTID(9)
      void removeEntry(
        int index);


      /**
       */

      @VTID(10)
      void clearTable();


      /**
       */

      @VTID(11)
      void restoreDefaultTable();


      // Properties:
    }

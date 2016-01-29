package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{30CBE57D-D9D0-452A-AB13-7AC5AC4825EE}")
public interface IUIAutomation extends Com4jObject {
  // Methods:
  /**
   * @param el1 Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param el2 Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type int
   */

  @VTID(3)
  int compareElements(
    mmarquee.automation.uiautomation.IUIAutomationElement el1,
    mmarquee.automation.uiautomation.IUIAutomationElement el2);


  /**
   * @param runtimeId1 Mandatory int[] parameter.
   * @param runtimeId2 Mandatory int[] parameter.
   * @return  Returns a value of type int
   */

  @VTID(4)
  int compareRuntimeIds(
    int[] runtimeId1,
    int[] runtimeId2);


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationElement getRootElement();


  /**
   * @param hwnd Mandatory java.nio.Buffer parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElement elementFromHandle(
    java.nio.Buffer hwnd);


    /**
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
     */

    @VTID(8)
    mmarquee.automation.uiautomation.IUIAutomationElement getFocusedElement();


    /**
     * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
     */

    @VTID(9)
    mmarquee.automation.uiautomation.IUIAutomationElement getRootElementBuildCache(
      mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


    /**
     * @param hwnd Mandatory java.nio.Buffer parameter.
     * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
     */

    @VTID(10)
    mmarquee.automation.uiautomation.IUIAutomationElement elementFromHandleBuildCache(
      java.nio.Buffer hwnd,
      mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


      /**
       * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
       */

      @VTID(12)
      mmarquee.automation.uiautomation.IUIAutomationElement getFocusedElementBuildCache(
        mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


      /**
       * @param pCondition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTreeWalker
       */

      @VTID(13)
      mmarquee.automation.uiautomation.IUIAutomationTreeWalker createTreeWalker(
        mmarquee.automation.uiautomation.IUIAutomationCondition pCondition);


      /**
       * <p>
       * Getter method for the COM property "ControlViewWalker"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTreeWalker
       */

      @VTID(14)
      mmarquee.automation.uiautomation.IUIAutomationTreeWalker controlViewWalker();


      /**
       * <p>
       * Getter method for the COM property "ContentViewWalker"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTreeWalker
       */

      @VTID(15)
      mmarquee.automation.uiautomation.IUIAutomationTreeWalker contentViewWalker();


      /**
       * <p>
       * Getter method for the COM property "RawViewWalker"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTreeWalker
       */

      @VTID(16)
      mmarquee.automation.uiautomation.IUIAutomationTreeWalker rawViewWalker();


      /**
       * <p>
       * Getter method for the COM property "RawViewCondition"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(17)
      mmarquee.automation.uiautomation.IUIAutomationCondition rawViewCondition();


      /**
       * <p>
       * Getter method for the COM property "ControlViewCondition"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(18)
      mmarquee.automation.uiautomation.IUIAutomationCondition controlViewCondition();


      /**
       * <p>
       * Getter method for the COM property "ContentViewCondition"
       * </p>
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(19)
      mmarquee.automation.uiautomation.IUIAutomationCondition contentViewCondition();


      /**
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCacheRequest
       */

      @VTID(20)
      mmarquee.automation.uiautomation.IUIAutomationCacheRequest createCacheRequest();


      /**
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(21)
      mmarquee.automation.uiautomation.IUIAutomationCondition createTrueCondition();


      /**
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(22)
      mmarquee.automation.uiautomation.IUIAutomationCondition createFalseCondition();


      /**
       * @param propertyId Mandatory int parameter.
       * @param value Mandatory java.lang.Object parameter.
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(23)
      mmarquee.automation.uiautomation.IUIAutomationCondition createPropertyCondition(
        int propertyId,
        @MarshalAs(NativeType.VARIANT) java.lang.Object value);


      /**
       * @param propertyId Mandatory int parameter.
       * @param value Mandatory java.lang.Object parameter.
       * @param flags Mandatory mmarquee.automation.uiautomation.PropertyConditionFlags parameter.
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(24)
      mmarquee.automation.uiautomation.IUIAutomationCondition createPropertyConditionEx(
        int propertyId,
        @MarshalAs(NativeType.VARIANT) java.lang.Object value,
        mmarquee.automation.uiautomation.PropertyConditionFlags flags);


      /**
       * @param condition1 Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
       * @param condition2 Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
       * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
       */

      @VTID(25)
      mmarquee.automation.uiautomation.IUIAutomationCondition createAndCondition(
        mmarquee.automation.uiautomation.IUIAutomationCondition condition1,
        mmarquee.automation.uiautomation.IUIAutomationCondition condition2);


        /**
         * @param conditions Mandatory Holder<mmarquee.automation.uiautomation.IUIAutomationCondition> parameter.
         * @param conditionCount Mandatory int parameter.
         * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
         */

        @VTID(27)
        mmarquee.automation.uiautomation.IUIAutomationCondition createAndConditionFromNativeArray(
          Holder<mmarquee.automation.uiautomation.IUIAutomationCondition> conditions,
          int conditionCount);


        /**
         * @param condition1 Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
         * @param condition2 Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
         * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
         */

        @VTID(28)
        mmarquee.automation.uiautomation.IUIAutomationCondition createOrCondition(
          mmarquee.automation.uiautomation.IUIAutomationCondition condition1,
          mmarquee.automation.uiautomation.IUIAutomationCondition condition2);


          /**
           * @param conditions Mandatory Holder<mmarquee.automation.uiautomation.IUIAutomationCondition> parameter.
           * @param conditionCount Mandatory int parameter.
           * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
           */

          @VTID(30)
          mmarquee.automation.uiautomation.IUIAutomationCondition createOrConditionFromNativeArray(
            Holder<mmarquee.automation.uiautomation.IUIAutomationCondition> conditions,
            int conditionCount);


          /**
           * @param condition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
           * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
           */

          @VTID(31)
          mmarquee.automation.uiautomation.IUIAutomationCondition createNotCondition(
            mmarquee.automation.uiautomation.IUIAutomationCondition condition);


          /**
           * @param eventId Mandatory int parameter.
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
           * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationEventHandler parameter.
           */

          @VTID(32)
          void addAutomationEventHandler(
            int eventId,
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.TreeScope scope,
            mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
            mmarquee.automation.uiautomation.IUIAutomationEventHandler handler);


          /**
           * @param eventId Mandatory int parameter.
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationEventHandler parameter.
           */

          @VTID(33)
          void removeAutomationEventHandler(
            int eventId,
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.IUIAutomationEventHandler handler);


          /**
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
           * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler parameter.
           * @param propertyArray Mandatory Holder<Integer> parameter.
           * @param propertyCount Mandatory int parameter.
           */

          @VTID(34)
          void addPropertyChangedEventHandlerNativeArray(
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.TreeScope scope,
            mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
            mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler handler,
            Holder<Integer> propertyArray,
            int propertyCount);


          /**
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
           * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler parameter.
           * @param propertyArray Mandatory int[] parameter.
           */

          @VTID(35)
          void addPropertyChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.TreeScope scope,
            mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
            mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler handler,
            int[] propertyArray);


          /**
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler parameter.
           */

          @VTID(36)
          void removePropertyChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.IUIAutomationPropertyChangedEventHandler handler);


          /**
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
           * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationStructureChangedEventHandler parameter.
           */

          @VTID(37)
          void addStructureChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.TreeScope scope,
            mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
            mmarquee.automation.uiautomation.IUIAutomationStructureChangedEventHandler handler);


          /**
           * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationStructureChangedEventHandler parameter.
           */

          @VTID(38)
          void removeStructureChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationElement element,
            mmarquee.automation.uiautomation.IUIAutomationStructureChangedEventHandler handler);


          /**
           * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationFocusChangedEventHandler parameter.
           */

          @VTID(39)
          void addFocusChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
            mmarquee.automation.uiautomation.IUIAutomationFocusChangedEventHandler handler);


          /**
           * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationFocusChangedEventHandler parameter.
           */

          @VTID(40)
          void removeFocusChangedEventHandler(
            mmarquee.automation.uiautomation.IUIAutomationFocusChangedEventHandler handler);


          /**
           */

          @VTID(41)
          void removeAllEventHandlers();


          /**
           * @param array Mandatory Holder<Integer> parameter.
           * @param arrayCount Mandatory int parameter.
           * @return  Returns a value of type int[]
           */

          @VTID(42)
          int[] intNativeArrayToSafeArray(
            Holder<Integer> array,
            int arrayCount);


                /**
                 * @param factory Mandatory mmarquee.automation.uiautomation.IUIAutomationProxyFactory parameter.
                 * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry
                 */

                @VTID(47)
                mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry createProxyFactoryEntry(
                  mmarquee.automation.uiautomation.IUIAutomationProxyFactory factory);


                /**
                 * <p>
                 * Getter method for the COM property "ProxyFactoryMapping"
                 * </p>
                 * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationProxyFactoryMapping
                 */

                @VTID(48)
                mmarquee.automation.uiautomation.IUIAutomationProxyFactoryMapping proxyFactoryMapping();


                /**
                 * @param property Mandatory int parameter.
                 * @return  Returns a value of type java.lang.String
                 */

                @VTID(49)
                java.lang.String getPropertyProgrammaticName(
                  int property);


                /**
                 * @param pattern Mandatory int parameter.
                 * @return  Returns a value of type java.lang.String
                 */

                @VTID(50)
                java.lang.String getPatternProgrammaticName(
                  int pattern);


                    /**
                     * @param value Mandatory java.lang.Object parameter.
                     * @return  Returns a value of type int
                     */

                    @VTID(53)
                    int checkNotSupported(
                      @MarshalAs(NativeType.VARIANT) java.lang.Object value);


                    /**
                     * <p>
                     * Getter method for the COM property "ReservedNotSupportedValue"
                     * </p>
                     * @return  Returns a value of type com4j.Com4jObject
                     */

                    @VTID(54)
                    com4j.Com4jObject reservedNotSupportedValue();


                    /**
                     * <p>
                     * Getter method for the COM property "ReservedMixedAttributeValue"
                     * </p>
                     * @return  Returns a value of type com4j.Com4jObject
                     */

                    @VTID(55)
                    com4j.Com4jObject reservedMixedAttributeValue();


                    /**
                     * @param accessible Mandatory mmarquee.automation.uiautomation.IAccessible parameter.
                     * @param childId Mandatory int parameter.
                     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
                     */

                    @VTID(56)
                    mmarquee.automation.uiautomation.IUIAutomationElement elementFromIAccessible(
                      mmarquee.automation.uiautomation.IAccessible accessible,
                      int childId);


                    /**
                     * @param accessible Mandatory mmarquee.automation.uiautomation.IAccessible parameter.
                     * @param childId Mandatory int parameter.
                     * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
                     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
                     */

                    @VTID(57)
                    mmarquee.automation.uiautomation.IUIAutomationElement elementFromIAccessibleBuildCache(
                      mmarquee.automation.uiautomation.IAccessible accessible,
                      int childId,
                      mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


                    // Properties:
                  }

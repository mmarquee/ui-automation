/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmarquee.automation.uiautomation;

import com4j.IID;
import com4j.VTID;


@IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}")
/**
 * Requires Windows 8 or higher
 */
public interface IUIAutomation2 extends mmarquee.automation.uiautomation.IUIAutomation {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "AutoSetFocus"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(58)
  int autoSetFocus();


  /**
   * <p>
   * Setter method for the COM property "AutoSetFocus"
   * </p>
   * @param autoSetFocus Mandatory int parameter.
   */

  @VTID(59)
  void autoSetFocus(
    int autoSetFocus);


  /**
   * <p>
   * Getter method for the COM property "ConnectionTimeout"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(60)
  int connectionTimeout();


  /**
   * <p>
   * Setter method for the COM property "ConnectionTimeout"
   * </p>
   * @param timeout Mandatory int parameter.
   */

  @VTID(61)
  void connectionTimeout(
    int timeout);


  /**
   * <p>
   * Getter method for the COM property "TransactionTimeout"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(62)
  int transactionTimeout();


  /**
   * <p>
   * Setter method for the COM property "TransactionTimeout"
   * </p>
   * @param timeout Mandatory int parameter.
   */

  @VTID(63)
  void transactionTimeout(
    int timeout);


  // Properties:
}

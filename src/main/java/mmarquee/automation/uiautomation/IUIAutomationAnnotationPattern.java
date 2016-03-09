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

import com4j.Com4jObject;
import com4j.IID;
import com4j.VTID;

@IID("{9A175B21-339E-41B1-8E8B-623F6B681098}")
public interface IUIAutomationAnnotationPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentAnnotationTypeId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int currentAnnotationTypeId();


  /**
   * <p>
   * Getter method for the COM property "CurrentAnnotationTypeName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String currentAnnotationTypeName();


  /**
   * <p>
   * Getter method for the COM property "CurrentAuthor"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(5)
  java.lang.String currentAuthor();


  /**
   * <p>
   * Getter method for the COM property "CurrentDateTime"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String currentDateTime();


  /**
   * <p>
   * Getter method for the COM property "CurrentTarget"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElement currentTarget();


  /**
   * <p>
   * Getter method for the COM property "CachedAnnotationTypeId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int cachedAnnotationTypeId();


  /**
   * <p>
   * Getter method for the COM property "CachedAnnotationTypeName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(9)
  java.lang.String cachedAnnotationTypeName();


  /**
   * <p>
   * Getter method for the COM property "CachedAuthor"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(10)
  java.lang.String cachedAuthor();


  /**
   * <p>
   * Getter method for the COM property "CachedDateTime"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(11)
  java.lang.String cachedDateTime();


  /**
   * <p>
   * Getter method for the COM property "CachedTarget"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(12)
  mmarquee.automation.uiautomation.IUIAutomationElement cachedTarget();


  // Properties:
}

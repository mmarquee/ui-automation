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

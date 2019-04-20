/*
 * Copyright 2018 inpwtepydjuf@gmail.com
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
package mmarquee.uiautomation;

import com.sun.jna.platform.win32.Guid;

/**
 * Wrapper around the IUIAutomation9 interface, only implementing the extra
 * methods.
 *
 * This interface is supported in Windows 10 [desktop apps only] upwards
 *
 * @author Mark Humphreys
 * Date 04/10/2018
 */
public interface IUIAutomationElement9 extends IUIAutomationElement8 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{39325FAC-039D-440E-A3A3-5EB81A5CECC3}");

}
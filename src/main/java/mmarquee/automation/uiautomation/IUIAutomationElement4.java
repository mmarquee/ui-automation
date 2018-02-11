/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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

import com.sun.jna.platform.win32.Guid;

/**
 * Wrapper around the IUIAutomation4 interface.
 *
 * This interface is supported in Windows 10 [desktop apps only] upwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement4 extends IUIAutomationElement3 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{3B6E233C-52FB-4063-A4C9-77C075C2A06B}");

        /*
    CachedLevel
    CachedPositionInSet
    CachedSizeOfSet
    CurrentLevel
    CurrentPositionInSet
    CurrentSizeOfSet
     */

}
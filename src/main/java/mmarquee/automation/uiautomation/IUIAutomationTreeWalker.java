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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author Mark Humphreys
 * Date 02/02/2017.
 */
public interface IUIAutomationTreeWalker extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "{4042C624-389C-4AFC-A630-9DF854A541FC}");

    int getParentElement(Pointer element, PointerByReference parent);
    int getFirstChildElement(Pointer element, PointerByReference first);
    int getLastChildElement(Pointer element, PointerByReference last);
    int getNextSiblingElement(Pointer element, PointerByReference next);
    int getPreviousSiblingElement(Pointer element, PointerByReference previous);
/* 8-14 cache equivalents */
    int getCondition(PointerByReference condition);
}

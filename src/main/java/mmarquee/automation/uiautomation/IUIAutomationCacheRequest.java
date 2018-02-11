/*
 * Copyright 2017 inpwtepydjuf@gmail.com
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

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.PointerByReference;

/**
 * Exposes properties and methods of a cache request. Client applications use this interface to specify the properties
 * and control patterns to be cached when a UI Automation element is obtained.
 *
 * @author Mark Humphreys
 * Date 16/12/2017
 */
public interface IUIAutomationCacheRequest extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "B32A92B5-BC25-4078-9C08-D7EE95C48E03");

    /**
     * Adds a control pattern to the cache request.
     * @param inVal The pattern to cache
     * @return COM result
     */
    int addPattern(int inVal);

    /**
     * Adds a property to the cache request.
     * @param inVal The property to cache
     * @return COM result
     */
    int addProperty(int inVal);

    /**
     * Creates a copy of the cache request.
     * @param retVal The cache to copy
     * @return COM resylt
     */
    int clone(PointerByReference retVal);

    /**
     * Gets the view of the UI Automation element tree that is used when caching.
     * @param inVal The value
     * @return COM result
     */
    int getTreeScope(PointerByReference inVal);

    /**
     * Specifies the view of the UI Automation element tree that is used when caching.
     * @param retVal The value
     * @return COM result
     */
    int setTreeScope(PointerByReference retVal);

    /**
     * Gets the view of the UI Automation element tree that is used when caching.
     * @param inVal The value
     * @return COM result
     */
    int getTreeFilter(PointerByReference inVal);

    /**
     * Specifies the view of the UI Automation element tree that is used when caching.
     * @param retVal The value
     * @return COM result
     */
    int setTreeFilter(PointerByReference retVal);

    /**
     * Indicates whether returned elements contain full references to the underlying UI, or only cached information.
     * @param mode The mode to use
     * @return COM result
     */
    int getAutomationElementMode(PointerByReference mode);

    /**
     * Sets whether returned elements contain full references to the underlying UI, or only cached information.
     * @param mode The mode
     * @return COM result
     */
    int setAutomationElementMode(PointerByReference mode);
}

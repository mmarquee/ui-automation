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
package mmarquee.automation.cache;

import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.condition.Condition;
import mmarquee.automation.uiautomation.IUIAutomationCacheRequest;
import mmarquee.automation.PatternID;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by inpwt on 05/05/2016.
 *
 * Wrapper for the IUIAutomationCacheRequest
 */
public class CacheRequest {

    private IUIAutomationCacheRequest cacheRequest;

    private Condition treeFilter;

    /**
     * Constructor for the cacheRequest
     */
    public CacheRequest() {
        this.cacheRequest = UIAutomation.getInstance().CreateCacheRequest();
    }

    /**
     * Get the raw cache request
     * @return The raw cache request
     */
    public IUIAutomationCacheRequest getCacheRequest() {
        return this.cacheRequest;
    }

    /**
     * Stets the tree filter condition
     * @param condition The filter condition.
     */
    public void setTreeFilter(Condition condition) {
        this.treeFilter = condition;
        this.cacheRequest.treeFilter(condition.getCondition());
    }

    /**
     * Gets the treeScope of the cache
     * @return The treeScope
     */
    public Condition getTreeFilter() {
        return this.treeFilter;
    }

    /**
     * Sets the treeScope of the cache
     * @param treeScope The treeScope to use.
     */
    public void setTreeScope(TreeScope treeScope) {
        this.cacheRequest.treeScope(treeScope);
    }

    /**
     * Gets the treeScope of the cache
     * @return The treeScope
     */
    public TreeScope getTreeScope() {
        return this.cacheRequest.treeScope();
    }

    /**
     * Adds a property to the cache request
     * @param propertyId the property to add
     */
    public void add(PropertyID propertyId) {
        this.cacheRequest.addProperty(propertyId.getValue());
    }

    /**
     * Adds a patternID to the request
     * @param patternId Pattern to add
     */
    public void add(PatternID patternId) {
        this.cacheRequest.addPattern(patternId.getValue());
    }
}

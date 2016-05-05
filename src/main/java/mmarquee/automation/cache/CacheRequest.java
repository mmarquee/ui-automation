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
import mmarquee.automation.uiautomation.IUIAutomationCacheRequest;
import mmarquee.automation.PatternID;

/**
 * Created by inpwt on 05/05/2016.
 *
 * Wrapper for the IUIAutomationCacheRequest
 */
public class CacheRequest {

    private IUIAutomationCacheRequest cacheRequest;

    /**
     * Constructor for the cacheRequest
     */
    public CacheRequest() {
        this.cacheRequest = UIAutomation.getInstance().automation.createCacheRequest();
    }

    /**
     * Adds a property to the cache request
     */
    public void Add(PropertyID propertyId) {
        this.cacheRequest.addProperty(propertyId.getValue());
    }

    /**
     * Adds a patternID to the reque
     * @param patternId
     */
    public void Add(PatternID patternId) {
        this.cacheRequest.addPattern(patternId.getValue());;
    }
}

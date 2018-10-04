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
package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationCacheRequest;
import mmarquee.automation.uiautomation.IUIAutomationCacheRequestConverter;

/**
 * Encapsulated the cache request.
 *
 * @author Mark Humphreys
 * Date 17/12/2017.
 *
 */
public class CacheRequest {

    /**
     * Makes an Unknown value fro the pointer.
     * @param pvInstance The pointer
     * @return The Unknown value
     */
    private Unknown makeUnknown(final Pointer pvInstance) {
        return new Unknown(pvInstance);
    }

    /**
     * The raw pointer.
     */
    private PointerByReference cache;

    /**
     * The cache request.
     */
    private IUIAutomationCacheRequest request;

    /**
     * Gets the value.
     * @return The value
     */
    public Pointer getValue() {
        return this.cache.getValue();
    }

    /**
     * Constructor for the CacheRequest.
     * @param automation The automation library
     * @throws AutomationException Something has gone wrong
     */
    public CacheRequest(final UIAutomation automation)
            throws AutomationException {

        this.cache = automation.createCacheRequest();

        PointerByReference pbr = new PointerByReference();

        Unknown unknown = makeUnknown(cache.getValue());

        WinNT.HRESULT result0 =
                unknown.QueryInterface(
                        new Guid.REFIID(IUIAutomationCacheRequest.IID), pbr);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException(result0.intValue());
        }

        this.request =
                IUIAutomationCacheRequestConverter.pointerToInterface(pbr);
    }

    /**
     * Adds a pattern to the request.
     * @param inVal The pattern
     */
    public void addPattern(final int inVal) {
        this.request.addPattern(inVal);
    }

    /**
     * Adds a property to the request.
     * @param inVal The property
     */
    public void addProperty(final int inVal) {
        this.request.addProperty(inVal);
    }
}

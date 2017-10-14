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
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.utils.Canalizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wrapper for the access to Ole32, which only exists on Windows, so tests can't be run on Linux, etc.
 *
 * This should allow the underlying functionality to be Mocked, and allow further testing to be carried
 * out via travis-ci (for example).
 */
public class Ole32Wrapper {
    private static Ole32 CANALIZED_OLE32_INSTANCE = null;

    private Unknown unknown = null;

    /**
     * Gets the Unknown value of the Ole32 library.
     * @return Unknown the Unknown representation of the Ole32 instance.
     */
    public Unknown getUnknown() {
        return unknown;
    }

    private ExecutorService executor;

    /**
     * Constructor for the Wrapper.
     */
    Ole32Wrapper() {
        executor = Executors.newSingleThreadExecutor();
        createWrapper();
    }

    /** 
     * Cleanup the wrapper.
     */
    public void cleanUp() {
        executor.shutdown();
    }

    /**
     * Create the wrapper of the canalized instance.
     */
    private void createWrapper() {
        CANALIZED_OLE32_INSTANCE = Canalizer.canalize(com.sun.jna.platform.win32.Ole32.INSTANCE,
                executor);

        CANALIZED_OLE32_INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_APARTMENTTHREADED);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT hr = CANALIZED_OLE32_INSTANCE.CoCreateInstance (
                IUIAutomation.CLSID,
                null,
                WTypes.CLSCTX_SERVER,
                IUIAutomation.IID,
                pbr);

        COMUtils.checkRC(hr);

        unknown = new Unknown(pbr.getValue());
    }
}

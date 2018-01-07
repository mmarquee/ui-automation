/*
 * Copyright 2016-18 inpwtepydjuf@gmail.com
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
package mmarquee.automation.accessibility;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IAccessible;
import mmarquee.automation.uiautomation.IAccessibleConverter;

/**
 * Wrapper for basic MSAA functionality.
 *
 * @author Mark Humphreys
 * Date 06/01/2018
 */
public class MSAAWrapper {

    /**
     * The OLEACC instance.
     */
    private static Oleacc oleacc;

    /**
     * Constructor for the MSAAWrapper.
     */
    public MSAAWrapper() {
        oleacc = Oleacc.INSTANCE;
    }

    /**
     * Constructor for the MSAAWrapper, primarily used for tests.
     *
     * @param oleacc The Oleacc instance to use
     */
    public MSAAWrapper(Oleacc oleacc) {
        this.oleacc = oleacc;
    }

    /**
     * Gets the version number of the OLEACC library being used.
     * @return String representation of the library version
     */
    public String getVersionInfo() {
        WinDef.DWORDByReference pbr0 = new WinDef.DWORDByReference();
        WinDef.DWORDByReference pbr1 = new WinDef.DWORDByReference();

        oleacc.GetOleaccVersionInfo(pbr0, pbr1);

        WinDef.DWORD version = pbr0.getValue();
        WinDef.DWORD build = pbr1.getValue();

        return String.format("%d.%d.%d.%d",
                version.getHigh().intValue(),
                version.getLow().intValue(),
                build.getHigh().intValue(),
                build.getLow().intValue());
    }

    public IAccessible accessibleObjectFromWindow(WinDef.HWND hwnd) {
        Guid.GUID.ByReference IID = new Guid.IID.ByReference(IAccessible.IID);

        PointerByReference ptr = new PointerByReference();

        oleacc.AccessibleObjectFromWindow(hwnd, new WinDef.DWORD(0), IID, ptr);

        return IAccessibleConverter.pointerToInterface(ptr);
    }

}

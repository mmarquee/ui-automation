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
 * Wrapper for the IUIAutomation3 COM interface.
 *
 * @author Mark Humphreys
 * Date 12/09/2016.
 *
 * Use this like:
 * PointerByReference pbr=new PointerByReference();
 * HRESULT result=SomeCOMObject.QueryInterface(IID_IUIAUTOMATION2, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation2 iua=IUIAutomation2.Converter.pointerToInterface(pbr);
 *
 */
public interface IUIAutomation2 extends IUIAutomation {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}");

/*
    function Get_AutoSetFocus(out AutoSetFocus: Integer): HResult; stdcall;
    function Set_AutoSetFocus(AutoSetFocus: Integer): HResult; stdcall;
    function Get_ConnectionTimeout(out timeout: LongWord): HResult; stdcall;
    function Set_ConnectionTimeout(timeout: LongWord): HResult; stdcall;
    function Get_TransactionTimeout(out timeout: LongWord): HResult; stdcall;
    function Set_TransactionTimeout(timeout: LongWord): HResult; stdcall;
     */
}


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
 * HRESULT result=SomeCOMObject.QueryInterface(IID, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation3 iua=IUIAutomation3.Converter.pointerToInterface(pbr);
 *
 */
public interface IUIAutomation3 extends IUIAutomation2 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{73D768DA-9B51-4B89-936E-C209290973E7}");
}


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

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/**
 * Wrapper for the IUIAutomation3 COM interface.
 *
 * @author Mark Humphreys
 * Date 12/09/2016.
 *
 * Use this like:
 * PointerByReference pbr=new PointerByReference();
 * HRESULT result=SomeCOMObject.QueryInterface(IID, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation3 iua=IUIAutomation3.Converter.PointerToInterface(pbr);
 *
 */
public interface IUIAutomation3 extends IUIAutomation2 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}");

    int AddRef();

    int Release();

    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);
}

//    class Converter {
//
//        private static int UIAutomation3_Methods  = 66;
//        // 0-2 IUnknown, 3-57 IUIAutomation, 58-63 = IUIAutomation2, 64-65 = IUIAutomation3
//        private static Pointer myInterfacePointer;
//
//        public static IUIAutomation3 PointerToInterface(final PointerByReference ptr) {
//            myInterfacePointer = ptr.getValue();
//            Pointer vTablePointer = myInterfacePointer.getPointer(0);
//
  //          final Pointer[] vTable = new Pointer[UIAutomation3_Methods];
    //        vTablePointer.read(0, vTable, 0, vTable.length);
      //      return new IUIAutomation3() {
//
  //              @Override
    //            public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
      //              Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
        //            return new WinNT.HRESULT(f.invokeInt(new Object[]{myInterfacePointer, byValue, pointerByReference}));
          //      }
//
  //              @Override
    //            public int AddRef() {
      //              Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
        //            return f.invokeInt(new Object[]{myInterfacePointer});
          //      }
//
  //              public int Release() {
    //                Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
      //              return f.invokeInt(new Object[]{myInterfacePointer});
        //        }
          //  };
//        }
//    }
//}


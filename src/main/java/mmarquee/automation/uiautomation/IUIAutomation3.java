package mmarquee.automation.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 12/09/2016.
 *
 * Use this like:
 * PointerByReference pbr=new PointerByReference();
 * HRESULT result=SomeCOMObject.QueryInterface(IID, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation3 iua=IUIAutomation3.Converter.PointerToInterface(pbr);
 *
 */
public interface IUIAutomation3 extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}");

    class Converter {

        private static int UIAutomation3_Methods  = 66;
        // 0-2 IUnknown, 3-57 IUIAutomation, 58-63 = IUIAutomation2, 64-65 = IUIAutomation3
        private static Pointer myInterfacePointer;

        public static IUIAutomation3 PointerToInterface(final PointerByReference ptr) {
            myInterfacePointer = ptr.getValue();
            Pointer vTablePointer = myInterfacePointer.getPointer(0);

            final Pointer[] vTable = new Pointer[UIAutomation3_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomation3() {

                @Override
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{myInterfacePointer, byValue, pointerByReference}));
                }

                @Override
                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }
            };
        }
    }
}


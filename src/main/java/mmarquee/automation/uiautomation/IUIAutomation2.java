package mmarquee.automation.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 12/09/2016.
 *
 * Use this like:
 * PointerByReference pbr=new PointerByReference();
 * HRESULT result=SomeCOMObject.QueryInterface(IID_IUIAUTOMATION2, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation2 iua=IUIAutomation2.Converter.PointerToInterface(pbr);
 *
 */
public interface IUIAutomation2 extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{34723AFF-0C9D-49D0-9896-7AB52DF8CD8A}");

    int AddRef();
    int Release();
    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);

    class Converter {

        private static int UIAutomation2_Methods  = 64; // 0-2 IUnknown, 3-57 IUIAutomation, 58-63 = IUIAutomation2
        private static Pointer myInterfacePointer;

        public static IUIAutomation2 PointerToInterface(final PointerByReference ptr) {
            myInterfacePointer = ptr.getValue();
            Pointer vTablePointer = myInterfacePointer.getPointer(0);

            final Pointer[] vTable = new Pointer[UIAutomation2_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomation2() {

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


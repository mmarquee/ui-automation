package mmarquee.automation.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 04/02/2017.
 */
public interface IUIAutomationEventHandler extends IUnknown {

    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "{146C3C17-F12E-4E22-8C27-F894B9B79C69}");

    int AddRef();
    int Release();
    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);

    int handleAutomationEvent(PointerByReference sender, IntByReference event);

    class Converter {
        private static int IUIAutomationEventHandlerArray_Methods = 4; // 0-2 IUnknown, 3 IUIAutomationEventHandler

        public static IUIAutomationEventHandler PointerToInterface(final PointerByReference ptr) {
            final Pointer interfacePointer = ptr.getValue();
            final Pointer vTablePointer = interfacePointer.getPointer(0);
            final Pointer[] vTable = new Pointer[IUIAutomationEventHandlerArray_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomationEventHandler() {
                // IUnknown
                @Override
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, byValue, pointerByReference}));
                }

                @Override
                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int handleAutomationEvent(PointerByReference sender, IntByReference event) {
                    Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, sender, event});
                }
            };
        }
    }
}
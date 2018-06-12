package mmarquee.automation.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author Mark Humphreys
 * Date 05/06/2017.
 */
public class IUIAutomationWindowPatternConverter {
    private static int METHODS = 18; // 0-2 IUnknown, 3-17 IUIAutomationInvokePattern

    public static IUIAutomationWindowPattern pointerToInterface(final PointerByReference ptr) {
        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[METHODS];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationWindowPattern() {
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

            public int close() {
                Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int waitForInputIdle(Integer milliseconds, IntByReference success) {
                Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, milliseconds, success});
            }

            public int setWindowVisualState(Integer state){
                Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, state});
            }

            public int getCurrentCanMaximize(IntByReference retVal){
                Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentCanMinimize(IntByReference retVal){
                Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsModal(IntByReference retVal) {
                Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsTopmost(IntByReference retVal) {
                Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
        };
    }
}

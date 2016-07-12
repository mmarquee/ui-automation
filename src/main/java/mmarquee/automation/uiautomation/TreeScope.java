package mmarquee.automation.uiautomation;

import java.util.List;
import java.util.Arrays;

import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinNT.*;
import com.sun.jna.platform.win32.OaIdl.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.Variant.VARIANT;
import com.sun.jna.platform.win32.WTypes.*;
import com.sun.jna.platform.win32.COM.*;
import com.sun.jna.platform.win32.Guid.*;

/**
 * uuid({00000000-0000-0000-0000-000000000000})
 * helpstring()
 *
 */
public class TreeScope extends Structure {
    public static class ByReference extends TreeScope implements
            Structure.ByReference {
    }

    public int value;

    public  TreeScope() {
    }

    public  TreeScope(int value) {
        this.value = value;
    }

    public  TreeScope(Pointer pointer) {
        super(pointer);
        this.read();
    }

    //TreeScope_Element
    public static final int TreeScope_Element = 1;
    //TreeScope_Children
    public static final int TreeScope_Children = 2;
    //TreeScope_Descendants
    public static final int TreeScope_Descendants = 4;
    //TreeScope_Parent
    public static final int TreeScope_Parent = 8;
    //TreeScope_Ancestors
    public static final int TreeScope_Ancestors = 16;
    //TreeScope_Subtree
    public static final int TreeScope_Subtree = 7;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] { "value" });
    }
}
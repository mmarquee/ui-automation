package mmarquee.automation.structure;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

public class PointNativeLong extends Structure implements Structure.ByReference {
    public static final List<String> FIELDS = createFieldsOrder(new String[]{"x", "y"});
    public NativeLong x;
    public NativeLong y;

    public PointNativeLong() {
    }

    public PointNativeLong(Pointer memory) {
        super(memory);
        this.read();
    }

    public PointNativeLong(double x, double y) {
        this.x = new NativeLong((long) x);
        this.y = new NativeLong((long) y);
    }

    protected List<String> getFieldOrder() {
        return FIELDS;
    }

    public class ByValue extends PointNativeLong implements Structure.ByValue {
        public ByValue(Pointer pointer) {
            super(pointer);
            this.read();
        }
    }
}

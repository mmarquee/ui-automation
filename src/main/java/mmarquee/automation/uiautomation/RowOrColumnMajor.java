package mmarquee.automation.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mark Humphreys on 12/09/2016.
 */
public enum RowOrColumnMajor {
    RowMajor(0),
    ColumnMajor(1),
    Indeterminate(2);

    private int value;

    public int getValue() {
        return this.value;
    }

    RowOrColumnMajor (int value) {
        this.value = value;
    }

    private static final Map<Integer, RowOrColumnMajor> intToTypeMap = new HashMap<Integer, RowOrColumnMajor>();
    static {
        for (RowOrColumnMajor type : RowOrColumnMajor.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer
     * @param i The given integer
     * @return The value (as an RowOrColumnMajor)
     */
    public static RowOrColumnMajor fromInt(int i) {
        RowOrColumnMajor type = intToTypeMap.get(Integer.valueOf(i));
        if (type == null)
            return RowOrColumnMajor.Indeterminate;
        return type;
    }
}

package mmarquee.automation.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark Humphreys
 * Date 12/09/2016.
 */
public enum RowOrColumnMajor {
    /**
     * Row is major.
     */
    RowMajor(0),

    /**
     * Column is major.
     */
    ColumnMajor(1),

    /**
     * Indeterminate major.
     */
    Indeterminate(2);

    /**
     * The value.
     */
    private int value;

    /**
     * Gets the value.
     * @return The value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for RowOrColumnMajor.
     * @param inValue The value.
     */
    RowOrColumnMajor (final int inValue) {
        this.value = inValue;
    }

    private static final Map<Integer, RowOrColumnMajor> intToTypeMap = new HashMap<>();
    static {
        for (RowOrColumnMajor type : RowOrColumnMajor.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     *
     * @param i The given integer
     * @return The value (as an RowOrColumnMajor)
     */
    public static RowOrColumnMajor fromInt(int i) {
        RowOrColumnMajor type = intToTypeMap.get(i);
        if (type == null)
            return RowOrColumnMajor.Indeterminate;
        return type;
    }
}

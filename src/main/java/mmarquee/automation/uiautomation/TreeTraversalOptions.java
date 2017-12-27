package mmarquee.automation.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines values that can be used to customize tree navigation order.
 *
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public enum TreeTraversalOptions {
    /**
     * Pre-order, visit children from first to last.
     */
    Default(0),

    /**
     * Post-order.
     */
    PostOrder(1),

    /**
     * Visit children from last to first.
     */
    LastToFirstOrder(2);

    /**
     * The underlying value.
     */
    private int value;

    /**
     * Gets the value associated with this enumeration.
     * @return The associated value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for TreeTraversalOptions.
     * @param inValue The value of the object.
     */
    TreeTraversalOptions(final int inValue) {
        this.value = inValue;
    }

    private static final Map<Integer, TreeTraversalOptions> IntToTypeMap =
            new HashMap<>();

    static {
        for (TreeTraversalOptions type : TreeTraversalOptions.values()) {
            IntToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     * @param value The given integer.
     * @return The value (as an TreeTraversalOptions).
     */
    public static TreeTraversalOptions fromInt(final int value) {
        TreeTraversalOptions type = IntToTypeMap.get(value);
        if (type == null) {
            return TreeTraversalOptions.Default;
        }

        return type;
    }
}

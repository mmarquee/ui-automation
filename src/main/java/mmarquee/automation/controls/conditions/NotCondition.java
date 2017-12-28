package mmarquee.automation.controls.conditions;

import com.sun.jna.ptr.PointerByReference;

public class NotCondition extends Condition {
    public NotCondition(PointerByReference inCondition) {
        super(inCondition);
    }
}

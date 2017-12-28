package mmarquee.automation.controls.conditions;

import com.sun.jna.ptr.PointerByReference;

public class BooleanCondition extends Condition {
    public BooleanCondition(PointerByReference inCondition) {
        super(inCondition);
    }

//    @Override
//    public boolean getValue() {
//        int value = -1;
//
//        ((IUIAutomationBoolCondition)this.condition).getBooleanValue(value);
//
//        return (value == 1);
//    }
}

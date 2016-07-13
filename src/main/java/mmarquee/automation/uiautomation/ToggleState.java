package mmarquee.automation.uiautomation;

/**
 * Created by inpwt on 13/07/2016.
 */
public enum ToggleState {
    ToggleState_Off(0),
    ToggleState_On(1),
    ToggleState_Indeterminate(2);

    private int value;

    public int getValue() {
        return this.value;
    }

    ToggleState (int value) {
        this.value = value;
    }
}
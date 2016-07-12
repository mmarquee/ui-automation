/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.pattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the value pattern.
 */
public class Value extends BasePattern {
    /**
     * Get the current value of the control
     * @return The current value
     */
    public String value() {
        return ((IUIAutomationValuePattern)pattern).currentValue();
    }

    /**
     * Gets the current readonly status of the control
     * @return True if read-only
     */
    public int isReadOnly() {
        return ((IUIAutomationValuePattern)pattern).currentIsReadOnly();
    }

    /**
     * Sets the value of the control
     * @param value Value to use
     */
    public void setValue(String value) {
        ((IUIAutomationValuePattern)pattern).setValue(value);
    }
}

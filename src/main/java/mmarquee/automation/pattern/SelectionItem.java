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
 * Wrapper for the SelectionItem pattern.
 */
public class SelectionItem extends BasePattern {
    /**
     * Selects the given item
     */
    public void select () {
        ((IUIAutomationSelectionItemPattern)pattern).select();
    }

    /**
     * Is the control selected
     * @return True if selected
     */
    public boolean isSelected () {
        return ((IUIAutomationSelectionItemPattern)pattern).currentIsSelected() == 1.;
    }
}

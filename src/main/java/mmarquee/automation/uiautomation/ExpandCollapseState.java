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
package mmarquee.automation.uiautomation;

/**
 */
public enum ExpandCollapseState {
    ExpandCollapseState_Collapsed(0),
    ExpandCollapseState_Expanded(1),
    ExpandCollapseState_PartiallyExpanded(2),
    ExpandCollapseState_LeafNode(3);

    private int value;

    public int getValue() {
        return this.value;
    }

    ExpandCollapseState(int value) {
        this.value = value;
    }
}

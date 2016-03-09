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

import mmarquee.automation.uiautomation.IUIAutomationTextPattern;
import mmarquee.automation.uiautomation.IUIAutomationTextRange;
import mmarquee.automation.uiautomation.IUIAutomationTextRangeArray;

/**
 * Created by inpwt on 25/02/2016.
 */
public class Text extends BasePattern {
    //?? Needs to do SOMETHING!!!
    public void getSelection() {
        IUIAutomationTextRangeArray selection =
                ((IUIAutomationTextPattern)pattern).getSelection();

        // OK, now what?
    }

    public void getDocumentRange () {
        ((IUIAutomationTextPattern)pattern).getSelection();
    }

    public String getText() {
        IUIAutomationTextRange range = ((IUIAutomationTextPattern)pattern).documentRange();

        String text = range.getText(-1);

        return range.getText(-1);
    }
}

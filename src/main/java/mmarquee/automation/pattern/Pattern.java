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

import com4j.Com4jObject;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 24/02/2016.
 */
public abstract class Pattern {
    protected IUIAutomation uiAuto;

    public Pattern (IUIAutomation uiAuto) {
        this.uiAuto = uiAuto;
    }

    public Com4jObject getPattern() {
        return null;
    }
}

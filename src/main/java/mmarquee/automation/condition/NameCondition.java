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
package mmarquee.automation.condition;

import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 24/02/2016.
 */
public class NameCondition  extends PropertyCondition {
    private String name;

    public NameCondition (IUIAutomation uiAuto) {
        super(uiAuto);
        this.property = PropertyID.Name;
    }

    public NameCondition (IUIAutomation uiAuto, String name) {
        super(uiAuto);
        this.name = name;
        this.property = PropertyID.Name;
    }

    public IUIAutomationCondition getCondition () {
        return uiAuto.createPropertyCondition(this.property, this.name);
    }
}

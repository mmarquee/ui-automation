/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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

package mmarquee.automation.controls;

/**
 * Wrapper for the ProgressBar control.
 *
 * @author Mark Humphreys
 * Date 25/04/2016.
 */
public final class AutomationProgressBar extends AutomationBase
        implements ImplementsRangeValue {
	
    /**
     * Construct the AutomationPanel.
     *
     * @param builder The builder
     */
    AutomationProgressBar(final ElementBuilder builder) {
        super(builder);
    }
}

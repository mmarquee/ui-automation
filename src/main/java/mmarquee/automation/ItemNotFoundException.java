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
package mmarquee.automation;

/**
 * Created by Mark Humphreys on 26/02/2016.
 *
 * Thrown when an item is not found.
 */
public class ItemNotFoundException extends AutomationException {

    private static final long serialVersionUID = -7175159800746711939L;

    public ItemNotFoundException(String name) {
        super("Item " + name + " not found");
    }

    public ItemNotFoundException(int index) {
        super("Item " + index + " not found");
    }
}

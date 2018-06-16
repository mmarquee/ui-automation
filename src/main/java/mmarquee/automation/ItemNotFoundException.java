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
 * Thrown when an item is not found.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 */
public class ItemNotFoundException extends AutomationException {

    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = -7175159800746711939L;

    /**
     * Constructor, with a name of the not found item.
     * @param name The element name
     */
    public ItemNotFoundException(String name) {
        super("Item " + name + " not found");
    }

    /**
     * Constructor, with the index of the not found item.
     * @param index The index
     */
    public ItemNotFoundException(int index) {
        super("Item " + index + " not found");
    }
}

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
 * Thrown when an element is not found.
 *
 * @author Mark Humphreys
 * Date 06/03/2016.
 */
public class ElementNotFoundException extends AutomationException {

    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 5575725853478222085L;

    /**
     * Default constructor.
     */
    public ElementNotFoundException() {
        super("Element not found");
    }

    /**
     * Constructor, with a name of the not found element.
     * @param name The element name
     */
    public ElementNotFoundException(String name) {
        super("Element " + name +" not found");
    }

    /**
     * Constructor, with a cause.
     * @param cause The cause
     */
    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }
}

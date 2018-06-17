/*
 * Copyright 2017-18 inpwtepydjuf@gmail.com
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
 * Meta Data Identifiers.
 *
 * @author Mark Humphreys
 * Date 24/12/2017.
 */
public enum MetaDataID {
    /**
     * Say as interpret as.
     */
    SayAsInterpretAs(100000);

    /**
     * The actual value.
     */
    private final int value;

    /**
     * Gets the value.
     * @return The actual value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for MetaDataID.
     *
     * @param theValue Initial value.
     */
    MetaDataID(int theValue) {
        this.value = theValue;
    }
}

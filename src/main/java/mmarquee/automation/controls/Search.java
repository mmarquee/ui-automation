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

import java.util.regex.Pattern;

/**
 * The search criteria for the standard, consistent searching.
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 */
public class Search {
    /**
     * Automation id to search for.
      */
    private String automationId;

    /**
     * Name part of the search.
     */
    private String name;

    /**
     * Id (index) search criteria.
     */
    private int id;

    /**
     * Classname criteria.
     */
    private String className;

    /**
     * Name pattern (regex expression) criteria.
     */
    private Pattern namePattern;

    /**
     * Control type search criteria.
     */
    private String controlType;

    /**
     * The builder for the Search criteria.
     */
    public static class Builder {
        /**
         * Automation id to search for.
         */
        private String automationId;

        /**
         * Name part of the search.
         */
        private String name;

        /**
         * Id (index) search criteria.
         */
        private int id;

        /**
         * Classname criteria.
         */
        private String className;

        /**
         * Name pattern (regex expression) criteria.
         */
        private Pattern namePattern;

        /**
         * Control type search criteria.
         */
        private String controlType;

        /**
         * Default constructor for the search criteria.
         */
        public Builder() {
            this.automationId = "";
            this.name = "";
            this.id = -1;
            this.className = "";
            this.namePattern = null;
            this.controlType = "";
        }

        /**
         * Constructor with a name criteria.
         * @param inName The name
         */
        public Builder(String inName ) {
            super();
            this.name = inName;
        }

        /**
         * Constructor with a name pattern criteria.
         * @param inPattern The name pattern
         */
        public Builder(Pattern inPattern) {
            super();
            this.namePattern = inPattern;
        }

        /**
         * Constructor with an id (index) criteria.
         * @param inIndex The id (index)
         */
        public Builder(int inIndex) {
            super();
            this.id = inIndex;
        }

        /**
         * Sets the name for the search criteria.
         * @param inName The name value
         * @return The Builder created
         */
        public Builder name(String inName) {
            this.name = inName;
            return this;
        }

        /**
         * Sets the automation id for the search criteria.
         * @param inAutomationId The name value
         * @return The Builder created
         */
        public Builder automationId(String inAutomationId) {
            this.automationId = inAutomationId;
            return this;
        }

        /**
         * Sets the class name for the search criteria.
         * @param inClassName The name value
         * @return The Builder created
         */
        public Builder className(String inClassName) {
            this.className = inClassName;
            return this;
        }

        /**
         * Sets the name pattern for the search criteria.
         * @param inNamePattern The name value
         * @return The Builder created
         */
        public Builder namePattern(Pattern inNamePattern) {
            this.namePattern = inNamePattern;
            return this;
        }

        /**
         * Sets the id (index) for the search criteria.
         * @param inID The name value
         * @return The Builder created
         */
        public Builder id(int inID) {
            this.id = inID;
            return this;
        }

        /**
         * Sets the control type for the search criteria.
         * @param inControlType The name value
         * @return The Builder created
         */
        public Builder controlType(String inControlType) {
            this.controlType = inControlType;
            return this;
        }

        /**
         * Builds the search criteria.
         * @return The Search Criteria created
         */
        public Search build() {
            return new Search(this);
        }
    }

    /**
     * Construct a search criteria from the builder.
     * @param builder The search builder
     */
    private Search(Builder builder) {
        this.automationId = builder.automationId;
        this.name = builder.name;
        this.className = builder.className;
        this.id = builder.id;
        this.namePattern = builder.namePattern;
        this.controlType = builder.controlType;
    }

    public boolean getHasAutomationId() {
        return !this.automationId.equals("");
    }

    public boolean getHasId() {
        return this.id != -1;
    }

    public boolean getHasPattern() {
        return this.namePattern != null;
    }

    public boolean getHasName() {
        return !this.name.equals("");
    }

    public boolean getHasControlType() {
        return !this.controlType.equals("");
    }

    public boolean getHasClassName() {
        return !this.className.equals("");
    }

    public String getAutomationId() {
        return this.automationId;
    }

    public Pattern getPattern() {
        return this.namePattern;
    }

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return this.className;
    }

    public String getControlType() {
        return this.controlType;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Creates a new builder object.
     * @return The new builder object
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param name The name criteria
     * @return The new builder object
     */
    public static Builder getBuilder(String name) {
        return new Builder(name);
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param id The id (index) criteria
     * @return The new builder object
     */
    public static Builder getBuilder(int id) {
        return new Builder(id);
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param pattern The pattern criteria
     * @return The new builder object
     */
    public static Builder getBuilder(Pattern pattern) {
        return new Builder(pattern);
    }
}

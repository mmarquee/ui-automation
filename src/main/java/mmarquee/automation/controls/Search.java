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
    private final String automationId;

    /**
     * Name part of the search.
     */
    private final String name;

    /**
     * Index search criteria.
     */
    private final int index;

    /**
     * Classname criteria.
     */
    private final String className;

    /**
     * Name pattern (regex expression) criteria.
     */
    private final Pattern namePattern;

    /**
     * Control type search criteria.
     */
    private final String controlType;

    /**
     * Row criteria.
     */
    private final int row;

    /**
     * Column criteria.
     */
    private final int column;

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
         * Index search criteria.
         */
        private int index;

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
         * Row criteria.
         */
        private int row;

        /**
         * Column criteria.
         */
        private int column;

        /**
         * Initialise the search criteria.
         */
        private void initialise() {
            this.automationId = "";
            this.name = "";
            this.index = -1;
            this.className = "";
            this.namePattern = null;
            this.controlType = "";
            this.row = -1;
            this.column = -1;
        }

        /**
         * Default constructor for the search criteria.
         */
        public Builder() {
            this.initialise();
        }

        /**
         * Constructor with a name criteria.
         * @param inName The name
         */
        public Builder(final String inName) {
            this.initialise();
            this.name = inName;
        }

        /**
         * Constructor with a name pattern criteria.
         * @param inPattern The name pattern
         */
        public Builder(final Pattern inPattern) {
            this.initialise();
            this.namePattern = inPattern;
        }

        /**
         * Constructor with an id (index) criteria.
         * @param inIndex The id (index)
         */
        public Builder(final int inIndex) {
            this.initialise();
            this.index = inIndex;
        }

        /**
         * Constructor with x,y coordinates criteria.
         * @param inRow The X value
         * @param inColumn The Y value
         */
        public Builder(final int inRow, final int inColumn) {
            this.initialise();
            this.row = inRow;
            this.column = inColumn;
        }

        /**
         * Sets the name for the search criteria.
         * @param inName The name value
         * @return The Builder created
         */
        public Builder name(final String inName) {
            this.name = inName;
            return this;
        }

        /**
         * Sets the automation id for the search criteria.
         * @param inAutomationId The name value
         * @return The Builder created
         */
        public Builder automationId(final String inAutomationId) {
            this.automationId = inAutomationId;
            return this;
        }

        /**
         * Sets the class name for the search criteria.
         * @param inClassName The name value
         * @return The Builder created
         */
        public Builder className(final String inClassName) {
            this.className = inClassName;
            return this;
        }

        /**
         * Sets the x,y coordinates for the search criteria.
         * @param inRow The X value
         * @param inColumn The Y value
         * @return The Builder created
         */
        public Builder coordinates(final int inRow,
                                   final int inColumn) {
            this.row = inRow;
            this.column = inColumn;
            return this;
        }

        /**
         * Sets the name pattern for the search criteria.
         * @param inNamePattern The name value
         * @return The Builder created
         */
        public Builder namePattern(final Pattern inNamePattern) {
            this.namePattern = inNamePattern;
            return this;
        }

        /**
         * Sets the index for the search criteria.
         * @param inIndex The index value
         * @return The Builder created
         */
        public Builder index(final int inIndex) {
            this.index = inIndex;
            return this;
        }

        /**
         * Sets the control type for the search criteria.
         * @param inControlType The name value
         * @return The Builder created
         */
        public Builder controlType(final String inControlType) {
            this.controlType = inControlType;
            return this;
        }

        /**
         * Sets the row for the search criteria.
         * @param inRow The row criteria
         * @return The Builder created
         */
        public Builder row(final int inRow) {
            this.row = inRow;
            return this;
        }

        /**
         * Sets the column for the search criteria.
         * @param inColumn The column criteria
         * @return The Builder created
         */
        public Builder column(final int inColumn) {
            this.column = inColumn;
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
    private Search(final Builder builder) {
        this.automationId = builder.automationId;
        this.name = builder.name;
        this.className = builder.className;
        this.index = builder.index;
        this.namePattern = builder.namePattern;
        this.controlType = builder.controlType;
        this.row = builder.row;
        this.column = builder.column;
    }

    /**
     * Does the search criteria have an automation id.
     * @return True is present
     */
    public boolean getHasAutomationId() {
        return !this.automationId.equals("");
    }

    /**
     * Does the search have an index.
     * @return True or false
     */
    public boolean getHasIndex() {
        return this.index != -1;
    }

    /**
     * Does the search criteria have a row.
     * @return True is present
     */
    public boolean getHasRow() {
        return this.row != -1;
    }

    /**
     * Does the search criteria have a column.
     * @return True is present
     */
    public boolean getHasColumn() {
        return this.column != -1;
    }

    /**
     * Does the search criteria have a name pattern.
     * @return True is present
     */
    public boolean getHasNamePattern() {
        return this.namePattern != null;
    }

    /**
     * Does the search criteria have a name.
     * @return True is present
     */
    public boolean getHasName() {
        return !this.name.equals("");
    }

    /**
     * Does the search criteria have a control type.
     * @return True is present
     */
    public boolean getHasControlType() {
        return !this.controlType.equals("");
    }

    /**
     * Does the search criteria have a class name.
     * @return True is present
     */
    public boolean getHasClassName() {
        return !this.className.equals("");
    }

    /**
     * Gets the automation id.
     * @return The automation id
     */
    public String getAutomationId() {
        return this.automationId;
    }

    /**
     * Gets the name pattern.
     * @return The name pattern
     */
    public Pattern getNamePattern() {
        return this.namePattern;
    }

    /**
     * Gets the name criteria.
     * @return The name value
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the classname criteria.
     * @return The criteria
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * Gets the control=type criteria.
     * @return The criteria
     */
    public String getControlType() {
        return this.controlType;
    }

    /**
     * Gets the index criteria.
     * @return The index value
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Gets the row criteria.
     * @return The row value
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column criteria.
     * @return The column value
     */
    public int getColumn() {
        return this.column;
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
    public static Builder getBuilder(final String name) {
        return new Builder(name);
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param id The id (index) criteria
     * @return The new builder object
     */
    public static Builder getBuilder(final int id) {
        return new Builder(id);
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param inRow The offset criteria
     * @param inColumn The offset criteria
     * @return The new builder object
     */
    public static Builder getBuilder(final int inRow,
                                     final int inColumn) {
        return new Builder(inRow, inColumn);
    }

    /**
     * Creates a new builder object, with a name criteria.
     * @param pattern The pattern criteria
     * @return The new builder object
     */
    public static Builder getBuilder(final Pattern pattern) {
        return new Builder(pattern);
    }
}

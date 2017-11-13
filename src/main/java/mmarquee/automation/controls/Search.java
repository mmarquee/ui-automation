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

public class Search {
    private String automationId;
    private String name;
    private int id;
    private String className;
    private Pattern namePattern;
    private String controlType;

    public static class Builder {
        private String automationId;
        private String name;
        private int id;
        private String className;
        private Pattern namePattern;
        private String controlType;

        public Builder() {
            this.automationId = "";
            this.name = "";
            this.id = -1;
            this.className = "";
            this.namePattern = null;
            this.controlType = "";
        }

        public Builder name(String inName) {
            this.name = inName;
            return this;
        }

        public Builder automationId(String inAutomationId) {
            this.automationId = inAutomationId;
            return this;
        }

        public Builder className(String inClassName) {
            this.className = inClassName;
            return this;
        }

        public Builder namePattern(Pattern inNamePattern) {
            this.namePattern = inNamePattern;
            return this;
        }

        public Builder id(int inID) {
            this.id = inID;
            return this;
        }

        public Builder controlType(String inControlType) {
            this.controlType = inControlType;
            return this;
        }

        public Search build() {
            return new Search(this);
        }
    }

    public Search(Builder builder) {
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
}

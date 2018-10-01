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
 * Wrapper around the 'virtual' cell element in the automated Delphi string grid.
 *
 * @author Mark Humphreys
 * Date 04/02/2016.
 */
public final class DataGridCell
        extends AutomationBase
        implements ImplementsValue, ImplementsSelect, ImplementsGridItem {

    /**
     * Construct the DataGridCell.
     * @param builder The builder
     */
    public DataGridCell(final ElementBuilder builder) {
        super(builder);
    }
}

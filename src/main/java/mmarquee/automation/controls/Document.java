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

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.uiautomation.TreeScope;

import java.util.List;

/**
 * Wrapper for the Document element.
 *
 * @author Mark Humphreys
 * Date 16/02/2016.
 */
public final class Document extends AutomationBase implements ImplementsText {

    /**
     * Constructor for the Document.
     * @param builder The builder
     */
    Document(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Gets the page, based on index.
     * @param index The index
     * @return The selected page
     * @throws AutomationException Something has gone wrong
     */
    public DocumentPage getPage(final int index)
            throws AutomationException {
        List<Element> items = this.findAll(
                new TreeScope(TreeScope.DESCENDANTS),
                this.createControlTypeCondition(ControlType.Custom));

        return new DocumentPage(new ElementBuilder(items.get(index)));
    }

    /**
     * Get the page, using the search criteria.
     *
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public DocumentPage getPage(final Search search)
            throws AutomationException {
       if (search.getHasIndex()) {
           return getPage(search.getIndex());
       } else {
           throw new AutomationException("Search type not found");
       }
    }
}

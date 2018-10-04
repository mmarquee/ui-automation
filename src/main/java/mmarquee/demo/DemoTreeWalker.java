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

package mmarquee.demo;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.AutomationTreeWalker.AutomationElementVisitor;
import mmarquee.automation.UIAutomation;

/**
 * Test code for TreeWalker.
 *
 * @author Mark Humphreys
 * Date 02/02/2017.
 */
public class DemoTreeWalker extends TestBase {

	/**
	 * Recursion level.
	 */
    private static final int recurseLevel = 50;

	/**
	 * Constructor for DemoTreeWalker.
	 */
	public DemoTreeWalker() {

    }

	/**
	 * Run the demo.
	 */
	public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationTreeWalker walker = automation.getControlViewWalker();

            AutomationElement root = automation.getRootElement();

            AutomationElementVisitor logVisitor =
                    new AutomationElementVisitor() {
				
            	int level = 0;
            	
            	@Override
				public boolean visit(AutomationTreeWalker walker,
                                     AutomationElement element)
                        throws AutomationException {

				    String name = element.getName();
					String className = element.getClassName();
					String indent = level == 0 ?
                            "" :
                            String.format("%"+ level*2 + "s","");
					String message =
                            String.format("%s'%s' [%s]",
                                    indent,
                                    name,
                                    className);
					
					logger.info(message);
					
					if (recurseLevel > level) {
						level++;
						walker.walk(this, element);
						level--;
					}
					
					return true;
				}
			};
            
			walker.walk(logVisitor, root);

			logger.info("All done");

        } catch (Throwable ex) {
            // Smother
            logger.error("Exception thrown - " + ex.toString());
            ex.printStackTrace();
        }
    }
}

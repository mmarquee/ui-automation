package mmarquee.demo;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.AutomationTreeWalker.AutomationElementVisitor;
import mmarquee.automation.UIAutomation;

/**
 * Created by Mark Humphreys on 02/02/2017.
 */
public class DemoTreeWalker extends TestBase {

    final int recurse_level = 2;
    
    public DemoTreeWalker() {

    }

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationTreeWalker walker = automation.getControlViewWalker();

            AutomationElement root = automation.getRootElement();

            AutomationElementVisitor logVisitor = new AutomationElementVisitor() {			
				
            	int level = 0;
            	
            	@Override
				public boolean visit(AutomationTreeWalker walker, AutomationElement element) throws AutomationException {

				    String name = element.getName();
					String className = element.getClassName();
					String indent = level == 0 ? "" : String.format("%"+ level*2 + "s","");
					String message = String.format("%s'%s' [%s]", indent, name, className);
					
					logger.info(message);
					
					if (recurse_level > level) {
						level++;
						walker.walk(this, element);
						level--;
					}
					
					return true;
				}
			};
            
			walker.walk(logVisitor, root);
            
        } catch (Throwable ex) {
            // Smother
            logger.error("Exception thrown - " + ex.toString());
            ex.printStackTrace();
        }
    }
}

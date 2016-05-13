
package de.hsbochum.fbg.kswe.scrum;

import de.hsbochum.fbg.kswe.scrum.events.UnexpectedNextEventException;
import de.hsbochum.fbg.kswe.scrum.events.InvalidSprintPeriodException;
import de.hsbochum.fbg.kswe.scrum.artifacts.BacklogItem;
import de.hsbochum.fbg.kswe.scrum.artifacts.ProductBacklog;
import de.hsbochum.fbg.kswe.scrum.events.InitializationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public class Main {
    
    private static final Logger LOG = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        try {
            Scrum scrum = new Scrum(prepareProductBacklog());
            
            scrum.planSprint(2);
            //scrum.reviewSprint();
            scrum.startSprint(14);
            scrum.doDailyScrum();               //Methode is empty
            scrum.reviewSprint();
            scrum.doSprintRetrospective();      //done added
            
            scrum.planSprint(2);
            //scrum.startSprint(10);            //done edit see next line
            scrum.startSprint(14);
            scrum.doDailyScrum();               //Methode is empty
            scrum.reviewSprint();               //done added
            scrum.doSprintRetrospective();
            
            //scrum.planSprint(2);              //done removed no BacklogItems for Sprint

        } catch (UnexpectedNextEventException | InitializationException |
                InvalidSprintPeriodException ex) {
            LOG.warn(ex.getMessage(), ex);
        }
    }
    
    private static ProductBacklog prepareProductBacklog() {
        ProductBacklog bl = new ProductBacklog();
        
        bl.addItem(new BacklogItem("Design database structure",
                "A fine-grained definition of the database structure",
                BacklogItem.Priority.HIGH));
        bl.addItem(new BacklogItem("Implement Read DAO",
                "Develop the Data Access Objects to provide read capabilities for the database",
                BacklogItem.Priority.MEDIUM));
        bl.addItem(new BacklogItem("Create UI Mockups for Weather entries view",
                "Create Some Mockups for the weather entry overview",
                BacklogItem.Priority.HIGH));
        bl.addItem(new BacklogItem("Implement Write DAO",
                "Develop the Data Access Objects to provide write capabilities for the database",
                BacklogItem.Priority.LOW));
        
        return bl;
    }

}

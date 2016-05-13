
package de.hsbochum.fbg.kswe.scrum;

import de.hsbochum.fbg.kswe.scrum.events.UnexpectedNextEventException;
import de.hsbochum.fbg.kswe.scrum.events.InvalidSprintPeriodException;
import de.hsbochum.fbg.kswe.scrum.artifacts.ProductBacklog;
import de.hsbochum.fbg.kswe.scrum.events.DailyScrum;
import de.hsbochum.fbg.kswe.scrum.events.Event;
import de.hsbochum.fbg.kswe.scrum.events.InitializationException;
import de.hsbochum.fbg.kswe.scrum.events.Sprint;
import de.hsbochum.fbg.kswe.scrum.events.SprintPlanning;
import de.hsbochum.fbg.kswe.scrum.events.SprintRetrospective;
import de.hsbochum.fbg.kswe.scrum.events.SprintReview;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public class Scrum {
    
    private static final Logger LOG = LogManager.getLogger(Scrum.class);

    private final ProductBacklog productBacklog;
    private Event currentEvent;
    private Sprint initialSprint;
    
    public Scrum(ProductBacklog pbl) {
        this.productBacklog = pbl;
    }

    private void moveToNextEvent(Event event) throws UnexpectedNextEventException, InitializationException, InvalidSprintPeriodException {
        LOG.info("Moving to next event...");
        Event previousEvent = null;
        
        if (this.currentEvent == null) {
            this.currentEvent = event;
        }
        else {
            /*
             * TODO implement the assertion of the logical order. Throw an
             * UnexpectedNextEventException if the order is not correct.
             * Hint: the method Class#isAssignableFrom() might be helpful
             */
            previousEvent = this.currentEvent;
            if(this.currentEvent.followingEventType().isAssignableFrom(event.getClass())){
                this.currentEvent = event;
                LOG.info(event.getClass().getCanonicalName());
            }else{
                String e = "No:\t"+event.getClass().getCanonicalName()+" is wrong";
                LOG.info(e);
                throw new InvalidSprintPeriodException("Wrong event");
            }
            
        }
        
        event.init(previousEvent, productBacklog);
        LOG.info("Moved to next event: {}", event);
    }

    public void planSprint(int itemCount) throws UnexpectedNextEventException, InitializationException, InvalidSprintPeriodException {
        SprintPlanning planning = new SprintPlanning(itemCount);
        moveToNextEvent(planning);
    }
    
    public void startSprint(int numberOfDays) throws UnexpectedNextEventException, InitializationException, InvalidSprintPeriodException {
        Sprint sprint = new Sprint(numberOfDays);
        ensureCorrectNumberOfDays(sprint);
        moveToNextEvent(sprint);
    }
    
    public void doDailyScrum() {
    }

    public void reviewSprint() throws UnexpectedNextEventException, InitializationException, InvalidSprintPeriodException {
        SprintReview review = new SprintReview();
        moveToNextEvent(review);
    }

    public void doSprintRetrospective() throws UnexpectedNextEventException, InitializationException, InvalidSprintPeriodException {
        SprintRetrospective retro = new SprintRetrospective();
        moveToNextEvent(retro);
    }

    private void ensureCorrectNumberOfDays(Sprint sprint) throws InvalidSprintPeriodException {
        if (initialSprint == null) {
            initialSprint = sprint;
        }
        else {
            if (initialSprint.getNumberOfDays() != sprint.getNumberOfDays()) {
                throw new InvalidSprintPeriodException(String.format(
                        "Sprints always have to have same period. Expected: %s. Got: %s",
                        initialSprint.getNumberOfDays(), sprint.getNumberOfDays()));
            }
        }
    }

}

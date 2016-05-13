
package de.hsbochum.fbg.kswe.scrum.events;

import de.hsbochum.fbg.kswe.scrum.artifacts.ProductBacklog;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public class SprintReview implements Event {

    @Override
    public Class<? extends Event> followingEventType() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return SprintRetrospective.class;
    }

    @Override
    public void init(Event previos, ProductBacklog productBacklog) throws InitializationException {
    }

}

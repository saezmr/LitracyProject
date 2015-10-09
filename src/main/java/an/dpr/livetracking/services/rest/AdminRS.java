package an.dpr.livetracking.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.bo.AdminBO;
import an.dpr.livetracking.domain.Event;

@Path("/admin/")
public class AdminRS {

    private static final Logger log = LoggerFactory.getLogger(AdminRS.class);
    @Autowired private AdminBO bo;
    
    @GET
    @Path("/persistEvent/{id},{name},{description},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event persistEvent(
        	@PathParam("id") String id,
        	@PathParam("name") String name,
        	@PathParam("description") String description,
        	@PathParam("defaultSport") String defaultSport,
        	@PathParam("defaultType") String defaultType) {
	log.debug("params: "+id+","+name+","+description+","+defaultSport+","+defaultType);
	Event event = getEvent(id, name, description, defaultSport, defaultType);
	return bo.persistEvent(event);
    }

    private Event getEvent(String id, String name, String description, String defaultSport, String defaultType) {
	Event event = new Event();
	if (id != null && !id.isEmpty()){
	    Long lid = Long.valueOf(id);
	    if (lid > 0){
		event.setId(Long.valueOf(id));
	    }
	}
	event.setName(name);
	event.setDescription(description);
	event.setDefaultSport(Sport.valueOf(defaultSport));
	event.setDefaultType(EventType.valueOf(defaultType));
	log.debug("Event:"+event.toString());
	return event;
    }
    
    @GET
    @Path("/events/{name},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents(
	    	@PathParam("name") String name,
        	@PathParam("defaultSport") String defaultSport,
        	@PathParam("defaultType") String defaultType) {
	EventSearch query = getEventSearch(name, defaultSport, defaultType);
	log.debug("search events:"+query);
	List<Event> list = bo.getEvents(query);
	return list;
    }

    private EventSearch getEventSearch(String name, String defaultSport, String defaultType) {
	EventSearch query = new EventSearch();
	if (name != null && !name.isEmpty())
	    query.setName("%"+name+"%");
	if (defaultSport != null && !defaultSport.isEmpty())
	    query.setDefaultSport(Sport.valueOf(defaultSport));
	if (defaultType != null && !defaultType.isEmpty())
	    query.setDefaultType(EventType.valueOf(defaultType));
	return query;
    }
}

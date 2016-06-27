package sportIO;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;

import sport.vereniging.Lid;
import sport.vereniging.Team;

public class SportIO {
	
    private DatastoreService datastore;
	
    public SportIO() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }
    
    public void updateLid(Lid lid) {
        Entity ent = new Entity("Lid", lid.getSpelerscode());
        ent.setProperty("spelerscode", lid.getSpelerscode());
        ent.setProperty("roepnaam", lid.getRoepnaam());
        ent.setProperty("tussenvoegsels", lid.getTussenvoegsels());
        ent.setProperty("achternaam", lid.getAchternaam());
        ent.setProperty("email", lid.getEmail());
        ent.setProperty("geb_datum", lid.getGeb_datum());
        datastore.put(ent);
    }
    
    public void verwijderLid(String spelerscode) {
    	datastore.delete(KeyFactory.createKey("Lid", spelerscode));
    }
    
    public Lid getLid(String spelerscode)  {
    	Lid lid = null;
    	try {
    		Entity ent = datastore.get(KeyFactory.createKey("Lid", spelerscode));
    		lid = new Lid();
    		lid.setSpelerscode(ent.getProperty("spelerscode").toString());
    		lid.setRoepnaam(ent.getProperty("roepnaam").toString());
    		lid.setTussenvoegsels(ent.getProperty("tussenvoegsels").toString());
    		lid.setAchternaam(ent.getProperty("achternaam").toString());
    		lid.setEmail(ent.getProperty("email").toString());
    		lid.setGeb_datum((Date)(ent.getProperty("geb_datum")));
    	}
    	catch (EntityNotFoundException e) {}
    	return lid;
    }
    
    public ArrayList<Lid> getAlleLeden() {
        ArrayList<Lid> leden = new ArrayList<Lid>();
        for (Entity ent : datastore.prepare(new Query("Lid")).asIterable()) {
            leden.add(getLid(ent.getProperty("spelerscode").toString()));
        }
        return leden;
    }
    
    public void setTeamspeler(Team team, Lid lid) {
    	Entity ent = new Entity("Teamspeler", team.getTeamcode()+lid.getSpelerscode());
    	ent.setProperty("teamcode", team.getTeamcode());
    	ent.setProperty("spelerscode", lid.getSpelerscode());
    	datastore.put(ent);
    }
    
    public ArrayList<Lid> getTeamspelers(Team team)  {
        ArrayList<Lid> teamleden = new ArrayList<Lid>();
        for (Entity ent : datastore.prepare(new Query("Teamspeler").setFilter(new FilterPredicate("teamcode", FilterOperator.EQUAL, team.getTeamcode()))).asIterable()) {
            teamleden.add(getLid(ent.getProperty("spelerscode").toString()));
        }
        return teamleden;
    }
    
    public void updateTeam(Team team) {
        Entity ent = new Entity("Team", team.getTeamcode());
        ent.setProperty("teamcode", team.getTeamcode());
        ent.setProperty("teamnaam", team.getTeamnaam());
        datastore.put(ent);
    }
    
    public void verwijderTeam(String teamcode) {
    	datastore.delete(KeyFactory.createKey("Team", teamcode));
    }
    
    public Team getTeam(String teamcode)  {
    	Team team = null;
    	try {
    		Entity ent = datastore.get(KeyFactory.createKey("Team", teamcode));
    		team = new Team(
				ent.getProperty("teamcode").toString(),
				ent.getProperty("teamnaam").toString()
			);
    	}
    	catch (EntityNotFoundException e) {}
    	return team;
    }
    
    public ArrayList<Team> getAlleTeams() {
        ArrayList<Team> teams = new ArrayList<Team>();
        for (Entity ent : datastore.prepare(new Query("Team")).asIterable()) {
            teams.add(getTeam(ent.getProperty("teamcode").toString()));
        }
        return teams;
    }
    
}
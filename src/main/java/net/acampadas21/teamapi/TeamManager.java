package net.acampadas21.teamapi;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import net.acampadas21.teamapi.groups.Team;

import org.bukkit.entity.Player;

import couk.Adamki11s.SQL.SyncSQL;

public class TeamManager {

	// NOTEPAD
	
	// In the "leader" row 1 means that the player is the leader. Any other value means the opposite.
	
	private static SyncSQL db;
	private static TeamAPI plugin;
	private static HashMap<String, Team> teams;

	public TeamManager(TeamAPI instance) {
		plugin = instance;
		try {
			db = new SyncSQL(new File(plugin.getDataFolder() + "database.db"));
			db.initialise();
		} catch (Exception e) {
			throw new Error("Can't connect to the database");
		}
	}

	protected boolean isTeam(String name) {
		return db.doesTableExist(name);
	}

	protected boolean newTeam(String name) {
		if (!isTeam(name)) {
			db.standardQuery("CREATE TABLE " + name + "(player VARCHAR(20), leader TINYINT);");
			return true;
		}
		return false;
	}
	
	protected void joinTeam(Player p, Team t){
			db.standardQuery("INSERT INTO " + t.getName() + " VALUES("+ t.getName() +", 0);");
	}
	
	protected void deleteTeam(Team t){
		if(isTeam(t.getName())){
			db.standardQuery("DELETE TABLE " + t.getName() + ";");
		}
	
	}
	
	protected Team getTeamByName(String name){
		if(isTeam(name)){
			return ;
		}
	}
	
	protected Team getTeamByPlayer(Player p){
		ResultSet rs = db.sqlQuery(query);
	}


    protected String[] listTeams() {
        ArrayList<String> a = new ArrayList<String>();
        for (Team t : teams.values()) {
            a.add(t.getName());
        }
        return (String[]) a.toArray();
    }
	
	
}

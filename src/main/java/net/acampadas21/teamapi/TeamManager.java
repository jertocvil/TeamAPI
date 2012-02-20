package net.acampadas21.teamapi;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.acampadas21.teamapi.groups.Team;

import org.bukkit.entity.Player;

import couk.Adamki11s.SQL.SyncSQL;

public class TeamManager {

	// NOTEPAD
	
	// In the "leader" row 1 means that the player is the leader. Any other value means the opposite.
	
	private static SyncSQL db;

	public static void initialize() {
		try {
			db = new SyncSQL(new File("./TeamAPI/database.db"));
			db.initialise();
		} catch (Exception e) {
			throw new Error("Can't connect to the database");
		}
	}

	public static boolean isTeam(String name) {
		return db.doesTableExist(name);
	}

	public static void newTeam(String name) {
		if (!isTeam(name)) {
			db.standardQuery("CREATE TABLE " + name + "(player VARCHAR(20), leader TINYINT);");
		}
	}
	
	public static void joinTeam(Player p, Team t){
			db.standardQuery("INSERT INTO " + t.getName() + " VALUES("+ t.getName() +", 0);");
	}
	
	public static void deleteTeam(Team t){
		if(isTeam(t.getName())){
			db.standardQuery("DELETE TABLE " + t.getName() + ";");
		}
	
	}
	
	public static void getTeamByName(String name){
		if(isTeam(name)){
			
		}
	}
	
	public static void getTeamByPlayer(Player p){
		ResultSet rs = db.sqlQuery(query);
	}


    public static String[] listTeams() {
        ArrayList<String> a = new ArrayList<String>();
        for (Team t : teams.values()) {
            a.add(t.getName());
        }
        return (String[]) a.toArray();
    }
	
	
}

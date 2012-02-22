package net.acampadas21.teamapi;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import net.acampadas21.teamapi.groups.Team;

import org.bukkit.Bukkit;
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
			db.standardQuery("DROP TABLE " + t.getName() + ";");
		}
	
	}
	
	protected Team getTeamByName(String name){
		if(isTeam(name)){
			ArrayList<Player> p = new ArrayList<Player>();
			Player leader = null;
				try {
				    ResultSet rs = db.sqlQuery("SELECT * FROM " + name);
				    while (rs.next()) {
				        p.add(Bukkit.getServer().getPlayer(rs.getString(1)));
				        if(rs.getInt(2) != 0) leader = Bukkit.getServer().getPlayer(rs.getString(1));
				    }
				    
				} catch (SQLException e) {
					// TODO logger
				}
				return new Team(name, p, leader);
				
		}
		return null;
	}
	
	protected Team getTeamByPlayer(Player p){
		ResultSet rs = db.sqlQuery("");
	}


    protected String[] listTeams() {
        ArrayList<String> a = new ArrayList<String>();
        for (Team t : teams.values()) {
            a.add(t.getName());
        }
        return (String[]) a.toArray();
    }
	
	
}

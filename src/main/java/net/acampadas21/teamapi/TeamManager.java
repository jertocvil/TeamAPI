package net.acampadas21.teamapi;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import net.acampadas21.teamapi.groups.DBTeam;
import net.acampadas21.teamapi.groups.Team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import couk.Adamki11s.SQL.SyncSQL;

public class TeamManager {

	// NOTEPAD
	
	// In the "leader" row 1 means that the player is the leader. Any other value means the opposite.
	
	private static SyncSQL db;
	private static TeamAPI plugin;
	private static File f;

	public TeamManager(TeamAPI instance) {
		plugin = instance;
		dbInit();
	}

	private void dbInit(){
		try {
			f = new File(plugin.getDataFolder() + "database.db");
			db = new SyncSQL(f);
			db.initialise();
			db.closeConnection();
		} catch (Exception e) {
			throw new Error("Can't connect to the database");
		}
	}
	
	/**
	 * Checks if exists any team with that name. 
	 * @param name The name of the team that is going to be checked.
	 * @return True if exists a team with that name
	 */
	public boolean isTeam(String name) {
		db.refreshConnection();
		boolean b = db.doesTableExist(name);
		db.closeConnection();
		return b;
	}

	/**
	 * Creates a new team. 
	 * @param name The name of the team that is going to be checked.
	 * @return True if exists a team with that name
	 */
	public boolean newTeam(String name) {
		if (!isTeam(name)) {
			db.refreshConnection();
			db.standardQuery("CREATE TABLE " + name + "(player VARCHAR(20), leader TINYINT);");
			db.closeConnection();
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a player to a certain team
	 * @param p The player we are adding
	 * @param t The team where the player is going to be added.
	 */
	public void joinTeam(Player p, Team t){
		db.refreshConnection();
			db.standardQuery("INSERT INTO " + t.getName() + " VALUES("+ t.getName() +", 0);");
			db.closeConnection();
	}
	
	/**
	 * Removes a team
	 * @param t The team that is going to be removed.
	 */
	public void deleteTeam(Team t){
		if(isTeam(t.getName())){
			db.refreshConnection();
			db.standardQuery("DROP TABLE " + t.getName() + ";");
			db.closeConnection();
		}
	
	}
	
	/**
	 * Gets a team from its name
	 * @param name The name of the team
	 * @return Team with that name. Returns null if there isn't a team with that name.
	 */
	public Team getTeamByName(String name){
		if(isTeam(name)){
			ArrayList<Player> p = new ArrayList<Player>();
			Player leader = null;
				try {
					db.refreshConnection();
				    ResultSet rs = db.sqlQuery("SELECT * FROM " + name);
				    while (rs.next()) {
				        p.add(Bukkit.getServer().getPlayer(rs.getString(1)));
				        if(rs.getInt(2) != 0) leader = Bukkit.getServer().getPlayer(rs.getString(1));
				    }
				    rs.close();
				    db.closeConnection();
				    
				} catch (SQLException e) {
					TeamAPI.logger.log(Level.SEVERE, "Can't connect to database");
				}
				return new DBTeam(name, p, leader, this);
				
		}
		return null;
	}
	
	/**
	 * Gets the team where a player belongs.
	 * @param p The player we are checking.
	 * @return The team where the player belongs. Returns null if it doesn't belong to any team.
	 */
	public Team getTeamByPlayer(Player p){
		Team[] teams = listTeams();
		if(teams == null) return null;
		for(Team t : teams){
			if(t.isInTeam(p)) return t;
		}
		return null;
	}


	/**
	 * Gets all the teams stored in the database.
	 * @return Array that contains all the teams.
	 */
    public Team[] listTeams() {
		ArrayList<Team> t = new ArrayList<Team>();
		try {
			db.refreshConnection();
	    	ResultSet rs = db.sqlQuery("SELECT name FROM sqlite_master WHERE type='table'");
			while(rs.next()){
				t.add(this.getTeamByName(rs.getString(1)));
			}
			rs.close();
			db.closeConnection();
			return (Team[]) (t.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    public void clearTeams(){
    		f.delete();
    		dbInit();
    	
    }
	
	
}

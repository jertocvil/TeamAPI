package net.acampadas21.teamapi;

import lib.PatPeter.SQLibrary.SQLite;
import net.acampadas21.teamapi.groups.Team;

import org.bukkit.entity.Player;

public class GroupManager {

	private SQLite db;

	public GroupManager() {
		try {
			db = new SQLite(TeamAPI.logger, "TeamAPI", "teamapi",
					"./plugins/TeamAPI");
			db.open();
		} catch (Exception e) {
			throw new Error("Can't connect to the database");
		}
	}

	public boolean isTeam(String name) {
		return db.checkTable(name);
	}

	public void newTeam(String name) {
		if (!isTeam(name)) {
			db.createTable("CREATE TABLE " + name + "(player VARCHAR(20), leader TINYINT);");
		}
	}
	
	public void joinTeam(Player p, Team t){
			db.query("INSERT INTO " + t.getName() + " VALUES("+ t.getName() +", 0);");
	}
	
	public void deleteTeam(Team t){
		if(isTeam(t.getName())){
			db.query("DELETE TABLE " + t.getName() + ";");
		}
	
	}
	
}

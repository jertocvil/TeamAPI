package net.acampadas21.teamapi;

import lib.PatPeter.SQLibrary.SQLite;

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
	
	public boolean isTeam(String name){
		return db.checkTable(name);
	}
	
	public 

}

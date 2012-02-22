package net.acampadas21.teamapi;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.acampadas21.teamapi.groups.Team;
import net.acampadas21.teamapi.listeners.Executor;
import net.acampadas21.teamapi.listeners.TeamAPIPlayerListener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamAPI extends JavaPlugin {

    public static TeamAPI plugin;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public final TeamAPIPlayerListener playerListener = new TeamAPIPlayerListener(this);
    private static TeamManager tm;
    private Executor myExecutor;
    public static boolean signalOn;

    @Override
    public void onDisable() {
        TeamAPI.logger.log(Level.INFO, "TeamAPI v{0}  is now disabled", plugin.getDescription().getVersion());
    }

    @Override
    public void onEnable() {
    	signalOn = false;
 //       FileConfiguration config = this.getConfig();
    	tm = new TeamManager(this);
        myExecutor = new Executor();
        getCommand("team").setExecutor(myExecutor);
        getCommand("test").setExecutor(myExecutor);
        TeamAPI.logger.log(Level.INFO, "TeamAPI v{0} has been enabled.", plugin.getDescription().getVersion());
    }
    
    //////////////////
    /// API METHODS///
    //////////////////

    /**
     * Gets how many teams are registered.
     * @return The number of teams
     */
    public int howManyTeams() {
        return tm.listTeams().length;
    }

    /**
     * Gets an array that contains the players of the specified team.
     * @param team The team we are checking.
     * @return The player array.
     */
    public Player[] playersIn(Team team) {
        return team.getPlayers();
    }
    
    /**
     * Gets an array that contains the players of the specified team.
     * @param team The name of the team we are checking.
     * @return The player array. Returns null if doesn't exist a team with the specified name
     */
    public Player[] playersIn(String t) {
    	try{
        return tm.getTeamByName(t).getPlayers();
    	}catch(NullPointerException e){
    		return null;
    	}
    }

    /**
     * Returns the team where a player belongs.
     * @param p The player we are checking.
     * @return The team. Returns null if the player isn't in a team.
     */
    public Team inWhichTeam(Player p) {
        return tm.getTeamByPlayer(p);
    }

    /**
     * Checks if there is a team with that name.
     * @param input The name of the team.
     * @return True if exists.
     */
    public boolean isTeam(String input) {
        return tm.isTeam(input);
    }

    /**
     * Creates a new team
     * @param input The name of the new team.
     * @return True if the team has been successfully created.
     */
    public boolean newTeam(String input) {
        return tm.newTeam(input);
    }
    
    /**
     * Gets a team with the specified name.
     * @param name The name we are checking.
     * @return Team with that name. Returns null if there isn't a team with that name.
     */
    public Team getTeamFromName(String name) {
        return tm.getTeamByName(name);
    }

    /**
	* Gets all the teams stored.
	* @return Array that contains all the teams.
	*/
    public Team[] listTeams() {
        return tm.listTeams();
    }
}

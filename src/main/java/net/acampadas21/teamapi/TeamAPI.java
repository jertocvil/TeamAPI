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
    	TeamManager.initialize();
        myExecutor = new Executor();
        getCommand("team").setExecutor(myExecutor);
        getCommand("test").setExecutor(myExecutor);
        TeamAPI.logger.log(Level.INFO, "TeamAPI v{0} has been enabled.", plugin.getDescription().getVersion());
    }

    public int howManyTeams() {
        return TeamManager.teams.size();
    }

    public Player[] playersIn(Team t) {
        return t.getPlayers();
    }

    public Player[] playersIn(String t) {
        return TeamManager.getTeamFromName(t).getPlayers();
    }

    public Team inWhichTeam(Player p) {
        return TeamManager.inWhichTeam(p);
    }

    public boolean isTeam(String input) {
        return TeamManager.isTeam(input);
    }

    public boolean createTeam(String input) {
        return TeamManager.createTeam(input);
    }
    
    public Team getTeamFromName(String name) {
        return TeamManager.getTeamFromName(name);
    }

    public String[] listTeams() {
        return TeamManager.listTeams();
    }
}

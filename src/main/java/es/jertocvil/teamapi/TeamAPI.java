package es.jertocvil.teamapi;
import lib.PatPeter.SQLibrary.SQLite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamAPI extends JavaPlugin {

    public static TeamAPI plugin;
    public static SQLite sq;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public final TeamAPIPlayerListener playerListener = new TeamAPIPlayerListener(this);
    private Executor myExecutor;

    @Override
    public void onDisable() {
        TeamAPI.logger.log(Level.INFO, "TeamAPI v{0}  is now disabled", plugin.getDescription().getVersion());
    }

    @Override
    public void onEnable() {
    	
        FileConfiguration config = this.getConfig();
        Utils.teams = new HashMap<String, Team>();
        Utils.signalOn = false;
        myExecutor = new Executor(this);
        getCommand("team").setExecutor(myExecutor);
        getCommand("test").setExecutor(myExecutor);
        TeamAPI.logger.log(Level.INFO, "TeamAPI v{0} has been enabled.", plugin.getDescription().getVersion());
    }

    public int howManyTeams() {
        return Utils.teams.size();
    }

    public Player[] playersIn(Team t) {
        return t.members();
    }

    public Player[] playersIn(String t) {
        return Utils.getTeamFromName(t).members();
    }

    public Team inWhichTeam(Player p) {
        return Utils.inWhichTeam(p);
    }

    public boolean isTeam(String input) {
        return Utils.isTeam(input);
    }

    public boolean createTeam(String input) {
        return Utils.createTeam(input);
    }
    
    public static Team getTeamFromName(String name) {
        return Utils.getTeamFromName(name);
    }

    public static String[] listTeams() {
        return Utils.listTeams();
    }
}

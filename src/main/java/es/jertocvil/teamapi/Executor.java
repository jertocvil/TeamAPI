package es.jertocvil.teamapi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

    private TeamAPI plugin;

    public Executor(TeamAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if ("team".equalsIgnoreCase(commandLabel)) {
            if (args[0].equalsIgnoreCase("new")) {
                Commands.createTeam(args[1], p);
            } else if (args[0].equalsIgnoreCase("remove")) {
                Commands.removeTeam(args[1], p);
            } else if(args[0].equalsIgnoreCase("flush")){
                Commands.clearTeams(p);
            } else if(args[0].equalsIgnoreCase("sign")){
                Commands.signConfig(args[1], p);
            } else if(args[0].equalsIgnoreCase("tp")){
                Commands.transport(args[1], p);
            }
        }


        return false;
    }
    
    
}

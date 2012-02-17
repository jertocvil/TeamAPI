package net.acampadas21.teamapi.listeners;


import net.acampadas21.teamapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

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

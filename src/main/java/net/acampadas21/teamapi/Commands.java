package net.acampadas21.teamapi;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Commands {
    public static void sendUsage(Player p){
        p.sendMessage("Comando incorrecto");
    }
    
    public static void createTeam(String name, Player p){
        if (name != null) {
                    if(TeamManager.createTeam(name)){
                    p.sendMessage(ChatColor.GREEN + "Team " + ChatColor.GOLD + name + ChatColor.GREEN + " created.");
                    } else {
                        p.sendMessage(ChatColor.YELLOW + "Team " + ChatColor.GOLD + name + ChatColor.YELLOW + " already exists.");
                    }
                } else {
                    sendUsage(p);
                }
    }
    
    public static void removeTeam(String name, Player p){
        if (name != null) {
            if(TeamManager.isTeam(name)){
                    TeamManager.teams.remove(name);
                    p.sendMessage(ChatColor.RED + "Team " + ChatColor.GOLD + name + ChatColor.RED + " removed.");
            } else {
                p.sendMessage(ChatColor.YELLOW + "Team " + ChatColor.GOLD + name + ChatColor.YELLOW + " doesn't exist.");
            }
                }
    }
    public static void clearTeams(Player p){
        TeamManager.teams.clear();
        p.sendMessage(ChatColor.RED + "All teams cleared.");
    }
    
    public static void signConfig(String value, Player p){
        if(value != null){
                    if(value.equalsIgnoreCase("true")){
                        TeamManager.signalOn = true;
                        p.sendMessage(ChatColor.YELLOW + "Joining teams by signal click " + ChatColor.GREEN + "enabled.");
                    } else if(value.equalsIgnoreCase("false")){
                        TeamManager.signalOn = false;
                        p.sendMessage(ChatColor.YELLOW + "Joining teams by signal click " + ChatColor.RED + "disabled.");
                    } else sendUsage(p);
                }
        else{
            sendUsage(p);
        }
    }
    
    public static void transport(String team, Player p){
        TeamManager.getTeamFromName(team).transport(p.getLocation());
    }
}

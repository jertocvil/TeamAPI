package net.acampadas21.teamapi.listeners;

import net.acampadas21.teamapi.TeamAPI;
import net.acampadas21.teamapi.Utils;
import net.acampadas21.teamapi.groups.TempTeam;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerChatEvent;
//import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class TeamAPIPlayerListener implements Listener {

    public static TeamAPI plugin;

    public TeamAPIPlayerListener(TeamAPI instance) {
        plugin = instance;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

//    @Override
//    public void onPlayerChat(PlayerChatEvent chat){
//        Player p = chat.getPlayer();
//        String message = chat.getMessage();
//        String message_lower = message.toLowerCase();
//        ChatColor RED = ChatColor.RED;
//        ChatColor WHITE = ChatColor.WHITE;
//        if(message_lower.contains("hi") && message_lower.contains("server")){
//            p.sendMessage(RED + "[SERVER]" + WHITE + " Hello " + p.getName());
//            chat.setCancelled(true);
//        }
//    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getClickedBlock().getTypeId() == 63 && Utils.signalOn) { //se�al
            signalInt(event);
        }
        if (event.getClickedBlock().getTypeId() == 57) { //diamante
            armor(Utils.inWhichTeam(event.getPlayer()));
        }
        
    }

    private void signalInt(PlayerInteractEvent event) {
        
        Player p = event.getPlayer();
        Sign s = (Sign) event.getClickedBlock().getState();
        if (Utils.isTeam(s.getLine(0))) {
            Utils.teams.get(s.getLine(0)).add(p);
        }
    }
}
package net.acampadas21.teamapi.listeners;

import net.acampadas21.teamapi.TeamAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class TeamAPIPlayerListener implements Listener {

    public static TeamAPI plugin;

    public TeamAPIPlayerListener(TeamAPI instance) {
        plugin = instance;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // TODO Join team by clicking sign
    
    /*
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getClickedBlock().getTypeId() == 63 && Utils.signalOn) { //senyal
            signalInt(event);
        }
    }
    */

    
    /*
    private void signalInt(PlayerInteractEvent event) {
        
        Player p = event.getPlayer();
        Sign s = (Sign) event.getClickedBlock().getState();
        if (Utils.isTeam(s.getLine(0))) {
            Utils.teams.get(s.getLine(0)).add(p);
        }
    }
    */
}
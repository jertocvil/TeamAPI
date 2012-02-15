
package es.jertocvil.teamapi;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Team implements TeamT {
    private ArrayList<Player> players = new ArrayList<Player>();
    private String nombre;
    private Player leader;
    
    public Team(String nombre){
        this.nombre = nombre;
        this.leader = null;
    }

    @Override
    public boolean hasLeader(){
        if(leader != null){
            return true;
        }
        return false;
    }
    
    @Override
    public Player getLeader(){
        if(hasLeader()){
            return leader;
        }
        return null;
}
    
    @Override
    public void setLeader(Player p){
        if(isInTeam(p)) leader = p;
    }
    
    @Override
    public void add(Player p){
        if(Utils.inWhichTeam(p) == null){
            players.add(p);
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.GREEN + " added to team " + ChatColor.GOLD + nombre);
        }
        else{
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " already belongs to team " + ChatColor.GOLD + Utils.inWhichTeam(p).getName());
        }
    }
    @Override
    public void quit(Player p){
        if(isInTeam(p))
        players.remove(p);
    }
    
    @Override
    public Player[] members(){
        Player[] membersArray = new Player[players.size()];
        for (int i = 0; i < membersArray.length; i++) {
            membersArray[i] = players.get(i);
        }
        return membersArray;
    }

    public String[] membersNames(){
        String[] membersArray = new String[players.size()];
        for (int i = 0; i < membersArray.length; i++) {
            membersArray[i] = players.get(i).getName();
        }
        return membersArray;
    }
    
    
    @Override
    public Boolean isInTeam(Player p) {
     
        return players.contains(p);
    }

    @Override
    public String getName() {
        return nombre;
    }
    
    @Override
    public void transport(Location l){
        for (Player p : players) {
            p.teleport(l);
        }
    }
    
    public void give(int item, int quantity){
        for (Player p : players) {
            p.getInventory().addItem(new ItemStack(item, quantity));
        }
    }
    
    public void sendMessage(String message){
        for (Player p : players) {
            p.sendMessage(message);
        }
    }
    }
    


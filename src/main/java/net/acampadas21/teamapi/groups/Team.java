
package net.acampadas21.teamapi.groups;

import java.util.ArrayList;

import net.acampadas21.teamapi.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Team {
    private ArrayList<Player> players = new ArrayList<Player>();
    private String nombre;
    private Player leader;
    
    public Team(String nombre){
        this.nombre = nombre;
        this.leader = null;
    }

    /**
     * Checks if the team has a leader
     * @return True if the team has a leader
     */
    public boolean hasLeader(){
        if(leader != null){
            return true;
        }
        return false;
    }
    
    /**
     * Gets the Player object of the leader
     * @return Leader's player object
     */
    public Player getLeader(){
        if(hasLeader()){
            return leader;
        }
        return null;
    }
    
    /**
     * Sets the leader of the team
     * @param p Leader's player object
     */
    public void setLeader(Player p){
        if(isInTeam(p)) leader = p;
    }
    
    /**
     * Adds a player to the team
     * @param p The player to add to the team
     */
    public void add(Player p){
        if(Utils.inWhichTeam(p) == null){
            players.add(p);
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.GREEN + " added to team " + ChatColor.GOLD + nombre);
        }
        else{
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " already belongs to team " + ChatColor.GOLD + Utils.inWhichTeam(p).getName());
        }
    }

    /**
     * Removes a player from the team
     * @param p The player to remove from the team
     */
    public void remove(Player p){
        if(isInTeam(p))
        players.remove(p);
    }

    /**
     * Player array of the team members
     * @return A player array containing the members of the team
     */
    public Player[] getPlayers(){
    	/*
        Player[] membersArray = new Player[players.size()];
        for (int i = 0; i < membersArray.length; i++) {
            membersArray[i] = players.get(i);
        }
        */
        return (Player[]) players.toArray();
    }

    
    /**
     * Function that checks if a certain player belongs to the team
     * @param p Player to check
     * @return True if the player belongs to the team
     */
    public Boolean isInTeam(Player p) {
     
        return players.contains(p);
    }

    /**
     * Gets the name of the team
     * @return The name of the team
     */
    public String getName() {
        return nombre;
    }
    
    /**
     * Teleports all the players of the team to l
     * @param l The location to teleport the players
     */
    public void transport(Location l){
        for (Player p : players) {
            p.teleport(l);
        }
    }
    
    /**
     * Gives a certain block or item to all the team's players
     * @param item Item ID of the block or item to give
     * @param quantity Quantity of the block or item to give
     */
    public void give(int item, int quantity){
        for (Player p : players) {
            p.getInventory().addItem(new ItemStack(item, quantity));
        }
    }
    
    /**
     * Sends a message to all the team players
     * @param message Message to send
     */
    public void sendMessage(String message){
        for (Player p : players) {
            p.sendMessage(message);
        }
    }
}

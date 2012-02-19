
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

    public boolean hasLeader(){
        if(leader != null){
            return true;
        }
        return false;
    }
    
    public Player getLeader(){
        if(hasLeader()){
            return leader;
        }
        return null;
}
    
    public void setLeader(Player p){
        if(isInTeam(p)) leader = p;
    }
    
    public void add(Player p){
        if(Utils.inWhichTeam(p) == null){
            players.add(p);
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.GREEN + " added to team " + ChatColor.GOLD + nombre);
        }
        else{
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " already belongs to team " + ChatColor.GOLD + Utils.inWhichTeam(p).getName());
        }
    }


    public void quit(Player p){
        if(isInTeam(p))
        players.remove(p);
    }


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
    
    

    public Boolean isInTeam(Player p) {
     
        return players.contains(p);
    }


    public String getName() {
        return nombre;
    }
    

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

	public Player[] getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPlayer(Player p) {
		players.add(p);
		
	}

	public void removePlayer(Player p) {
		// TODO Auto-generated method stub
		
	}
    }
    
/*
 *     void add(Player p); // Añade jugador p al equipo
    void quit(Player p); // Borra al jugador p del equipo
    Player[] members(); // Lista los jugadores de este equipo
    Boolean isInTeam(Player p); // Dice si pertenece al equipo
    String getName(); // Devuelve el nombre del equipo
    void transport(Location l); //transporta todos los jugadores del equipo a l
    boolean hasLeader(); // dice si el equipo tiene líder
    void setLeader(Player p); //si el jugador p es del equipo, pasa a ser líder.
    Player getLeader(); //devuelve el líder del equipo
    
    */

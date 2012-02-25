package net.acampadas21.teamapi.groups;

import java.util.ArrayList;

import net.acampadas21.teamapi.TeamManager;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DBTeam implements Team {
    private ArrayList<Player> players;
	private String name;
    private Player leader;
    private TeamManager tm;
    
    public DBTeam(String name, TeamManager tm){
        this(name, new ArrayList<Player>(), null, tm);
    }
    
    public DBTeam(String name, ArrayList<Player> players, Player leader, TeamManager tm){
        this.name = name;
        this.players = players;
        this.leader = leader;
        this.tm = tm;
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#hasLeader()
	 */
    @Override
	public boolean hasLeader(){
        if(leader != null){
            return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#getLeader()
	 */
    @Override
	public Player getLeader(){
        if(hasLeader()){
            return leader;
        }
        return null;
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#setLeader(org.bukkit.entity.Player)
	 */
    @Override
	public void setLeader(Player p){
        if(isInTeam(p)) leader = p;
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#add(org.bukkit.entity.Player)
	 */
    @Override
	public void add(Player p){
        if(tm.getTeamByPlayer(p) == null){
            players.add(p);
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.GREEN + " added to team " + ChatColor.GOLD + name);
        }
        else{
            p.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " already belongs to team " + ChatColor.GOLD + tm.getTeamByPlayer(p).getName());
        }
    }

    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#remove(org.bukkit.entity.Player)
	 */
    @Override
	public void remove(Player p){
        if(isInTeam(p))
        players.remove(p);
    }

    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#getPlayers()
	 */
    @Override
	public Player[] getPlayers(){
    	/*
        Player[] membersArray = new Player[players.size()];
        for (int i = 0; i < membersArray.length; i++) {
            membersArray[i] = players.get(i);
        }
        */
        return (Player[]) players.toArray();
    }

    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#isInTeam(org.bukkit.entity.Player)
	 */
    @Override
	public Boolean isInTeam(Player p) {
     
        return players.contains(p);
    }

    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#getName()
	 */
    @Override
	public String getName() {
        return name;
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#transport(org.bukkit.Location)
	 */
    @Override
	public void transport(Location l){
        for (Player p : players) {
            p.teleport(l);
        }
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#give(int, int)
	 */
    @Override
	public void give(int item, int quantity){
        for (Player p : players) {
            p.getInventory().addItem(new ItemStack(item, quantity));
        }
    }
    
    /* (non-Javadoc)
	 * @see net.acampadas21.teamapi.groups.Team#sendMessage(java.lang.String)
	 */
    @Override
	public void sendMessage(String message){
        for (Player p : players) {
            p.sendMessage(message);
        }
    }
}

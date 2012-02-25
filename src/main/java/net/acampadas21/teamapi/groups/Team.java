package net.acampadas21.teamapi.groups;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Team {

	/**
	 * Checks if the team has a leader
	 * @return True if the team has a leader
	 */
	public abstract boolean hasLeader();

	/**
	 * Gets the Player object of the leader
	 * @return Leader's player object
	 */
	public abstract Player getLeader();

	/**
	 * Sets the leader of the team
	 * @param p Leader's player object
	 */
	public abstract void setLeader(Player p);

	/**
	 * Adds a player to the team
	 * @param p The player to add to the team
	 */
	public abstract void add(Player p);

	/**
	 * Removes a player from the team
	 * @param p The player to remove from the team
	 */
	public abstract void remove(Player p);

	/**
	 * Player array of the team members
	 * @return A player array containing the members of the team
	 */
	public abstract Player[] getPlayers();

	/**
	 * Function that checks if a certain player belongs to the team
	 * @param p Player to check
	 * @return True if the player belongs to the team
	 */
	public abstract Boolean isInTeam(Player p);

	/**
	 * Gets the name of the team
	 * @return The name of the team
	 */
	public abstract String getName();

	/**
	 * Teleports all the players of the team to l
	 * @param l The location to teleport the players
	 */
	public abstract void transport(Location l);

	/**
	 * Gives a certain block or item to all the team's players
	 * @param item Item ID of the block or item to give
	 * @param quantity Quantity of the block or item to give
	 */
	public abstract void give(int item, int quantity);

	/**
	 * Sends a message to all the team players
	 * @param message Message to send
	 */
	public abstract void sendMessage(String message);

}
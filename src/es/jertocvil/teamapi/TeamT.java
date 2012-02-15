
package es.jertocvil.teamapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface TeamT {
    
    void add(Player p); // Añade jugador p al equipo
    void quit(Player p); // Borra al jugador p del equipo
    Player[] members(); // Lista los jugadores de este equipo
    Boolean isInTeam(Player p); // Dice si pertenece al equipo
    String getName(); // Devuelve el nombre del equipo
    void transport(Location l); //transporta todos los jugadores del equipo a l
    boolean hasLeader(); // dice si el equipo tiene líder
    void setLeader(Player p); //si el jugador p es del equipo, pasa a ser líder.
    Player getLeader(); //devuelve el líder del equipo
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jertocvil.teamapi;

import java.util.HashMap;

/**
 *
 * @author Jeronimo
 */
public class Group {
    private String groupName;
    private HashMap<String, Team> teams;
    public Group(String name, Team[] teams){
        groupName = name;
        for (Team t : teams) {
            this.teams.put(t.getName(), t);
        }
    }
    
}

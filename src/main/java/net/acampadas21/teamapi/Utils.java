package net.acampadas21.teamapi;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class Utils {

    public static HashMap<String, Team> teams;
    public static boolean signalOn;

    public static boolean isTeam(String input) {
        boolean marca = false;
        for (Team t : teams.values()) {
            if (t.getName().equalsIgnoreCase(input)) {
                marca = true;
            }
        }
        return marca;
    }

    public static boolean createTeam(String input) {
        if (!teams.containsKey(input)) {
            teams.put(input, new Team(input));
            return true;
        } else {
            return false;
        }
    }

    public static Team inWhichTeam(Player p) {
        for (Team t : teams.values()) {
            if (t.isInTeam(p)) {
                return t;
            }
        }
        return null;
    }

    public static Team getTeamFromName(String name) {
        for (Team team : teams.values()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    public static String[] listTeams() {
        ArrayList<String> a = new ArrayList<String>();
        for (Team t : teams.values()) {
            a.add(t.getName());
        }
        return (String[]) a.toArray();
    }
//    public static String[] listPlayers(Team[] ts){
//        ArrayList<String> a = new ArrayList<String>();
//        for (Team t : ts) {
//            if(t != null){
//                String[] s = t.members();
//                for (String string : s) {
//                    a.add(string);
//                }
//            }
//        }
//        return (String[])a.toArray();
//    }
}

package net.acampadas21.teamapi.listeners;

import net.acampadas21.teamapi.TeamManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

	private TeamManager tm;

	public Executor(TeamManager tm) {
		this.tm = tm;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if ("team".equalsIgnoreCase(commandLabel)) {
			if (args[0].equalsIgnoreCase("new")) {
				// Commands.createTeam(args[1], p);
				if (args[1] != null) {
					tm.newTeam(args[1]);
					p.sendMessage("Team created");
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (args[1] != null) {
					if (tm.isTeam(args[1])) {
						tm.deleteTeam(tm.getTeamByName(args[1]));
						p.sendMessage("Team removed");
					}
				}
			} else if (args[0].equalsIgnoreCase("clear")) {
				tm.clearTeams();
				p.sendMessage("All teams deleted");
				/*
				 * } else if(args[0].equalsIgnoreCase("sign")){ if(args[1] !=
				 * null){ tm.newTeam(args[1]); p.sendMessage("Team created"); }
				 */
			} else if (args[0].equalsIgnoreCase("tp")) {
				if (args[1] != null) {
					if (tm.isTeam(args[1])) {
						tm.getTeamByName(args[1]).transport(p.getLocation());
					}
				}
			}
		}

		return false;
	}

}

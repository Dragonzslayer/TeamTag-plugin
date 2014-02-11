package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave
{
	private main plugin = null;

public Leave(CommandSender sender, String[] args)
  {
	this.plugin = main.getInstance();
    Player player = (Player)sender;
	if (this.plugin.votedPlayers.contains(player)) this.plugin.votedPlayers.remove(player);
    this.plugin.tagPlayers.remove(player);
    if (this.plugin.redTeam.contains(player))
    {
      this.plugin.redTeam.remove(player);
      if (this.plugin.kills.containsKey(player.getName())) {
        this.plugin.kills.remove(player.getName());
      }
      if (this.plugin.deaths.containsKey(player.getName())) {
        this.plugin.deaths.remove(player.getName());
      }
      this.plugin.joinLobby.tpToLobby(player);
      player.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " You have joined the lobby.");
      player.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Thanks for playing!");
      player.sendMessage("========================");
	  player.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Your stats this game:");
	  player.sendMessage(ChatColor.GREEN + "You tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.kills.get(sender.getName()) : "0") + " people.");
	  player.sendMessage(ChatColor.GREEN + "You were tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.deaths.get(sender.getName()) : "0") + " times.");
	  player.sendMessage("========================");
	  player.getInventory().clear();
      this.plugin.Methods.restoreInv(player);
    }
    if (this.plugin.blueTeam.contains(player))
    {
      this.plugin.blueTeam.remove(player);
      if (this.plugin.kills.containsKey(player.getName())) {
        this.plugin.kills.remove(player.getName());
      }
      if (this.plugin.deaths.containsKey(player.getName())) {
        this.plugin.deaths.remove(player.getName());
      }
      this.plugin.joinLobby.tpToLobby(player);
      player.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " You have joined the lobby.");
      player.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Thanks for playing!");
      player.sendMessage("========================");
	  player.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Your stats this game:");
	  player.sendMessage(ChatColor.GREEN + "You tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.kills.get(sender.getName()) : "0") + " people.");
	  player.sendMessage(ChatColor.GREEN + "You were tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.deaths.get(sender.getName()) : "0") + " times.");
	  player.sendMessage("========================");
	  player.getInventory().clear();
      this.plugin.Methods.restoreInv(player);
    }
  }
}

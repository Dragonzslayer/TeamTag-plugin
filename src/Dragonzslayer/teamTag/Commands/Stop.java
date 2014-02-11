package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop
{
	private main plugin = null;
public Stop(CommandSender sender, String[] args) {
		this.plugin = main.getInstance();
    if (this.plugin.start.containsKey(this.plugin.startString))
    {
      for (Player player : this.plugin.blueTeam)
      {
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " The game has ended! Thanks for playing!");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Your stats this game:");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(ChatColor.GREEN + "You tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.kills.get(sender.getName()) : "0") + " people.");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(ChatColor.GREEN + "You were tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.deaths.get(sender.getName()) : "0") + " times.");
    	    this.plugin.votedPlayers.remove(player);
    	    this.plugin.tagPlayers.remove(player);
          this.plugin.blueTeam.remove(player);
          if (this.plugin.kills.containsKey(player.getName())) {
            this.plugin.kills.remove(player.getName());
          }
          if (this.plugin.deaths.containsKey(player.getName())) {
            this.plugin.deaths.remove(player.getName());
          }
          player.getInventory().clear();
        this.plugin.Methods.restoreInv(player);
        this.plugin.joinLobby.tpToLobby(player);
        }

      for (Player player : this.plugin.redTeam)
      {
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " The game has ended! Thanks for playing!");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Your stats this game:");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(ChatColor.GREEN + "You tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.kills.get(sender.getName()) : "0") + " people.");
    	  this.plugin.getServer().getPlayer(player.getName()).sendMessage(ChatColor.GREEN + "You were tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.deaths.get(sender.getName()) : "0") + " times.");
      	if (this.plugin.votedPlayers.contains(player)) this.plugin.votedPlayers.remove(player);
        this.plugin.tagPlayers.remove(player);
          this.plugin.redTeam.remove(player);
          if (this.plugin.kills.containsKey(player.getName())) {
            this.plugin.kills.remove(player.getName());
          }
          if (this.plugin.deaths.containsKey(player.getName())) {
            this.plugin.deaths.remove(player.getName());
          }
      	player.getInventory().clear();
        this.plugin.Methods.restoreInv(player);
        this.plugin.joinLobby.tpToLobby(player);
        }

      this.plugin.blueTeam.clear();
      this.plugin.redTeam.clear();
      this.plugin.start.clear();

      this.plugin.kills.clear();
      this.plugin.deaths.clear();
      sender.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + "The game was stopped sucessfully!");
      return;
    }

    if (!this.plugin.start.containsKey(this.plugin.startString)) {
      sender.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " There is no tag game running!");
    }
    else
    {
      sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You don't have the permission to use that command!");
      this.plugin.logger.info(sender.getName() + " tried to use a command, failed: no permission!");
    }
  }
}

package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats
{
	private main plugin = null;
  public Stats(CommandSender sender, String[] args) {
	  this.plugin = main.getInstance();
    if (args.length == 1) {
      Player player = (Player)sender;
      sender.sendMessage(ChatColor.AQUA + "======== " + this.plugin.pPrefix + ChatColor.GREEN + player.getName() + "'s" + ChatColor.YELLOW + " stats:" + ChatColor.AQUA + " ========");
      sender.sendMessage(ChatColor.GREEN + "You have tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.kills.get(sender.getName()) : "0") + " people.");
      sender.sendMessage(ChatColor.GREEN + "You have been tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(sender.getName()) ? (Comparable<?>)this.plugin.deaths.get(sender.getName()) : "0") + " times.");
    }
    if (args.length == 2) {
      Player targetPlayer = this.plugin.getServer().getPlayer(args[1]);
      sender.sendMessage(ChatColor.AQUA + "======== " + this.plugin.pPrefix + ChatColor.GREEN + targetPlayer.getName() + "'s" + ChatColor.YELLOW + " stats:" + ChatColor.AQUA + " ========");
      sender.sendMessage(ChatColor.GREEN + "You have been tagged " + ChatColor.YELLOW + (this.plugin.kills.containsKey(targetPlayer.getName()) ? (Comparable<?>)this.plugin.kills.get(targetPlayer.getName()) : "0") + " people.");
      sender.sendMessage(ChatColor.GREEN + "You have been tagged " + ChatColor.YELLOW + (this.plugin.deaths.containsKey(targetPlayer.getName()) ? (Comparable<?>)this.plugin.deaths.get(targetPlayer.getName()) : "0") + " times.");
    }
  }
}

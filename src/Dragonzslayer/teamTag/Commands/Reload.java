package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Reload
{
	private main plugin = null;
  public Reload(CommandSender sender, String[] args) {
	  this.plugin = main.getInstance();
    this.plugin.reloadConfig();
    sender.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + "Config reloaded");
  }
}

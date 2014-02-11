package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Start
{
	private main plugin = null;
  public Start(CommandSender sender, String[] args) {
		this.plugin = main.getInstance();
    if (!this.plugin.start.containsKey(this.plugin.startString))
    {
    	this.plugin.startGame.start();
      }
    else if (this.plugin.start.containsKey(this.plugin.startString)) {
      sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " The game is already running!");
      sender.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To stop the game use /tt stop");
    }
    else
    {
      sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You don't have the permission to use that command!");
      this.plugin.logger.info(sender.getName() + " tried to use a command, failed: no permission!");
    }
  }
}

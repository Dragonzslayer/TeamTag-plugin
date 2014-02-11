package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn
{
	 private main plugin = null;
  public SetSpawn(CommandSender sender, String[] args) {
	this.plugin = main.getInstance();
    Player player = (Player)sender;
    Location l = player.getLocation();
    if ((args.length == 1))
    {
      sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Command Usage: /teamtag setspawn <blue|red>");
    }

    if ((args.length == 2) && (args[1].equalsIgnoreCase("blue")))
    {
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "blue" + "." + "X", Double.valueOf(l.getX()));
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "blue" + "." + "Y", Double.valueOf(l.getY()));
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "blue" + "." + "Z", Double.valueOf(l.getZ()));
    	this.plugin.saveConfig();

      player.sendMessage(this.plugin.pPrefix + ChatColor.BLUE + " Spawn set for blue team");
    }

    if ((args.length == 2) && (args[1].equalsIgnoreCase("red")))
    {
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "red" + "." + "X", Double.valueOf(l.getX()));
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "red" + "." + "Y", Double.valueOf(l.getY()));
    	this.plugin.getConfig().set(player.getWorld().getName() + "." + "red" + "." + "Z", Double.valueOf(l.getZ()));
    	this.plugin.saveConfig();

      player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Spawn set for red team");
    }
  }
}

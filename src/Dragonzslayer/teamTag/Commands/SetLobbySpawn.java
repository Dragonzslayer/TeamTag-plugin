package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySpawn
{
	  private main plugin = null;
  public SetLobbySpawn(CommandSender sender, String[] args) {
	this.plugin = main.getInstance();
    Player player = (Player)sender;
    this.plugin.getConfig().set(player.getWorld().getName() + "." + "lobby" + "." + "X", Integer.valueOf(player.getLocation().getBlockX()));
    this.plugin.getConfig().set(player.getWorld().getName() + "." + "lobby" + "." + "Y", Integer.valueOf(player.getLocation().getBlockY()));
    this.plugin.getConfig().set(player.getWorld().getName() + "." + "lobby" + "." + "Z", Integer.valueOf(player.getLocation().getBlockZ()));
    this.plugin.saveConfig();

    sender.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Lobby spawn set sucessfully");
  }
}

package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby
{
	private main plugin = null;
public Lobby(CommandSender sender, String[] args) {
		this.plugin = main.getInstance();
    Player player = (Player)sender;
    int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
    int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
    int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

    if ((!this.plugin.blueTeam.contains(player)) || (!this.plugin.redTeam.contains(player))) {
      player.teleport(new Location(player.getWorld(), x, y, z));

      sender.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " You joined the TeamTag lobby.");
    }

    if (this.plugin.blueTeam.contains(player))
    {
    if (this.plugin.votedPlayers.contains(player)) this.plugin.votedPlayers.remove(player);
      this.plugin.tagPlayers.remove(player);
      this.plugin.blueTeam.remove(player);
      player.teleport(new Location(player.getWorld(), x, y, z));
      sender.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " You left the " + ChatColor.BLUE + "Blue Team" + ChatColor.YELLOW + " and joined the lobby!");
      player.getInventory().clear();
      this.plugin.Methods.removeArmor(player);
      this.plugin.Methods.restoreInv(player);
    }

    if (this.plugin.redTeam.contains(player))
    {
    if (this.plugin.votedPlayers.contains(player)) this.plugin.votedPlayers.remove(player);
       this.plugin.tagPlayers.remove(player);
      this.plugin.redTeam.remove(player);
      player.teleport(new Location(player.getWorld(), x, y, z));
      sender.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " You left the " + ChatColor.RED + "Red Team" + ChatColor.YELLOW + " and joined the lobby!");
      player.getInventory().clear();
      this.plugin.Methods.removeArmor(player);
      this.plugin.Methods.restoreInv(player);
    }
  }
}

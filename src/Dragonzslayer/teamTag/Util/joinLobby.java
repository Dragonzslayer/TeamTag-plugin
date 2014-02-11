package Dragonzslayer.teamTag.Util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import Dragonzslayer.teamTag.main;

public class joinLobby {
	private main plugin = null;
public void tpToLobby(Player player){
	this.plugin = main.getInstance();
	int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
    int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
    int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());
    player.teleport(new Location(player.getWorld(), x, y, z));
	}
}

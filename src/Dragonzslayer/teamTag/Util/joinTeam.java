package Dragonzslayer.teamTag.Util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Dragonzslayer.teamTag.main;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class joinTeam {
	private main plugin = null;
	public final void joinBlueTeam(Player player){
		this.plugin = main.getInstance();
        player.sendMessage(this.plugin.pPrefix + ChatColor.BLUE + " You joined the Blue team!");
        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
        
        this.plugin.tagPlayers.add(player);
        this.plugin.blueTeam.add(player);
        this.plugin.Methods.joinBlue(player);

        int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
        int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
        int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

        player.teleport(new Location(player.getWorld(), x, y, z));

        ItemStack[] inv = player.getInventory().getContents();
        List invList = Arrays.asList(inv);
        this.plugin.getConfig().set("Inventories." + player.getName(), invList);
        this.plugin.saveConfig();
        player.getInventory().clear();
        this.plugin.Methods.removeArmor(player);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().setItemInHand(new ItemStack(Material.SNOW_BALL, 64));
        player.updateInventory();
	}
	public final void joinRedTeam(Player player){
		player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You joined the Red team!.");
        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
        
        this.plugin.tagPlayers.add(player);
        this.plugin.redTeam.add(player);
        this.plugin.Methods.joinRed(player);

        int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
        int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
        int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "spectate" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

        player.teleport(new Location(player.getWorld(), x, y, z));

        ItemStack[] inv = player.getInventory().getContents();
        List invList = Arrays.asList(inv);
        this.plugin.getConfig().set("Inventories." + player.getName(), invList);
        this.plugin.saveConfig();
        player.getInventory().clear();
        this.plugin.Methods.removeArmor(player);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().setItemInHand(new ItemStack(Material.SNOW_BALL, 64));
        player.updateInventory();
	}
}

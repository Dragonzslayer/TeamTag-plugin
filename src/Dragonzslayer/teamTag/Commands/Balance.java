package Dragonzslayer.teamTag.Commands;

import java.util.Arrays;
import java.util.List;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Balance
{
	private main plugin = null;
  @SuppressWarnings({ "rawtypes", "deprecation" })
public Balance(CommandSender sender, String[] args)
  {
		this.plugin = main.getInstance();
	  if (this.plugin.redTeam.size() > this.plugin.blueTeam.size()){
		  for (Player tP:this.plugin.tagPlayers){
			  tP.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Balancing Teams!");
		  }
		  while (this.plugin.redTeam.size() > this.plugin.blueTeam.size()){
			  for (Player p : this.plugin.redTeam){
				  if (!((sender.hasPermission("TeamTag.vipJoin")||sender.hasPermission("TeamTag.admin.*")|| sender.isOp()))){
					  if (this.plugin.redTeam.contains(p))
				        {
				          this.plugin.redTeam.remove(p);
				          if (this.plugin.kills.containsKey(p.getName())) {
				            this.plugin.kills.remove(p.getName());
				          }
				          if (this.plugin.deaths.containsKey(p.getName())) {
				            this.plugin.deaths.remove(p.getName());
				          }
				        Player player = p;
				        player.sendMessage(this.plugin.pPrefix + ChatColor.BLUE + " You joined the Blue team!");
				        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
				        
				        this.plugin.tagPlayers.add(player);
				        this.plugin.blueTeam.add(player);

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
			            this.plugin.Methods.joinBlue(player);
				        player.setGameMode(GameMode.ADVENTURE);
				        player.getInventory().setItemInHand(new ItemStack(Material.SNOW_BALL, 64));
				        player.updateInventory();
				        }
				  }
			  }
		  }
	  }
	  else if (this.plugin.redTeam.size() < this.plugin.blueTeam.size()){
		  for (Player tP:this.plugin.tagPlayers){
			  tP.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Balancing Teams!");
		  }
		  while (this.plugin.redTeam.size() < this.plugin.blueTeam.size()){
			  for (Player p : this.plugin.blueTeam){
				  if (!((sender.hasPermission("TeamTag.vipJoin")||sender.hasPermission("TeamTag.admin.*")|| sender.isOp()))){
					  if (this.plugin.blueTeam.contains(p))
				        {
				          this.plugin.blueTeam.remove(p);
				          if (this.plugin.kills.containsKey(p.getName())) {
				            this.plugin.kills.remove(p.getName());
				          }
				          if (this.plugin.deaths.containsKey(p.getName())) {
				            this.plugin.deaths.remove(p.getName());
				          }
				        Player player = p;
				  		player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You joined the Red team!.");
				        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
				        
				        this.plugin.tagPlayers.add(player);
				        this.plugin.redTeam.add(player);

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
			            this.plugin.Methods.joinRed(player);
				        player.setGameMode(GameMode.ADVENTURE);
				        player.getInventory().setItemInHand(new ItemStack(Material.SNOW_BALL, 64));
				        player.updateInventory();
				        }
				  }
			  }
		  }
	  }
	  else if (this.plugin.redTeam.size() == this.plugin.blueTeam.size()){
		  sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Teams are already balanced!");
	  }
  }
}

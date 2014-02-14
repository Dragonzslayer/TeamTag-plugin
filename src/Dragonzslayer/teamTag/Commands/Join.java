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

public class Join
{
	private main plugin = null;
@SuppressWarnings({ "deprecation", "rawtypes" })
public Join(CommandSender sender, String[] args)
  {
	this.plugin = main.getInstance();
    Player player = (Player)sender;
    if (!this.plugin.start.containsKey(this.plugin.startString)){
    	if ((args.length == 1)){
     	   if (this.plugin.redTeam.size() < this.plugin.blueTeam.size()) {
     	      if (!this.plugin.redTeam.contains(player)) {
     	 		player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You joined the Red team!.");
     	        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
     	        
     	        this.plugin.tagPlayers.add(player);
     	        this.plugin.redTeam.add(player);

     	       int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
     	      int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
     	      int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

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
     	      this.checkNumber();
     	      }
     	    }
     	   else if (!this.plugin.blueTeam.contains(player)) {
     	        player.sendMessage(this.plugin.pPrefix + ChatColor.BLUE + " You joined the Blue team!");
     	        player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
     	        
     	        this.plugin.tagPlayers.add(player);
     	        this.plugin.blueTeam.add(player);

     	       int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
     	      int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
     	      int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

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
     	      this.checkNumber();
     	   }
     	}
      if ((sender.hasPermission("TeamTag.vipJoin")) || (sender.hasPermission("TeamTag.admin.*")) || (sender.isOp())) {
        if ((args.length == 2) && (args[1].equalsIgnoreCase("blue"))){
          if (!this.plugin.blueTeam.contains(player)) {
            if (this.plugin.redTeam.size() < this.plugin.blueTeam.size()) {
              sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " WARNING: The Blue team is now larger than the red team!");
              if ((sender.hasPermission("TeamTag.admin.balance")) || (sender.isOp())) {
              sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Please use /teamtag balance!");
              }
            }
            player.sendMessage(this.plugin.pPrefix + ChatColor.BLUE + " You joined the Blue team!");
            player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
            
            this.plugin.tagPlayers.add(player);
            this.plugin.blueTeam.add(player);

            int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
            int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
            int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

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
         this.checkNumber();
          }
          else if (this.plugin.blueTeam.contains(player)) {
            player.sendMessage(this.plugin.pPrefix + ChatColor.DARK_RED + " You are already on the" + ChatColor.BLUE + ChatColor.BOLD + " blue " + ChatColor.RESET + ChatColor.DARK_RED + "team.");
          }
          else if (this.plugin.redTeam.contains(player)) {
            player.sendMessage(this.plugin.pPrefix + ChatColor.DARK_RED + " You are already on the" + ChatColor.RED + ChatColor.BOLD + " red " + ChatColor.RESET + ChatColor.DARK_RED + "team.");
          }

        }

        if ((args.length == 2) && (args[1].equalsIgnoreCase("red"))) {
          if (!this.plugin.redTeam.contains(player)) {
            if (this.plugin.redTeam.size() > this.plugin.blueTeam.size()) {
              player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " WARNING: The Red team is now larger than the blue team!");
              if ((sender.hasPermission("TeamTag.admin.balance")) || (sender.isOp())) {
                player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Please use /teamtag balance!");
              }
            }
    		player.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You joined the Red team!.");
            player.sendMessage(this.plugin.pPrefix + ChatColor.GOLD + " To vote to start the game do: /tt vote");
            
            this.plugin.tagPlayers.add(player);
            this.plugin.redTeam.add(player);

            int x = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "X", player.getWorld().getSpawnLocation().getBlockX());
            int y = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Y", player.getWorld().getSpawnLocation().getBlockY());
            int z = this.plugin.getConfig().getInt(player.getWorld().getName() + "." + "lobby" + "." + "Z", player.getWorld().getSpawnLocation().getBlockZ());

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
          this.checkNumber();
          }
          else if (this.plugin.blueTeam.contains(player)) {
            player.sendMessage(this.plugin.pPrefix + ChatColor.DARK_RED + " You are already on the" + ChatColor.BLUE + ChatColor.BOLD + " blue " + ChatColor.RESET + ChatColor.DARK_RED + "team.");
          }
          else if (this.plugin.redTeam.contains(player)) {
            player.sendMessage(this.plugin.pPrefix + ChatColor.DARK_RED + " You are already on the" + ChatColor.RED + ChatColor.BOLD + " red " + ChatColor.RESET + ChatColor.DARK_RED + "team.");
          }
        }

      }
      else if ((args.length == 2)&&((args[1].equalsIgnoreCase("red"))||(args[1].equalsIgnoreCase("blue")))){
    	  sender.sendMessage(this.plugin.pPrefix + ChatColor.RED + "Please purchase VIP to be able to choose which team you join!");
      }

    }

    if (this.plugin.start.containsKey(this.plugin.startString))
    {
      player.sendMessage(this.plugin.pPrefix + ChatColor.RED + "Sorry! The game has already started! Please use /tt spectate instead.");
    }
  }
public void checkNumber (){
	if (this.plugin.getConfig().getBoolean("AutoStart.Enabled")){
	if (this.plugin.tagPlayers.size() >= this.plugin.getConfig().getInt("AutoStart.NumberToStart")) {
		this.plugin.startGame.start();
	}
	}
}
}

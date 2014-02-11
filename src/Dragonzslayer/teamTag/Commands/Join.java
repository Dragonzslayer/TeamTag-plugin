package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join
{
	private main plugin = null;
public Join(CommandSender sender, String[] args)
  {
	this.plugin = main.getInstance();
    Player player = (Player)sender;
    if (!this.plugin.start.containsKey(this.plugin.startString)){
    	if ((args.length == 1)){
     	   if (this.plugin.redTeam.size() < this.plugin.blueTeam.size()) {
     	      if (!this.plugin.redTeam.contains(player)) {
     	      this.plugin.joinTeam.joinRedTeam(player);
     	      this.checkNumber();
     	      }
     	    }
     	   else if (!this.plugin.blueTeam.contains(player)) {
     	      this.plugin.joinTeam.joinBlueTeam(player);
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
         this.plugin.joinTeam.joinBlueTeam(player);
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
          this.plugin.joinTeam.joinRedTeam(player);
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

package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager extends main
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
    if ((args.length == 0) || (args[0].equalsIgnoreCase("help"))) {
      if ((sender.hasPermission("TeamTag.admin.help")) || (sender.isOp()))
      sender.sendMessage(ChatColor.AQUA + "====== TeamTag Help (1/2) ======");
      sender.sendMessage(ChatColor.GOLD + "/tt adminhelp: Show the admin's help page");
      if (!sender.hasPermission("TeamTag.admin.help"))
      sender.sendMessage(ChatColor.AQUA + "========= TeamTag Help =========");
      sender.sendMessage(ChatColor.GOLD + "/tt help: Show the help page");
      sender.sendMessage(ChatColor.GOLD + "/tt join <blue|red> : Join a team");
      sender.sendMessage(ChatColor.GOLD + "/tt leave : Leaves the current TeamTag game");
      sender.sendMessage(ChatColor.GOLD + "/tt stats : See your stats");
      sender.sendMessage(ChatColor.GOLD + "/tt lobby : Join the lobby");
      sender.sendMessage(ChatColor.AQUA + "================================");
      return true;
    }

    if ((args.length == 0) || (args[0].equalsIgnoreCase("adminhelp"))) {
      if ((sender.hasPermission("TeamTag.admin.help")) || (sender.isOp())) {
        sender.sendMessage(ChatColor.AQUA + "======  TeamTag Help (2/2) =====");
        sender.sendMessage(ChatColor.AQUA + "= ------  " + ChatColor.RED + "Admin Commands" + ChatColor.AQUA + " ----- =");
        sender.sendMessage(ChatColor.GOLD + "/tt start : starts the game");
        sender.sendMessage(ChatColor.GOLD + "/tt stop : stops the game");
        sender.sendMessage(ChatColor.GOLD + "/tt reload : reloads the TeamTag config");
        sender.sendMessage(ChatColor.GOLD + "/tt setlobby : set the lobby location");
        sender.sendMessage(ChatColor.GOLD + "/tt setspawn <blue|red> : sets the spawnplaces of the teams");
        sender.sendMessage(ChatColor.GOLD + "/tt balance: use TeamTag's Team-Balancer");
        sender.sendMessage(ChatColor.AQUA + "================================");
      }return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("info"))) {
      String ver = pdfFile.getVersion() + ChatColor.AQUA;
      String auth = ChatColor.DARK_RED + "Dragonz" + ChatColor.RED + "slayer_" + ChatColor.AQUA;

      sender.sendMessage(ChatColor.AQUA + "======== TeamTag Info ========");
      sender.sendMessage(ChatColor.AQUA + "=        Version: " + ver + "        =");
      sender.sendMessage(ChatColor.AQUA + "=   Author: " + auth + "   =");
      sender.sendMessage(ChatColor.AQUA + "=      Copyright (c)2014     =");
      sender.sendMessage(ChatColor.AQUA + "==============================");
      return true;
    }

    if (!(sender instanceof Player)) {
      if (args[0].equalsIgnoreCase("start")) new Start(sender, args);
      if (args[0].equalsIgnoreCase("stop")) new Stop(sender, args);
      if (args[0].equalsIgnoreCase("reload")) new Reload(sender, args);
      if (args[0].equalsIgnoreCase("list")) new List(sender, args);
      if (args[0].equalsIgnoreCase("kick")) new Kick(sender, args);
      else {
        sender.sendMessage(ChatColor.RED + "That command can only be used in-game or was not recognized");
      }
    }
    if ((sender instanceof Player)) {
      if ((sender.hasPermission("TeamTag.player")) || (sender.hasPermission("TeamTag.admin.*")) || (sender.isOp())) {
        if (args[0].equalsIgnoreCase("join")) new Join(sender, args);
        else if (args[0].equalsIgnoreCase("leave")) new Leave(sender, args);
        else if (args[0].equalsIgnoreCase("vote")) new Vote(sender, args);
        else if (args[0].equalsIgnoreCase("list")) new List(sender, args);
        else if (args[0].equalsIgnoreCase("lobby")) new Lobby(sender, args);
        else if (args[0].equalsIgnoreCase("stats")) new Stats(sender, args);
      }
      if (((sender.hasPermission("TeamTag.admin.start")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("start"))) new Start(sender, args);

      if (((sender.hasPermission("TeamTag.admin.stop")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("stop"))) new Stop(sender, args);

      if (((sender.hasPermission("TeamTag.admin.setspawn")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("setspawn"))) new SetSpawn(sender, args);

      if (((sender.hasPermission("TeamTag.admin.kick")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("kick"))) new Kick(sender, args);

      if (((sender.hasPermission("TeamTag.admin.setLobby")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("setlobby"))) new SetLobbySpawn(sender, args);

      if (((sender.hasPermission("TeamTag.admin.reload")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("reload"))) new Reload(sender, args);

      if (((sender.hasPermission("TeamTag.admin.balance")) || (sender.isOp())) && 
        (args[0].equalsIgnoreCase("balance"))) new Balance(sender, args);

    }

    return false;
  }
}

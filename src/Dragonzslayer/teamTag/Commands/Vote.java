package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote
{
	private main plugin = null;
public Vote(CommandSender sender, String[] args) {
		this.plugin = main.getInstance();
    Player p = (Player)sender;
    if (this.plugin.votedPlayers.contains(p)) {
      p.sendMessage(this.plugin.pPrefix + ChatColor.RED + "You have already voted to start the game!");
      return;
    }
    if (this.plugin.start.containsKey(this.plugin.startString)) {
      p.sendMessage(this.plugin.pPrefix + ChatColor.RED + "The game has already started!");
      return;
    }
    this.plugin.votedPlayers.add(p);
    Bukkit.getServer().broadcastMessage(this.plugin.pPrefix + ChatColor.GREEN + " " + p.getName() + " voted to start the game!");
    if ((this.plugin.tagPlayers.size() > 2) && (this.plugin.votedPlayers.size() / this.plugin.tagPlayers.size() > 0.75D) && 
      (!this.plugin.start.containsKey(this.plugin.startString)))
    {
      this.plugin.startGame.start();
    }
  }
}

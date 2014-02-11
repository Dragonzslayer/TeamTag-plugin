package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class List
{
	private main plugin = null;
  public List(CommandSender sender, String[] args)
  {
		this.plugin = main.getInstance();
    StringBuilder blueSb = new StringBuilder();
    StringBuilder redSb = new StringBuilder();
    for (Player bluePlayer : this.plugin.blueTeam) {
      if (bluePlayer != this.plugin.blueTeam.get(this.plugin.blueTeam.size() - 1)) {
        redSb.append(bluePlayer.getName() + ", ");
      }
      else
      {
        redSb.append(bluePlayer);
      }
    }

    for (Player redPlayer : this.plugin.redTeam) {
      if (redPlayer != this.plugin.redTeam.get(this.plugin.redTeam.size() - 1)) {
        redSb.append(redPlayer.getName() + ", ");
      }
      else
      {
        redSb.append(redPlayer);
      }
    }
    sender.sendMessage(ChatColor.AQUA + "============ [TeamTag] List ============");
    sender.sendMessage(ChatColor.GOLD + "Players on team" + ChatColor.BLUE + " blue" + ChatColor.GOLD + ": " + ChatColor.BLUE + blueSb.toString());
    sender.sendMessage(ChatColor.GOLD + "Players on team" + ChatColor.RED + " red" + ChatColor.GOLD + ": " + ChatColor.RED + redSb.toString());
    sender.sendMessage(ChatColor.AQUA + "========================================");
  }
}

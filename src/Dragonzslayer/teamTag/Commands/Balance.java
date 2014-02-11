package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance
{
	private main plugin = null;
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
				          this.plugin.joinTeam.joinBlueTeam(p);
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
				          this.plugin.joinTeam.joinRedTeam(p);
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

package Dragonzslayer.teamTag.Event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import Dragonzslayer.teamTag.main;

public class signClick
implements Listener
{
	private main plugin = null;
@EventHandler(priority=EventPriority.HIGHEST)
public void clickHandler(PlayerInteractEvent e)
{this.plugin = main.getInstance();
	Player p = e.getPlayer();
  if ((e.getAction() != Action.RIGHT_CLICK_BLOCK) && (e.getAction() != Action.LEFT_CLICK_BLOCK)) return;

  Block clickedBlock = e.getClickedBlock();
  if ((clickedBlock.getType() != Material.SIGN) && (clickedBlock.getType() != Material.SIGN_POST) && (clickedBlock.getType() != Material.WALL_SIGN)) return;
  Sign sign = (Sign)clickedBlock.getState();

  String[] lines = sign.getLines();
  if (lines.length < 3) return;
  if (lines[0].equalsIgnoreCase("[TeamTag]")) {
    e.setCancelled(true);
    try {
      if (lines[2].equalsIgnoreCase("Auto Assign")) {
        
      }
      else {
        if (lines[2].equalsIgnoreCase("Join Blue")){
        	if (!(p.hasPermission("TeamTag.vipJoin"))){
        		p.sendMessage(this.plugin.pPrefix + ChatColor.RED + "Please buy VIP status to choose your team!");
        		return;
        	}
            else{
                if (this.plugin.redTeam.size() < this.plugin.blueTeam.size()) {
                    p.sendMessage(this.plugin.pPrefix + ChatColor.RED + " WARNING: The Blue team is now larger than the red team!");
                    if ((p.hasPermission("TeamTag.admin.balance")) || (p.isOp())) {
                    p.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Please use /teamtag balance!");
                    }
                  }
            	this.plugin.joinTeam.joinBlueTeam(p);
            	this.plugin.Join.checkNumber();
            }
        }
        if (lines[2].equalsIgnoreCase("Join Red")){
        	if (!(p.hasPermission("TeamTag.vipJoin"))){
        		p.sendMessage(this.plugin.pPrefix + ChatColor.RED + "Please buy VIP status to choose your team!");
        		return;
        	}
        	else{
                if (this.plugin.redTeam.size() > this.plugin.blueTeam.size()) {
                    p.sendMessage(this.plugin.pPrefix + ChatColor.RED + " WARNING: The Blue team is now larger than the red team!");
                    if ((p.hasPermission("TeamTag.admin.balance")) || (p.isOp())) {
                    p.sendMessage(this.plugin.pPrefix + ChatColor.RED + " Please use /teamtag balance!");
                    }
                  }
        		this.plugin.joinTeam.joinRedTeam(p);
            	this.plugin.Join.checkNumber();
        	}
        }
      }
    }
    catch (Exception ex)
    {
    }
  }
  }
}

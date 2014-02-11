package Dragonzslayer.teamTag.Event;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class signListener implements Listener
{
	  @EventHandler
	  public void onSignChange(SignChangeEvent event)
	  {
	    Player p = event.getPlayer();
	    Sign sign = (Sign)event.getBlock();

	    if (event.getLine(0).equalsIgnoreCase("[TeamTag]"))
	    {
	      if (!(p.hasPermission("TeamTag.admin.sign.place"))&&(!(p.hasPermission("TeamTag.admin.*")))&&(!(p.isOp()))) {
	        return;
	      }
	    	  sign.setLine(0, "§3[TeamTag]");
	    	  if (event.getLine(2).equalsIgnoreCase("Auto Assign")){
	    		  sign.setLine(2, "§6Auto §2Assign");
	    	  }
	    	  if (event.getLine(2).equalsIgnoreCase("Join Red")){
	    		  sign.setLine(2, "§LJoin §r§4Red");
	    	  }
	    	  if (event.getLine(2).equalsIgnoreCase("Join Blue")){
	    		  sign.setLine(2, "§LJoin §r§1Blue");
	    	  }
	          sign.update();
	  }
	}
}

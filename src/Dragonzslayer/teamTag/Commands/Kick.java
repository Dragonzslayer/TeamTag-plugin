package Dragonzslayer.teamTag.Commands;

import Dragonzslayer.teamTag.main;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kick
{
	private main plugin = null;
  @SuppressWarnings({ "rawtypes", "unchecked" })
public Kick(CommandSender sender, String[] args)
  {
		this.plugin = main.getInstance();
    Player targetPlayer = this.plugin.getServer().getPlayer(args[1]);

    if (this.plugin.redTeam.contains(targetPlayer))
    {
    	if (this.plugin.votedPlayers.contains(targetPlayer)) this.plugin.votedPlayers.remove(targetPlayer);
        this.plugin.tagPlayers.remove(targetPlayer);
      this.plugin.redTeam.remove(targetPlayer);
      if (this.plugin.kills.containsKey(targetPlayer.getName())) {
        this.plugin.kills.remove(targetPlayer.getName());
      }
      if (this.plugin.deaths.containsKey(targetPlayer.getName())) {
        this.plugin.deaths.remove(targetPlayer.getName());
      }

      int x = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "X", targetPlayer.getWorld().getSpawnLocation().getBlockX());
      int y = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "Y", targetPlayer.getWorld().getSpawnLocation().getBlockY());
      int z = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "Z", targetPlayer.getWorld().getSpawnLocation().getBlockZ());

      targetPlayer.teleport(new Location(targetPlayer.getWorld(), x, y, z));

      targetPlayer.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You have been kicked from the game!");
      targetPlayer.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " Joined the lobby.");
      targetPlayer.getInventory().clear();

      List savedInv = (List)this.plugin.getConfig().get("Inventories." + targetPlayer.getName());
      ItemStack[] inv = (ItemStack[])savedInv.toArray(new ItemStack[] { new ItemStack(Material.AIR, 1) });
      targetPlayer.getInventory().setContents(inv);
    }
    if (this.plugin.blueTeam.contains(targetPlayer))
    {
    	if (this.plugin.votedPlayers.contains(targetPlayer)) this.plugin.votedPlayers.remove(targetPlayer);
        this.plugin.tagPlayers.remove(targetPlayer);
      this.plugin.blueTeam.remove(targetPlayer);
      if (this.plugin.kills.containsKey(targetPlayer.getName())) {
        this.plugin.kills.remove(targetPlayer.getName());
      }
      if (this.plugin.deaths.containsKey(targetPlayer.getName())) {
        this.plugin.deaths.remove(targetPlayer.getName());
      }

      int x = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "X", targetPlayer.getWorld().getSpawnLocation().getBlockX());
      int y = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "Y", targetPlayer.getWorld().getSpawnLocation().getBlockY());
      int z = this.plugin.getConfig().getInt(targetPlayer.getWorld().getName() + "." + "lobby" + "." + "Z", targetPlayer.getWorld().getSpawnLocation().getBlockZ());

      targetPlayer.teleport(new Location(targetPlayer.getWorld(), x, y, z));
      if (args.length == 2) {
        targetPlayer.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You have been kicked from the game!");
      }
      else if (args.length == 3) {
        String reason = args[2];
        targetPlayer.sendMessage(this.plugin.pPrefix + ChatColor.RED + " You have been kicked from the game for " + reason + "!");
      }
      targetPlayer.sendMessage(this.plugin.pPrefix + ChatColor.YELLOW + " Joined the lobby");
      targetPlayer.getInventory().clear();

      List savedInv = (List)this.plugin.getConfig().get("Inventories." + targetPlayer.getName());
      ItemStack[] inv = (ItemStack[])savedInv.toArray(new ItemStack[] { new ItemStack(Material.AIR, 1) });
      targetPlayer.getInventory().setContents(inv);
    }
  }
}

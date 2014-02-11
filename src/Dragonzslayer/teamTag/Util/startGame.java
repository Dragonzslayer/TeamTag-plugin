package Dragonzslayer.teamTag.Util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import Dragonzslayer.teamTag.main;
@SuppressWarnings("deprecation")
public class startGame {
	private main plugin = null;
	public String Status = "WAITING";
	public int CountDown = 60;
    private BukkitTask startingCountDownTask = null;
 public void start(){
	this.plugin = main.getInstance();
	this.Status = "STARTING";
    this.CountDown = 60;
    this.startingCountDownTask = this.plugin.getServer().getScheduler().runTaskTimer(this.plugin, new Runnable() {
      public void run() {
        if (startGame.this.CountDown > 0) {
          if (startGame.this.CountDown > 1)
            startGame.this.sendMessage(startGame.this.plugin.pPrefix + "Game starting in " + startGame.this.CountDown + " seconds!");
            if (startGame.this.CountDown < 10){
            	startGame.this.sendSound();
            }
          else {
            startGame.this.sendMessage(startGame.this.plugin.pPrefix + "Game starting in " + startGame.this.CountDown + " second!");
            startGame.this.sendSound();
          }
          startGame.this.CountDown -= 1;
        } else {
          startGame.this.cancelStart();
          startGame.this.teleportTagPlayers();
          startGame.this.Status = "INGAME";
        }
      }
    }
    , 0L, 20L);
 }
 

 public void cancelStart() {
   if (this.startingCountDownTask != null)
     this.startingCountDownTask.cancel();
 }
 public void sendMessage(String Message) {
	    for (Player p : this.plugin.tagPlayers)
	      p.sendMessage(Message);
	  }
 public void sendSound() {
	    for (Player p : this.plugin.tagPlayers)
	      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2F, 1F);
	  }
 public void teleportTagPlayers(){
     for (Player redplayers : this.plugin.redTeam)
     {
       int x = this.plugin.getConfig().getInt(redplayers.getWorld().getName() + "." + "red" + "." + "X", redplayers.getWorld().getSpawnLocation().getBlockX());
       int y = this.plugin.getConfig().getInt(redplayers.getWorld().getName() + "." + "red" + "." + "Y", redplayers.getWorld().getSpawnLocation().getBlockY());
       int z = this.plugin.getConfig().getInt(redplayers.getWorld().getName() + "." + "red" + "." + "Z", redplayers.getWorld().getSpawnLocation().getBlockZ());
       int blockid = this.plugin.getConfig().getInt("ReloadBlockID");
       String blockname = Material.getMaterial(blockid).name();
       redplayers.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Game started! right click on the " + ChatColor.RED + blockname + ChatColor.GREEN + " to get ammo!!");
       redplayers.teleport(new Location(redplayers.getWorld(), x, y, z));
     }

     for (Player blueplayers : this.plugin.blueTeam) {
       int x = this.plugin.getConfig().getInt(blueplayers.getWorld().getName() + "." + "blue" + "." + "X", blueplayers.getWorld().getSpawnLocation().getBlockX());
       int y = this.plugin.getConfig().getInt(blueplayers.getWorld().getName() + "." + "blue" + "." + "Y", blueplayers.getWorld().getSpawnLocation().getBlockY());
       int z = this.plugin.getConfig().getInt(blueplayers.getWorld().getName() + "." + "blue" + "." + "Z", blueplayers.getWorld().getSpawnLocation().getBlockZ());
       int blockid = this.plugin.getConfig().getInt("ReloadBlockID");
       String blockname = Material.getMaterial(blockid).name();
       blueplayers.sendMessage(this.plugin.pPrefix + ChatColor.GREEN + " Game started! right click on the " + ChatColor.RED + blockname + ChatColor.GREEN + " to get ammo!!");
       blueplayers.teleport(new Location(blueplayers.getWorld(), x, y, z));
     }
     this.plugin.start.put(this.plugin.startString, this.plugin.startString);
 }
}

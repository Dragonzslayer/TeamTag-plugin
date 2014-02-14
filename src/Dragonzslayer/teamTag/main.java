package Dragonzslayer.teamTag;

import Dragonzslayer.teamTag.Commands.CommandManager;
import Dragonzslayer.teamTag.Util.Methods;
import Dragonzslayer.teamTag.Util.joinLobby;
import Dragonzslayer.teamTag.Util.startGame;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
  implements Listener
{
  public String pPrefix = ChatColor.GOLD + "[TeamTag]" + ChatColor.AQUA;
  public Logger logger = Logger.getLogger("Minecraft");
  private static main plugin = null;
  public Methods Methods;
  public joinLobby joinLobby;
  public startGame startGame;
  public List<Player> votedPlayers = new ArrayList<Player>();
  public List<Player> tagPlayers = new ArrayList<Player>();
  public List<Player> redTeam = new ArrayList<Player>();
  public List<Player> blueTeam = new ArrayList<Player>();
  public List<String> redteamstring = new ArrayList<String>();
  public List<String> blueteamstring = new ArrayList<String>();

  public PluginDescriptionFile pdfFile = getDescription();

  public Map<String, Location> blueteamspawn = new HashMap<String, Location>();
  public Map<String, Location> redteamspawn = new HashMap<String, Location>();
  
  public HashMap<String, Integer> kills = new HashMap<String, Integer>();
  public HashMap<String, Integer> deaths = new HashMap<String, Integer>();

  public HashMap<String, String> start = new HashMap<String, String>();
  public String startString = "started";

  public String redteamspawnplace = "redteamspawnplace";
  public String blueteamspawnplace = "blueteamspawnplace";

  public void onDisable()
  {
    PluginDescriptionFile pdfFile = getDescription();
    logger.info("[TeamTag] version " + pdfFile.getVersion() + " has been disabled!");
  }

  public void onEnable()
  {
    PluginDescriptionFile pdfFile = getDescription();
    logger.info("[TeamTag] version " + pdfFile.getVersion() + " has been enabled!");
    plugin = this;
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this, this);

    createConfig();
    reloadConfig();

    Methods = new Methods();
    joinLobby = new joinLobby();
    startGame = new startGame();
    getCommand("teamtag").setExecutor(new CommandManager());
  }
  public void writeToConsole(String Message) {
    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[TeamTag] " + ChatColor.BLUE + Message);
  }
  public void createConfig() {
    File configFile = new File(getDataFolder() + File.separator + "config.yml");
    if (!configFile.exists()) {
      getLogger().warning(String.format(pPrefix + ChatColor.RED + "Cannot find config.yml, Generating deafult....", new Object[] { getDescription().getName() }));
      saveDefaultConfig();
      getLogger().warning(String.format(pPrefix + "Config generated !", new Object[] { getDescription().getName() }));
    }
  }
  public static main getInstance()
  {
    return plugin;
  }
  

  @EventHandler(priority=EventPriority.HIGHEST)
  public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if((event.getDamager() instanceof Snowball) && (event.getEntity() instanceof Player)) {
			Snowball s = (Snowball)event.getDamager();
			Player damaged = (Player)event.getEntity();
			if(s.getShooter() instanceof Player) {
				Player shooter  = ((Player)s.getShooter());
				if ((redTeam.contains(damaged)) || (redTeam.contains(shooter))){
					shooter.sendMessage(pPrefix + ChatColor.YELLOW + " you've tagged " + ChatColor.GREEN + damaged.getName());
					damaged.sendMessage(pPrefix + ChatColor.YELLOW + " you've been tagged by " + ChatColor.GREEN + shooter.getName());

					kills.put(shooter.getName(), Integer.valueOf(kills.containsKey(shooter.getName()) ? ((Integer)kills.get(shooter.getName())).intValue() + 1 : 1));
					deaths.put(damaged.getName(), Integer.valueOf(deaths.containsKey(damaged.getName()) ? ((Integer)deaths.get(damaged.getName())).intValue() + 1 : 1));
					redTeam.remove(damaged);
					blueTeam.add(damaged);
					Methods.joinBlue(damaged);
					if (start.containsKey(startString)) {
						int x = getConfig().getInt(damaged.getWorld().getName() + "." + "blue" + "." + "X", damaged.getWorld().getSpawnLocation().getBlockX());
						int y = getConfig().getInt(damaged.getWorld().getName() + "." + "blue" + "." + "Y", damaged.getWorld().getSpawnLocation().getBlockY());
						int z = getConfig().getInt(damaged.getWorld().getName() + "." + "blue" + "." + "Z", damaged.getWorld().getSpawnLocation().getBlockZ());
						damaged.teleport(new Location(damaged.getWorld(), x, y, z));
					}
					else{
						damaged.sendMessage(ChatColor.RED + "We're sorry, but an internal error has occured.");
						blueTeam.remove(damaged);
						damaged.getInventory().clear();
						Methods.removeArmor(damaged);
						Methods.restoreInv(damaged);
						tagPlayers.remove(damaged);
						joinLobby.tpToLobby(damaged);
					}
				}

				if ((blueTeam.contains(shooter)) || (blueTeam.contains(damaged))){
					shooter.sendMessage(pPrefix + ChatColor.YELLOW + " you've tagged " + ChatColor.GREEN + damaged.getName());
					damaged.sendMessage(pPrefix + ChatColor.YELLOW + " you've been tagged by " + ChatColor.GREEN + shooter.getName());

					kills.put(shooter.getName(), Integer.valueOf(kills.containsKey(shooter.getName()) ? ((Integer)kills.get(shooter.getName())).intValue() + 1 : 1));
					deaths.put(damaged.getName(), Integer.valueOf(deaths.containsKey(damaged.getName()) ? ((Integer)deaths.get(damaged.getName())).intValue() + 1 : 1));
					blueTeam.remove(damaged);
					redTeam.add(damaged);
					Methods.joinRed(damaged);
					if (start.containsKey(startString)) {
						int x = getConfig().getInt(damaged.getWorld().getName() + "." + "red" + "." + "X", damaged.getWorld().getSpawnLocation().getBlockX());
						int y = getConfig().getInt(damaged.getWorld().getName() + "." + "red" + "." + "Y", damaged.getWorld().getSpawnLocation().getBlockY());
						int z = getConfig().getInt(damaged.getWorld().getName() + "." + "red" + "." + "Z", damaged.getWorld().getSpawnLocation().getBlockZ());
						damaged.teleport(new Location(damaged.getWorld(), x, y, z));
					}
					else{
						damaged.sendMessage(ChatColor.RED + "We're sorry, but an internal error has occured.");
						redTeam.remove(damaged);
						damaged.getInventory().clear();
						Methods.removeArmor(damaged);
						Methods.restoreInv(damaged);
						tagPlayers.remove(damaged);
						joinLobby.tpToLobby(damaged);
					}
				}
			}
		}
  }

  @SuppressWarnings("deprecation")
@EventHandler
  public void onPlayerIntract(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if ((blueTeam.contains(player)) || (redTeam.contains(player))) {
      if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        int id = getConfig().getInt("ReloadBlockID");
        if (event.getClickedBlock().getTypeId() == id) {
          if ((blueTeam.contains(player)) || (redTeam.contains(player)))
          {
            player.getInventory().setItemInHand(new ItemStack(Material.SNOW_BALL, 64));
            player.updateInventory();
            player.sendMessage(pPrefix + ChatColor.GREEN + " You just recieved 64 snowballs!");
          }
          if ((!blueTeam.contains(player.getName())) || (!redTeam.contains(player.getName()))) {
            event.setCancelled(false);
          }
        }
      }
      if (event.getAction() == Action.RIGHT_CLICK_AIR) {
        boolean message = getConfig().getBoolean("LowAmoMessage");
        if (player.getItemInHand().equals(new ItemStack(Material.SNOW_BALL, 8))){
            if (message)
            {
              int blockid = getConfig().getInt("ReloadBlockID");
              String BlockName = Material.getMaterial(blockid).name();

              player.sendMessage(pPrefix + ChatColor.RED + " Out of snowballs!");
              player.sendMessage(pPrefix + ChatColor.GREEN + " Right Click on the " + ChatColor.RED + BlockName + ChatColor.GREEN + " again!");
            }

        }
        else if (message) {
          player.sendMessage(pPrefix + ChatColor.RED + " Out of ammo!!");
          player.sendMessage(pPrefix + ChatColor.GREEN + " Click on the iron block to get ammo");
        }
      }
    }
  }


  @EventHandler
  public void onPlayerTeleport(PlayerTeleportEvent e) { PlayerTeleportEvent.TeleportCause cause = e.getCause();
    Player player = e.getPlayer();
    if (((redTeam.contains(player)) || (blueTeam.contains(player))) && 
      (cause == PlayerTeleportEvent.TeleportCause.ENDER_PEARL))
      e.setCancelled(true);
  }

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent event)
  {
    Entity entity = event.getEntity();
    if (entity == null)
      event.blockList().clear();
  }
}

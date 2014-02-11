package Dragonzslayer.teamTag.Util;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Dragonzslayer.teamTag.main;

public class Methods
{
	private main plugin = null;
	public void joinBlue(Player player) {

    ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta helmMeta = (LeatherArmorMeta)helm.getItemMeta();
    helmMeta.setColor(Color.BLUE);
    helm.setItemMeta(helmMeta);
    player.getInventory().setHelmet(helm);

    ItemStack ChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta ChestPlateMeta = (LeatherArmorMeta)ChestPlate.getItemMeta();
    ChestPlateMeta.setColor(Color.BLUE);
    ChestPlate.setItemMeta(ChestPlateMeta);
    player.getInventory().setChestplate(ChestPlate);

    ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta)Leggings.getItemMeta();
    LeggingsMeta.setColor(Color.BLUE);
    Leggings.setItemMeta(LeggingsMeta);
    player.getInventory().setLeggings(Leggings);

    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta BootsMeta = (LeatherArmorMeta)Boots.getItemMeta();
    BootsMeta.setColor(Color.BLUE);
    Boots.setItemMeta(BootsMeta);
    player.getInventory().setBoots(Boots);
    
    player.setHealth(player.getMaxHealth());
    player.setFoodLevel(100);
    for (PotionEffect e : player.getActivePotionEffects()){
    	player.removePotionEffect(e.getType());
    }
  }

  public void joinRed(Player player) {

    ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta helmMeta = (LeatherArmorMeta)helm.getItemMeta();
    helmMeta.setColor(Color.RED);
    helm.setItemMeta(helmMeta);
    player.getInventory().setHelmet(helm);

    ItemStack ChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta ChestPlateMeta = (LeatherArmorMeta)ChestPlate.getItemMeta();
    ChestPlateMeta.setColor(Color.RED);
    ChestPlate.setItemMeta(ChestPlateMeta);
    player.getInventory().setChestplate(ChestPlate);

    ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta)Leggings.getItemMeta();
    LeggingsMeta.setColor(Color.RED);
    Leggings.setItemMeta(LeggingsMeta);
    player.getInventory().setLeggings(Leggings);

    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta BootsMeta = (LeatherArmorMeta)Boots.getItemMeta();
    BootsMeta.setColor(Color.RED);
    Boots.setItemMeta(BootsMeta);
    player.getInventory().setBoots(Boots);
    
    player.setHealth(player.getMaxHealth());
    player.setFoodLevel(100);
    for (PotionEffect e : player.getActivePotionEffects()){
    	PotionEffectType allEffects = e.getType();
    	player.removePotionEffect(allEffects);
    }
  }

	  public void removeArmor(Player player) {
	    ItemStack helm = new ItemStack(Material.AIR);
	    player.getInventory().setHelmet(helm);
	    ItemStack ChestPlate = new ItemStack(Material.AIR);
	    player.getInventory().setChestplate(ChestPlate);
	    ItemStack Leggings = new ItemStack(Material.AIR);
		player.getInventory().setLeggings(Leggings);
	    ItemStack Boots = new ItemStack(Material.AIR);
	    player.getInventory().setBoots(Boots);
	  }
	  
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	public void restoreInv(Player player){
		this.plugin = main.getInstance();
	    List savedInv = (List)this.plugin.getConfig().get("Inventories." + player.getName());
	    ItemStack[] inv = (ItemStack[])savedInv.toArray(new ItemStack[] { new ItemStack(Material.AIR, 1) });
	    player.getInventory().setContents(inv);
	  }
}

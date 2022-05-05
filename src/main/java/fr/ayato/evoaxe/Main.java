package fr.ayato.evoaxe;

import fr.ayato.evoaxe.listeners.GiveEvoAxe;
import fr.ayato.evoaxe.listeners.OnBlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "*---*_ " + ChatColor.AQUA + "Evolutive Axe Enabled !" + ChatColor.LIGHT_PURPLE + " _*---*");
        saveDefaultConfig();
        getCommand("evoaxe").setExecutor(new GiveEvoAxe(this));
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
    }

    @Override
    public void onDisable(){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE +"*---*_ " + ChatColor.AQUA + "Evolutive Axe Disabled !" + ChatColor.LIGHT_PURPLE + " _*---*");
    }

    public String GetName() {
        return this.getConfig().getString("name");
    }

    public String GetLore() {
        return this.getConfig().getString("lore");
    }

    public String GetEnchantments(Integer axeLevel) {
        return this.getConfig().getString("level." + axeLevel + ".ench");
    }

    public String GetEnchantmentsLevels(Integer axeLevel) {
        return this.getConfig().getString("level." + axeLevel + ".niveau");
    }

    public Integer GetExperienceFloor(Integer axeLevel) {
        return Integer.valueOf(this.getConfig().getString("level." + axeLevel + ".exp"));
    }

    public Boolean hasAxeLevelUp(Integer experience, Integer axeLevel) {
        return experience >= GetExperienceFloor(axeLevel);
    }

}

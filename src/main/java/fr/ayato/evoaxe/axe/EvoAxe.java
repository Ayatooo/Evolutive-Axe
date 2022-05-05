package fr.ayato.evoaxe.axe;

import de.tr7zw.nbtapi.NBTItem;
import fr.ayato.evoaxe.Main;
import fr.ayato.evoaxe.utils.Spliter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EvoAxe {

    // Plugin
    private static Main plugin;
    public EvoAxe(Main main) {
        plugin = main;
    }

    // Initialize an axe with data of the configuration file
    // Executed by the command
    public static ItemStack itemToGive(String displayName, String loreFromConfig, String enchantments,  String levels, String axeLevel) {
        final ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        final ItemMeta itemMeta = item.getItemMeta();

        // Set Text Data
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Spliter.strToList(Spliter.stringToSplit(loreFromConfig)));

        // Set Enchantments on the axe
        Utils.SetEnchantments(item, itemMeta, enchantments, levels);

        // Set the nbti tags
        NBTItem nbti = new NBTItem(item);
        final Integer exp = 0;
        nbti.setInteger("lvl", Integer.valueOf(axeLevel));
        nbti.setInteger("exp", exp);
        nbti.applyNBT(item);
        return item;
    }

}

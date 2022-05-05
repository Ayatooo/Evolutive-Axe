package fr.ayato.evoaxe.axe;

import de.tr7zw.nbtapi.NBTItem;
import fr.ayato.evoaxe.utils.Spliter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    //Get the level of the axe
    public static Integer GetAxeLevel(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        nbti.getItem();
        return nbti.getInteger("lvl");
    }

    //Get the experience of the axe
    public static Integer GetAxeExperience(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        nbti.getItem();
        return nbti.getInteger("exp");
    }

    //Add one experience point to the axe
    public static void AddExperienceToAxe(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        nbti.getItem();
        Integer experience = nbti.getInteger("exp");
        experience += 1;
        nbti.setInteger("exp", experience);
        nbti.applyNBT(item);
    }

    //Update the data of the axe
    public static void changeAxeData(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        nbti.getItem();
        Integer experience = 0;
        Integer axeLevel = nbti.getInteger("lvl");
        axeLevel += 1;
        nbti.setInteger("exp", experience);
        nbti.setInteger("lvl", axeLevel);
        nbti.applyNBT(item);
    }

    //Update the enchantments of the axe
    public static void changeAxeEnchantments(ItemStack item, String enchantments, String enchantmentsLevel) {
        ItemMeta itemMeta = item.getItemMeta();
        SetEnchantments(item, itemMeta, enchantments, enchantmentsLevel);
    }

    //Set the enchantments on the axe
    public static void SetEnchantments(ItemStack item, ItemMeta itemMeta, String enchantments, String enchantLevels) {
        List<String> enchantList = Spliter.strToList(Spliter.stringToSplit(enchantments));
        List<String> levelsList = Spliter.strToList(Spliter.stringToSplit(enchantLevels));
        for (int i = 0; i < enchantList.size(); i++) {
            itemMeta.addEnchant(Enchantment.getByName(enchantList.get(i)), Integer.parseInt(levelsList.get(i)), true);
        }
        item.setItemMeta(itemMeta);
    }

    //Set the experience on the lore of the axe
    public static void SetLoreExperience(ItemStack item, Integer experience, Integer axeLevel) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> loreList = itemMeta.getLore();
        List<String> newLoreList = new ArrayList<>();
        for (String lore : loreList) {
            if (lore.contains("Exp")) {
                lore = "§f➥ §bExp §f» §b" + experience + " §f| §bLevel §d" + axeLevel;
            }
            newLoreList.add(lore);
        }
        itemMeta.setLore(newLoreList);
        item.setItemMeta(itemMeta);
    }
}

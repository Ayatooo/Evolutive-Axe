package fr.ayato.evoaxe.listeners;

import fr.ayato.evoaxe.Main;
import fr.ayato.evoaxe.axe.Utils;
import fr.ayato.evoaxe.utils.Spliter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class OnBlockBreak implements Listener {

    // Plugin
    private static Main plugin;
    public OnBlockBreak(Main main) {
        plugin = main;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent eventBlock) {
        final Block block = eventBlock.getBlock();
        final Player player = eventBlock.getPlayer();
        final ItemStack item = player.getItemInHand();

        //Check if the player is using the evoaxe correctly
        if (item != null && item.getType() == Material.DIAMOND_AXE && block.getType() == Material.MELON_BLOCK) {
            if (item.hasItemMeta()) {
                assert item.getItemMeta().getLore() != null;
                final List<String> itemLore = item.getItemMeta().getLore();
                final List<String> loreConfig = Spliter.strToList(plugin.GetLore());

                //Check if the item really is the evoaxe
                if (itemLore.get(1).equals(loreConfig.get(1)) && !eventBlock.isCancelled()) {
                    Integer level = Utils.GetAxeLevel(item);
                    Utils.AddExperienceToAxe(item);
                    final Integer experience = Utils.GetAxeExperience(item);

                    //Check if the axe has leveled up
                    if (level != 5) {
                        if (plugin.hasAxeLevelUp(experience, level)) {
                            Utils.changeAxeData(item);
                            level = Utils.GetAxeLevel(item);
                            final String enc = plugin.GetEnchantments(level);
                            final String lvl = plugin.GetEnchantmentsLevels(level);
                            Utils.changeAxeEnchantments(item, enc, lvl);
                        }
                    }
                    Utils.SetLoreExperience(item, experience, level);
                    player.updateInventory();
                }
            }
        }
    }
}

package fr.ayato.evoaxe.listeners;

import fr.ayato.evoaxe.axe.EvoAxe;
import fr.ayato.evoaxe.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GiveEvoAxe implements CommandExecutor {
    // Plugin
    private final Main plugin;
    public GiveEvoAxe(Main main) {
        this.plugin = main;
    }

    // Executed when a player type the /evoaxe command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            final Player player = Bukkit.getPlayer(args[0]);
            final String level = args[1];
            final Integer axeLevel = Integer.valueOf(level);

            // Only a player with the permission or a player buying / executing the command with console can
            // have an evolutive axe
            if (player.hasPermission("evoaxe") || sender instanceof ConsoleCommandSender) {

                // Item's Data
                final String name = plugin.GetName();
                final String lore = plugin.GetLore();
                final String enchantments = plugin.GetEnchantments(axeLevel);
                final String enchantmentsLevel = plugin.GetEnchantmentsLevels(axeLevel);

                // Give the evolutive axe to the player
                player.getInventory().addItem(EvoAxe.itemToGive(name, lore, enchantments, enchantmentsLevel, level));
                player.updateInventory();
                }
        } else {
            sender.sendMessage("§b§lEvolutive-Axe §e» §b/evoaxe <pseudo> <axe-level>");
        }
        return false;
    }
}

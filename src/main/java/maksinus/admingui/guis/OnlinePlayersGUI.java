package maksinus.admingui.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class OnlinePlayersGUI implements Listener {
    public static Inventory whitelist = Bukkit.createInventory(null, 54, "Игроки");
    public static void players(Player p) {
        int slot = 0;
        for (OfflinePlayer all : Bukkit.getOnlinePlayers()) {
            SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
            skull.setOwningPlayer(all);
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("");
            if (all.isOnline()) {
                lore.add(ChatColor.GREEN + "Онлайн");
            } else lore.add(ChatColor.RED + "Оффлайн");
            lore.add("");
            skull.setLore(lore);
            ItemStack listedPlayers = new ItemStack(Material.PLAYER_HEAD, 1);
            skull.setDisplayName(ChatColor.YELLOW + all.getName());
            listedPlayers.setItemMeta(skull);
            whitelist.setItem(slot, listedPlayers);
            slot += 1;

        }

        p.openInventory(whitelist);
    }
}

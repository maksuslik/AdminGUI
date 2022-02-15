package maksinus.admingui.guis;

import maksinus.admingui.AdminGUI;
import maksinus.admingui.utils.Colorize;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class OnlinePlayersGUI implements Listener {
    public static Inventory whitelist = Bukkit.createInventory(null, 54, "Игроки");

    private static final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

    public static void players(Player p) {
        int slot = 0;
        whitelist.clear();
        for (Player all : onlinePlayers) {
            SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
            skull.setOwningPlayer(all);
            ItemStack listedPlayers = new ItemStack(Material.PLAYER_HEAD, 1);
            skull.setDisplayName(ChatColor.YELLOW + all.getName());
            listedPlayers.setItemMeta(skull);
            whitelist.setItem(slot, listedPlayers);
            slot++;
        }
        p.openInventory(whitelist);
    }
}

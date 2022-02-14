package maksinus.admingui.guis;

import maksinus.admingui.AdminGUI;
import maksinus.admingui.utils.Colorize;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerInfoGUI implements Listener {

    public static String targetName;

    @EventHandler
    public void playerInfoClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("Игроки")) {
            e.setCancelled(true);

            OfflinePlayer playername = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName());
            Inventory playerInv = Bukkit.createInventory(null, 9, "Информация об игроке");
            // голова
            SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
            skull.setOwningPlayer(playername);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            skull.setLore(lore);
            ItemStack listedPlayers = new ItemStack(Material.PLAYER_HEAD, 1);
            skull.setDisplayName(ChatColor.WHITE + playername.getName());
            listedPlayers.setItemMeta(skull);
            playerInv.setItem(4, listedPlayers);
            // Назад
            ItemStack back = new ItemStack(Material.ARROW);
            ItemMeta bmeta = back.getItemMeta();
            bmeta.setDisplayName(Colorize.color("&cНазад"));
            back.setItemMeta(bmeta);
            playerInv.setItem(0, back);
            // Выдать привилегию
            ItemStack donate = new ItemStack(Material.DIAMOND);
            ItemMeta donateMeta = donate.getItemMeta();
            donateMeta.setDisplayName(Colorize.color("&eВыдать донат"));
            donate.setItemMeta(donateMeta);
            playerInv.setItem(1, donate);
            // Телепортироваться к игроку
            ItemStack tpToPlayer = new ItemStack(Material.ENDER_EYE);
            ItemMeta tpToPlayerMeta = tpToPlayer.getItemMeta();
            tpToPlayerMeta.setDisplayName(Colorize.color("&aТелепортироваться к игроку"));
            tpToPlayer.setItemMeta(tpToPlayerMeta);
            playerInv.setItem(2, tpToPlayer);
            // Телепортировать к себе
            ItemStack tpToSender = new ItemStack(Material.ENDER_PEARL);
            ItemMeta tpToSenderMeta = tpToSender.getItemMeta();
            tpToSenderMeta.setDisplayName(Colorize.color("&aТелепортировать к себе"));
            tpToSender.setItemMeta(tpToSenderMeta);
            playerInv.setItem(3, tpToSender);
            // Убить
            ItemStack kill = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta killMeta = kill.getItemMeta();
            killMeta.setDisplayName(Colorize.color("&cУбить игрока"));
            kill.setItemMeta(killMeta);
            playerInv.setItem(5, kill);
            // Телепортировать на спавн
            ItemStack tpToSpawn = new ItemStack(Material.RED_BED);
            ItemMeta tpToSpawnMeta = tpToSpawn.getItemMeta();
            tpToSpawnMeta.setDisplayName(Colorize.color("&aТелепортировать на спавн"));
            tpToSpawn.setItemMeta(tpToSpawnMeta);
            playerInv.setItem(6, tpToSpawn);
            // кикнуть
            ItemStack kick = new ItemStack(Material.PINK_TERRACOTTA);
            ItemMeta kmeta = kick.getItemMeta();
            kmeta.setDisplayName(Colorize.color("&6Кикнуть"));
            kick.setItemMeta(kmeta);
            playerInv.setItem(7, kick);
            // забанить
            ItemStack ban = new ItemStack(Material.RED_TERRACOTTA);
            ItemMeta banmeta = ban.getItemMeta();
            banmeta.setDisplayName(Colorize.color("&4Забанить"));
            ban.setItemMeta(banmeta);
            playerInv.setItem(8, ban);
            p.openInventory(playerInv);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("Информация об игроке")) {
            e.setCancelled(true);
            targetName = Objects.requireNonNull(Objects.requireNonNull(e.getInventory().getItem(4)).getItemMeta()).getDisplayName().trim().substring(2);
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            switch (e.getCurrentItem().getType()) {
                case ARROW:
                    OnlinePlayersGUI.players(p);
                    return;
                case PINK_TERRACOTTA:
                    if (target == null) p.sendMessage("Игрок оффлайн!");
                    else p.chat("/kick " + Bukkit.getOfflinePlayer(targetName).getName());
                    return;
                case RED_TERRACOTTA:
                    if (target == null) p.sendMessage("Игрок оффлайн!");
                    p.chat("/ban " + target.getName());
                    return;
                case DIAMOND:
                    DonateGUI.donate(p);
                    return;
                case ENDER_EYE:
                    p.chat("/tp " + target.getName());
                    return;
                case ENDER_PEARL:
                    p.chat("/tp " + p.getName() + " " + target.getName());
                    return;
                case RED_STAINED_GLASS_PANE:
                    p.chat("/kill " + target.getName());
                    return;
                case RED_BED:
                    Location loc = AdminGUI.getInstance().getConfig().getLocation("spawnLocation");
                    if(loc == null) p.sendMessage(Objects.requireNonNull(AdminGUI.getInstance().getConfig().getString("messages.noSpawnLocation")));
                    p.chat("/tp " + target.getName() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
            }
        }
    }
}

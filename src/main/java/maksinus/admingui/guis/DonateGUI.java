package maksinus.admingui.guis;

import maksinus.admingui.AdminGUI;
import maksinus.admingui.utils.Colorize;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Objects;

public class DonateGUI implements Listener {
    private static Inventory donateInv = Bukkit.createInventory(null, 9, "Выдать донат");

    private static ConfigurationSection donateList = AdminGUI.getInstance().getConfig().getConfigurationSection("donateList");

    public static void donate(Player p) {
        // default
        ItemStack defaultt = new ItemStack(Material.RED_TERRACOTTA);
        ItemMeta defaulttMeta = defaultt.getItemMeta();
        defaulttMeta.setDisplayName(Colorize.color(donateList.getString("default")));
        defaultt.setItemMeta(defaulttMeta);
        donateInv.setItem(0, defaultt);
        // default1
        ItemStack default1 = new ItemStack(Material.PINK_TERRACOTTA);
        ItemMeta default1Meta = default1.getItemMeta();
        default1Meta.setDisplayName(Colorize.color(donateList.getString("default1")));
        default1.setItemMeta(default1Meta);
        donateInv.setItem(1, default1);
        // default2
        ItemStack default2 = new ItemStack(Material.ORANGE_TERRACOTTA);
        ItemMeta default2Meta = default2.getItemMeta();
        default2Meta.setDisplayName(Colorize.color(donateList.getString("default2")));
        default2.setItemMeta(default2Meta);
        donateInv.setItem(2, default2);
        // build
        ItemStack builder = new ItemStack(Material.YELLOW_TERRACOTTA);
        ItemMeta builderMeta = builder.getItemMeta();
        builderMeta.setDisplayName(Colorize.color(donateList.getString("builder")));
        builder.setItemMeta(builderMeta);
        donateInv.setItem(3, builder);
        // helper
        ItemStack helper = new ItemStack(Material.LIME_TERRACOTTA);
        ItemMeta helperMeta = helper.getItemMeta();
        helperMeta.setDisplayName(Colorize.color(donateList.getString("helper")));
        helper.setItemMeta(helperMeta);
        donateInv.setItem(4, helper);
        // moder
        ItemStack moder = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA);
        ItemMeta moderMeta = moder.getItemMeta();
        moderMeta.setDisplayName(Colorize.color(donateList.getString("moderator")));
        moder.setItemMeta(moderMeta);
        donateInv.setItem(5, moder);

        p.openInventory(donateInv);
    }

    @EventHandler
    public void donateClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("Выдать донат")) {
            String targetName = PlayerInfoGUI.targetName;
            Player player = Bukkit.getPlayerExact(targetName);
            if(player == null) {
                e.getWhoClicked().sendMessage(Colorize.color(AdminGUI.getInstance().getConfig().getString("messages.noPlayer")));
                e.getWhoClicked().sendMessage(String.valueOf(player));
            }
            e.setCancelled(true);
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case RED_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set default");
                    return;
                case PINK_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set default1");
                    return;
                case ORANGE_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set default2");
                    return;
                case YELLOW_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set builder");
                    return;
                case LIME_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set helper");
                    return;
                case LIGHT_BLUE_TERRACOTTA:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent set moderator");
                default:
                    e.setCancelled(true);
            }
        }
    }
}

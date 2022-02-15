package maksinus.admingui;

import maksinus.admingui.commands.ASetSpawnCommand;
import maksinus.admingui.commands.AdminGUICommand;
import maksinus.admingui.guis.DonateGUI;
import maksinus.admingui.guis.PlayerInfoGUI;
import maksinus.admingui.utils.Colorize;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminGUI extends JavaPlugin {

    private static AdminGUI instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerInfoGUI(), this);
        Bukkit.getPluginManager().registerEvents(new DonateGUI(), this);

        getCommand("admingui").setExecutor(new AdminGUICommand());
        getCommand("asetspawn").setExecutor(new ASetSpawnCommand());

        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    public static AdminGUI getInstance() {
        return instance;
    }
}

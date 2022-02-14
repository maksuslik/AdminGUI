package maksinus.admingui.commands;

import maksinus.admingui.AdminGUI;
import maksinus.admingui.utils.Colorize;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ASetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(!(p.hasPermission("admingui.setspawn"))) {
            p.sendMessage(Colorize.color(Objects.requireNonNull(AdminGUI.getInstance().getConfig().getString("messages.noPermissions"))));
            return true;
        }
        Location loc = p.getLocation();
        AdminGUI.getInstance().getConfig().set("spawnLocation", loc);
        AdminGUI.getInstance().saveConfig();
        int x = (int) Math.round(loc.getX());
        int y = (int) Math.round(loc.getY());
        int z = (int) Math.round(loc.getZ());
        p.sendMessage(Colorize.color("Точка спавна установлена по координатам &a" + x + " " + y + " " + z));
        return true;
    }
}

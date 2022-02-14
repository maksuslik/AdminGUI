package maksinus.admingui.commands;

import maksinus.admingui.AdminGUI;
import maksinus.admingui.guis.OnlinePlayersGUI;
import maksinus.admingui.guis.PlayerInfoGUI;
import maksinus.admingui.utils.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AdminGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(!p.hasPermission("admingui.use")) {
            p.sendMessage(Colorize.color(Objects.requireNonNull(AdminGUI.getInstance().getConfig().getString("messages.noPermissions"))));
            return true;
        }
        OnlinePlayersGUI.players(p);
        return true;
    }
}

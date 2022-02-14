package maksinus.admingui.utils;

import org.bukkit.ChatColor;

public class Colorize {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

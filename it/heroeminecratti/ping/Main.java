package it.heroeminecratti.ping;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Plugin, CommandExecutor, Listener
{
    public static Configuration config;
    public FileConfiguration fileConfig;
    
    public void onEnable() {
        this.saveDefaultConfig();
        Main.config = (Configuration)this.getConfig();
        this.getConfig().options().copyDefaults(true);
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.getLogger().info("PingCMD e' stato abilitato correttamente senza errori!");
    }
    
    public void onDisable() {
        this.getLogger().info("PingCMD e' stato disabilitato correttamente senza errori!");
    }
    
    public void onWarning() {
        this.getLogger().warning("Il plugin ha riscontrato un lieve errore!");
    }
    
    public void onError() {
        this.getLogger().severe("Il plugin ha riscontrato un grave errore!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
        String prefix = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix"));
        if (!cmd.getName().equalsIgnoreCase("ping")) {
            return true;
        }
        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendMessage(this.getConfig().getString("no-player"));
            return true;
        }
        if (sender.hasPermission("pingcmd.me")) {
            Player player = (Player)sender;
            int ping = ((CraftPlayer)player).getHandle().ping;
            player.sendMessage(String.valueOf(prefix) + ChatColor.translateAlternateColorCodes('&', "&aIl tuo ping \u00e8 di &b" + ping + " &ams"));
        }
        else {
            Player player = (Player)sender;
            player.sendMessage(this.getConfig().getString("no-permesso"));
        }
        return true;
    }
}

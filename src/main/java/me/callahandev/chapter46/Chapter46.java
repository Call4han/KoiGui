package me.callahandev.chapter46;

import me.callahandev.chapter46.commands.OpenCommand;
import me.callahandev.chapter46.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chapter46 extends JavaPlugin {
    private static Chapter46 plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("vault").setExecutor(new OpenCommand());
        getServer().getPluginManager().registerEvents(new Listeners(),this);


    }

    public static Chapter46 getPlugin() {
        return plugin;
    }
}

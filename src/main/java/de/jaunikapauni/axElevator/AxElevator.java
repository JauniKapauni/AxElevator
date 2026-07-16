package de.jaunikapauni.axElevator;

import de.jaunikapauni.axElevator.listener.ElevatorListener;
import de.jaunikapauni.axElevator.manager.ElevatorManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxElevator extends JavaPlugin {
    ElevatorManager elevatorManager;
    public ElevatorManager getElevatorManager(){
        return elevatorManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        elevatorManager = new ElevatorManager(this);
        elevatorManager.reload();
        getServer().getPluginManager().registerEvents(new ElevatorListener(this), this);
        getLogger().info("");
        getLogger().info("----------------------------------------");
        getLogger().info("Name: " + getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info(String.join("Authors: " + ", ", getDescription().getAuthors()));
        getLogger().info("----------------------------------------");
        getLogger().info("");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

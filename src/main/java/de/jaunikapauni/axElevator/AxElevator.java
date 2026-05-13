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
        getServer().getPluginManager().registerEvents(new ElevatorListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

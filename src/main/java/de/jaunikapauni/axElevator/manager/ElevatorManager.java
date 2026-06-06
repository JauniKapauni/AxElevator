package de.jaunikapauni.axElevator.manager;

import de.jaunikapauni.axElevator.AxElevator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ElevatorManager {

    AxElevator reference;
    public ElevatorManager(AxElevator reference){
        this.reference = reference;
    }

    public Material getElevatorBlock(){
        return Material.valueOf(reference.getConfig().getString("elevator.block"));
    }

    public int getDistance(){
        return reference.getConfig().getInt("elevator.max_distance");
    }

    public boolean isOnElevatorBlock(Player p){
        Location loc = p.getLocation().clone().subtract(0, 1, 0);
        return loc.getBlock().getType() == getElevatorBlock();
    }

    public void teleport(Player p, int yOffset){
        Location loc = p.getLocation().clone().add(0, yOffset, 0);
        while(!loc.getBlock().isPassable() && loc.getY() < p.getWorld().getMaxHeight()){
            loc.add(0, 1, 0);
        }
        p.teleport(loc);
    }

    public boolean isAboveElevatorBlock(Player p){
        for(int i = 0; i < getDistance(); i++){
            Location loc = p.getLocation().clone().add(0, i, 0);
            Material mat = loc.getBlock().getType();
            if(mat == getElevatorBlock()){
                return true;
            }
        }
        return false;
    }
}

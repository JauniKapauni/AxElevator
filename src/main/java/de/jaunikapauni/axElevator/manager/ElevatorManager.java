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

    public int getNextElevatorAbove(Player p){
        Location elevatorBlock = p.getLocation().clone().subtract(0, 1, 0);
        for(int i = 1; i <= getDistance(); i++){
            Location check = elevatorBlock.clone().add(0, i, 0);
            if(check.getBlock().getType() == getElevatorBlock()){
                return (int) check.getY();
            }
        }
        return -1;
    }

    public int getNextElevatorBelow(Player p){
        Location elevatorBlock = p.getLocation().clone().subtract(0, 1, 0);
        for(int i = 1; i <= getDistance(); i++){
            Location check = elevatorBlock.clone().subtract(0, i, 0);
            if(check.getBlock().getType() == getElevatorBlock()){
                return (int) check.getY();
            }
        }
        return -1;
    }

    public void teleport(Player p, int y){
        Location loc = p.getLocation().clone();
        loc.setY(y + 1);
        if(!loc.getBlock().isPassable() || !loc.clone().add(0, 1, 0).getBlock().isPassable()){
            return;
        }
        p.teleport(loc);
    }
}

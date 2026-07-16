package de.jaunikapauni.axElevator.manager;

import de.jaunikapauni.axElevator.AxElevator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ElevatorManager {

    AxElevator reference;
    public ElevatorManager(AxElevator reference){
        this.reference = reference;
    }
    Material elevatorBlock;
    int maxDistance;

    public void reload(){
        elevatorBlock = Material.valueOf(reference.getConfig().getString("elevator.block", "DIAMOND_BLOCK"));
        maxDistance = reference.getConfig().getInt("elevator.max_distance", 5);
    }

    public Material getElevatorBlock(){
        return elevatorBlock;
    }

    public int getDistance(){
        return maxDistance;
    }

    public boolean isOnElevatorBlock(Player p){
        Location loc = p.getLocation().clone().subtract(0, 1, 0);
        return loc.getBlock().getType() == getElevatorBlock();
    }

    public int getNextElevatorAbove(Player p){
        Block elevatorBlock = p.getLocation().clone().subtract(0, 1, 0).getBlock();
        for(int i = 1; i <= getDistance(); i++){
            Block check = elevatorBlock.getRelative(0, i, 0);
            if(check.getType() == elevatorBlock.getType()){
                return (int) check.getY();
            }
        }
        return -1;
    }

    public int getNextElevatorBelow(Player p){
        Block elevatorBlock = p.getLocation().clone().subtract(0, 1, 0).getBlock();
        for(int i = 1; i <= getDistance(); i++){
            Block check = elevatorBlock.getRelative(0, i, 0);
            if(check.getType() == elevatorBlock.getType()){
                return (int) check.getY();
            }
        }
        return -1;
    }

    public boolean teleport(Player p, int y){
        Location loc = p.getLocation().clone();
        loc.setX(loc.getBlockX() + 0.5);
        loc.setZ(loc.getBlockZ() + 0.5);
        loc.setY(y + 1);
        if(!loc.getBlock().isPassable() || !loc.clone().add(0, 1, 0).getBlock().isPassable()){
            return false;
        }
        p.teleport(loc);
        p.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        return true;
    }
}

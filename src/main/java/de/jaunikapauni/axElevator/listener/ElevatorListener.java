package de.jaunikapauni.axElevator.listener;

import de.jaunikapauni.axElevator.AxElevator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ElevatorListener implements Listener {

    AxElevator reference;
    public ElevatorListener(AxElevator reference){
        this.reference = reference;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(reference.getElevatorManager().isOnElevatorBlock(p)){
            return;
        }
        double deltaY = e.getTo().getY() - e.getFrom().getY();
        double threshold = 0.3;
        if(deltaY > threshold){
            if(reference.getElevatorManager().isAboveElevatorBlock(p)){
                reference.getElevatorManager().teleport(p, reference.getElevatorManager().getDistance() - 1);
            }
        }
        p.sendActionBar(ChatColor.GREEN + "Up!");
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        if(e.isSneaking()){
            return;
        }
        Player p = e.getPlayer();
        if(reference.getElevatorManager().isOnElevatorBlock(p)){
            return;
        }
        reference.getElevatorManager().teleport(p, -reference.getElevatorManager().getDistance());
        p.sendActionBar(ChatColor.GREEN + "Down!");
    }
}

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
        if(!reference.getElevatorManager().isOnElevatorBlock(p)){
            return;
        }
        if(!p.hasPermission("axelevator.use")){
            return;
        }
        double deltaY = e.getTo().getY() - e.getFrom().getY();
        double threshold = 0.3;
        if(deltaY > threshold){
            int elevator = reference.getElevatorManager().getNextElevatorAbove(p);
            if(elevator != -1){
                if(reference.getElevatorManager().teleport(p, elevator)){
                    p.sendActionBar(ChatColor.GREEN + "Up!");
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();
        if(!e.isSneaking()){
            return;
        }
        if(reference.getElevatorManager().isOnElevatorBlock(p)){
            if(!p.hasPermission("axelevator.use")){
                return;
            }
            int elevator = reference.getElevatorManager().getNextElevatorBelow(p);
            if(elevator != -1){
                if(reference.getElevatorManager().teleport(p, elevator)){
                    p.sendActionBar(ChatColor.GREEN + "Down!");
                }
            }
        }
    }
}

package me.callahandev.chapter46.listeners;

import com.sun.tools.javac.jvm.Items;
import me.callahandev.chapter46.Chapter46;
import me.callahandev.chapter46.utils.Vaultutils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PersistentDataContainer data = p.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(Chapter46.getPlugin(), "vault"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(Chapter46.getPlugin(), "vault"), PersistentDataType.STRING, "");
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getView().getTitle().equalsIgnoreCase("Your Personal Vault")) {
            ArrayList<ItemStack> prudenitem = new ArrayList<>();
            Arrays.stream(p.getInventory().getContents())
                    .filter(itemStack -> {
                        if (itemStack == null) {
                            return false;
                        }
                        return true;
                    }).forEach(itemStack -> prudenitem.add(itemStack));
            Vaultutils.storeItem(prudenitem, p);
        }

    }}



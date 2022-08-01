package me.callahandev.chapter46.commands;

import com.sun.tools.javac.jvm.Items;
import me.callahandev.chapter46.Chapter46;
import me.callahandev.chapter46.utils.Vaultutils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OpenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length<0){
                if (args[0].equalsIgnoreCase("open")){
                    ArrayList<ItemStack> vaultutils = Vaultutils.getItem(p);
                    Inventory vault = Bukkit.createInventory(p,54,"Your Personal Vault");

                }
            }
        }



        return true;
    }
}

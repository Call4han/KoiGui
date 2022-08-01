package me.callahandev.chapter46.utils;

import me.callahandev.chapter46.Chapter46;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

public class Vaultutils {

    public static void storeItem(List<ItemStack> items, Player p){
        PersistentDataContainer data = p.getPersistentDataContainer();

        if (items.size()==0){
            data.set(new NamespacedKey(Chapter46.getPlugin(),"vault"), PersistentDataType.STRING,"");

        }else {
            try{
                ByteArrayOutputStream io = new ByteArrayOutputStream();
                BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

                os.write(items.size());
                for (int a =0;a<items.size();a++){
                    os.writeObject(items.get(a));
                }
                byte[] rawdata = io.toByteArray();
                String encodedData = Base64.getEncoder().encodeToString(rawdata);
                data.set(new NamespacedKey(Chapter46.getPlugin(),"vault"),PersistentDataType.STRING,encodedData);
                os.close();
            }catch (IOException e){
                System.out.println(e);

            }
        }
    }
    public static ArrayList<ItemStack> getItem(Player p){
        PersistentDataContainer data = p.getPersistentDataContainer();
        ArrayList<ItemStack> items = new ArrayList<>();
        String encodedItem = data.get(new NamespacedKey(Chapter46.getPlugin(),"vault"),PersistentDataType.STRING);
        if (!encodedItem.isEmpty()){
            byte[] rawData = Base64.getDecoder().decode(encodedItem);

            try {
                ByteArrayInputStream io = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream in = new BukkitObjectInputStream(io);
                int intcount = in.readInt();
                for (int i =0;i<intcount;i++){
                    items.add((ItemStack) in.readObject());
                }
                in.close();
            }catch (IOException|ClassNotFoundException ex){
                System.out.println(ex);
            }
        }



    return items;}
}

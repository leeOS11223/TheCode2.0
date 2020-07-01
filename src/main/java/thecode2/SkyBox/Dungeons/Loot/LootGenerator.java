package thecode2.SkyBox.Dungeons.Loot;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class LootGenerator {

    public static ArrayList<LootTable> tables = new ArrayList<>();

    public static void setup(){
        LootTable one=new LootTable();
            one.AddLoot(Material.CARROT_ITEM,5,.4f);
        tables.add(one);
    }

    public static void Generate(){

    }

}

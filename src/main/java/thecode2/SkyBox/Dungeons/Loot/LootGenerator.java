package thecode2.SkyBox.Dungeons.Loot;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class LootGenerator {

    public static ArrayList<LootTable> tables = new ArrayList<>();

    public static void setup(){
        LootTable one=new LootTable(1);
            one.AddLoot(Material.COAL,5,.25f);
            one.AddLoot(Material.SAPLING,2,.1f);
            one.AddLoot(Material.WEB,10,.5f);
            one.AddLoot(Material.IRON_INGOT,3,.05f);
            one.AddLoot(Material.ROTTEN_FLESH,5,.25f);
            one.AddLoot(Material.BREAD,3,.1f);
            one.AddLoot(Material.CARROT_ITEM,1,.01f);
            one.AddLoot(Material.GRAVEL,10,.3f);
        tables.add(one);

        LootTable two=new LootTable(2);
            two.AddLoot(Material.COAL,20,.5f);
            two.AddLoot(Material.FEATHER,5,.25f);
            two.AddLoot(Material.SUGAR_CANE,10,.3f);
            two.AddLoot(Material.WATER_BUCKET,1,.2f);
            two.AddLoot(Material.CACTUS,5,.4f);
            two.AddLoot(Material.SAND,20,.5f);
            two.AddLoot(Material.IRON_INGOT,5,.1f);
        tables.add(two);

        LootTable three=new LootTable(3);
            three.AddLoot(Material.WATER_BUCKET,1,.3f);
            three.AddLoot(Material.SAND,10,.4f);
            three.AddLoot(Material.IRON_INGOT,10,.2f);
            three.AddLoot(Material.IRON_BLOCK,1,.1f);
            three.AddLoot(Material.CLAY,10,.4f);
            three.AddLoot(Material.SLIME_BALL,3,.2f);
            three.AddLoot(Material.EGG,10,.6f);
        tables.add(three);

        LootTable four=new LootTable(4);
            four.AddLoot(Material.CLAY,20,.6f);
            four.AddLoot(Material.CLAY_BRICK,5,.25f);
            four.AddLoot(Material.WATER_BUCKET,1,.4f);
            four.AddLoot(Material.SAND,10,.35f);
            four.AddLoot(Material.IRON_BLOCK,1,.2f);
            four.AddLoot(Material.IRON_INGOT,20,.5f);
            four.AddLoot(Material.QUARTZ,30,.5f);
            four.AddLoot(Material.ENDER_PEARL,1,.1f);
            four.AddLoot(Material.SLIME_BALL,5,.4f);
        tables.add(four);

        LootTable five=new LootTable(5);
            five.AddLoot(Material.GHAST_TEAR,3,.1f);
            five.AddLoot(Material.ENDER_PEARL,3,.25f);
            five.AddLoot(Material.BLAZE_ROD,3,.1f);
            five.AddLoot(Material.DIAMOND,5,.1f);
            five.AddLoot(Material.NETHER_STAR,1,.01f);
            five.AddLoot(Material.SHULKER_SHELL,1,.1f);
            five.AddLoot(Material.IRON_BLOCK,2,.2f);
            five.AddLoot(Material.OBSIDIAN,10,.4f);
            five.AddLoot(Material.EMERALD,5,.3f);
        tables.add(five);

        LootTable six=new LootTable(6);
            six.AddLoot(Material.NETHER_STAR,2,.05f);
            six.AddLoot(Material.DIAMOND_BLOCK,5,.1f);
            six.AddLoot(Material.EXP_BOTTLE,10,.25f);
            six.AddLoot(Material.BLAZE_ROD,5,.25f);
            six.AddLoot(Material.GOLDEN_APPLE,1,.25f);
            six.AddLoot(Material.ENDER_PEARL,5,.5f);
            six.AddLoot(Material.GHAST_TEAR,5,.25f);
            six.AddLoot(Material.SHULKER_SHELL,2,.3f);
            six.AddLoot(Material.OBSIDIAN,10,.6f);
            six.AddLoot(Material.BEDROCK,1,.001f);
        tables.add(six);
    }

    public static void Generate(){

    }

}

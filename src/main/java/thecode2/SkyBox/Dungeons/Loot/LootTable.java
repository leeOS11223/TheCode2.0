package thecode2.SkyBox.Dungeons.Loot;

import org.bukkit.Material;
import org.bukkit.entity.Item;

import java.util.ArrayList;

public class LootTable {

    public ArrayList<Loot> lootTable = new ArrayList<>();
    public int level=-1;

    public LootTable(int level){
        this.level=level;
    }

    public void AddLoot(Material item, int max, float chance){
        Loot l=new Loot(item,max,chance);
        lootTable.add(l);
    }

    public void AddLoot(String item, int max, float chance){
        Loot l=new Loot(item,max,chance);
        lootTable.add(l);
    }

}

package thecode2.SkyBox.Dungeons.Loot;

import org.bukkit.Material;
import org.bukkit.entity.Item;

public class Loot {

    public Material item;
    public int maxNumber=1;
    public float chance=.4f;

    public Loot(Material item,int max,float chance){
        this.item=item;
        this.maxNumber=max;
        this.chance=chance;
    }

}

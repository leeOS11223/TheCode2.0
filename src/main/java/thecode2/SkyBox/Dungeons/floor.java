package thecode2.SkyBox.Dungeons;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationLayer;

public abstract class floor {

    public int floorHeight;
    public Dungeon dungeon;
    public Material wallBlock;
    public int blockData;

    public floor(int floorHeight, Dungeon dungeon, Material wallBlock, int blockData){
        this.floorHeight=floorHeight;
        this.dungeon=dungeon;
        this.wallBlock=wallBlock;
        this.blockData=blockData;
    }

    public void generate(int y) {
        dungeon.addGenObject(new GenerationLayer(y,2,dungeon, wallBlock,blockData));
        dungeon.addGenObject(new GenerationLayer(y+floorHeight-1,2,dungeon, wallBlock,blockData));
    }

}

package thecode2.SkyBox.Dungeons;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

import java.util.ArrayList;

public abstract class Dungeon extends SkyBox {

    public ArrayList<floor> floors;
    public Material wallBlock;
    public int blockData;

    public Dungeon(int x, int y,Material wallBlock,int blockData) {
        super(x, y);
        this.floors=new ArrayList<>();
        this.wallBlock=wallBlock;
        this.blockData=blockData;
        wallMaterial=wallBlock;
        wallMaterialID= blockData;
    }

    @Override
    public void generate() {
        addGenObject(new GenerationWall(maxY,this, wallBlock,blockData));
        addGenObject(new GenerationLayer(0,this, wallBlock,blockData));
        addGenObject(new GenerationInnerWall(maxY,-2,this, wallBlock,blockData));
        addGenObject(new GenerationLayer(150,this, wallBlock,blockData));

        int floorGenerationHeight=1;
        for(floor f:floors){
            f.generate(floorGenerationHeight);
            floorGenerationHeight+=f.floorHeight-1;
        }

    }

    public void registerFloor(floor floor){
        floors.add(floor);
    }
}

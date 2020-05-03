package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationOre extends GenerationObject {
    private Material block;
    private int data=0;
    private int MinVeinSize=5;
    private int MaxVeinSize=12;
    private int MinVeins=10;
    private int MaxVeins=14;
    private SkyBox box=null;

    public GenerationOre(int x, int maxY, int z, Material block, int data) {
        super(x, maxY, z);
        this.block=block;
        this.data=data;
    }

    public GenerationOre(int x, int maxY, int z, Material block) {
        super(x, maxY, z);
        this.block=block;
    }

    public GenerationOre(int maxY, SkyBox box, Material block) {
        super(0, maxY, 0, box);
        this.block=block;
    }

    public GenerationOre(int maxY, SkyBox box, Material block,int MinVeinSize,int MaxVeinSize,int MinVeins,int MaxVeins) {
        super(0, maxY, 0, box);
        this.block=block;
        this.MinVeinSize=MinVeinSize;
        this.MaxVeinSize=MaxVeinSize;
        this.MinVeins=MinVeins;
        this.MaxVeins=MaxVeins;
        this.box=box;
    }

    public GenerationOre(int maxY, SkyBox box, Material block, int data) {
        super(0, maxY, 0, box);
        this.block=block;
        this.data=data;
        this.box=box;
    }

    public GenerationOre(int maxY, SkyBox box, Material block,int data,int MinVeinSize,int MaxVeinSize,int MinVeins,int MaxVeins) {
        super(0, maxY, 0, box);
        this.block=block;
        this.data=data;
        this.MinVeinSize=MinVeinSize;
        this.MaxVeinSize=MaxVeinSize;
        this.MinVeins=MinVeins;
        this.MaxVeins=MaxVeins;
        this.box=box;
    }

    @Override
    public void generate(GenerationTarget target){
        for(int i=0;i<=(int)(Math.random()*(MaxVeins-MinVeins)+MinVeins);i++) {
            Generator.Queue(new GenerationOreVein(x+(int)(Math.random()*(SkyBoxWorld.TileSize[0])), y-(int)(Math.random()*y), z+(int)(Math.random()*(SkyBoxWorld.TileSize[1])), (int) (Math.random() * (MaxVeinSize - MinVeinSize) + MinVeinSize), block, data,y,box));
        }
    }
}

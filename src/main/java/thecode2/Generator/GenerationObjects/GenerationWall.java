package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationWall extends GenerationObject {
    private Material block;
    private int data;

    private boolean south=true;
    private boolean north=true;
    private boolean east=true;
    private boolean west=true;

    private int height;

    public GenerationWall(int x, int y, int z, int height, Material block) {
        super(x, y, z);
        this.block=block;
        this.height=height;
        this.data=0;
    }

    public GenerationWall(int y, SkyBox box, Material block) {
        super(0, y, 0, box);
        this.block=block;
        this.height=box.maxY;
        this.data=0;
    }

    public GenerationWall(int y, SkyBox box, Material block, int blockData) {
        super(0, y, 0, box);
        this.block=block;
        this.data=blockData;
        this.height=box.maxY;
    }

    @Override
    public void generate(GenerationTarget target){
        if(west) Generator.FillBlocks(x-1, 0, z,x-1, height, z+ SkyBoxWorld.TileSize[1], block,data,target);
        if(north) Generator.FillBlocks(x, 0, z-1,x+ SkyBoxWorld.TileSize[0], height, z-1, block,data,target);
        if(east) Generator.FillBlocks(x+ SkyBoxWorld.TileSize[0]+1, 0, z,x+ SkyBoxWorld.TileSize[0]+1, height, z+ SkyBoxWorld.TileSize[1], block,data,target);
        if(south) Generator.FillBlocks(x, 0, z+ SkyBoxWorld.TileSize[1]+1,x+ SkyBoxWorld.TileSize[0], height, z+ SkyBoxWorld.TileSize[1]+1, block,data,target);
    }
}

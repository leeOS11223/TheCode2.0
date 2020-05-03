package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationInnerWall extends GenerationObject {
    private Material block;
    private int data;

    private boolean south=true;
    private boolean north=true;
    private boolean east=true;
    private boolean west=true;

    private int height;
    private int offset;

    public GenerationInnerWall(int x, int y, int z,int offset, int height, Material block) {
        super(x, y, z);
        this.block=block;
        this.height=height;
        this.data=0;
        this.offset=offset;
    }

    public GenerationInnerWall(int y,int offset, SkyBox box, Material block) {
        super(0, y, 0, box);
        this.block=block;
        this.height=box.maxY;
        this.data=0;
        this.offset=offset;
    }

    public GenerationInnerWall(int y,int offset, SkyBox box, Material block, int blockData) {
        super(0, y, 0, box);
        this.block=block;
        this.data=blockData;
        this.height=box.maxY;
        this.offset=offset;
    }

    @Override
    public void generate(GenerationTarget target){
        if(west) Generator.FillBlocks(x-1-offset, 0, z,x-1-offset, height, z+ SkyBoxWorld.TileSize[1], block,data,target);
        if(north) Generator.FillBlocks(x, 0, z-1-offset,x+ SkyBoxWorld.TileSize[0], height, z-1-offset, block,data,target);
        if(east) Generator.FillBlocks(x+ SkyBoxWorld.TileSize[0]+1+offset, 0, z,x+ SkyBoxWorld.TileSize[0]+1+offset, height, z+ SkyBoxWorld.TileSize[1], block,data,target);
        if(south) Generator.FillBlocks(x, 0, z+ SkyBoxWorld.TileSize[1]+1+offset,x+ SkyBoxWorld.TileSize[0], height, z+ SkyBoxWorld.TileSize[1]+1+offset, block,data,target);
    }
}

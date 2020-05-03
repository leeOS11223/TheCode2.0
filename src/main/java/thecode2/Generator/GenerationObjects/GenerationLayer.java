package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationLayer extends GenerationObject {
    private Material block;
    private int data=0;
    private int offset=0;

    public GenerationLayer(int x, int y, int z, Material block, int data) {
        super(x, y, z);
        this.block=block;
        this.data=data;
    }

    public GenerationLayer(int x, int y, int z, Material block) {
        super(x, y, z);
        this.block=block;
    }

    public GenerationLayer(int y, SkyBox box, Material block) {
        super(0, y, 0, box);
        this.block=block;
    }

    public GenerationLayer(int y, SkyBox box, Material block,int data) {
        super(0, y, 0, box);
        this.block=block;
        this.data=data;
    }

    public GenerationLayer(int y, int offset,SkyBox box, Material block,int data) {
        super(0, y, 0, box);
        this.block=block;
        this.data=data;
        this.offset=offset;
    }

    @Override
    public void generate(GenerationTarget target){
        Generator.FillBlocks(x+offset, y, z+offset,x+ SkyBoxWorld.TileSize[0]-offset, y, z+ SkyBoxWorld.TileSize[1]-offset, block,data,target);;
    }
}

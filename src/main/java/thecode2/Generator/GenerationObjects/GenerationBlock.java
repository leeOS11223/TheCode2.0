package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationBlock extends GenerationObject {
    private String blockName;
    private Material block;
    private int data;
    private SkyBox box=null;

    public GenerationBlock(int x, int y, int z,Material block) {
        super(x, y, z);
        this.block=block;
        this.data=0;
    }

    public GenerationBlock(int x, int y, int z, SkyBox box, Material block) {
        super(x, y, z, box);
        this.block=block;
        this.data=0;
        this.box=box;
    }

    public GenerationBlock(int x, int y, int z,Material block,int data) {
        super(x, y, z);
        this.block=block;
        this.data=data;
    }

    public GenerationBlock(int x, int y, int z, SkyBox box, Material block,int data) {
        super(x, y, z, box);
        this.block=block;
        this.data=data;
        this.box=box;
    }

    public GenerationBlock(int x, int y, int z, String blockName,int data) {
        super(x, y, z);
        this.blockName=blockName;
        this.data=data;
    }

    @Override
    public void generate(GenerationTarget target){
        //TODO skybox restriction mode, ON by default
        if(box!=null){
            int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(box);
            if(!(xz[0]>x||xz[0]+SkyBoxWorld.TileSize[0]<x
             ||xz[1]<z||xz[1]+SkyBoxWorld.TileSize[1]>z)){
                return;
            }
        }
        if(blockName!=null)
            Generator.SetBlockNOW(x,y,z,blockName,data);
         else
            Generator.SetBlockNOW(x,y,z,block,data);
    }
}

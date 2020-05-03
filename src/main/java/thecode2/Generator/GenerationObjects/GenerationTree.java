package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.SkyBox.SkyBox;

public class GenerationTree extends GenerationObject {
    private Material LogBlock;
    private Material LeafBlock;
    private int length;

    public GenerationTree(int x, int y, int z,int length, Material LogBlock,Material LeafBlock) {
        super(x, y, z);
        this.LogBlock=LogBlock;
        this.LeafBlock=LeafBlock;
        this.length=length-3;
    }

    public GenerationTree(int x, int y, int z, SkyBox box, int length, Material LogBlock, Material LeafBlock) {
        super(x, y, z, box);
        this.LogBlock=LogBlock;
        this.LeafBlock=LeafBlock;
        this.length=length-3;
    }

    @Override
    public void generate(GenerationTarget target){
        Generator.FillBlocks(x-2,y+length-1,z-2,x+2,y+length,z+2,LeafBlock,target);
        Generator.FillBlocks(x-1,y+length+1,z,x+1,y+length+2,z,LeafBlock,target);
        Generator.FillBlocks(x,y+length+1,z-1,x,y+length+2,z+1,LeafBlock,target);

        Generator.FillBlocks(x,y,z,x,y+length+1,z,LogBlock,target);

        if(Math.round(Math.random())==1) {
            Generator.SetBlock(x - 2, y + length - 1, z - 2, Material.AIR,target);
            Generator.SetBlock(x + 2, y + length, z - 2, Material.AIR,target);
            Generator.SetBlock(x + 2, y + length - 1, z + 2, Material.AIR,target);
            Generator.SetBlock(x - 2, y + length, z + 2, Material.AIR,target);
        }else{
            Generator.SetBlock(x - 2, y + length, z - 2, Material.AIR,target);
            Generator.SetBlock(x + 2, y + length- 1, z - 2, Material.AIR,target);
            Generator.SetBlock(x + 2, y + length, z + 2, Material.AIR,target);
            Generator.SetBlock(x - 2, y + length- 1, z + 2, Material.AIR,target);
        }
    }
}

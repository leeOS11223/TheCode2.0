package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

public class Earth extends SkyBox {

    public Earth(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("light-blue");
    }

    @Override
    public void generate(){
        //addGenObject(new GenerationBlock(0,50,0,this, Material.EMERALD_BLOCK));

        addGenObject(new GenerationLayer(50,this, Material.GRASS));
        addGenObject(new GenerationLayer(49,this, Material.DIRT));
        addGenObject(new GenerationLayer(48,this, Material.DIRT));
        addGenObject(new GenerationGround(47,this, Material.STONE));

        //trees
        for(int i=0;i<=(int)(Math.random()*15)+3;i++){
            Generator.Queue(new GenerationTree(2+(int)(Math.random()*(SkyBoxWorld.TileSize[0]-3)),  51,  2+(int)(Math.random()*(SkyBoxWorld.TileSize[1]-3)),  this,  (int)(Math.random()*4)+5,  Material.LOG, Material.LEAVES));
        }

        addGenObject(new GenerationOre(48,this, Material.COAL_ORE,5,12,20,22));
        addGenObject(new GenerationOre(40,this, Material.IRON_ORE,5,12,15,17));
        addGenObject(new GenerationOre(32,this, Material.GOLD_ORE,5,12,6,15));
        //addGenObject(new GenerationOre(16,this, Material.REDSTONE_ORE,5,12,6,20));
        //addGenObject(new GenerationOre(31,this, Material.LAPIS_ORE,3,12,6,20));
        //addGenObject(new GenerationOre(16,this, Material.DIAMOND_ORE,1,8,0,6));
        //addGenObject(new GenerationOre(32,this, Material.EMERALD_ORE,1,1,0,5));

        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));

        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredTransparentBlock, Blocks.getBlockData("light-blue")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("light-blue")));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Earth(x,y);
        b.maxY=maxY;
        b.minY=minY;
        b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

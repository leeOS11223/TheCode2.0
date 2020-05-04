package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

public class Wasteland extends SkyBox {

    public Wasteland(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("gray");
    }

    @Override
    public void generate(){

        addGenObject(new GenerationLayer(50,this, Material.DIRT));
        addGenObject(new GenerationLayer(49,this, Material.DIRT));
        addGenObject(new GenerationLayer(48,this, Material.DIRT));
        addGenObject(new GenerationGround(47,this, Material.STONE));

        //trees
        for(int i=0;i<=(int)(Math.random()*15)+3;i++){
            Generator.Queue(new GenerationTree(2+(int)(Math.random()*(SkyBoxWorld.TileSize[0]-3)),  51,  2+(int)(Math.random()*(SkyBoxWorld.TileSize[1]-3)),  this,  (int)(Math.random()*4)+5,  Material.LOG, Material.AIR));
        }

        addGenObject(new GenerationOre(48,this, Material.COAL_ORE,5,12,2,6));
        addGenObject(new GenerationOre(40,this, Material.GRAVEL,5,12,2,6));
        addGenObject(new GenerationOre(40,this, Material.IRON_ORE,5,12,2,6));

        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));

        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredBlock, Blocks.getBlockData("gray")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("gray")));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Wasteland(x,y);
        b.maxY=maxY;
        b.minY=minY;
       // b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

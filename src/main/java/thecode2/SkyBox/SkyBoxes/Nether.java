package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

public class Nether extends SkyBox {

    public Nether(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("red");
    }

    @Override
    public void generate(){

        addGenObject(new GenerationGround(150,this, Material.NETHERRACK));

        addGenObject(new GenerationOre(149,this, Material.QUARTZ_ORE,5,12,20,88));

        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));
        addGenObject(new GenerationDelay(new GenerationLayer(maxY,this, Material.BEDROCK)));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("red")));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Nether(x,y);
        b.maxY=maxY;
        b.minY=minY;
        b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

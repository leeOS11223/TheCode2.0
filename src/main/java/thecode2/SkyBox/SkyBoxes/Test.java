package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationDoor;
import thecode2.Generator.GenerationObjects.GenerationLayer;
import thecode2.Generator.GenerationObjects.GenerationWall;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

public class Test extends SkyBox {

    public Test(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("yellow");
    }

    @Override
    public void generate(){
        addGenObject(new GenerationLayer(50,this, Material.SANDSTONE));
       // addGenObject(new GenerationWall(150,this, Material.SANDSTONE));
       // addGenObject(new GenerationDoor(0,60,0,this,6,4,Material.GOLD_BLOCK,Material.AIR));


        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredTransparentBlock, Blocks.getBlockData("yellow")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("yellow")));
    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Test(x,y);
        b.maxY=maxY;
        b.minY=minY;
       //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

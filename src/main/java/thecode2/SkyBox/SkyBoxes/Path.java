package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationLayer;
import thecode2.Generator.GenerationObjects.GenerationWall;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;

public class Path extends SkyBox {

    public Path(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("white");
    }

    @Override
    public void generate(){
        //addGenObject(new GenerationLayer(50,this, Material.SANDSTONE));
       // addGenObject(new GenerationWall(150,this, Material.SANDSTONE));
       // addGenObject(new GenerationDoor(0,60,0,this,6,4,Material.GOLD_BLOCK,Material.AIR));


        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredTransparentBlock, Blocks.getBlockData("white")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("white")));
    }

    @Override
    public SkyBox clone(int x,int y){
       /* SkyBox b= new Path(x,y);
        b.maxY=maxY;
        b.minY=minY;
       //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;*/
        return null;//b;
    }
}

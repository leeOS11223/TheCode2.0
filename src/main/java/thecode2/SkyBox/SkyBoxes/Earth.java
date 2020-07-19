package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;
import thecode2.main;

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

        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("osmium"),5,12,-30,56));
        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("copper"),Blocks.getMeta("copper"),5,12,-30,56));
        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("tin"),Blocks.getMeta("tin"),5,12,-30,56));

        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",0,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",1,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",2,2,7,-30,56));

        int level=main.RandomNumber(27,40);

        for(int i=0;main.RandomNumber(-3,4)>i;i++)
            Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8,SkyBoxWorld.TileSize[0]-8),level,main.RandomNumber(8,SkyBoxWorld.TileSize[1]-8),this,7,Material.WATER,Material.AIR)));

        level=main.RandomNumber(8,20);

        for(int i=0;main.RandomNumber(-3,4)>i;i++)
            Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8,SkyBoxWorld.TileSize[0]-8),level,main.RandomNumber(8,SkyBoxWorld.TileSize[1]-8),this,7,Material.LAVA,Material.AIR)));


        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));

        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredTransparentBlock, Blocks.getBlockData("light-blue")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("light-blue")));
        addGenObject(new GenerationBiome(this, Biome.PLAINS));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Earth(x,y);
        b.maxY=maxY;
        b.minY=minY;
        //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

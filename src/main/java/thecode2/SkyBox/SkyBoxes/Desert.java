package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;
import thecode2.main;

public class Desert extends SkyBox {

    public Desert(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("yellow");
    }

    @Override
    public void generate(){
        addGenObject(new GenerationLayer(50,this, Material.SAND));
        addGenObject(new GenerationLayer(49,this, Material.SAND));
        addGenObject(new GenerationLayer(48,this, Material.SAND));
        addGenObject(new GenerationLayer(47,this, Material.SANDSTONE));
        addGenObject(new GenerationLayer(46,this, Material.SANDSTONE));
        addGenObject(new GenerationGround(45,this, Material.STONE));

        //trees
        for(int i=0;i<=(int)(Math.random()*15)+3;i++){
            Generator.Queue(new GenerationTree(2+(int)(Math.random()*(SkyBoxWorld.TileSize[0]-3)),  51,  2+(int)(Math.random()*(SkyBoxWorld.TileSize[1]-3)),  this,  main.RandomNumber(4,5),  Material.CACTUS, Material.AIR));
        }

        addGenObject(new GenerationOre(48,this, Material.COAL_ORE,5,12,-5,63));
        addGenObject(new GenerationOre(40,this, Material.IRON_ORE,5,12,-5,56));
        addGenObject(new GenerationOre(32,this, Material.GOLD_ORE,5,12,-5,53));
        addGenObject(new GenerationOre(30,this, Material.REDSTONE_ORE,5,20,-5,80));
        addGenObject(new GenerationOre(48,this, Material.LAPIS_ORE,5,20,-5,80));
        addGenObject(new GenerationOre(48,this, Material.DIAMOND_ORE,1,5,-4,3));

        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("osmium"),5,12,-30,56));
        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("copper"),Blocks.getMeta("copper"),5,12,-30,56));
        addGenObject(new GenerationOre(48,this, Blocks.getMaterial("tin"),Blocks.getMeta("tin"),5,12,-30,56));

        addGenObject(new GenerationOre(48,this, "thaumcraft:ore_amber",1,2,-30,56));
        addGenObject(new GenerationOre(48,this, "thaumcraft:ore_cinnabar",1,5,-30,56));
        addGenObject(new GenerationOre(48,this, "thaumcraft:ore_quartz",1,5,-30,56));

        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",0,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",1,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",2,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",3,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",4,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",5,2,7,-30,56));
        addGenObject(new GenerationOre(48,this, "projectred-exploration:ore",6,2,7,-30,56));

        int level= main.RandomNumber(27,40);

        for(int i=0;main.RandomNumber(-3,4)>i;i++)
            Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8,SkyBoxWorld.TileSize[0]-8),level,main.RandomNumber(8,SkyBoxWorld.TileSize[1]-8),this,7,Material.WATER,Material.AIR)));

        level=main.RandomNumber(8,20);

        for(int i=0;main.RandomNumber(-3,4)>i;i++)
            Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8,SkyBoxWorld.TileSize[0]-8),level,main.RandomNumber(8,SkyBoxWorld.TileSize[1]-8),this,7,Material.LAVA,Material.AIR)));


        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));
        addGenObject(new GenerationLayer(maxY,this, Blocks.ColoredTransparentBlock, Blocks.getBlockData("yellow")));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("yellow")));
        addGenObject(new GenerationBiome(this, Biome.DESERT));
    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Desert(x,y);
        b.maxY=maxY;
        b.minY=minY;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

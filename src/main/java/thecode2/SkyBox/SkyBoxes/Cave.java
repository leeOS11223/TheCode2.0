package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;
import thecode2.main;

public class Cave extends SkyBox {

    public Cave(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("red");
    }

    @Override
    public void generate(){

        addGenObject(new GenerationGround(150,this, Material.STONE));

        addGenObject(new GenerationOre(150,this, Material.COAL_ORE,5,12,40,63));
        addGenObject(new GenerationOre(150,this, Material.IRON_ORE,5,12,30,56));
        addGenObject(new GenerationOre(150,this, Material.GOLD_ORE,5,12,12,53));
        addGenObject(new GenerationOre(150,this, Material.REDSTONE_ORE,5,20,30,80));
        addGenObject(new GenerationOre(150,this, Material.LAPIS_ORE,5,20,30,80));

        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_aer",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_ignis",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_aqua",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_terra",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_ordo",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_perditio",5,20,30,80));
        addGenObject(new GenerationOre(150,this, "thaumcraft:crystal_vitium",5,20,30,80));

        addGenObject(new GenerationOre(150,this, Blocks.getMaterial("osmium"),5,12,30,56));
        addGenObject(new GenerationOre(150,this, Blocks.getMaterial("copper"),Blocks.getMeta("copper"),5,12,30,56));
        addGenObject(new GenerationOre(150,this, Blocks.getMaterial("tin"),Blocks.getMeta("tin"),5,12,30,56));

        addGenObject(new GenerationOre(150,this, "thaumcraft:ore_amber",1,2,30,56));
        addGenObject(new GenerationOre(150,this, "thaumcraft:ore_cinnabar",1,5,30,56));
        addGenObject(new GenerationOre(150,this, "thaumcraft:ore_quartz",1,5,30,56));

        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",0,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",1,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",2,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",3,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",4,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",5,2,7,-3,56));
        addGenObject(new GenerationOre(150,this, "projectred-exploration:ore",6,2,7,-3,56));

        for(int i1=0;main.RandomNumber(30,60)>i1;i1++) {
            int level = main.RandomNumber(8, 140);

            for (int i = 0; main.RandomNumber(1, 8) > i; i++)
                Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8, SkyBoxWorld.TileSize[0]-8), level, main.RandomNumber(8, SkyBoxWorld.TileSize[1]-8), this, 7, Material.AIR, Material.AIR)));
        }


        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));
        addGenObject(new GenerationDelay(new GenerationLayer(maxY,this, Material.BEDROCK)));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("black")));
        addGenObject(new GenerationBiome(this, Biome.PLAINS));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Cave(x,y);
        b.maxY=maxY;
        b.minY=minY;
        //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

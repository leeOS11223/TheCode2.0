package thecode2.SkyBox.SkyBoxes;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import thecode2.Generator.GenerationObjects.*;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.SkyBox.SkyBox;
import thecode2.main;

public class Nether extends SkyBox {

    public Nether(int x, int y) {
        super(x, y);
        wallMaterialID=Blocks.getBlockData("red");
    }

    @Override
    public void generate(){

        addGenObject(new GenerationGround(150,this, Material.NETHERRACK));

        addGenObject(new GenerationOre(149,this, Material.QUARTZ_ORE,5,12,20,88));
        addGenObject(new GenerationOre(149,this, Material.GLOWSTONE,5,12,20,88));
        addGenObject(new GenerationOre(149,this, Material.SOUL_SAND,20,50,40,120));

        for(int i1 = 0; main.RandomNumber(30,60)>i1; i1++) {
            int level = main.RandomNumber(8, 140);

            for (int i = 0; main.RandomNumber(1, 8) > i; i++)
                Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8, SkyBoxWorld.TileSize[0]-8), level, main.RandomNumber(8, SkyBoxWorld.TileSize[1]-8), this, 7, Material.AIR, Material.AIR)));
        }
        for(int i1=0;main.RandomNumber(6,20)>i1;i1++) {
            int level = main.RandomNumber(8, 140);

            for (int i = 0; main.RandomNumber(1, 8) > i; i++)
                Generator.Queue(new GenerationDelay(new GenerationPool(main.RandomNumber(8, SkyBoxWorld.TileSize[0]-8), level, main.RandomNumber(8, SkyBoxWorld.TileSize[1]-8), this, 7, Material.LAVA, Material.AIR)));
        }

        addGenObject(new GenerationDelay(new GenerationLayer(0,this, Material.BEDROCK)));
        addGenObject(new GenerationDelay(new GenerationLayer(maxY,this, Material.BEDROCK)));
        addGenObject(new GenerationWall(maxY,this, Blocks.ColoredBlock,Blocks.getBlockData("red")));
        addGenObject(new GenerationBiome(this, Biome.HELL));

    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new Nether(x,y);
        b.maxY=maxY;
        b.minY=minY;
        //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        b.wallMaterialID=wallMaterialID;
        return b;
    }
}

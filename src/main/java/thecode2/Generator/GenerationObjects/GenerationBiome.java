package thecode2.Generator.GenerationObjects;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.meta.BookMeta;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationBiome extends GenerationObject {

    Biome b;

    public GenerationBiome(SkyBox box, Biome biome) {
        super(0,0,0, box);
        this.b=biome;
    }

    @Override
    public void generate(GenerationTarget target){
        World world = Generator.world;
        for(int x = 0; x <= SkyBoxWorld.TileSize[0]; x++) {
            for(int z = 0; z <= SkyBoxWorld.TileSize[1]; z++) {
                world.setBiome(this.x + x, this.z + z, b);
            }
        }
    }
}

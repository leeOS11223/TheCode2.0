package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationGround extends GenerationObject {
    private Material block;

    public GenerationGround(int x, int y, int z, Material block) {
        super(x, y, z);
        this.block=block;
    }

    public GenerationGround(int y, SkyBox box, Material block) {
        super(0, y, 0, box);
        this.block=block;
    }

    public void generate(GenerationTarget target){
        for(int i=0;i<=y;i++) {
            Generator.FillBlocks(x, y-i, z, x + SkyBoxWorld.TileSize[0], y-i, z + SkyBoxWorld.TileSize[1], block,target);

        }
    }
}

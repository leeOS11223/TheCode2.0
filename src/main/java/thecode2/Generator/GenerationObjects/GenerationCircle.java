package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.SkyBox.SkyBox;


public class GenerationCircle extends GenerationObject {
    private Material Block;
    private int radius;

    public GenerationCircle(int x, int y, int z, SkyBox box, int radius, Material Block) {
        super(x, y, z, box);
        this.Block=Block;
        this.radius=radius;
    }

    public GenerationCircle(int x, int y, int z, int radius, Material Block) {
        super(x, y, z);
        this.Block=Block;
        this.radius=radius;
    }

    @Override
    public void generate(GenerationTarget target){
        for(int y2=-radius; y2<=radius; y2++)
            for(int x2=-radius; x2<=radius; x2++)
                if(x2*x2+y2*y2 <= radius*radius)
                    Generator.Queue(new GenerationBlock(x+x2,y,z+y2,Block));
    }
}

package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.SkyBox.SkyBox;


//todo write this
public class GenerationPool extends GenerationObject {
    private Material WaterBlock;
    private Material AirBlock;
    private int size;

    public GenerationPool(int x, int y, int z, SkyBox box, int size, Material WaterBlock, Material AirBlock) {
        super(x, y, z, box);
        this.WaterBlock=WaterBlock;
        this.AirBlock=AirBlock;
        this.size=size;
    }

    public GenerationPool(int x, int y, int z, int size, Material WaterBlock, Material AirBlock) {
        super(x, y, z);
        this.WaterBlock=WaterBlock;
        this.AirBlock=AirBlock;
        this.size=size;
    }

    @Override
    public void generate(GenerationTarget target){
        for(int i=0;i<size;i++) {
            if(i<=size/2)
                Generator.Queue(new GenerationCircle(x, y+i-size/2, z, i, WaterBlock));
            else
                Generator.Queue(new GenerationCircle(x, y+i-size/2, z, size-i, AirBlock));
        }
    }
}

package thecode2.Generator.GenerationObjects;

import org.bukkit.Location;
import org.bukkit.Particle;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.SkyBox.SkyBox;

public class GenerationParticle extends GenerationObject {

    Particle effect;

    float xoff=0,yoff=0,zoff=0;

    public GenerationParticle(int x, int y, int z, Particle effect) {
        super(x, y, z);
        this.effect=effect;
    }

    public GenerationParticle(int x, int y, int z, SkyBox box, Particle effect) {
        super(x, y, z, box);
        this.effect=effect;
    }

    public GenerationParticle(int x, int y, int z, float xoff, float yoff, float zoff, Particle effect) {
        super(x, y, z);
        this.effect=effect;
        this.xoff=xoff;
        this.yoff=yoff;
        this.zoff=zoff;
    }

    @Override
    public void generate(GenerationTarget target){
        //Generator.world.playEffect(new Location(Generator.world,x,y,z), effect,0);
        Generator.world.spawnParticle(effect, new Location(Generator.world,x+xoff,y+yoff,z+zoff),3);
    }
}

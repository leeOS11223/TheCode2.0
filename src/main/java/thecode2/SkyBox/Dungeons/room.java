package thecode2.SkyBox.Dungeons;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationLayer;

public abstract class room {

    public Dungeon dungeon;

    public room(Dungeon dungeon){
        this.dungeon=dungeon;
    }

    public void generate(int x,int y,int z) {
    }

}

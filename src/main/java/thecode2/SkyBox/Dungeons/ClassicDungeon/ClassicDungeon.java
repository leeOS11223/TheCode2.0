package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Material;
import thecode2.SkyBox.Dungeons.Dungeon;
import thecode2.SkyBox.SkyBox;

public class ClassicDungeon extends Dungeon {

    public ClassicDungeon(int x, int y) {
        super(x, y,Material.SMOOTH_BRICK,0);
        registerProtectedMaterial(Material.SMOOTH_BRICK);
        registerFloor(new firstFloor(this));
        for(int i=1;i<36;i++) {
            registerFloor(new normalFloor(this, i,36));
        }
    }

    @Override
    public SkyBox clone(int x,int y){
        SkyBox b= new ClassicDungeon(x,y);
        b.maxY=maxY;
        b.minY=minY;
        //b.GenerationObjects=GenerationObjects;
        b.portalsConnections=portalsConnections;
        return b;
    }
}

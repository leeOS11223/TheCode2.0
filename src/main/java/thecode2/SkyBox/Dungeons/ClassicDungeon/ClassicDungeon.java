package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Material;
import thecode2.SkyBox.Dungeons.Dungeon;
import thecode2.SkyBox.SkyBox;

public class ClassicDungeon extends Dungeon {

    public ClassicDungeon(int x, int y) {
        super(x, y,Material.SMOOTH_BRICK,0);
        registerProtectedMaterial(Material.SMOOTH_BRICK);
        registerFloor(new firstFloor(this));
        registerFloor(new normalFloor(this,1));
        registerFloor(new normalFloor(this,2));
        registerFloor(new normalFloor(this,3));
        registerFloor(new normalFloor(this,4));
        registerFloor(new normalFloor(this,5));
        registerFloor(new normalFloor(this,6));
        registerFloor(new normalFloor(this,7));
        registerFloor(new normalFloor(this,8));
        registerFloor(new normalFloor(this,9));
        registerFloor(new normalFloor(this,10));
        registerFloor(new normalFloor(this,11));
        registerFloor(new normalFloor(this,12));
        registerFloor(new normalFloor(this,13));
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

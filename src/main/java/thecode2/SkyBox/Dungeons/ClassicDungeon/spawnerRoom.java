package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationDelay;
import thecode2.Generator.GenerationObjects.GenerationSpawner;
import thecode2.SkyBox.Dungeons.Dungeon;
import thecode2.SkyBox.Dungeons.Monster;
import thecode2.SkyBox.Dungeons.room;

public class spawnerRoom extends room {

    public Monster monster=null;

    public spawnerRoom(Dungeon dungeon,Monster monster) {
        super(dungeon);
        this.monster=monster;
    }

    @Override
    public void generate(int x,int y,int z) {
        //dungeon.addGenObject(new GenerationDelay(new GenerationSpawner(x+3,y,z+3, dungeon,"Skeleton - Level 1","skeleton")));
        //GenerationSpawner s=new GenerationSpawner(x+3,y,z+3, dungeon,"Skeleton - Level 23","skeleton");
        //s.setEquipment("diamond_axe","diamond_sword","diamond_boots","diamond_leggings","diamond_chestplate","diamond_helmet");

        GenerationSpawner s=new GenerationSpawner(x+3,y,z+3, dungeon,monster);
        dungeon.addGenObject(new GenerationDelay(s));


    }
}

package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationDelay;
import thecode2.Generator.GenerationObjects.GenerationSpawner;
import thecode2.Generator.Generator;
import thecode2.SkyBox.Dungeons.Dungeon;
import thecode2.SkyBox.Dungeons.Loot.LootGenerator;
import thecode2.SkyBox.Dungeons.Monster;
import thecode2.SkyBox.Dungeons.room;

public class spawnerRoom extends room {

    public Monster monster=null;
    public int lootLevel=1;

    public spawnerRoom(Dungeon dungeon,Monster monster,int lootLevel) {
        super(dungeon);
        this.monster=monster;
        this.lootLevel=lootLevel;
    }

    @Override
    public void generate(int x,int y,int z) {
        //dungeon.addGenObject(new GenerationDelay(new GenerationSpawner(x+3,y,z+3, dungeon,"Skeleton - Level 1","skeleton")));
        //GenerationSpawner s=new GenerationSpawner(x+3,y,z+3, dungeon,"Skeleton - Level 23","skeleton");
        //s.setEquipment("diamond_axe","diamond_sword","diamond_boots","diamond_leggings","diamond_chestplate","diamond_helmet");

        GenerationSpawner s=new GenerationSpawner(x+3,y,z+3, dungeon,monster);
        dungeon.addGenObject(new GenerationDelay(s));

        for(int i=0;i<(int)(Math.random()*2.2);i++) {
            int xx = 0;
            int zz = 0;

            if (Math.random() > .5) {
                xx = 6;
            }
            if (Math.random() > .5) {
                zz = 6;
            }

            if (Math.random() > .3) {
                int xz[] = dungeon.getWorldCoordsFromSkyBox();
                LootGenerator.Generate(new Location(Generator.world, x + xz[0] + xx, y , z + xz[1] + zz), lootLevel);
            }
        }
    }
}

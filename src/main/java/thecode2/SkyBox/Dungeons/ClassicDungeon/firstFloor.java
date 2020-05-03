package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationDelay;
import thecode2.Generator.GenerationObjects.GenerationLayer;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.Dungeons.Dungeon;
import thecode2.SkyBox.Dungeons.floor;

public class firstFloor extends floor {

    public firstFloor(Dungeon dungeon) {
        super(5,dungeon,Material.SMOOTH_BRICK,0);
    }

    @Override
    public void generate(int y) {

        dungeon.addGenObject(new GenerationLayer(y,dungeon, Material.WATER,0));
        super.generate(y);

        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y+1,1,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y+2,1,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y,1,dungeon,Material.SMOOTH_BRICK,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y,0,dungeon,Material.SMOOTH_BRICK,0)));

        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y+1, SkyBoxWorld.TileSize[1]-1,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y+2,SkyBoxWorld.TileSize[1]-1,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y,SkyBoxWorld.TileSize[1]-1,dungeon,Material.SMOOTH_BRICK,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9,y,SkyBoxWorld.TileSize[1],dungeon,Material.SMOOTH_BRICK,0)));

        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(1,y+1,9,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(1,y+2,9,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(1,y,9,dungeon,Material.SMOOTH_BRICK,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(0,y,9,dungeon,Material.SMOOTH_BRICK,0)));

        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(SkyBoxWorld.TileSize[1]-1,y+1, 9,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(SkyBoxWorld.TileSize[1]-1,y+2,9,dungeon,Material.AIR,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(SkyBoxWorld.TileSize[1]-1,y,9,dungeon,Material.SMOOTH_BRICK,0)));
        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(SkyBoxWorld.TileSize[1],y,9,dungeon,Material.SMOOTH_BRICK,0)));
    }
}

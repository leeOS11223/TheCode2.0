package thecode2.SkyBox.Dungeons.ClassicDungeon;

import org.bukkit.Material;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationDelay;
import thecode2.Generator.GenerationObjects.GenerationLayer;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.Dungeons.*;

import java.util.ArrayList;

public class normalFloor extends floor {

    public ArrayList<room> rooms = new ArrayList<>();

    public normalFloor(Dungeon dungeon,int level) {
        super(5,dungeon,Material.SMOOTH_BRICK,0);

        rooms.add(new spawnerRoom(dungeon, DungeonHandler.GetFairMonster(level,3,"zombie")));
        rooms.add(new spawnerRoom(dungeon, DungeonHandler.GetFairMonster(level,3,"zombie")));
        rooms.add(new spawnerRoom(dungeon, DungeonHandler.GetFairMonster(level,3,"zombie")));
        rooms.add(new spawnerRoom(dungeon, DungeonHandler.GetFairMonster(level,3,"zombie")));
    }

    @Override
    public void generate(int y) {
        super.generate(y);

        int xo=((int)(Math.round(Math.random()))*8);
        int yo=((int)(Math.round(Math.random()))*8);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                dungeon.addGenObject(new GenerationDelay(new GenerationBlock(4 + i+xo, y +floorHeight-1, 12+j-yo, dungeon, Material.COBBLESTONE, 0)));
            }
        }

        for(int i=0;i<17;i++) {
            for(int iy=1;iy<=3;iy++) {
                if(i!=4&&i!=12){
                    dungeon.addGenObject(new GenerationDelay(new GenerationBlock(1 + i, y+iy, 9, dungeon, Material.SMOOTH_BRICK, 0)));
                    dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9, y+iy, 1 + i, dungeon, Material.SMOOTH_BRICK, 0)));
                }else{
                    if(iy==3){
                        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(1 + i, y+iy, 9, dungeon, Material.SMOOTH_BRICK, 0)));
                        dungeon.addGenObject(new GenerationDelay(new GenerationBlock(9, y+iy, 1 + i, dungeon, Material.SMOOTH_BRICK, 0)));
                    }
                }
            }
        }

        rooms.get(0).generate(2,y+1,2);
        rooms.get(1).generate(10,y+1,2);
        rooms.get(2).generate(10,y+1,10);
        rooms.get(3).generate(2,y+1,10);

    }
}

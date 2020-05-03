package thecode2.Generator.GenerationObjects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.Dungeons.Monster;
import thecode2.SkyBox.SkyBox;

public class GenerationSpawner extends GenerationObject {
    private SkyBox box=null;
    private String mob="";
    private int delay=299;
    public boolean canPickupItems=false;
    public String name;

    public String boots=null;
    public String legs=null;
    public String chestP=null;
    public String helmet=null;
    public String lefthand=null;
    public String righthand=null;

    public String effects=null;

    public GenerationSpawner(int x, int y, int z, SkyBox box, Monster m) {
        super(x, y, z, box);
        this.box=box;
        this.name=m.name;
        this.mob=m.mob;
        this.helmet=m.helmet;
        this.lefthand=m.lefthand;
        this.righthand=m.righthand;
        this.boots=m.boots;
        this.legs=m.legs;
        this.chestP=m.chestP;
        this.canPickupItems=m.canPickupItems;

        if(m.effects.size()>0){
            effects=m.getEffectsasJson();
        }
    }

    public GenerationSpawner(int x, int y, int z, SkyBox box, String name, String mob) {
        super(x, y, z, box);
        this.box=box;
        this.mob=mob;
        this.name=name;
    }

    public void setEquipment(String lefthand,String righthand,String boots,String legs,String chest,String helmet){
        this.helmet=helmet;
        this.lefthand=lefthand;
        this.righthand=righthand;
        this.boots=boots;
        this.legs=legs;
        this.chestP=chest;
    }

    @Override
    public void generate(GenerationTarget target){
        //TODO skybox restriction mode, ON by default
        if(box!=null){
            int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(box);
            if(!(xz[0]>x||xz[0]+SkyBoxWorld.TileSize[0]<x
             ||xz[1]<z||xz[1]+SkyBoxWorld.TileSize[1]>z)){
                return;
            }
        }
        /*Generator.SetBlockNOW(x,y,z,Material.MOB_SPAWNER,0);

        Block block2 = Generator.world.getBlockAt(x,y,z);

        BlockState blockState = block2.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.BLAZE);
        blockState.update();*/

        String command="setblock "+x+" "+y+" "+z+" mob_spawner 0 replace {SpawnData:{id:"+mob+"";

        if(canPickupItems)
            command += ",CanPickUpLoot:1b";

        command+=",HandItems:[{";

        if(righthand!=null)
            command+="Count:1,id:"+righthand+"},";
        else
            command+="},";

        if(lefthand!=null)
            command+="{Count:1,id:"+lefthand+"}],ArmorItems:[";
        else
            command+="{}],ArmorItems:[";

        if(boots!=null)
            command+="{Count:1,id:"+boots+"},";
        else
            command+="{},";

        if(legs!=null)
            command+="{Count:1,id:"+legs+"},";
        else
            command+="{},";

        if(chestP!=null)
            command+="{Count:1,id:"+chestP+"},";
        else
            command+="{},";

        if(helmet!=null)
            command+="{Count:1,id:"+helmet+"}";
        else
            command+="{}";

        if(effects!=null)
            command+="],CustomName:\""+name+"\",ActiveEffects:["+effects+"]},Delay:"+String.valueOf(delay)+"}";
        else
            command+="],CustomName:\""+name+"\"},Delay:"+String.valueOf(delay)+"}";


        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                 command);



    }
}

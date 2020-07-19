package thecode2.Generator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationCache;
import thecode2.Generator.GenerationObjects.GenerationParticle;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

import java.util.ArrayList;

public class Generator implements GenerationTarget{

    private static ArrayList<GenerationObject> que = new ArrayList<GenerationObject>();
    public static World world;

    public static void HandleQueue(){
        for(int i=0;i<100;i++) {
            if (!que.isEmpty()) {
                GenerationObject o = que.get(0);
                Generate(o);
                que.remove(o);
            }else{
                return;
            }
        }
    }

    public static void Generate(GenerationObject o){
        o.generate(null);
    }

    public static boolean isQueueEmpty() {
        return que.size()==0;
    }

    public void queue(GenerationObject o){
        Generator.Queue(o);
    }

    public static void Queue(GenerationObject o){
        que.add(o);
    }

    public static boolean generateSkyBox(SkyBox box){
        //Generator.deleteSkyBox(box);//clean up
        box.generate();

        GenerationCache c = new GenerationCache(SkyBoxWorld.TileSize[0],SkyBoxWorld.height,SkyBoxWorld.TileSize[1]);
        for(int i=0;i<box.GenerationObjects.size();i++){
            //box.GenerationObjects.get(i).generate(c);
            c.queue(box.GenerationObjects.get(i));
        }
        c.generate();

        return true;//successful
    }

    public static void deleteSkyBox(SkyBox box){
        GenerationCache target = new GenerationCache(SkyBoxWorld.TileSize[0],SkyBoxWorld.height,SkyBoxWorld.TileSize[1]);

        int[] xz = box.getWorldCoordsFromSkyBox();
        FillBlocks(xz[0], 0, xz[1],xz[0]+ SkyBoxWorld.TileSize[0], box.maxY, xz[1]+ SkyBoxWorld.TileSize[1], Material.AIR,target);
        Queue(target);
    }

    public static void deleteSkyBoxNow(SkyBox box) {
        GenerationCache target = new GenerationCache(SkyBoxWorld.TileSize[0],SkyBoxWorld.height,SkyBoxWorld.TileSize[1]);

        int[] xz = box.getWorldCoordsFromSkyBox();
        FillBlocks(xz[0], 0, xz[1],xz[0]+ SkyBoxWorld.TileSize[0], box.maxY, xz[1]+ SkyBoxWorld.TileSize[1], Material.AIR,target);
        target.generate();
    }

    public void GenerateLayer(SkyBox box, int y, Material block,GenerationTarget target){
        int[] xz = box.getWorldCoordsFromSkyBox();
        FillBlocks(xz[0], y, xz[1],xz[0]+ SkyBoxWorld.TileSize[0], y, xz[1]+ SkyBoxWorld.TileSize[1], block,target);
    }

    public static void SetBlockNOW(int x, int y, int z, Material block) {
        SetBlockNOW(x,y,z,block,0);
    }

    public static void SetBlockNOW(int x, int y, int z, String block,int data) {
        String command="setblock "+x+" "+y+" "+z+" "+block+" "+data+" replace";
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                command);
    }

    public static void SetBlockNOW(int x, int y, int z, Material block, int data) {
        Block currentBlock = world.getBlockAt(x,y,z);
       // currentBlock.setType(block);
      //  currentBlock.setData((byte) data);

        BlockState bState = currentBlock.getState();
       // MaterialData d=bState.getData();
        bState.setType(block);
        //d.setData((byte) data);
        //bState.setData(d);
        bState.setRawData((byte) data);
        bState.update(true, true);
    }

    public static void SetBlock(int x, int y, int z, Material block,GenerationTarget target) {
        if(target==null) {
            Queue(new GenerationBlock(x, y, z, block));
        }else{
            target.queue(new GenerationBlock(x, y, z, block));
        }
    }
    public static void SetBlock(int x, int y, int z, String block,int data,GenerationTarget target) {
        if(target==null) {
            Queue(new GenerationBlock(x, y, z, block, data));
        }else{
            target.queue(new GenerationBlock(x, y, z, block, data));
        }
    }

    public static void SetBlock(int x, int y, int z, Material block,int data,GenerationTarget target) {
        if(target==null) {
            Queue(new GenerationBlock(x, y, z, block, data));
        }else{
            target.queue(new GenerationBlock(x, y, z, block, data));
        }
    }

    public static void FillBlocks(int x1, int y1, int z1,int x2, int y2, int z2, Material block,GenerationTarget target) {
        FillBlocks(x1,y1,z1,x2,y2,z2,block,0,target);
    }

    public static void FillBlocks(int x1, int y1, int z1,int x2, int y2, int z2, Material block,int data,GenerationTarget target) {
        FillBlocks(x1,y1,z1,x2,y2,z2,block,data,false,target);
    }

    public static void FillBlocks(int x1, int y1, int z1,int x2, int y2, int z2, Material block,int data,boolean now,GenerationTarget target) {
        for(int x=x1;x<=x2;x++){
            for(int y=y1;y<=y2;y++){
                for(int z=z1;z<=z2;z++){
                    if(now) {
                        Generator.SetBlockNOW(x,y,z,block,data);
                    }else{
                        if(target==null) {
                            Queue(new GenerationBlock(x, y, z, block, data));
                        }else{

                            target.queue(new GenerationBlock(x, y, z, block, data));
                        }
                    }
                }
            }
        }
    }

    public static void FillEffect(int x1, int y1, int z1, int x2, int y2, int z2, Particle effect) {
        for(int x=x1;x<=x2;x++){
            for(int y=y1;y<=y2;y++){
                for(int z=z1;z<=z2;z++){
                    Queue(new GenerationParticle(x,y,z,effect));
                }
            }
        }
    }

    public static void FillEffect(int x1, int y1, int z1, int x2, int y2, int z2, float xoff, float yoff, float zoff, Particle effect) {
        for(int x=x1;x<=x2;x++){
            for(int y=y1;y<=y2;y++){
                for(int z=z1;z<=z2;z++){
                    Queue(new GenerationParticle(x,y,z,xoff, yoff, zoff,effect));
                }
            }
        }
    }
}

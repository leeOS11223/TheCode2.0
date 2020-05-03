package thecode2.Generator.GenerationObjects;

import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GenerationCache extends GenerationObject implements GenerationTarget {

    public GenerationBlock[][][] blocks;
    public ArrayList<GenerationObject> queue = new ArrayList<>();

    public GenerationCache(int x, int y, int z,int length,int height,int width) {
        super(x, y, z);
        setup(length, height, width);
    }

    public GenerationCache(int x, int y, int z, SkyBox box,int length,int height,int width) {
        super(x, y, z, box);
        setup(length, height, width);
    }

    public GenerationCache(int length,int height,int width) {
        super(0,0,0);
        setup(length, height, width);
    }

    public void setup(int length,int height,int width){
        blocks = new GenerationBlock[length+2+2][height+1][width+2+2];
    }

    public void generate(){
        generate(null);
    }

    @Override
    public void generate(GenerationTarget target){
        while(queue.size()>0){
           // System.err.println("yay");
            GenerationObject o = queue.get(0);
            o.generate(this);
            queue.remove(0);
        }
        //System.err.println("GOT HERE!");

        for(int x=0;x<blocks.length;x++){
            for(int y=0;y<blocks[0].length;y++){
                for(int z=0;z<blocks[0][0].length;z++){
                    if(blocks[x][y][z]!=null)
                        blocks[x][y][z].generate(target);
                }
            }
        }
    }

    @Override
    public void queue(GenerationObject object) {
        if(object!=null) {
            if (GenerationBlock.class.isAssignableFrom(object.getClass())) {
                GenerationBlock b = (GenerationBlock) object;

                int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(SkyBoxWorld.getSkyBoxCoordsFromWorld(b.x,b.z));

                if(Math.abs(b.x+1-xz[0])<blocks.length&&b.y<blocks[0].length&&Math.abs(b.z+1-xz[1])<blocks[0][0].length) {
                    blocks[Math.abs(b.x+1-xz[0])][b.y][Math.abs(b.z+1-xz[1])] = b;
                   // System.out.println(b.x+", "+b.y+", "+b.z);
                }
            } else {
                queue.add(object);
            }
        }
    }
}

package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.SkyBox.SkyBox;

public class GenerationDoor extends GenerationObject {

    private int length;
    private int height;

    private boolean rotated=false;
    private boolean now=false;

    private Material frame;
    private int frameData=0;
    private Material air;
    private int airData=0;

    public GenerationDoor(int x, int y, int z, int height, int length, Material frame, Material air,boolean rotated) {
        super(x, y, z);
        this.frame=frame;
        this.air=air;
        this.length=length-1;
        this.height=height-1;
        this.rotated=rotated;
    }

    public GenerationDoor(int x, int y, int z, int height, int length, Material frame, Material air,boolean rotated,boolean now) {
        super(x, y, z);
        this.frame=frame;
        this.air=air;
        this.length=length-1;
        this.height=height-1;
        this.rotated=rotated;
        this.now=now;
    }

    public GenerationDoor(int x, int y, int z, int height, int length, Material frame,int frameData, Material air,int airData,boolean rotated,boolean now) {
        this(x,y,z,height,length,frame,air,rotated,now);
        this.frameData=frameData;
        this.airData=airData;
    }

    public GenerationDoor(int x, int y, int z, int height, int length, SkyBox box, Material frame, Material air,boolean rotated) {
        super(x,y,z, box);
        this.frame=frame;
        this.air=air;
        this.length=length-1;
        this.height=height-1;
        this.rotated=rotated;
    }

    public GenerationDoor(int x, int y, int z, int height, int length, SkyBox box, Material frame, Material air,boolean rotated,boolean now) {
        super(x,y,z, box);
        this.frame=frame;
        this.air=air;
        this.length=length-1;
        this.height=height-1;
        this.rotated=rotated;
        this.now=now;
    }

    @Override
    public void generate(GenerationTarget target){
        if(rotated){
            Generator.FillBlocks(x, y, z+1, x, y , z+ length-1, frame,frameData,now,target);
            Generator.FillBlocks(x, y+ height, z+1, x, y + height, z+ length-1, frame,frameData,now,target);
            Generator.FillBlocks(x, y+1, z+ length, x, y + height-1, z+ length, frame,frameData,now,target);
            Generator.FillBlocks(x, y+1, z, x, y + height-1, z, frame,frameData,now,target);
            if(air!=null) {
                Generator.FillBlocks(x, y + 1, z + 1, x, y + height - 1, z + length - 1, air,airData,now,target);
            }
        }else {
            Generator.FillBlocks(x+ length, y+1, z, x + length, y + height-1, z, frame,frameData,now,target);
            Generator.FillBlocks(x, y+1, z, x , y + height-1, z, frame,frameData,now,target);
            Generator.FillBlocks(x+1, y, z, x + length-1, y , z, frame,frameData,now,target);
            Generator.FillBlocks(x+1, y+ height, z, x + length-1, y + height, z, frame,frameData,now,target);
            if(air!=null) {
                Generator.FillBlocks(x + 1, y + 1, z, x + length - 1, y + height - 1, z, air,airData,now,target);
            }
        }
    }
}

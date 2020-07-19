package thecode2.Generator.GenerationObjects;

import org.bukkit.Material;
import thecode2.Generator.GenerationObject;
import thecode2.Generator.GenerationTarget;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class GenerationOreVein extends GenerationObject {
    private String blockName;
    private Material ore;
    private int data=0;
    private int size;
    private int maxY=999;
    private SkyBox restristionBox=null;
    private int restristionHeight=47;

    public GenerationOreVein(int x, int y, int z, int size, Material ore) {
        super(x, y, z);
        this.ore=ore;
        this.size=size;
    }

    public GenerationOreVein(int x, int y, int z, SkyBox box, int size, Material ore) {
        super(x, y, z, box);
        this.ore=ore;
        this.size=size;
    }

    public GenerationOreVein(int x, int y, int z, int size, Material ore,int data) {
        super(x, y, z);
        this.ore=ore;
        this.size=size;
        this.data=data;
    }

    public GenerationOreVein(int x, int y, int z, int size, Material ore,int data,int maxy) {
        super(x, y, z);
        this.ore=ore;
        this.size=size;
        this.data=data;
        this.maxY=maxy;
    }

    public GenerationOreVein(int x, int y, int z, int size, Material ore,int data,int maxy,SkyBox restristionBox) {
        super(x, y, z);
        this.ore=ore;
        this.size=size;
        this.data=data;
        this.maxY=maxy;
        this.restristionBox=restristionBox;
    }

    public GenerationOreVein(int x, int y, int z, int size, Material ore,int data,int maxy,SkyBox restristionBox,int restrictedHeight) {
        super(x, y, z);
        this.ore=ore;
        this.size=size;
        this.data=data;
        this.maxY=maxy;
        this.restristionBox=restristionBox;
        this.restristionHeight=restrictedHeight;
    }

    public GenerationOreVein(int x, int y, int z, SkyBox box, int size, Material ore,int data) {
        super(x, y, z, box);
        this.ore=ore;
        this.size=size;
        this.data=data;
    }

    public GenerationOreVein(int x, int y, int z, int size, String blockName, int data, int maxy,SkyBox restristionBox,int restrictedHeight) {
        super(x, y, z);
        this.blockName=blockName;
        this.size=size;
        this.data=data;
        this.maxY=maxy;
        this.restristionBox=restristionBox;
        this.restristionHeight=restrictedHeight;
    }

    @Override
    public void generate(GenerationTarget target){
        String direction ="";

        int xoff=0;
        int yoff=0;
        int zoff=0;

        int change=0;

        for(int i=0;i<size;i++){
            if(restristionBox!=null){
                int[] xz= SkyBoxWorld.getWorldCoordsFromSkyBox(restristionBox);
                if(!(xz[0]<x+xoff&&xz[0]+SkyBoxWorld.TileSize[0]>x+xoff
                   &&xz[1]<z+zoff&&xz[1]+SkyBoxWorld.TileSize[1]>z+zoff)){
                    return;
                }
                if(restristionHeight<y+yoff)
                    return;
            }

            if(blockName!=null)
                Generator.SetBlock(x+xoff,y+yoff,z+zoff,blockName,data,target);
            else
                Generator.SetBlock(x+xoff,y+yoff,z+zoff,ore,data,target);
            if(Math.round(Math.random())==0){
                change=-1;
            }else{
                if(y+yoff<maxY) {
                    change = 1;
                }
            }
            if(Math.round(Math.random()*2)==0){
                xoff+=change;
            }else if(Math.round(Math.random()*2)==1){
                yoff+=change;
            }else{
                zoff+=change;
            }

        }
    }
}

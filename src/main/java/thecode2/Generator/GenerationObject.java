package thecode2.Generator;

import thecode2.SkyBox.SkyBox;

public class GenerationObject {

    public int x;
    public int y;
    public int z;

    public GenerationObject(int x,int y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public GenerationObject(int x, int y, int z, SkyBox box){
        int[] xz = box.getWorldCoordsFromSkyBox();

        this.x=x+xz[0];
        this.y=y;
        this.z=z+xz[1];
    }

    public void generate(GenerationTarget target){}

}

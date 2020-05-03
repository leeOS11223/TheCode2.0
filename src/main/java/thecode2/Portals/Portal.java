package thecode2.Portals;

import com.sun.javafx.scene.traversal.Direction;
import org.json.simple.JSONObject;
import thecode2.Grid.SkyBoxWorld;
import thecode2.SkyBox.SkyBox;

public class Portal {

    public int x,y;
    public int length,height;
    public SkyBox box;
    private int BoxX,BoxY;
    public Character side;

    public Portal(SkyBox box,Character side,int x,int y,int length,int height){
        this.box=box;
        this.side=side;
        this.length=length;
        this.height=height;
        this.x=x;
        this.y=y;
    }

    public Portal(JSONObject o) {
        Long l= (Long) o.get("x");
        x= Math.toIntExact(l);
        l= (Long) o.get("y");
        y= Math.toIntExact(l);
        l= (Long) o.get("length");
        length= Math.toIntExact(l);
        l= (Long) o.get("height");
        height= Math.toIntExact(l);
        l= (Long) o.get("side");
        side= (char)Math.toIntExact(l);

        l= (Long) o.get("BoxX");
        BoxX= Math.toIntExact(l);
        l= (Long) o.get("BoxY");
        BoxY= Math.toIntExact(l);
    }

    public JSONObject getJSON() {
        JSONObject o =new JSONObject();
        o.put("x",x);
        o.put("y",y);
        o.put("length",length);
        o.put("height",height);
        o.put("side",(int)side);

        if(box!=null) {
            o.put("BoxX", box.x);
            o.put("BoxY", box.y);
        }else{
            o.put("BoxX", BoxX);
            o.put("BoxY", BoxY);
        }

        return o;
    }

    public void checkBoxRegistered(){
        if(box==null) {
            SkyBox b =SkyBoxWorld.getSkyBox(BoxX, BoxY);
            if(b!=null)
                box = b;
        }
    }

    public int[] getConnectedSkyboxXZCords(){
        int xy[] = new int[2];

        switch(side){
            case 'n':
                xy[0]=box.x;
                xy[1]=box.y-1;
                break;
            case 's':
                xy[0]=box.x;
                xy[1]=box.y+1;
                break;
            case 'e':
                xy[0]=box.x+1;
                xy[1]=box.y;
                break;
            case 'w':
                xy[0]=box.x-1;
                xy[1]=box.y;
                break;
        }

        return xy;
    }

    public int[] getWorldXYZCords(boolean inWall){
        int xyz[] = new int[3];
        int tempXZ[] = SkyBoxWorld.getWorldCoordsFromSkyBox(box);

        int wallOffset=0;
        if(inWall) wallOffset=1;

        switch (side){
            case 'n':
                xyz[0]=tempXZ[0]+x;
                xyz[1]=y;
                xyz[2]=tempXZ[1]-wallOffset;
                break;
            case 's':
                xyz[0]=tempXZ[0]+x;
                xyz[1]=y;
                xyz[2]=tempXZ[1]+SkyBoxWorld.TileSize[1]+wallOffset;
                break;
            case 'e':
                xyz[0]=tempXZ[0]+SkyBoxWorld.TileSize[0]+wallOffset;
                xyz[1]=y;
                xyz[2]=tempXZ[1]+x;
                break;
            case 'w':
                xyz[0]=tempXZ[0]-wallOffset;
                xyz[1]=y;
                xyz[2]=tempXZ[1]+x;
                break;
        }


        return xyz;
    }

    public String toString(){
        String s = "";

        s+="box: "+box.toString();
        s+="\n";
        s+="side: "+side.toString();
        s+="\n";
        s+="size: "+length+", "+height;
        s+="\n";
        s+="offset: "+x+", "+y;

        return s;
    }

    public Portal generateCounterPortal() {
        int[] xz=this.getConnectedSkyboxXZCords();
        SkyBox box=SkyBoxWorld.getSkyBox(xz[0],xz[1]);

        Character s='r';

        switch(side){
            case 'n':
                s='s';
                break;
            case 's':
                s='n';
                break;
            case 'w':
                s='e';
                break;
            case 'e':
                s='w';
                break;
        }

        Portal p = new Portal(box,s,x,y,length,height);
        return p;
    }

    public int[] getSkyboxXZCords() {
        int xy[] = new int[2];

        xy[0]=box.x;
        xy[1]=box.y;

        return xy;

    }

    public int count() {
        return 5;//todo fix
    }

    public void configure() {
        checkBoxRegistered();
    }
}

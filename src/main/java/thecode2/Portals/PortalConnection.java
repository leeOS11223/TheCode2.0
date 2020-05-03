package thecode2.Portals;

import org.bukkit.Material;
import org.json.simple.JSONObject;
import thecode2.Generator.GenerationObjects.GenerationDoor;
import thecode2.Generator.Generator;
import thecode2.Modded.Blocks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PortalConnection {

    public Portal p1;
    public Portal p2;

    /**
     * 0 - closed
     * 1 - open
     */
    public int state =0;

    public int PortalConnectionID=-1;

    public PortalConnection(Portal p1,Portal p2){
        this.p1=p1;
        this.p2=p2;
        PortalConnectionID=PortalHandler.getNewID();
    }

    public PortalConnection(int portalConnectionID,JSONObject o) {
        PortalConnectionID=portalConnectionID;
        PortalHandler.newBiggestConnectionID(portalConnectionID);

        p1=new Portal((JSONObject) o.get("1"));
        p2=new Portal((JSONObject) o.get("2"));
        Long l= (Long) o.get("state");
        state= Math.toIntExact(l);
    }

    public JSONObject getJSON() {
        JSONObject o = new JSONObject();

        o.put("1",p1.getJSON());
        o.put("2",p2.getJSON());
        o.put("state",state);

        return o;
    }

    public void Generate(){
        boolean rotated=true;
        if(p1.side.equals('n')||p1.side.equals('s')){
            rotated=false;
        }
        if (state != 2) {
            int[] xyz = p1.getWorldXYZCords(true);
            Generator.Queue(new GenerationDoor(xyz[0], xyz[1], xyz[2], p1.height, p1.length, Blocks.ColoredBlock, getFrameData(), getFillMateral(), getAirData(), rotated, false));
            xyz = p2.getWorldXYZCords(true);
            Generator.Queue(new GenerationDoor(xyz[0], xyz[1], xyz[2], p2.height, p2.length, Blocks.ColoredBlock, getFrameData(), getFillMateral(), getAirData(), rotated, false));
        }else{
            int[] xyz = p1.getWorldXYZCords(true);
            Generator.Queue(new GenerationDoor(xyz[0], xyz[1], xyz[2], p1.height, p1.length, p1.box.wallMaterial, p1.box.wallMaterialID, p1.box.wallMaterial, p1.box.wallMaterialID, rotated, false));
            xyz = p2.getWorldXYZCords(true);
            Generator.Queue(new GenerationDoor(xyz[0], xyz[1], xyz[2], p2.height, p2.length, p2.box.wallMaterial, p2.box.wallMaterialID, p2.box.wallMaterial, p2.box.wallMaterialID, rotated, false));

        }
    }

    private int getAirData() {
        switch(state){
            case 0:
                return 4;//yellow
            case 2:
                return 0;//white
        }
        return 0;
    }

    private Material getFillMateral() {
        switch(state){
            case 0:
                return Blocks.ColoredBlock;
        }
        return Material.AIR;
    }

    public int getFrameData(){
        switch(state){
            case 0:
                return 14;//red
            case 1:
                return 5;//green
            case 2:
                return 0;//white
        }
        return 0;
    }

    public int getPortalConnectionID() {
        return PortalConnectionID;
    }

    public void saveToFile() {
        try {
            Files.write(Paths.get("./TheCode2/Portal/"+PortalConnectionID+".json"),getJSON().toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configure() {
        p1.configure();
        p2.configure();
    }
}

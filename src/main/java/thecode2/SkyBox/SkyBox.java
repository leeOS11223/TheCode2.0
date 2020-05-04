package thecode2.SkyBox;

import org.bukkit.Material;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import thecode2.Generator.GenerationObject;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Modded.Blocks;
import thecode2.Portals.Portal;
import thecode2.Portals.PortalConnection;
import thecode2.Portals.PortalHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SkyBox {

    public int x;
    public int y;
    public int minY=0;
    public int maxY=150;
    public String type="NULL";
    public ArrayList<GenerationObject> GenerationObjects=new ArrayList<GenerationObject>();
    public ArrayList<PortalConnection> portalsConnections=new ArrayList<PortalConnection>();
    public Material wallMaterial= Blocks.ColoredBlock;
    public int wallMaterialID=0;
    public ArrayList<Material> protectedMaterials = new ArrayList<>();

    public SkyBox(){
        this.type=this.getClass().getSimpleName();
    }

    public SkyBox(int x,int y){
        this.x=x;
        this.y=y;
        this.type=this.getClass().getSimpleName();
    }

    public SkyBox(int xy[]){
        this.x=xy[0];
        this.y=xy[1];
    }

    public SkyBox(int x,int y,JSONObject o) {
        this.x=x;
        this.y=y;
        Long l= (Long) o.get("minY");
        minY= Math.toIntExact(l);
        l= (Long) o.get("maxY");
        maxY= Math.toIntExact(l);
        type= (String) o.get("type");

        l= (Long) o.get("wallMaterial");
        wallMaterial= Material.getMaterial(Math.toIntExact(l));
        l= (Long) o.get("wallMaterialID");
        wallMaterialID= Math.toIntExact(l);

        JSONArray portals = (JSONArray) o.get("portals");
        for(Object p2:portals.toArray()){
            Long p=(Long)p2;
            portalsConnections.add(PortalHandler.getPortalConnectionByID(Math.toIntExact(p)));
        }

        JSONArray protectedmaterials = (JSONArray) o.get("protectedMaterials");
        for(Object p2:protectedmaterials.toArray()){
            Long p=(Long)p2;
            protectedMaterials.add(Material.getMaterial(Math.toIntExact(p)));
        }
    }

    public void registerProtectedMaterial(Material m){
        protectedMaterials.add(m);
    }


    public JSONObject getJSON(){
        JSONObject o = new JSONObject();
        o.put("minY",minY);
        o.put("maxY",maxY);
        o.put("type",type);

        o.put("wallMaterial",wallMaterial.getId());
        o.put("wallMaterialID",wallMaterialID);

        /*JSONArray portals = new JSONArray();

        for(PortalConnection p:portalsConnections){
            portals.add(p.getJSON());
        }

        o.put("portals",portals);*/

        JSONArray portals = new JSONArray();

        for(PortalConnection p:portalsConnections){
            portals.add(p.getPortalConnectionID());
        }

        o.put("portals",portals);


        JSONArray protectedMaterials = new JSONArray();

        for(Material m:this.protectedMaterials){
            protectedMaterials.add(m.getId());
        }

        o.put("protectedMaterials",protectedMaterials);

        return o;
    }

    /**
     * @return int[x,z]
     */
    public int[] getWorldCoordsFromSkyBox(){
        return SkyBoxWorld.getWorldCoordsFromSkyBox(this);
    }

    public void generate(){}

    public void addGenObject(GenerationObject o){
        GenerationObjects.add(o);
    }

    public void addPortal(PortalConnection connect) {
        portalsConnections.add(connect);
    }

    public String toString(){
        String s="("+x+","+y+"), maxY: "+maxY;
        return s;
    }

    public SkyBox clone(int x,int y){
        SkyBox b= new SkyBox(x,y);
        b.maxY=maxY;
        b.minY=minY;
        b.GenerationObjects=GenerationObjects;
        b.wallMaterialID=wallMaterialID;
        b.protectedMaterials=protectedMaterials;
        //b.portalsConnections=portalsConnections;
        return b;
    }

    public void saveToFile(){
        try {
            Files.write(Paths.get("./TheCode2/Box/"+x+","+y+".json"),getJSON().toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

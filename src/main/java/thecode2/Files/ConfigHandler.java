package thecode2.Files;

import org.bukkit.Material;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Portals.PortalHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigHandler {

    public static void deletePortal(int ID){
        File file = new File("./TheCode2/Portal/"+String.valueOf(ID)+".json");
        file.delete();
    }

    public static void loadPortals(){
        File folder = new File("./TheCode2/Portal");

        for (final File fileEntry : folder.listFiles()) {
            try {
                Reader reader = new FileReader(fileEntry.toPath().toString());
                PortalHandler.loadPortalfromJSON(fileEntry.getName().replace(".json",""),reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadWorlds() {
        File folder = new File("./TheCode2/Box");

        for (final File fileEntry : folder.listFiles()) {
            //System.out.println(fileEntry.getName());
            try {
                //System.err.println(fileEntry.getName().replace(".json",""));
                Reader reader = new FileReader(fileEntry.toPath().toString());//Files.readAllLines(fileEntry.toPath()).toString());
                SkyBoxWorld.RegisterSkyBoxFromJSON(fileEntry.getName().replace(".json",""),reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //SkyBoxWorld.checkPortalSetup();

    }

    public static void writeTemplate(){
        JSONObject o = new JSONObject();

        o.put("minY",0);
        o.put("maxY",150);
        o.put("FrameBlock",4);
        o.put("FrameBlockID",0);
        o.put("IgnitonBlock",51);
        o.put("IgnitonBlockID",0);

        JSONArray generationObjects = new JSONArray();
        JSONObject layer = new JSONObject();
        layer.put("type","layer");
        layer.put("blockID",2);
        layer.put("blockMetaID",0);
        layer.put("y",50);
        generationObjects.add(layer);

        JSONObject ground = new JSONObject();
        ground.put("type","ground");
        ground.put("blockID",1);
        ground.put("blockMetaID",0);
        ground.put("maxY",49);
        generationObjects.add(ground);

        JSONObject trees = new JSONObject();
        trees.put("type","trees");
        trees.put("woodBlockID",1);
        trees.put("woodBlockMetaID",0);
        trees.put("leavesBlockID",4);
        trees.put("leavesBlockMetaID",0);
        trees.put("MaxHeight",7);
        trees.put("MinHeight",3);
        trees.put("MaxTrees",6);
        trees.put("MinTrees",3);
        generationObjects.add(trees);

        JSONObject ores = new JSONObject();
        ores.put("type","ores");
        ores.put("maxY",49);
            JSONArray ore = new JSONArray();
                JSONObject exampleOre = new JSONObject();
                    exampleOre.put("blockID",14);
                    exampleOre.put("blockMetaID",0);
                    exampleOre.put("maxVeinSize",5);
                    exampleOre.put("minVeinSize",1);
                    exampleOre.put("maxVeins",12);
                    exampleOre.put("minVeins",-5);
                    exampleOre.put("maxHeight",30);

            ore.add(exampleOre);
        ores.put("ores",ore);
        generationObjects.add(ores);

        o.put("GenerationObjects",generationObjects);

        try {
            Files.write(Paths.get("./template.json"),o.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

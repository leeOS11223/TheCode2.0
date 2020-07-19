package thecode2.Grid;

import org.bukkit.Location;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import thecode2.Generator.Generator;
import thecode2.Players.PlayerData;
import thecode2.Portals.PortalConnection;
import thecode2.Portals.PortalHandler;
import thecode2.SkyBox.SkyBox;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class SkyBoxWorld {

    public static int spawnHeight=51;
    private static ArrayList<SkyBox> boxes = new ArrayList<SkyBox>();

    public static final int[] TileSize = {18,18};//length of 19
    private static final int[] TileSpacing = {3,3};//spacing of 2
    public static final int height=150;

    public static void RegisterNewSkyBox(SkyBox box){
        if(Generator.generateSkyBox(box)){
            boxes.add(box);
        }else{
            Generator.deleteSkyBox(box);
        }
    }

    public static void RegisterSkyBoxFromJSON(String Filename, Reader JSON){
        String[] xy=Filename.split(",");
        int x=Integer.parseInt(xy[0]);
        int y=Integer.parseInt(xy[1]);

        JSONParser jsonParser = new JSONParser();
        try {
            try {
                JSONObject o = (JSONObject) jsonParser.parse(JSON);

                SkyBox box = new SkyBox(x,y,o);

                boxes.add(box);

                JSON.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static SkyBox getSkyBox(int x,int y){
        for(SkyBox box:boxes){
            if(box.x==x&&box.y==y){
                return box;
            }
        }

        return null;
    }

    public static void DestroySkyBox(int x,int y){
        DestroySkyBox(getSkyBox(x,y));
    }
    public static void DestroySkyBox(SkyBox box){
        boxes.remove(box);
        Generator.deleteSkyBox(box);
    }

    /**
     * @param box
     * @return int[x,z]
     */
    public static int[] getWorldCoordsFromSkyBox(SkyBox box){
        return getWorldCoordsFromSkyBox(new int[]{box.x,box.y});
    }

    /**
     * @param x, y
     * @return int[x,z]
     */
    public static int[] getWorldCoordsFromSkyBox(int x,int y){
        return getWorldCoordsFromSkyBox(new int[]{x,y});
    }

    /**
     * @param xy
     * @return int[x,z]
     */
    public static int[] getWorldCoordsFromSkyBox(int xy[]){
        int location[]=new int[2];
        location[0]=xy[0]*(TileSize[0]+TileSpacing[0]);
        location[1]=xy[1]*(TileSize[1]+TileSpacing[1]);

        return location;
    }

    public static int[] getSkyBoxCoordsFromWorld(int x,int z){
        int Xoff=0;
        if(x<0){
            Xoff=-1;
        }
        int Zoff=0;
        if(z<0){
            Zoff=-1;
        }

        int location[]=new int[2];
        location[0]=(x-Xoff)/(TileSize[0]+TileSpacing[0]);
        location[1]=(z-Zoff)/(TileSize[1]+TileSpacing[1]);

        if(x<0){
            location[0]--;
        }
        if(z<0){
            location[1]--;
        }

        return location;
    }

    public static void saveAll() {
        for(SkyBox box:boxes){
            box.saveToFile();
            for(PortalConnection portal:box.portalsConnections){
                portal.saveToFile();
            }
        }
    }

    public static void checkPortalSetup() {
        for(SkyBox b:boxes){
            for(PortalConnection pc:b.portalsConnections){
                pc.p1.checkBoxRegistered();
                pc.p2.checkBoxRegistered();
            }
        }
    }
}

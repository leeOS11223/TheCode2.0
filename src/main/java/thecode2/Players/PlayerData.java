package thecode2.Players;

import org.json.simple.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerData {

    public String UUID;
    public Point SpawnBox;

    public PlayerData(String UUID, int x,int y){
        this.UUID=UUID;
        this.SpawnBox=new Point(x,y);
        saveToFile();
    }

    public PlayerData(String UUID, JSONObject o){
        this.UUID=UUID;

        Long x= (Long) o.get("x");
        int xx= Math.toIntExact(x);
        Long y= (Long) o.get("y");
        int yy= Math.toIntExact(y);

        SpawnBox=new Point(xx,yy);
    }

    public void saveToFile() {
        try {
            Files.write(Paths.get("./TheCode2/Players/"+UUID+".json"),getJSON().toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSON() {
        JSONObject o = new JSONObject();

        o.put("x",SpawnBox.x);
        o.put("y",SpawnBox.y);

        return o;
    }

}

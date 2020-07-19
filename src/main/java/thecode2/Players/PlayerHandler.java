package thecode2.Players;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import thecode2.Files.ConfigHandler;
import thecode2.Portals.PortalConnection;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class PlayerHandler {

    public static ArrayList<PlayerData> players = new ArrayList<>();

    public static void setup(){
        ConfigHandler.loadPlayers();
    }

    public static void loadPlayerfromJSON(String Filename, Reader JSON) {
        JSONParser jsonParser = new JSONParser();
        try {
            try {
                JSONObject o = (JSONObject) jsonParser.parse(JSON);

                PlayerData p = new PlayerData(Filename,o);
                players.add(p);

                JSON.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void saveAll() {
        for(PlayerData player:players){
            player.saveToFile();
        }
    }

    public static PlayerData getPlayer(Player player) {
        for(PlayerData p:players){
            if(p.UUID.equals(player.getUniqueId().toString())){
                return p;
            }
        }
        return null;
    }
}

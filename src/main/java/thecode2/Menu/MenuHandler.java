package thecode2.Menu;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MenuHandler {

    public static void test(Player player){

    }

    public static void sendMenu(Player player,Menu menu) {
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                "tellraw "+player.getName()+" " + menu.generateCommandText());
        //System.err.println("tellraw "+player.getName()+" " + menu.generateCommandText());
    }

}

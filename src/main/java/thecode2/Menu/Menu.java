package thecode2.Menu;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Menu {

    public ArrayList<MenuOption> parts=new ArrayList<>();

    public void addOption(MenuOption option){
        parts.add(option);
    }

    public String generateCommandText(){
        String out="[\"\",";

        for(MenuOption option:parts){
            out+=option.generateCommandPart();
            out+=",";
        }
        out=out.substring(0,out.length()-1);

        return out+"]";
    }

    public void run(Player player){
        MenuHandler.sendMenu(player,this);
    }

}

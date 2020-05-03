package thecode2.Menu.Menus;

import thecode2.Menu.Menu;
import thecode2.Menu.MenuOption;
import thecode2.SkyBox.SkyBox;

public class DoorMenu extends Menu {

    public DoorMenu(boolean isOpen, SkyBox box, int portalID) {
        if(!isOpen)
            addOption(new MenuOption("open","green","open "+String.valueOf(box.x)+" "+String.valueOf(box.y)+" "+String.valueOf(portalID)+"","opens the door"));
        else
            addOption(new MenuOption("close","gold","close "+String.valueOf(box.x)+" "+String.valueOf(box.y)+" "+String.valueOf(portalID)+"","closes the door"));
        addOption(new MenuOption("DESTROY","red","destroy "+String.valueOf(box.x)+" "+String.valueOf(box.y)+" "+String.valueOf(portalID)+"","DESTROYS the door, but not the world."));
        System.err.println(parts.size());
    }

}

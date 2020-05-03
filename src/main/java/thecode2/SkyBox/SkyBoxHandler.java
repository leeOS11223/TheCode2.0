package thecode2.SkyBox;

import org.bukkit.Material;
import thecode2.SkyBox.SkyBoxes.Earth;
import thecode2.SkyBox.SkyBoxes.Test;

import java.util.ArrayList;

public class SkyBoxHandler {

    public static ArrayList<SkyBoxLink> PortalLinks=new ArrayList<>();

    public static void registerSkyboxToPortal(SkyBox box, Material block, Material igniter){
        SkyBoxLink link = new SkyBoxLink(box,block,igniter);
        PortalLinks.add(link);
    }

    public static SkyBox getSkybox(Material block, Material igniter,int x,int y){
        SkyBox boxO=null;//new Test(x,y);
        for(SkyBoxLink box:PortalLinks){
            if(box.portalframe.equals(block)&&box.ignitionBlock.equals(igniter)){
                boxO=box.getNewSkybox(x,y);
                System.out.println(boxO);
            }
        }
        return boxO;
    }

}

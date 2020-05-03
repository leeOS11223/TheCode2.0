package thecode2.SkyBox;

import org.bukkit.Material;
import thecode2.SkyBox.SkyBoxes.Earth;

public class SkyBoxLink {

    public SkyBox skyboxTemplate;
    public Material portalframe;
    public Material ignitionBlock;

    public SkyBoxLink(SkyBox box, Material frame, Material fire){
        skyboxTemplate=box;
        portalframe=frame;
        ignitionBlock=fire;
    }

    public SkyBox getNewSkybox(int x,int y){
        SkyBox b=skyboxTemplate.clone(x,y);
        return b;
    }

}

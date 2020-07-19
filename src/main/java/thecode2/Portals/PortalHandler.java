package thecode2.Portals;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Players.PlayerData;
import thecode2.SkyBox.SkyBox;

import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import static thecode2.Generator.Generator.FillEffect;

public class PortalHandler {

    private static int portalConnectionID=-1;
    public static ArrayList<PortalConnection> portals=new ArrayList<PortalConnection>();

    public static boolean isOverlap(SkyBox box, Portal p){

        for(PortalConnection pc:box.portalsConnections){
            if(pc.p1.side==p.side&&pc.p1.box==p.box){
                if(overlapping(pc.p1,p)){
                    return true;
                }
            }
            if(pc.p2.side==p.side&&pc.p2.box==p.box){
                if(overlapping(pc.p1,p)){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isOverlap(SkyBox box, Point p, Character side){
        for(PortalConnection pc:box.portalsConnections){
            if((pc.p1.side==side&&pc.p1.box==box)&&(pc.p1.state==1&&pc.p2.state==1)) {
                if (overlapping(pc.p1, p,1)) {
                    return true;
                }
            }
            if(pc.p2.side==side&&pc.p2.box==box) {
                if (overlapping(pc.p1, p,1)&&(pc.p1.state==1&&pc.p2.state==1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static PortalConnection getOverlappingPortal(SkyBox box, Point p, Character side, int offset){
        for(PortalConnection pc:box.portalsConnections){
            if((pc.p1.side==side&&pc.p1.box==box)) {
                if (overlapping(pc.p1, p,offset)) {
                    return pc;
                }
            }
            if(pc.p2.side==side&&pc.p2.box==box) {
                if (overlapping(pc.p1, p,offset)) {
                    return pc;
                }
            }
        }

        return null;
    }

    public static boolean overlapping(Portal p1, Portal p2){
        if(p1.x>=p2.x&&p1.x<p2.x+p2.length&&p1.y>=p2.y&&p1.y<p2.y+p2.height
                ||p2.x>=p1.x&&p2.x<p1.x+p1.length&&p2.y>=p1.y&&p2.y<p1.y+p1.height){
            return true;
        }
        return false;
    }

    public static boolean overlapping(Portal p1, Point p2,int offset){
        //p1.x-offset>=p2.x&&p1.y-offset>=p2.y
        //                ||
        if(p2.x-offset>=p1.x&&p2.x<p1.x+p1.length-offset&&p2.y-offset>=p1.y&&p2.y<p1.y+p1.height-offset){
            return true;
        }
        return false;
    }

    public static Portal CheckPortalFrame(int startX, int startY, int startZ, boolean rotated, Material type, byte data) {
        if(type.equals(Material.AIR)) return null;

        int bottomLeftX=0;
        int bottomLeftY=0;
        int bottomLeftZ=0;

        int length=0;
        int height=0;

        int lookingX=0;
        int lookingY=0;
        int lookingZ=0;

        World world=Generator.world;

        if(rotated){
            while (!world.getBlockAt(startX + lookingX, startY + lookingY + 1, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingZ++;
                } else {
                    return null;
                }
            }

            lookingY++;
            while (!world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ-1).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingY++;
                } else {
                    return null;
                }
            }

            lookingZ--;
            length++;
            while (!world.getBlockAt(startX + lookingX, startY + lookingY - 1, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingZ--;
                    length++;
                } else {
                    return null;
                }
            }

            lookingY--;
            height++;
            while (!world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ+1).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingY--;
                    height++;
                } else {
                    return null;
                }
            }
        }else {
            while (!world.getBlockAt(startX + lookingX, startY + lookingY + 1, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingX++;
                } else {
                    return null;
                }
            }

            lookingY++;
            while (!world.getBlockAt(startX + lookingX - 1, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingY++;
                } else {
                    return null;
                }
            }

            lookingX--;
            length++;
            while (!world.getBlockAt(startX + lookingX, startY + lookingY - 1, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingX--;
                    length++;
                } else {
                    return null;
                }
            }

            lookingY--;
            height++;
            while (!world.getBlockAt(startX + lookingX + 1, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                if (world.getBlockAt(startX + lookingX, startY + lookingY, startZ + lookingZ).getType().equals(type)) {
                    lookingY--;
                    height++;
                } else {
                    return null;
                }
            }
        }

        bottomLeftX=startX+lookingX;
        bottomLeftY=startY+lookingY;
        bottomLeftZ=startZ+lookingZ;

        //Generator.Queue(new GenerationDoor(bottomLeftX,bottomLeftY,bottomLeftZ,height+1,length+1, Material.AIR, null,rotated));

        if(rotated){
            FillEffect(bottomLeftX, bottomLeftY, bottomLeftZ, bottomLeftX, bottomLeftY + height, bottomLeftZ + length, Particle.PORTAL);
        }else {
            FillEffect(bottomLeftX, bottomLeftY, bottomLeftZ, bottomLeftX + length, bottomLeftY + height, bottomLeftZ, Particle.PORTAL);
        }

        int xz2[]=SkyBoxWorld.getSkyBoxCoordsFromWorld(bottomLeftX, bottomLeftZ);

        SkyBox box = SkyBoxWorld.getSkyBox(xz2[0],xz2[1]);//new SkyBox(SkyBoxWorld.getSkyBoxCoordsFromWorld(bottomLeftX, bottomLeftZ));
        int xz[] = SkyBoxWorld.getWorldCoordsFromSkyBox(box);
        Character d = '?';
        int xoffset;
        if(!rotated) {
            xoffset = bottomLeftX-xz[0];
            if(bottomLeftZ-xz[1]==0){
                d = 'n';
            }else{
                d = 's';
            }
        }else{
            xoffset = bottomLeftZ-xz[1];
            if(bottomLeftX-xz[0]==0){
                d = 'w';
            }else{
                d = 'e';
            }
        }
        int yoffset=bottomLeftY;

        Portal p = new Portal(box,d,xoffset,yoffset,length+1,height+1);

        return p;
    }

    public static int getNewID() {
        portalConnectionID++;
        return portalConnectionID;
    }

    public static void loadPortalfromJSON(String Filename, Reader JSON) {
        int ID=Integer.parseInt(Filename);
        JSONParser jsonParser = new JSONParser();
        try {
            try {
                JSONObject o = (JSONObject) jsonParser.parse(JSON);

                PortalConnection p = new PortalConnection(ID,o);
                portals.add(p);

                JSON.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static PortalConnection getPortalConnectionByID(int id) {

        for(PortalConnection p:portals){
            if(p.PortalConnectionID==id){
                return p;
            }
        }
        return null;
    }

    public static void configureLoadedPortals() {

        for(PortalConnection p:portals){
            p.configure();
        }

        portals.clear();
        portals = null;

    }

    public static void newBiggestConnectionID(int id) {
        if(id>portalConnectionID){
            portalConnectionID=id;
        }
    }

    public static void setPortalState(int BoxX,int BoxZ,int portalID,int state){
        ArrayList<PortalConnection> portals=SkyBoxWorld.getSkyBox(BoxX,BoxZ).portalsConnections;
        for(PortalConnection p:portals){
            if(p.PortalConnectionID==portalID){
                p.p1.state=state;
                p.p2.state=state;
                p.Generate();
            }
        }
    }

    public static void requestPortalState(Player player, int BoxX, int BoxZ, int portalID, int state){
        ArrayList<PortalConnection> portals=SkyBoxWorld.getSkyBox(BoxX,BoxZ).portalsConnections;
        for(PortalConnection p:portals){
            if(p.PortalConnectionID==portalID){
                if(player.getUniqueId().toString().equals(p.p1.box.getOwner()))
                    if(state!=0&&!player.getUniqueId().toString().equals(p.p2.box.getOwner()))
                        p.p1.state=3;
                    else
                        p.p1.state=state;
                if(player.getUniqueId().toString().equals(p.p2.box.getOwner()))
                    if(state!=0&&!player.getUniqueId().toString().equals(p.p1.box.getOwner()))
                        p.p2.state=3;
                    else
                        p.p2.state=state;

                 if(p.p1.state==3&&p.p2.state==3){
                     p.p1.state=1;
                     p.p2.state=1;
                 }
                p.Generate();
            }
        }
    }

    public static void setPortalState(SkyBox box,int portalID,int state){
        ArrayList<PortalConnection> portals=box.portalsConnections;
        for(PortalConnection p:portals){
            if(p.PortalConnectionID==portalID){
                p.p1.state=state;
                p.p2.state=state;
                p.Generate();
            }
        }
    }

    public static void deletePortal(int BoxX,int BoxZ, int portalID) {
        ArrayList<PortalConnection> portals=SkyBoxWorld.getSkyBox(BoxX,BoxZ).portalsConnections;
        for(PortalConnection p:portals) {
            if (p.PortalConnectionID == portalID) {
                p.p1.box.portalsConnections.remove(p);
                p.p2.box.portalsConnections.remove(p);
                return;
            }
        }
    }

    public static boolean canDeletePortal(int BoxX,int BoxZ, int portalID, Player player) {
        ArrayList<PortalConnection> portals=SkyBoxWorld.getSkyBox(BoxX,BoxZ).portalsConnections;
        for(PortalConnection p:portals) {
            if (p.PortalConnectionID == portalID) {
                if(p.p1.box.getOwner().equals(player.getUniqueId().toString())||p.p2.box.getOwner().equals(player.getUniqueId().toString()))
                    return true;
            }
        }
        return false;
    }
}

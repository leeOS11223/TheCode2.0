package thecode2;

import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import thecode2.Files.ConfigHandler;
import thecode2.Generator.GenerationObjects.GenerationDoor;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Menu.MenuHandler;
import thecode2.Menu.Menus.DoorMenu;
import thecode2.Portals.Portal;
import thecode2.Portals.PortalConnection;
import thecode2.Portals.PortalHandler;
import thecode2.SkyBox.Dungeons.ClassicDungeon.ClassicDungeon;
import thecode2.SkyBox.Dungeons.DungeonHandler;
import thecode2.SkyBox.SkyBox;
import thecode2.SkyBox.SkyBoxHandler;
import thecode2.SkyBox.SkyBoxes.Earth;
import thecode2.SkyBox.SkyBoxes.Nether;
import thecode2.SkyBox.SkyBoxes.Test;
import thecode2.SkyBox.SkyBoxes.Wasteland;

import java.awt.*;
import java.util.ArrayList;

public final class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic
        //TODO load generator que from file

        //ConfigHandler.writeTemplate();

        SkyBoxHandler.registerSkyboxToPortal(new Earth(0,0), Material.SMOOTH_BRICK, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Wasteland(0,0), Material.COBBLESTONE, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Test(0,0), Material.SANDSTONE, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Nether(0,0), Material.OBSIDIAN, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new ClassicDungeon(0,0), Material.BRICK, Material.FIRE);

        Generator.world=getServer().getWorld("world");
        getLogger().info("set generator's world to: "+ Generator.world.getName());

        getLogger().info("Loading Portals.");
        //load portals
        ConfigHandler.loadPortals();
        getLogger().info("Done.\n");

        getLogger().info("Loading Worlds.");
        //load worlds
        ConfigHandler.loadWorlds();
        getLogger().info("Done.\n");

        getLogger().info("Configuring Portals.");
        //configure portals
        PortalHandler.configureLoadedPortals();
        getLogger().info("Done.\n");


        getLogger().info("Setting up Dungeons.");
        //setup dungeon stuff
        DungeonHandler.SetUp();
        getLogger().info("Done.\n");

        getLogger().info("beginning update loop!");
        //loop
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
               update();
            }
        }, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //TODO save generator que to file

        //save worlds
        SkyBoxWorld.saveAll();
    }

    public void update(){
        Generator.HandleQueue();
        //getServer().broadcastMessage("boop!");
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(e.toWeatherState());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("basic")) {
            Location loc=((Player)sender).getLocation();
            int[] xz= SkyBoxWorld.getSkyBoxCoordsFromWorld(loc.getBlockX(),loc.getBlockZ());
            getServer().broadcastMessage(xz[0]+", "+xz[1]);

            Block block = Generator.world.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ());


            getServer().broadcastMessage(String.valueOf(block.getTypeId())+":"+String.valueOf(block.getData()));
            //Generator.Queue(new GenerationOreVein(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),12, Material.DIAMOND_ORE));
            //Generator.Queue(new GenerationDoor(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),6,5, Material.GOLD_BLOCK, Material.AIR,true));


            //Generator.generateSkyBox(
            SkyBoxWorld.RegisterNewSkyBox(new Wasteland(xz[0],xz[1]));

            //Generator.Queue(new GenerationBlock(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(), Material.SANDSTONE));
            //Generator.Queue(new GenerationTree(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),6, Material.LOG,Material.LEAVES));

            return true;
        }else if (cmd.getName().equalsIgnoreCase("close")) {
            int x=Integer.parseInt(args[0]);
            int z=Integer.parseInt(args[1]);
            int portalID=Integer.parseInt(args[2]);
            PortalHandler.setPortalState(x,z,portalID,0);
            return true;
        }else if (cmd.getName().equalsIgnoreCase("open")) {
            int x=Integer.parseInt(args[0]);
            int z=Integer.parseInt(args[1]);
            int portalID=Integer.parseInt(args[2]);
            PortalHandler.setPortalState(x,z,portalID,1);
            return true;
        }else if (cmd.getName().equalsIgnoreCase("destroy")) {
            int x = Integer.parseInt(args[0]);
            int z = Integer.parseInt(args[1]);
            int portalID = Integer.parseInt(args[2]);
            PortalHandler.setPortalState(x, z, portalID, 2);
            PortalHandler.deletePortal(x, z, portalID);
            ConfigHandler.deletePortal(portalID);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.STICK) {
            //player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());

            Location loc=player.getLocation();
            Block block = Generator.world.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ());

           // player.sendTitle(String.valueOf(block.getTypeId())+":"+String.valueOf(block.getData()),"",1,5,1);

            Block b=event.getClickedBlock();
            int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX(),b.getZ());
            player.sendTitle(String.valueOf(boxCoords[0]+","+boxCoords[1]),"",1,5,1);

            int xz[]=SkyBoxWorld.getWorldCoordsFromSkyBox(SkyBoxWorld.getSkyBoxCoordsFromWorld(loc.getBlockX(),loc.getBlockZ()));

           // player.sendMessage(xz[0]+", "+xz[1]+" : "+(loc.getBlockX()-xz[0]) + ", "+(loc.getBlockZ()-xz[1]));

            SkyBox box = SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]);

            player.sendMessage(box.toString());
            player.sendMessage(box.getJSON().toJSONString());

            player.sendMessage(String.valueOf(box.portalsConnections.size()));

           // Generator.SetBlockNOW(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ(), Blocks.ColoredBlock,Blocks.getBlockData("red"));
        }
    }

    @EventHandler
    public void onBlockPlaced2(BlockPlaceEvent event){
        Block b=event.getBlock();
        b=Generator.world.getBlockAt(b.getX(),b.getY(),b.getZ());
        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX(),b.getZ());

        for(Material m:SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).protectedMaterials) {
            if (b.getType().equals(m)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockIgniteEvent event){
        if(event.getPlayer()==null)
            return;
        Block b=event.getBlock();
        b=Generator.world.getBlockAt(b.getX(),b.getY()-1,b.getZ());

        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX(),b.getZ());

        int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(boxCoords[0],boxCoords[1]);

        if (xz[0] == b.getX() || xz[0] + SkyBoxWorld.TileSize[0] == b.getX() || xz[1] == b.getZ() || xz[1] + SkyBoxWorld.TileSize[1] == b.getZ()) {
            if (SkyBoxWorld.getSkyBox(boxCoords[0], boxCoords[1]) == null){
                return;
            }

            SkyBox box=SkyBoxWorld.getSkyBox(boxCoords[0], boxCoords[1]);

            Portal p1 = PortalHandler.CheckPortalFrame(b.getX(), b.getY(), b.getZ(), false, b.getType(), b.getData());
            Portal p2 = PortalHandler.CheckPortalFrame(b.getX(), b.getY(), b.getZ(), true, b.getType(), b.getData());
            Material frame=Generator.world.getBlockAt(b.getX(),b.getY(),b.getZ()).getType();
            if (p1 != null&&!PortalHandler.isOverlap(box,p1)) {//x
                int k[] = p1.getConnectedSkyboxXZCords();
                SkyBox bb=SkyBoxHandler.getSkybox(frame,Material.FIRE,k[0], k[1]);
                bb.portalsConnections=new ArrayList<>();

                if(bb==null) {
                    //make particles if bad
                    Generator.FillEffect(b.getX(), b.getY() + 2, b.getZ(), b.getX(), b.getY() + 2, b.getZ(), 0.5f, 0, 0.5f, Particle.BARRIER);
                    return;
                }

                //delete old portal
                int xyz[] = p1.getWorldXYZCords(false);
                Generator.Generate(new GenerationDoor(xyz[0], xyz[1], xyz[2], p1.height, p1.length, Material.AIR, null, false, true));

                //register and generate new world
                try{
                    if (SkyBoxWorld.getSkyBox(k[0], k[1]) == null) {
                        SkyBoxWorld.RegisterNewSkyBox(bb);//new Earth(k[0], k[1]));
                    }
                    //create new portal
                    setUpNewPortalWorldData(boxCoords, p1);
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getPlayer().sendMessage("An error occurred please report this to your server admin immediately");
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), new ItemStack(b.getType(),p1.count(), (short) b.getData()));
                }
            } else if (p2 != null&&!PortalHandler.isOverlap(box,p2)) {//z

                int k[] = p2.getConnectedSkyboxXZCords();
                SkyBox bb=SkyBoxHandler.getSkybox(frame,Material.FIRE,k[0], k[1]);

                if(bb==null) {
                    Generator.FillEffect(b.getX(), b.getY() + 2, b.getZ(), b.getX(), b.getY() + 2, b.getZ(), 0.5f, 0, 0.5f, Particle.BARRIER);
                    return;
                }

                System.err.println(p2);
                int xyz[] = p2.getWorldXYZCords(false);
                Generator.Generate(new GenerationDoor(xyz[0], xyz[1], xyz[2], p2.height, p2.length, Material.AIR, null, true, true));

                //create new portal
                //  xyz=p2.getWorldXYZCords(true);
                //  Generator.Queue(new GenerationDoor(xyz[0],xyz[1],xyz[2],p1.height,p1.length, Blocks.ColoredBlock, Material.AIR,true,true));
                try{
                    if (SkyBoxWorld.getSkyBox(k[0], k[1]) == null) {
                        SkyBoxWorld.RegisterNewSkyBox(bb);//new Earth(k[0], k[1]));
                    }

                    setUpNewPortalWorldData(boxCoords, p2);
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getPlayer().sendMessage("An error occurred please report this to your server admin immediately");
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), new ItemStack(b.getType(),p2.count(), (short) b.getData()));
                }
            } else {
                Generator.FillEffect(b.getX(), b.getY() + 2, b.getZ(), b.getX(), b.getY() + 2, b.getZ(), 0.5f, 0, 0.5f, Particle.BARRIER);
            }
        }


    }

    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent event){
        event.setCancelled(true);
        spawnFireworks(event.getLocation(),5,Color.RED);
    }

    public static void spawnFireworks(Location location, int amount,Color c){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(0);
        fwm.addEffect(FireworkEffect.builder().withColor(c).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }

    public static void setUpNewPortalWorldData(int[] boxCoords,Portal p){

        Portal p2=p.generateCounterPortal();

        PortalConnection connect = new PortalConnection(p,p2);
        //System.err.println(boxCoords[0]+", "+boxCoords[1]);
        SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).addPortal(connect);

        //System.err.println(p2);

        int counterxz[] = p2.getSkyboxXZCords();

        //System.err.println(counterxz[0]);

        SkyBox counterBox=SkyBoxWorld.getSkyBox(counterxz[0],counterxz[1]);

        //System.err.println(counterBox);

        if(counterBox!=null){//exists
            counterBox.addPortal(connect);
        }else{//doesnt exist
            counterBox = new SkyBox(counterxz[0],counterxz[1]);
            SkyBoxWorld.RegisterNewSkyBox(counterBox);
            counterBox.addPortal(connect);
        }
        connect.Generate();
        //todo find some way to run this after skybox is fully generated, perhaps pregenerate then generate all that at once.
        connect.state=1;
        connect.Generate();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Block b=event.getBlock();
        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX()+1,b.getZ()+1);
        int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(boxCoords[0],boxCoords[1]);

        if(xz[0]-1==b.getX()||xz[0]+SkyBoxWorld.TileSize[0]+1==b.getX()||xz[1]-1==b.getZ()||xz[1]+SkyBoxWorld.TileSize[1]+1==b.getZ()) {
            int x=b.getX()-xz[0];
            int z=b.getZ()-xz[1];
            Character side=null;
            if(x==-1){
                side='w';
            }else if(x==19){
                side='e';
            }else if(z==-1){
                side='n';
            }else if(z==19){
                side='s';
            }

            if(PortalHandler.isOverlap(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]),new Point(b.getX()-xz[0],b.getY()),side))
                return;
            if(PortalHandler.isOverlap(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]),new Point(b.getZ()-xz[1],b.getY()),side))
                return;


            PortalConnection p1 = PortalHandler.getOverlappingPortal(SkyBoxWorld.getSkyBox(boxCoords[0], boxCoords[1]), new Point(b.getX() - xz[0], b.getY()), side, 0);
            PortalConnection p2 = PortalHandler.getOverlappingPortal(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]),new Point(b.getZ()-xz[1],b.getY()),side,0);
            if(p1!=null) {
                MenuHandler.sendMenu(event.getPlayer(),new DoorMenu(p1.state==1,p1.p1.box,p1.PortalConnectionID));
            }
            if(p2!=null) {
                MenuHandler.sendMenu(event.getPlayer(),new DoorMenu(p2.state==1,p2.p1.box,p2.PortalConnectionID));
            }

            event.setCancelled(true);
        }

        if(b.getY()==SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).maxY||b.getY()==SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).minY){
            //todo add conditions here when removing entire walls
            event.setCancelled(true);
        }

        for(Material m:SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).protectedMaterials) {
            if (b.getType().equals(m)) {
                event.setCancelled(true);
                return;
            }
        }
    }

   /* @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){

        Player player = event.getPlayer();
        getServer().broadcastMessage(event.getBlockPlaced().getType().name());
        if (player.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
            player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
        }
    }*/

}

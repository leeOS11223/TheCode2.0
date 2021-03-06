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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import thecode2.Files.ConfigHandler;
import thecode2.Generator.GenerationObjects.GenerationBlock;
import thecode2.Generator.GenerationObjects.GenerationDoor;
import thecode2.Generator.Generator;
import thecode2.Grid.SkyBoxWorld;
import thecode2.Menu.MenuHandler;
import thecode2.Menu.Menus.DoorMenu;
import thecode2.Modded.Blocks;
import thecode2.Players.PlayerData;
import thecode2.Players.PlayerHandler;
import thecode2.Portals.Portal;
import thecode2.Portals.PortalConnection;
import thecode2.Portals.PortalHandler;
import thecode2.SkyBox.Dungeons.ClassicDungeon.ClassicDungeon;
import thecode2.SkyBox.Dungeons.DungeonHandler;
import thecode2.SkyBox.Dungeons.Loot.LootGenerator;
import thecode2.SkyBox.SkyBox;
import thecode2.SkyBox.SkyBoxHandler;
import thecode2.SkyBox.SkyBoxes.*;

import java.awt.*;
import java.util.ArrayList;

public final class main extends JavaPlugin implements Listener {

    private int count=0;

    public static int RandomNumber(int min, int max) {
        return (int) ((Math.random()*(max-min))+min);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic
        //TODO load generator que from file

        //ConfigHandler.writeTemplate();

        SkyBoxHandler.registerSkyboxToPortal(new Earth(0,0), Material.SMOOTH_BRICK, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Wasteland(0,0), Material.COBBLESTONE, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Desert(0,0), Material.SANDSTONE, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Nether(0,0), Material.OBSIDIAN, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new ClassicDungeon(0,0), Material.BRICK, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(null, Material.GLASS, Material.FIRE);
        SkyBoxHandler.registerSkyboxToPortal(new Cave(0,0), Material.COAL_BLOCK, Material.FIRE);

        Generator.world=getServer().getWorld("world");
        getLogger().info("set generator's world to: "+ Generator.world.getName());

        getLogger().info("Loading Players.");
        //load players
        PlayerHandler.setup();
        getLogger().info("Done.\n");

        getLogger().info("Loading Modded Blocks.");
        //load modded blocks
        Blocks.setup();
        getLogger().info("Done.\n");

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

        getLogger().info("Configuring Loot Tables.");
        //configure Loot Tables
        LootGenerator.setup();
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
        SaveAll();
    }

    public void update(){
        Generator.HandleQueue();
        //getServer().broadcastMessage("boop!");

        count++;
        if(count>5000){
            count=0;
            SaveAll();
        }
    }

    public void SaveAll(){
        getLogger().info("Saving The Code 2!");
        //save worlds
        SkyBoxWorld.saveAll();

        //save players
        PlayerHandler.saveAll();
        getLogger().info("Done!");
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(e.toWeatherState());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("basic")&&sender.isOp()) {
            Location loc=((Player)sender).getLocation();
            int[] xz= SkyBoxWorld.getSkyBoxCoordsFromWorld(loc.getBlockX(),loc.getBlockZ());
            getServer().broadcastMessage(xz[0]+", "+xz[1]);

            Block block = Generator.world.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ());

            getServer().broadcastMessage(String.valueOf(block.getTypeId())+":"+String.valueOf(block.getData()));
            SkyBox w=new Wasteland(xz[0],xz[1]);
            w.setOwner(((Player) sender).getPlayer().getUniqueId().toString());
            SkyBoxWorld.RegisterNewSkyBox(w);
            return true;
        }else if (cmd.getName().equalsIgnoreCase("startWorld")&&sender.isOp()) {
            String name=args[0];

            for(Player p:getServer().getOnlinePlayers()){
                if(name.equals(p.getDisplayName())){
                    Location loc=((Player)sender).getLocation();
                    int[] xz= SkyBoxWorld.getSkyBoxCoordsFromWorld(loc.getBlockX(),loc.getBlockZ());

                    SkyBox w=new Wasteland(xz[0],xz[1]);
                    w.setOwner(p.getUniqueId().toString());
                    SkyBoxWorld.RegisterNewSkyBox(w);
                    PlayerHandler.players.add(new PlayerData(p.getUniqueId().toString(),xz[0],xz[1]));
                    p.setHealth(0);
                    p.kickPlayer("World Generating...");
                    sender.sendMessage("Done.");
                }
            }

            return true;
        }else if (cmd.getName().equalsIgnoreCase("close")) {
            int x=Integer.parseInt(args[0]);
            int z=Integer.parseInt(args[1]);
            int portalID=Integer.parseInt(args[2]);
            PortalHandler.requestPortalState((Player)sender,x,z,portalID,0);
            return true;
        }else if (cmd.getName().equalsIgnoreCase("open")) {
            int x=Integer.parseInt(args[0]);
            int z=Integer.parseInt(args[1]);
            int portalID=Integer.parseInt(args[2]);
            //PortalHandler.setPortalState(x,z,portalID,1);
            PortalHandler.requestPortalState((Player)sender,x,z,portalID,1);
            return true;
        }else if (cmd.getName().equalsIgnoreCase("save")) {
            if(!sender.isOp()){
                sender.sendMessage("Dont do that! :)");
                return true;
            }
            SaveAll();
            sender.sendMessage("Saved!");
            return true;
        }else if (cmd.getName().equalsIgnoreCase("destroy")&&sender.isOp()) {
            int x = Integer.parseInt(args[0]);
            int z = Integer.parseInt(args[1]);
            int portalID = Integer.parseInt(args[2]);
            if(PortalHandler.canDeletePortal(x, z, portalID,(Player)sender)) {
                PortalHandler.setPortalState(x, z, portalID, 2);
                PortalHandler.deletePortal(x, z, portalID);
                ConfigHandler.deletePortal(portalID);
            }
            return true;
        }else if (cmd.getName().equalsIgnoreCase("regenerate")&&sender.isOp()) {
            Location loc=((Player)sender).getLocation();
            int[] xz= SkyBoxWorld.getSkyBoxCoordsFromWorld(loc.getBlockX(),loc.getBlockZ());
            SkyBox b=SkyBoxWorld.getSkyBox(xz[0],xz[1]);
            SkyBox b2 = b.clone(xz[0],xz[1]);
            //SkyBoxWorld.DestroySkyBox(b);
            Generator.deleteSkyBoxNow(b);
            b.GenerationObjects=b2.GenerationObjects;
            //SkyBoxWorld.RegisterNewSkyBox(b);
            Generator.generateSkyBox(b2);
            for(PortalConnection p:b2.portalsConnections){
                p.Generate();
            }
            return true;

        }else if (cmd.getName().equalsIgnoreCase("test")&&sender.isOp()) {

            //LootGenerator.Generate(((Player)sender).getLocation(),Integer.parseInt(args[0]));

            Location loc=((Player)sender).getLocation();
            Material m=Material.getMaterial(Integer.valueOf(args[0]));
            if(m==null){
                sender.sendMessage("ERROR");
                return true;
            }
            Generator.Queue(new GenerationBlock((int)loc.getX(),(int)loc.getY(),(int)loc.getZ(),m));

            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.STICK&&player.isOp()) {
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
        }else
        if (player.getItemInHand().getType() == Material.FEATHER&&player.isOp()) {
            Location loc=player.getLocation();
            Block block = Generator.world.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ());

            player.sendTitle(block.getTypeId()+":"+String.valueOf(block.getData()),"",1,5,1);
        }
    }

    @EventHandler
    public void onBlockPlaced2(BlockPlaceEvent event){
        Block b=event.getBlock();
        b=Generator.world.getBlockAt(b.getX(),b.getY(),b.getZ());
        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX(),b.getZ());

        int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(boxCoords[0],boxCoords[1]);
        if(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1])==null)
            return;

        for(Material m:SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).protectedMaterials) {
            if (b.getType().equals(m)) {
                event.setCancelled(true);
                return;
            }
        }

        /*if(event.getBlock().getType().equals(Material.TORCH)){
            for(int x=-10;x<10;x++){
                for(int z=-10;z<10;z++){
                    for(int y=-10;y<10;y++){
                        Block b2= Generator.world.getBlockAt(b.getX()+x,b.getY()+y,b.getZ()+z);
                        if(b2.getType().equals(Material.AIR))
                            b2.setType(Material.FIRE);
                    }
                }
            }
        }*/

       /* if(b.getType().equals(Material.TORCH)){
            Generator.Queue(new GenerationCircle(b.getX(),b.getY(),b.getZ(),3,Material.REDSTONE_TORCH_ON));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Generator.Queue(new GenerationPool(b.getX(),b.getY(),b.getZ(),7,Material.WATER,Material.AIR));
        }*/
    }

    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent event){
        Player player=event.getPlayer();
        PlayerData pd=PlayerHandler.getPlayer(player);
        Point sl=pd.SpawnBox;
        SkyBox skybox=SkyBoxWorld.getSkyBox(sl.x,sl.y);

        int xz[]=skybox.getWorldCoordsFromSkyBox();

        event.setRespawnLocation(new Location(Generator.world,xz[0]+SkyBoxWorld.TileSize[0]/2,SkyBoxWorld.spawnHeight,xz[1]+SkyBoxWorld.TileSize[1]/2));
    }

    @EventHandler
    public void onBlockPlaced(BlockIgniteEvent event){
        if(event.getPlayer()==null)
            return;
        Block b=event.getBlock();
        b=Generator.world.getBlockAt(b.getX(),b.getY()-1,b.getZ());

        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX(),b.getZ());

        int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(boxCoords[0],boxCoords[1]);

        if(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1])==null)
            return;

        if (xz[0] == b.getX() || xz[0] + SkyBoxWorld.TileSize[0] == b.getX() || xz[1] == b.getZ() || xz[1] + SkyBoxWorld.TileSize[1] == b.getZ()) {
            if (SkyBoxWorld.getSkyBox(boxCoords[0], boxCoords[1]) == null){
                return;
            }

            SkyBox box=SkyBoxWorld.getSkyBox(boxCoords[0], boxCoords[1]);

            Portal p1 = PortalHandler.CheckPortalFrame(b.getX(), b.getY(), b.getZ(), false, b.getType(), b.getData());
            Portal p2 = PortalHandler.CheckPortalFrame(b.getX(), b.getY(), b.getZ(), true, b.getType(), b.getData());
            Material frame=Generator.world.getBlockAt(b.getX(),b.getY(),b.getZ()).getType();
            if (p1 != null&&!PortalHandler.isOverlap(box,p1)) {//x
                if(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).getOwner()==null) {
                    event.getPlayer().sendMessage("THIS WORLD HAS ISSUES REPORT THIS TO LEE NOW THE CODE 2 IS BROKEN!?!?! *PANIC*" +
                            ". ERROR CODE: \"NO OWNER!?!\", WORLD LOCATION: "+boxCoords[0]+", "+boxCoords[1]);
                    return;
                }else if(!SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).getOwner().equals(event.getPlayer().getUniqueId().toString())){
                    event.getPlayer().sendMessage("You do not own this world.");

                    return;
                }

                if(SkyBoxWorld.getSkyBox(p1.getConnectedSkyboxXZCords()[0],p1.getConnectedSkyboxXZCords()[1])!=null&&!b.getType().equals(Material.GLASS)){
                    return;
                }

                int k[] = p1.getConnectedSkyboxXZCords();
                SkyBox bb=SkyBoxHandler.getSkybox(frame,Material.FIRE,k[0], k[1]);
                if(bb==null){
                    if(SkyBoxWorld.getSkyBox(p1.getConnectedSkyboxXZCords()[0],p1.getConnectedSkyboxXZCords()[1])==null)
                        return;
                    setUpNewPortalWorldData(boxCoords, p1);
                    int xyz[] = p1.getWorldXYZCords(false);
                    Generator.Generate(new GenerationDoor(xyz[0], xyz[1], xyz[2], p1.height, p1.length, Material.AIR, null, false, true));
                    return;
                }
                bb.portalsConnections=new ArrayList<>();
                bb.setOwner(event.getPlayer().getUniqueId().toString());

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
                if(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).getOwner()==null) {
                    event.getPlayer().sendMessage("THIS WORLD HAS ISSUES REPORT THIS TO LEE NOW THE CODE 2 IS BROKEN!?!?! *PANIC*" +
                            ". ERROR CODE: \"NO OWNER!?!\", WORLD LOCATION: "+boxCoords[0]+", "+boxCoords[1]);
                    return;
                }else if(!SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1]).getOwner().equals(event.getPlayer().getUniqueId().toString())){
                    event.getPlayer().sendMessage("You do not own this world.");

                    return;
                }

                if(SkyBoxWorld.getSkyBox(p2.getConnectedSkyboxXZCords()[0],p2.getConnectedSkyboxXZCords()[1])!=null&&!b.getType().equals(Material.GLASS)){
                    return;
                }

                int k[] = p2.getConnectedSkyboxXZCords();
                SkyBox bb=SkyBoxHandler.getSkybox(frame,Material.FIRE,k[0], k[1]);
                if(bb==null){
                    if(SkyBoxWorld.getSkyBox(p2.getConnectedSkyboxXZCords()[0],p2.getConnectedSkyboxXZCords()[1])==null)
                        return;
                    setUpNewPortalWorldData(boxCoords, p2);
                    int xyz[] = p2.getWorldXYZCords(false);
                    Generator.Generate(new GenerationDoor(xyz[0], xyz[1], xyz[2], p2.height, p2.length, Material.AIR, null, true, true));
                    return;
                }
                bb.portalsConnections=new ArrayList<>();
                bb.setOwner(event.getPlayer().getUniqueId().toString());

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
    public void PlayerMoveEvent(PlayerMoveEvent event){
        Player player=event.getPlayer();

        if(!player.getLocation().getWorld().equals(Generator.world)){
            player.sendTitle("You Shouldn't Be Here...","Should you... :)",10,60,10);
            //player.setHealth(0);
            PlayerData pd=PlayerHandler.getPlayer(player);
            Point sl=pd.SpawnBox;
            SkyBox skybox=SkyBoxWorld.getSkyBox(sl.x,sl.y);

            int xz[]=skybox.getWorldCoordsFromSkyBox();
            player.teleport(new Location(Generator.world,xz[0]+SkyBoxWorld.TileSize[0]/2,SkyBoxWorld.spawnHeight,xz[1]+SkyBoxWorld.TileSize[1]/2));
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
        connect.p1.state=0;
        connect.p2.state=0;

        connect.Generate();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Block b=event.getBlock();
        int[] boxCoords=SkyBoxWorld.getSkyBoxCoordsFromWorld(b.getX()+1,b.getZ()+1);
        int[] xz=SkyBoxWorld.getWorldCoordsFromSkyBox(boxCoords[0],boxCoords[1]);

        if(SkyBoxWorld.getSkyBox(boxCoords[0],boxCoords[1])==null)
            return;

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
                MenuHandler.sendMenu(event.getPlayer(),new DoorMenu(p1.p1.box,p1.PortalConnectionID));
            }
            if(p2!=null) {
                MenuHandler.sendMenu(event.getPlayer(),new DoorMenu(p2.p1.box,p2.PortalConnectionID));
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

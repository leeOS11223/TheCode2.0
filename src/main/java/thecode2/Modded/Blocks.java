package thecode2.Modded;

import org.bukkit.Material;

import java.util.ArrayList;

public class Blocks {

    public static Material ColoredBlock = Material.getMaterial(365);
    public static Material ColoredTransparentBlock = Material.getMaterial(386);

    public static ArrayList<ModdedBlock> blocks = new ArrayList<>();

    public static void setup(){
        blocks.add(new ModdedBlock("osmium",259,0));
        blocks.add(new ModdedBlock("copper",259,1));
        blocks.add(new ModdedBlock("tin",259,2));
        /*blocks.add(new ModdedBlock("ruby",473,0));
        blocks.add(new ModdedBlock("sapphire",473,1));
        blocks.add(new ModdedBlock("peridot",473,2));
        blocks.add(new ModdedBlock("copper2",473,3));
        blocks.add(new ModdedBlock("tin2",473,4));
        blocks.add(new ModdedBlock("silver",473,5));
        blocks.add(new ModdedBlock("electrotine",473,6));
        //blocks.add(new ModdedBlock("cinnabar",697,0));
        blocks.add(new ModdedBlock("cinnabar",473,0));*/
    }

    public static Material getMaterial(String bb){
        for(ModdedBlock b:blocks){
            System.err.println(b.block);
            if(b.block.equals(bb))
                System.err.println(b.id);
                return Material.getMaterial(b.id);
        }
        return null;
    }

    public static int getMeta(String bb) {
        for(ModdedBlock b:blocks) {
            if(b.block.equals(bb))
                return b.meta;
        }
        return 0;
    }

    public static int getBlockData(String colour){
        switch (colour){
            case "white":
                return 0;
            case "orange":
                return 1;
            case "magenta":
                return 2;
            case "light-blue":
                return 3;
            case "yellow":
                return 4;
            case "lime":
                return 5;
            case "pink":
                return 6;
            case "gray":
                return 7;
            case "light-gray":
                return 8;
            case "cyan":
                return 9;
            case "purple":
                return 10;
            case "blue":
                return 11;
            case "brown":
                return 12;
            case "green":
                return 13;
            case "red":
                return 14;
            case "black":
                return 15;
        }
        return 0;
    }

}

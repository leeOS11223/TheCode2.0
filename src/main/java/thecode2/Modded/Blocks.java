package thecode2.Modded;

import org.bukkit.Material;

public class Blocks {

    public static Material ColoredBlock = Material.getMaterial(365);
    public static Material ColoredTransparentBlock = Material.getMaterial(386);

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

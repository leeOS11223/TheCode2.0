package thecode2.SkyBox.Dungeons;

import java.util.ArrayList;

public class WeaponSet {

    public int level=0;
    public ArrayList<String> Helemts=new ArrayList<>();
    public ArrayList<String> Leggins=new ArrayList<>();
    public ArrayList<String> Chestplates=new ArrayList<>();
    public ArrayList<String> Boots=new ArrayList<>();
    public ArrayList<String> LeftHandedWeapons=new ArrayList<>();
    public ArrayList<String> RightHandedWeapons=new ArrayList<>();
    public ArrayList<Effect> Effects=new ArrayList<>();
    public boolean oneHand=false;

    public WeaponSet(int level){
        this.level=level;
    }

    public void addWeapon(String weapon) {
        LeftHandedWeapons.add(weapon);
        RightHandedWeapons.add(weapon);
    }
}

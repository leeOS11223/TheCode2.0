package thecode2.SkyBox.Dungeons;

import java.util.ArrayList;

public class DungeonHandler {

    public static ArrayList<WeaponSet> weaponSets=new ArrayList<>();

    public static void SetUp(){
        WeaponSet zero = new WeaponSet(0);
        weaponSets.add(zero);

        WeaponSet one = new WeaponSet(1);
        one.addWeapon("stick");
        one.oneHand=true;
        weaponSets.add(one);

        WeaponSet two = new WeaponSet(2);
        two.Helemts.add("leather_helmet");
        two.Boots.add("leather_boots");
        two.Chestplates.add("leather_chestplate");
        two.Leggins.add("leather_leggings");
        two.addWeapon("wooden_sword");
        two.addWeapon("wooden_axe");
        two.addWeapon("wooden_shovel");
        two.addWeapon("stone_hoe");
        two.oneHand=true;
        weaponSets.add(two);

        WeaponSet three = new WeaponSet(3);
        three.addWeapon("stone_sword");
        three.addWeapon("stone_axe");
        three.addWeapon("stone_shovel");
        three.addWeapon("iron_hoe");
        three.oneHand=true;
        weaponSets.add(three);

        WeaponSet five = new WeaponSet(5);
        five.Helemts.add("chainmail_helmet");
        five.Boots.add("chainmail_boots");
        five.Chestplates.add("chainmail_chestplate");
        five.Leggins.add("chainmail_leggings");
        five.addWeapon("iron_sword");
        five.addWeapon("iron_axe");
        five.addWeapon("iron_shovel");
        five.addWeapon("gold_hoe");
        five.oneHand=true;
        weaponSets.add(five);

    }

    public static Monster GetFairMonster(int level,int levelRange,String type){
        Monster m =new Monster(type+" - level "+level,type.toLowerCase());

        boolean canHavebow=false;
        if(type.toLowerCase()=="skeleton"){
            canHavebow=true;
        }

        int helmet = level-(int)(Math.random()*(levelRange));
        int boots = level-(int)(Math.random()*(levelRange));
        int chest = level-(int)(Math.random()*(levelRange));
        int legs = level-(int)(Math.random()*(levelRange));
        int right = level-(int)(Math.random()*(levelRange));
        int left = level-(int)(Math.random()*(levelRange));

        for(WeaponSet w:weaponSets){
            if(w.level<=helmet&&w.Helemts.size()>0){
                String h = w.Helemts.get((int)(Math.random()*(w.Helemts.size()-1)));
                m.helmet=h;
            }
            if(w.level<=boots&&w.Boots.size()>0){
                String h = w.Boots.get((int)(Math.random()*(w.Boots.size()-1)));
                m.boots=h;
            }
            if(w.level<=legs&&w.Leggins.size()>0){
                String h = w.Leggins.get((int)(Math.random()*(w.Leggins.size()-1)));
                m.legs=h;
            }
            if(w.level<=chest&&w.Chestplates.size()>0){
                String h = w.Chestplates.get((int)(Math.random()*(w.Chestplates.size()-1)));
                m.chestP=h;
            }
            if(w.level<=right&&w.RightHandedWeapons.size()>0){
                String h = w.RightHandedWeapons.get((int)(Math.random()*(w.RightHandedWeapons.size()-1)));
                m.righthand=h;
            }
            if(!w.oneHand&&w.level<=left&&w.LeftHandedWeapons.size()>0){
                String h = w.LeftHandedWeapons.get((int)(Math.random()*(w.LeftHandedWeapons.size()-1)));
                m.lefthand=h;
            }
        }

        return m;
    }

}

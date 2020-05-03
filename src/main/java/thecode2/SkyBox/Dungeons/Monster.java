package thecode2.SkyBox.Dungeons;

import thecode2.SkyBox.SkyBox;

import java.util.ArrayList;

public class Monster {

    public String mob="";
    public boolean canPickupItems=false;
    public String name;

    public String boots=null;
    public String legs=null;
    public String chestP=null;
    public String helmet=null;
    public String lefthand=null;
    public String righthand=null;

    public ArrayList<Effect> effects = new ArrayList<>();

    public void AddEffect(Effect effect){
        effects.add(effect);
    }

    public String getEffectsasJson(){
        String effect="";

        for(Effect e:effects){
            effect+=e.getJson();
            effect+=",";
        }
        effect=effect.substring(0,effect.length()-2);

        return effect;
    }

    public Monster(String name, String mob) {
        this.mob=mob;
        this.name=name;
    }

    public Monster(String name, String mob,boolean canPickupItems,String lefthand,String righthand,String boots,String legs,String chest,String helmet) {
        this.mob=mob;
        this.name=name;
        this.helmet=helmet;
        this.lefthand=lefthand;
        this.righthand=righthand;
        this.boots=boots;
        this.legs=legs;
        this.chestP=chest;
        this.canPickupItems=canPickupItems;
    }

    public void setEquipment(String lefthand,String righthand,String boots,String legs,String chest,String helmet){
        this.helmet=helmet;
        this.lefthand=lefthand;
        this.righthand=righthand;
        this.boots=boots;
        this.legs=legs;
        this.chestP=chest;
    }

}

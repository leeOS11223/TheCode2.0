package thecode2.SkyBox.Dungeons;

public class Effect {

    public int Id=0;
    public int Amplifier=0;
    public int Duration=0;

    public Effect(int Id, int Amplifier, int Duration){
        this.Id=Id;
        this.Amplifier=Amplifier;
        this.Duration=Duration;
    }

    public String getJson(){
        return "{Id:"+Id+",Amplifier:"+Amplifier+",Duration:"+Duration+"}";
    }

}

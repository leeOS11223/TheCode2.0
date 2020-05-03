package thecode2.Menu;

public class MenuOption {

    public String Text="null";
    public String ClickCommand=null;
    public String HoverText=null;
    public String colour="black";

    public MenuOption(String text){
        Text=text;
    }

    public MenuOption(String text,String ClickCommand){
        Text=text;
        this.ClickCommand=ClickCommand;
    }

    public MenuOption(String text,String ClickCommand,String HoverText){
        Text=text;
        this.ClickCommand=ClickCommand;
        this.HoverText=HoverText;
    }

    public MenuOption(String text,String colour,String ClickCommand,String HoverText){
        Text=text;
        this.ClickCommand=ClickCommand;
        this.HoverText=HoverText;
        this.colour=colour;
    }

    public String generateCommandPart(){
        String out= "{\"text\":\"["+Text+"] \",\"bold\":true,\"color\":\""+colour+"\"," +
                "\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/"+ClickCommand+"\"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\""+HoverText+"\"}]}}";

        return out;
    }


}

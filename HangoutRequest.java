import java.util.ArrayList;

public class HangoutRequest {

    //Instance Variables
    private String name;
    private String location;
    private int hour;
    private int minute;
    private ArrayList<String> tags;
    private ArrayList<String> users;

    public HangoutRequest(String name, String location, int hour, int minute, ArrayList<String> tags, ArrayList<String> users){
        this.name=name;
        this.location=location;
        this.hour=hour;
        this.minute=minute;
        this.tags=tags;
        this.users=users;
    }

    public String getName(){
        return this.name;
    }

    public String get(){
        return this.name;
    }
    
}

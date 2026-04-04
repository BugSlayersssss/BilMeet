import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class HangoutRequest {

    //Instance Variables
    private String name;
    private String requestID;
    private String location;
    private LocalDate date;
    private String startTime;
    private String endTimeS;
    private LocalTime endTime;
    private User organiser;
    private boolean status;
    private int quota;
    private List<String> tags;
    private List<User> participants;

    public HangoutRequest(String name, String location, int hourStart, int minuteStart, int hourEnd, int minuteEnd, List<String> tags, List<User> participants,int quota, User organiser){
        this.name=name;
        this.location=location;
        this.startTime=hourStart + ":"+ minuteStart;
        this.endTimeS=hourEnd + ":" + minuteEnd;
        this.tags=tags;
        this.participants.add(organiser);
        this.tags=tags;
        this.participants=participants;
        this.quota=quota;
        this.organiser=organiser;   
    }

    public void addParticipant(User user){
        this.participants.add(user);
    }

    public void removeParticipant(User user){
        this.participants.remove(user);
    }
    
    public void isFull(){
        if(this.participants.size()>=this.quota){
            this.status=true;
        }
    }

    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location;
    }

    public String getStartTime(){
        return this.startTime;
    }   

    public String getEndTime(){
        return this.endTimeS;
    }

    public List<String> getTags(){
        return this.tags;
    }

    public List<User> getParticipants(){
        return this.participants;
    }
    
    public boolean getStatus(){
        return this.status;
    }
 
}

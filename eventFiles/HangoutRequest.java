import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList; // Listeyi başlatmak için gerekli

public class HangoutRequest {

    private String name;
    private String requestID;
    private String location;
    private LocalDate date;
    private String startTime;
    private String endTimeS;
    private User organiser;
    private boolean status; 
    private int quota;
    private List<String> tags;
    private List<User> participants;

    public HangoutRequest(String name, String location, int hourStart, int minuteStart, int hourEnd, int minuteEnd, List<String> tags, List<User> participants, int quota, User organiser) {
        this.name = name;
        this.location = location;
        
        this.startTime = String.format("%02d:%02d", hourStart, minuteStart);
        this.endTimeS = String.format("%02d:%02d", hourEnd, minuteEnd);
        
        this.tags = tags;
        this.quota = quota;
        this.organiser = organiser;
        this.status = false;

        this.participants = new ArrayList<>();
        

        if (participants != null && !participants.isEmpty()) {
            this.participants.addAll(participants);
        }
 
        if (!this.participants.contains(organiser)) {
            this.participants.add(organiser);
        }
    }

    public void addParticipant(User user) {
        if (!isFull() && !contains(user)) {
            this.participants.add(user);
        }
    }

    public void removeParticipant(User user) {
        this.participants.remove(user);
      
        if (this.participants.size() < this.quota) {
            this.status = false;
        }
    }
    
    public boolean isFull() {
        
        if (this.participants.size() >= this.quota) {
            this.status = true;
        } else {
            this.status = false;
        }
        return this.status;
    }


    public String getName() { return this.name; }
    public String getLocation() { return this.location; }
    public String getStartTime() { return this.startTime; }
    public String getEndTime() { return this.endTimeS; }
    public List<String> getTags() { return this.tags; }
    public List<User> getParticipants() { return this.participants; }
    public int getQuota() { return this.quota; }
    public User getOrganiser() { return this.organiser; }
    public boolean getStatus() { return isFull(); }

    public boolean contains(User user) {
        if (this.participants == null) return false;
        return this.participants.contains(user);
    }
}

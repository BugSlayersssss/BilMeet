import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HangoutRequest {
    private String name, location, startTime, endTimeS;
    private User organiser;
    private int quota;
    private List<String> tags;
    private List<User> participants;

    private int startHour;
    private int endHour;
    private int day; 

    public HangoutRequest(String name, String location, int hS, int mS, int hE, int mE, List<String> tags, List<User> participants, int quota, User organiser) {
        this.name = name;
        this.location = location;
        this.startHour = hS;
        this.endHour = hE;
        this.startTime = String.format("%02d:%02d", hS, mS);
        this.endTimeS = String.format("%02d:%02d", hE, mE);
        this.tags = tags;
        this.quota = quota;
        this.organiser = organiser;
    
        this.day = java.time.LocalDate.now().getDayOfWeek().getValue(); 

        this.participants = new ArrayList<>(); 
        if (participants != null) this.participants.addAll(participants);
        if (organiser != null && !this.participants.contains(organiser)) this.participants.add(organiser);
    }

    public boolean isExpired() {
        int currentDay = java.time.LocalDate.now().getDayOfWeek().getValue();
        LocalTime now = LocalTime.now();

        if (currentDay > this.day) return true;
        if (currentDay == this.day && now.getHour() >= this.endHour) return true;

        return false;
    }

    public void addParticipant(User user) { if (!isFull()) this.participants.add(user); }
    public void removeParticipant(User user) { this.participants.remove(user); }
    public boolean contains(User user) { return this.participants.contains(user); }
    public boolean isFull() { return this.participants.size() >= this.quota; }
    
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTimeS; }
    public int getStartHour() { return startHour; }
    public int getEndHour() { return endHour; }
    public int getDay() { return day; }
    public List<String> getTags() { return tags; }
    public int getQuota() { return quota; }
    public User getOrganiser() { return organiser; }
    public List<User> getParticipants() { return participants; }
}

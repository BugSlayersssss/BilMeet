import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {
    private static EventManager instance;
    private List<HangoutRequest> events;

    private EventManager() {
        this.events = new ArrayList<>();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(HangoutRequest event) {
        events.add(event);
    }

    public List<HangoutRequest> getAllEvents() {
        return events;
    }

    public void handleJoinEvent(HangoutRequest event, User user) {
        if (event != null && user != null) {
            event.addParticipant(user);
        }
    }

    public void handleLeaveEvent(HangoutRequest event, User user) {
        if (event != null && user != null) {
            event.removeParticipant(user);
        }
    }

    public List<HangoutRequest> handleFilterByTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return getAllEvents();
        }
        
        return events.stream()
                .filter(e -> e.getTags() != null && 
                        e.getTags().stream().anyMatch(t -> t.equalsIgnoreCase(tag)))
                .collect(Collectors.toList());
    }
     public double calculateEventSocialScore(HangoutRequest event, User currentUser) {
        List<User> participants = event.getParticipants();
        if (participants == null || participants.isEmpty()) return 0.0;

        MatchAlgorithm matchAlgorithm = new MatchAlgorithm();
        int totalScore = 0;
        for (User p : participants) {
            totalScore += matchAlgorithm.calculateMatchScore(currentUser, p);
        }
        return (double) totalScore / participants.size();
    }

    public List<HangoutRequest> getRecommendedEvents(User currentUser) {
        List<HangoutRequest> sortedEvents = new ArrayList<>(events);
        sortedEvents.sort((e1, e2) -> {
            double score1 = calculateEventSocialScore(e1, currentUser);
            double score2 = calculateEventSocialScore(e2, currentUser);
            return Double.compare(score2, score1);
        });
        return sortedEvents;
    }
}

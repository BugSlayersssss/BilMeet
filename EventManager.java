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
        return events.stream()
                .filter(e -> e.getTags().contains(tag))
                .collect(Collectors.toList());
    }
}
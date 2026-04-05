import java.util.List;
import java.util.stream.Collectors;

public class MatchAlgorithm {
    
    public int calculateMatchScore(User currentUser, User otherUser) {
        int score = 0;
        for (String interest : currentUser.getUserInterests()) {
            if (otherUser.getUserInterests().contains(interest)) {
                score++;
            }
        }
        return score;
    }                                                                                                                                                                                                                              public List<User> getOrderedList(User currentUser, List<User> allUsers) {
    List<User> others = allUsers.stream()
        .filter(u -> !u.equals(currentUser))
        .collect(Collectors.toList());

    others.sort((u1, u2) -> {
        int score1 = calculateMatchScore(currentUser, u1);
        int score2 = calculateMatchScore(currentUser, u2);
        return Integer.compare(score2, score1);
    });
    return others;
    }

}

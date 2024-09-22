import java.util.HashSet;
import java.util.Set;

public class ReportSpamMessage {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> set = new HashSet<>();
        for (String ban : bannedWords) {
            set.add(ban);
        }
        int hit = 0;
        for (String m : message) {
            if (set.contains(m)) {
                ++hit;
            }
        }
        return hit >= 2;
    }
}

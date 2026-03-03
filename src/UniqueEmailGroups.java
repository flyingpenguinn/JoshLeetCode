import java.util.HashSet;
import java.util.Set;

public class UniqueEmailGroups {
    public int uniqueEmailGroups(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String em : emails) {
            String[] parts = em.split("@");
            String user = parts[0];
            user = user.replaceAll("\\.", "");
            if (user.contains("+")) {
                int idx = user.indexOf("+");
                user = user.substring(0, idx);
            }
            user = user.toLowerCase();
            String domain = parts[1];
            domain = domain.toLowerCase();
            String norm = user + "@" + domain;
            //   System.out.println(em+"->"+norm);
            set.add(norm);
        }
        return set.size();
    }
}

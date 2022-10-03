import java.util.HashSet;
import java.util.Set;

public class LongestUploadPrefix {
    class LUPrefix {
        private Set<Integer> uploaded = new HashSet<>();
        private int prefix = 0;

        public LUPrefix(int n) {

        }

        public void upload(int video) {
            uploaded.add(video);
            while (uploaded.contains(prefix + 1)) {
                ++prefix;
            }
        }

        public int longest() {
            return prefix;
        }
    }
}

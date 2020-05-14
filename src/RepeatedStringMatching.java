import java.util.HashMap;
import java.util.Map;

public class RepeatedStringMatching {
    public int repeatedStringMatch(String a, String b) {
        // we need to try at most these times because b is mostly of format (s2s1)*k if a is s1s2
        int times = ceiling(b.length(), a.length()) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append(a);
            if (sb.toString().indexOf(b) != -1) {
                return i;
            }
        }
        return -1;
    }

    private int ceiling(int b, int a) {
        return (b + a - 1) / a;
    }
}

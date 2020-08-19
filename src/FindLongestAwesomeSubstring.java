import java.util.HashMap;
import java.util.Map;

public class FindLongestAwesomeSubstring {
    // 1. as long as we can get only one odd or no odd, we can always swap the substring to get palindrome
    // 2. use subarray sum to get the longest <=1 odd substring. note we only need to note down the parity of the substring, 0 or 1
    // 3. encode the subarray results as numbers
    // 4. key: we can either flip no number, meaning a prefect match resulting in an all even substring, or just one odd
    // one odd means flip one digit: either currently odd, or even
    public int longestAwesome(String s) {
        int[] stat = new int[10];
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int max = 0;
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            int cind = s.charAt(i) - '0';
            // flip ith digit:
            // num ^= (1<<i)
            pre ^= (1 << cind);
            stat[cind] ^= 1;
            if (m.containsKey(pre)) {
                max = Math.max(max, i - m.get(pre));
            }
            for (int j = 0; j < 10; j++) {
                int changed = pre;
                changed ^= (1 << j);
                if (m.containsKey(changed)) {
                    max = Math.max(max, i - m.get(changed));
                }
            }
            m.putIfAbsent(pre, i); // must be put if absent to get the max len
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new FindLongestAwesomeSubstring().longestAwesome("32424"));
    }
}

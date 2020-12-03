import java.util.*;

/*
LC#691
We are given N different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

Example 1:

Input:

["with", "example", "science"], "thehat"
Output:

3
Explanation:

We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:

Input:

["notice", "possible"], "basicbasic"
Output:

-1
Explanation:

We can't form the target "basicbasic" from cutting letters from the given stickers.
Note:

stickers has length in the range [1, 50].
stickers consists of lowercase English words (without apostrophes).
target has length in the range [1, 15], and consists of lowercase English letters.
In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class StickersToSpellWords {
    // map<char, int> is actually string! so use string as key not a map
    private int[] tomap(String t) {
        int[] tmap = new int[26];
        for (int i = 0; i < t.length(); i++) {
            char tc = t.charAt(i);
            tmap[tc - 'a']++;
        }
        return tmap;
    }

    private String tostring(int[] tmap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int c = tmap[i];
            for (int j = 0; j < c; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> dp;
    private int Max = 1000;

    public int minStickers(String[] ss, String t) {

        int[][] ssmap = new int[ss.length][26];
        for (int i = 0; i < ss.length; i++) {
            ssmap[i] = tomap(ss[i]);
        }
        dp = new HashMap<>();
        int rt = domin(t, ssmap);
        return rt >= Max ? -1 : rt;
    }

    private int domin(String t, int[][] ssmap) {
        if (t.isEmpty()) {
            return 0;
        }
        Integer cached = dp.get(t);
        if (cached != null) {
            return cached;
        }
        int min = Max;
        for (int i = 0; i < ssmap.length; i++) {
            int[] tmap = tomap(t);
            for (int j = 0; j < 26; j++) {
                if (ssmap[i][j] > 0) {
                    int curs = ssmap[i][j];
                    tmap[j] = Math.max(0, tmap[j] - curs);
                }
            }
            String nt = tostring(tmap);
            if (!nt.equals(t)) {
                int cur = 1 + domin(nt, ssmap);
                min = Math.min(cur, min);
            }
        }
        dp.put(t, min);
        return min;
    }


    public static void main(String[] args) {
        String[] stickerlong = {"with", "example", "science"};
        System.out.println(new StickersToSpellWords().minStickers(stickerlong, "thehat"));

        String[] stickerlong3 = {"summer", "sky", "cent", "bright", "kill", "forest", "neighbor", "capital", "tall"};
        System.out.println(new StickersToSpellWords().minStickers(stickerlong3, "originalchair"));

        String[] stickerlong2 = {"city", "would", "feel", "effect", "cell", "paint"};
        System.out.println(new StickersToSpellWords().minStickers(stickerlong2, "putcat"));


        String[] stickerno = {"notice", "possible"};
        System.out.println(new StickersToSpellWords().minStickers(stickerno, "basicbasic"));
        String[] stickers = {"write", "their", "read", "quiet", "against", "down", "process", "check"};
        System.out.println(new StickersToSpellWords().minStickers(stickers, "togetherhand"));

    }
}

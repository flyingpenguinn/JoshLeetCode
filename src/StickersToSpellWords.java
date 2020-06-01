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
    // use string as dp key! maybe hard to use int /long as if we pick a sticker since there are duplicated chars
    // to represent a set of chars,  map<char, integer> is actually a string! it's also an int[] of len 26
    // so if we need to dp on it we use string otherwise use int[] + string both to do searching

    Map<String, Integer> dp = new HashMap<>();

    public int minStickers(String[] stickers, String t) {

        int rt = dom(t, stickers);
        return rt >= Max ? -1 : rt;
    }

    int Max = 1000000;


    private int dom(String t, String[] stickers) {
        if (t.isEmpty()) {
            return 0;
        }
        Integer ch = dp.get(t);
        if (ch != null) {
            return ch;
        }
        int min = Max;
        for (String s : stickers) {
            StringBuilder sb = new StringBuilder();
            int[] sm = getmap(s);
            boolean used = false;
            for (int i = 0; i < t.length(); i++) {
                if (sm[t.charAt(i) - 'a'] > 0) {
                    used = true;
                    sm[t.charAt(i) - 'a']--;
                } else {
                    sb.append((t.charAt(i)));
                }
            }
            if (!used) {
                continue;
            }
            String next = sb.toString();
            int later = dom(next, stickers);
            int cur = later + 1;
            min = Math.min(min, cur);
        }
        dp.put(t, min);
        return min;
    }

    private int[] getmap(String t) {
        int[] m = new int[26];
        for (int i = 0; i < t.length(); i++) {
            m[t.charAt(i) - 'a']++;
        }
        return m;
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

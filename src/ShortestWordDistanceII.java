import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#244
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistanceII {
    public static void main(String[] args) {
        String[] strs = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wd = new WordDistance(strs);
        System.out.println(wd.shortest("coding", "practice"));
        System.out.println(wd.shortest("makes", "coding"));
    }

}


class WordDistance {
    // using binary search. or use a merging algo
    Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            List<Integer> cm = map.get(words[i]);
            if (cm == null) {
                cm = new ArrayList<>();
                map.put(words[i], cm);
            }
            cm.add(i);
        }
    }

    public int shortest(String w1, String w2) {
        List<Integer> w1s = map.get(w1);
        List<Integer> w2s = map.get(w2);
        return merge(w1s, w2s);

    }

    int merge(List<Integer> l1, List<Integer> l2) {
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) < l2.get(j)) {
                min = Math.min(min, l2.get(j) - l1.get(i));
                i++;
            } else if (l1.get(i) > l2.get(j)) {
                min = Math.min(min, l1.get(i) - l2.get(j));
                j++;
            } else {
                return 0;
            }
        }
        return min;
    }

    // or call bs for everything in w2
    int bs(List<Integer> a, int t) {
        int n = a.size();
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // u is the last<=
        if (u == n - 1) {
            return u;
        }
        // u can be -1
        if (u == -1) {
            return 0;
        }
        int u1 = u + 1;
        int dl = diff(a.get(u), t);
        int dl1 = diff(a.get(u + 1), t);
        return dl < dl1 ? u : u + 1;
    }

    int diff(int a, int b) {
        return Math.abs(a - b);
    }
}

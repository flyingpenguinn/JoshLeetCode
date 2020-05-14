import java.util.Arrays;
import java.util.Comparator;

public class CompareStringByFreqSmallest {

    class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(getCount(o1), getCount(o2));
        }

        private int getCount(String s) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            int r = 0;
            for (int i = 0; i < sc.length; i++) {
                if (sc[i] == sc[0]) {
                    r++;
                } else {
                    break;
                }
            }
            return r;
        }
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] r = new int[queries.length];
        Arrays.sort(words, new StringComparator());
        for (int i = 0; i < queries.length; i++) {
            int index = binarySearchLastEqualOrBigger(words, queries[i], new StringComparator());
            // if index == then we count form index+1;
            // if index < then index+1 > we still count from it
            r[i] = words.length - 1 - index;
        }
        return r;
    }

    private int binarySearchLastEqualOrBigger(String[] words, String q, StringComparator comp) {
        int l = 0;
        int u = words.length-1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (comp.compare(words[mid], q) <= 0) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        String[] q = {"bba", "abaaaaaa", "aaaaaa", "bbabbabaab", "aba", "aa", "baab", "bbbbbb", "aab", "bbabbaabb"};
        String[] w = {"aaabbb", "aab", "babbab", "babbbb", "b", "bbbbbbbbab", "a", "bbbbbbbbbb", "baaabbaab", "aa"};
        System.out.println(Arrays.toString(new CompareStringByFreqSmallest().numSmallerByFrequency(q, w)));
    }
}

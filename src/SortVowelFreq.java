import java.util.*;

public class SortVowelFreq {
    private String vow = "aeiou";

    public String sortVowels(String s) {
        int n = s.length();
        List<Character> vs = new ArrayList<>();
        Map<Character, Integer> freq = new HashMap<>();
        Map<Character, Integer> first = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vow.indexOf(c) != -1) {
                vs.add(c);
                freq.put(c, freq.getOrDefault(c, 0) + 1);
                first.putIfAbsent(c, i);
            } else {
                continue;
            }
        }
        Collections.sort(vs, (x, y) -> {
            int f1 = freq.get(x);
            int f2 = freq.get(y);
            if (f1 != f2) {
                return Integer.compare(f2, f1);
            } else {
                return Integer.compare(first.get(x), first.get(y));
            }
        });
        StringBuilder sb = new StringBuilder();
        int vi = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vow.indexOf(c) != -1) {
                sb.append(vs.get(vi++));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

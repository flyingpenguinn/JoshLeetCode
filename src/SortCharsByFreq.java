

import java.util.*;
/*
LC#451
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.L
 */
public class SortCharsByFreq {
    public String frequencySort(String s) {
        if (s == null) {
            return "";
        }
        Map<Character, Integer> fmap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            fmap.put(c, fmap.getOrDefault(c, 0) + 1);
        }
        // java bucket sorting needs array of lists...
        List<Character>[] buckets = new List[s.length()+1];
        for (char k : fmap.keySet()) {
            int v = fmap.get(k);
            if(buckets[v] == null){
                buckets[v] = new ArrayList<>();
            }
            buckets[v].add(k);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 0; i--) {
            if (buckets[i] == null) {
                continue;
            }
            for (char c : buckets[i]) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SortCharsByFreq().frequencySort("reet"));
    }
}

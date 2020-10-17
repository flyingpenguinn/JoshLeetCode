import java.util.*;

/*
LC#187
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDnaSequence {
    // a watered down version of repeated substring problem...
    // use rolling hash as we did in repeated substring problem

    public List<String> findRepeatedDnaSequences(String s) {
        Map<Long, Integer> seen = new HashMap<>();
        List<String> r = new ArrayList<>();
        int len = 10;
        int base = 5;
        long hash = 0;
        long msb = 1;
        // in rolling hash we enumerate the end points
        for (int i = 0; i < s.length(); i++) {
            hash = hash * base + getCode(s.charAt(i));
            int head = i - len + 1;
            if (head >= 0) {
                int nc = seen.getOrDefault(hash, 0) + 1;
                if (nc == 2) {
                    r.add(s.substring(head, i + 1));
                }
                seen.put(hash, nc);
                hash -= msb * getCode(s.charAt(head));
            } else {
                msb *= base;
            }
        }
        return r;
    }

    private long getCode(char c) {
        switch (c) {
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
            default:
                return -1;
        }
    }


}

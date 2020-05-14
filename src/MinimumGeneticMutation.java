import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
LC#433
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.


Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1


Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2


Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        int init = code(start);
        q.offer(new int[]{init, 0});
        seen.add(init);
        int endcode = code(end);
        Set<Integer> bankset = new HashSet<>();
        for (String b : bank) {
            bankset.add(code(b));
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            if (top[0] == endcode) {
                return top[1];
            }
            for (int base = 1; base <= 100000000; base *= 10) {
                int mod = top[0] % (base * 10);
                int dig = mod / base;
                int rem = top[0] - dig * base;
                for (int j = 1; j <= 4; j++) {
                    int nt = rem + j * base;
                    if (bankset.contains(nt) && !seen.contains(nt)) {
                        seen.add(nt);
                        q.offer(new int[]{nt, top[1] + 1});
                    }
                }
            }
        }
        return -1;

    }

    char[] chars = {'-', 'A', 'C', 'G', 'T'};

    int code(String s) {
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 1; j <= 4; j++) {
                if (chars[j] == c) {
                    r = r * 10 + j;
                    break;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        String[] bank = {"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA"};
        System.out.println(new MinimumGeneticMutation().minMutation("AAAAAAAA", "CCCCCCCC", bank));
    }
}

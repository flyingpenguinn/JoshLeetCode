import java.util.*;

public class MaxDiffBetweenFreqEvenOddII {
    private int Min = (int) -1e9;
    private int Max = (int) 1e9;

    public int maxDifference(String s, int k) {
        // We will treat "negative infinity" as a very large negative number,
        // and "positive infinity" as a very large positive number.

        int res = Min;

        // The string only contains digits '0'..'4'
        // Try every ordered pair (a,b) with a != b
        for (char a = '0'; a <= '4'; a++) {
            for (char b = '0'; b <= '4'; b++) {
                if (a == b) {
                    continue;
                }

                // 'seen' will map a parity-pair (pA, pB) to the minimal (pa[ii] - pb[ii])
                // discovered so far, under certain conditions (explained below).
                Map<String, Integer> seen = new HashMap<>();

                // pa and pb track how many times 'a' or 'b' have appeared up to each index
                // pa[i] = count of 'a' in s[0..i-1]
                // pb[i] = count of 'b' in s[0..i-1]
                // Start both with a single "0" entry for the empty prefix.
                List<Integer> pa = new ArrayList<>();
                List<Integer> pb = new ArrayList<>();
                pa.add(0);
                pb.add(0);

                // 'ii' is a pointer that will move forward once the substring length >= k

                // Build prefix sums on the fly
                int j = 0;
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    // Append to the end of pa/pb the same values as previous,
                    // then possibly increment one of them if ch==a or ch==b.
                    pa.add(pa.get(pa.size() - 1));
                    pb.add(pb.get(pb.size() - 1));
                    if (ch == a) {
                        pa.set(pa.size() - 1, pa.get(pa.size() - 1) + 1);
                    } else if (ch == b) {
                        pb.set(pb.size() - 1, pb.get(pb.size() - 1) + 1);
                    }

                    // Now, while we can move 'ii' forward (and still have substring length >= k),
                    // and also pa[ii] < pa[i+1], pb[ii] < pb[i+1], we update 'seen'.
                    //
                    // Condition: ii <= i-k+1 ensures substring s[ii..i] has length >= k.
                    // Condition: pa[ii] < pa[i+1] and pb[ii] < pb[i+1] ensures
                    //            the substring s[ii..i] has at least 1 occurrence of 'a' and 'b'.
                    while (j <= i - k + 1 &&
                            pa.get(j) < pa.get(pa.size() - 1)
                            && pb.get(j) < pb.get(pb.size() - 1)) {
                        // We record the parity of pa[ii], pb[ii], and the difference
                        int pA = pa.get(j) % 2;
                        int pB = pb.get(j) % 2;
                        String key = pA + "," + pB;
                        int diff = pa.get(j) - pb.get(j);

                        // We want the minimal diff so far for this parity combination
                        int oldVal = seen.getOrDefault(key, Max);
                        if (diff < oldVal) {
                            seen.put(key, diff);
                        }
                        ++j;
                    }
                    // Now we check if we can form a valid substring that ends at i
                    // and has the required parity conditions:
                    //   freq(a) is odd => pA[i+1] != pA[start]
                    //   freq(b) is even => pB[i+1] == pB[start]
                    //
                    // The code accomplishes this by looking for a start index that had
                    // parity (pA, pB) = (1 - current_pA, current_pB).
                    int currentPA = pa.get(pa.size() - 1);
                    int currentPB = pb.get(pb.size() - 1);
                    int neededA = 1 - (currentPA % 2);
                    int neededB = currentPB % 2;
                    String neededKey = neededA + "," + neededB;

                    int curDiff = currentPA - currentPB;
                    int storedVal = seen.getOrDefault(neededKey, Max);
                    if (storedVal != Max) {
                        res = Math.max(res, curDiff - storedVal);
                    }
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxDiffBetweenFreqEvenOddII().maxDifference("2421", 1));
    }


}

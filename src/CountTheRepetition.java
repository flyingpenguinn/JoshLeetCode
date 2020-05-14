import java.util.*;

/*
LC#466
Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.

You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.

Example:

Input:
s1="acb", n1=4
s2="ab", n2=2

Return:
2
 */
public class CountTheRepetition {
    // think of s1 in segments. find the loop that starts from an s2 index that we met before
    // due to pigeonhole principle, we will find the repetition in at most s1*s2 times of s1 traverse. so overall min(s2,n1)*s1
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int c1 = 0;
        int i = 0;
        int j = 0;
        int s1n = s1.length();
        int s2n = s2.length();
        // record how many times we have traversed s2 on this s1 position
        List<Integer> times = new ArrayList<>();
        // record that we start at this position in s1 when we matched start of s2
        int[] start = new int[s1n];
        Arrays.fill(start, -1);
        int curtime = 0;
        while (c1 < n1) {
            times.add(curtime);
            int modi = i % s1n;
            if (s1.charAt(modi) == s2.charAt(j)) {
                if (j == 0) {
                    if (start[modi] != -1) {
                        // from oldstart to i, we got curtime-times.get(i) times of s2
                        int oldstart = start[modi];
                        int appear = curtime - times.get(oldstart);
                        // note we need to factor old start into account
                        int repeats = (n1 * s1n - oldstart) / (i - oldstart);
                        int allappaer = repeats * appear;
                        int stubi = (n1 * s1n - oldstart) % (i - oldstart);
                        int stub = times.get(oldstart + stubi);
                        return (allappaer + stub) / n2;
                    } else {
                        start[modi] = i;
                    }
                }
                j++;
            }
            i++;
            if (j == s2n) {
                j = 0;
                curtime++;
            }
            if (i % s1n == 0) {
                c1++;
            }
        }
        return curtime / n2;
    }

    public static void main(String[] args) {

        System.out.println(new CountTheRepetition().getMaxRepetitions("ecbafedcba", 3, "abcdef", 1));
        System.out.println(new CountTheRepetition().getMaxRepetitions("lovelive", 1, "lovelive", 10));
        System.out.println(new CountTheRepetition().getMaxRepetitions("aaa", 3, "aa", 1));
        System.out.println(new CountTheRepetition().getMaxRepetitions("acb", 1, "acb", 1));
        System.out.println(new CountTheRepetition().getMaxRepetitions("acb", 4, "ab", 2));
        System.out.println(new CountTheRepetition().getMaxRepetitions("abacb", 6, "bcaa", 1));
    }
}

class CountTheRepetitionBruteForce {
    // either exhaust n1, or we find s1*s == s2*r and do the calc
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int t = times(s1.toCharArray(), s2.toCharArray(), n1, n2);
        return t;
    }

    int times(char[] s1, char[] s2, int n1, int n2) {
        int i = 0;
        int j = 0;
        int r = 0;
        int s = 0;
        int on1 = n1;
        while (n1 > 0) {
            if (s1[i] == s2[j]) {
                j++;
            }
            i++;
            // s1*s == s2*r. we know how to do the rest
            if (i == s1.length && j == s2.length) {
                s++;
                r++;
                break;
            }
            if (i == s1.length) {
                n1--;
                s++;
                i = 0;
            }
            if (j == s2.length) {
                j = 0;
                r++;
            }
        }
        double p1 = on1 * 1.0 / s;
        double p2 = r * 1.0 / n2;
        return (int) (p1 * p2);
    }
}

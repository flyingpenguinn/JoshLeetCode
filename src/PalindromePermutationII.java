import java.util.*;

/*
LC#267
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
 */
public class PalindromePermutationII {
    // note even for odd chars we can -2 them unless there is only one
    List<String> r = new ArrayList<>();

    public List<String> generatePalindromes(String s) {
        int[] c = new int[255];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)]++;
        }
        int odd = 0;
        char oc = '*';
        for (int i = 0; i < c.length; i++) {
            if (c[i] % 2 == 1) {
                odd++;
                oc = (char) i;
                if (odd >= 2) {
                    return r;
                }
            }
        }
        dog(c, s.length(), "", "", oc);
        return r;
    }

    void dog(int[] c, int n, String cur, String rev, char oc) {
        if (n == 1) {
            r.add(cur + oc + rev);
            return;
        }
        if (n == 0) {
            r.add(cur + rev);
            return;
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 2) {
                c[i] -= 2;
                dog(c, n - 2, cur + (char) i, (char) i + rev, oc);
                c[i] += 2;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePermutationII().generatePalindromes("aabb"));
    }
}

import java.util.*;

/*
LC#1096
Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.

Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.



Example 1:

Input: "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]
Example 2:

Input: "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.


Constraints:

1 <= expression.length <= 60
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
 */
public class BraceExpansionII {
    // for prasing questions: parse level 0 , first, then separate single blocks out. in these single blocks there shouldnt be level 0 commas
    private Set<String> cart(Set<String> s1, Set<String> s2) {
        if (s1.isEmpty()) {
            return s2;
        }
        if (s2.isEmpty()) {
            return s1;
        }
        Set<String> res = new HashSet<>();
        for (String s1i : s1) {
            for (String s2i : s2) {
                res.add(s1i + s2i);
            }
        }
        return res;
    }

    private Set<String> union(Set<String> s1, Set<String> s2) {
        Set<String> res = new HashSet<>();
        res.addAll(s1);
        res.addAll(s2);
        return res;
    }

    public List<String> braceExpansionII(String s) {
        Set<String> set = solve(s, 0, s.length() - 1);
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    private Set<String> solve(String s, int l, int u) {
        Set<String> res = new HashSet<>();
        int n = u - l + 1;
        if (n == 1) {
            res.add(String.valueOf(s.charAt(l)));
            return res;
        }

        int level = 0;
        int pre = l;
        boolean found = false;
        for (int i = l; i <= u; ++i) {
            char c = s.charAt(i);
            if (c == '{') {
                ++level;
            } else if (c == '}') {
                --level;
            } else if (c == ',' && level == 0) {
                Set<String> cur = solve(s, pre, i - 1);
                res = union(res, cur);
                pre = i + 1;
                found = true;
            }
        }
        if (found) {
            Set<String> cur = solve(s, pre, u);
            res = union(res, cur);
            return res;
        }

        level = 0;
        int ilevel0 = l;
        for (int i = l; i <= u; ++i) {
            char c = s.charAt(i);
            if (c == '{') {
                if (level == 0) {
                    ilevel0 = i;
                }
                ++level;
            } else if (c == '}') {
                --level;
                if (level == 0) {
                    Set<String> cur = solve(s, ilevel0 + 1, i - 1);
                    res = cart(res, cur);
                }
            } else if (level == 0) {
                res = cart(res, solve(s, i, i));
            }
        }

        return res;

    }


    public static void main(String[] args) {
        // System.out.println(new BraceExpansionII().braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println(new BraceExpansionII().braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }
}

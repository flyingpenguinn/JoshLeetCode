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
    public List<String> braceExpansionII(String ex) {
        Set<String> r = docomma(ex, 0, ex.length() - 1);
        ArrayList<String> lr = new ArrayList<>(r);
        Collections.sort(lr);
        return lr;
    }

    // handle ones with ,. we should union them
    private Set<String> docomma(String s, int start, int end) {
        Set<String> r = new HashSet<>();
        if (start > end) {
            r.add("");
            return r;
        }
        int level = 0;
        int last = start;
        for (int i = start; i <= end + 1; i++) {
            if (i == end + 1 || s.charAt(i) == ',' && level == 0) {
                Set<String> cur = dosingleblock(s, last, i - 1);
                r.addAll(cur);
                last = i + 1;
            } else if (s.charAt(i) == '{') {
                level++;
            } else if (s.charAt(i) == '}') {
                level--;
            }
        }
        return r;
    }

    // l is the position of {
    private int nextright(String s, int l, int u) {
        int level = 0;
        for (int i = l; i <= u; i++) {
            if (s.charAt(i) == '{') {
                level++;
            } else if (s.charAt(i) == '}') {
                level--;
                if (level == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 2 kinds of single block: start with {, or not
    // if start with {, find the next right, and recurse on it. treat {} as a single unit
    // otherwise the char is a single unit
    private Set<String> dosingleblock(String s, int start, int end) {
        Set<String> r = new HashSet<>();
        if (start > end) {
            r.add("");
            return r;
        }
        if (Character.isLetter(s.charAt(start))) {
            Set<String> later = dosingleblock(s, start + 1, end);
            for (String l : later) {
                r.add(s.charAt(start) + l);
            }
            return r;
        } else {
            int rend = nextright(s, start, end);
            Set<String> cur = docomma(s, start + 1, rend - 1);
            Set<String> later = docomma(s, rend + 1, end);
            for (String c : cur) {
                for (String l : later) {
                    r.add(c + l);
                }
            }
            return r;
        }
    }


    public static void main(String[] args) {
        // System.out.println(new BraceExpansionII().braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println(new BraceExpansionII().braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }
}

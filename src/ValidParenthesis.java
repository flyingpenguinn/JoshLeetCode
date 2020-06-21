import java.util.*;

/*
LC#20

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */
public class ValidParenthesis {
    Map<Character, Character> m = new HashMap<>();
    {
        m.put(')', '(');
        m.put(']', '[');
        m.put('}', '{');
    }

    Set<Character> lset = new HashSet<>();
    {
        lset.add('(');
        lset.add('[');
        lset.add('{');
    }

    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        int n = s.length();
        for(int i=0; i<n;i++){
            char c = s.charAt(i);
            if(lset.contains(c)){
                st.push(c);
            }else{
                char mapped = m.get(c);
                if(!st.isEmpty() && st.peek() == mapped){
                    st.pop();
                }else{
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}

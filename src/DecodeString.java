import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
LC#394
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
    public String decodeString(String s) {
        return dodeco(s, 0, s.length()-1);
    }

    private String dodeco(String s, int l, int u){
        StringBuilder sb = new StringBuilder();
        int rep = 0;
        int i = l;
        while(i<=u){
            char c = s.charAt(i);
            if(c>='a' && c<='z'){
                sb.append(c);
                i++;
            }else if(c>='0' && c<='9'){
                rep = rep*10 + (c-'0');
                i++;
            }else{
                int j = i;
                int level = 0;
                while(j<=u){
                    char cj = s.charAt(j++);
                    if(cj=='['){
                        level++;
                    }else if(cj==']'){
                        level--;
                        if(level==0){
                            break;
                        }
                    }
                }
                String inner = dodeco(s, i+1, j-1);
                while(rep>0){
                    sb.append(inner);
                    rep--;
                }
                rep = 0;
                i = j;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecodeStringStack().decodeString("3[a]2[bc]"));
    }
}

class DecodeStringStack {
    // keep all chars within a [] in stack pop up when we see ]
    public String decodeString(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c!= ']'){
                st.push(c);
            }else{
                List<Character> chars = new ArrayList<>();
                while(st.peek() !='['){
                    chars.add(st.pop());
                }
                st.pop();
                int rep = 0;
                int base = 1;
                while(!st.isEmpty() && Character.isDigit(st.peek())){
                    rep += base* (st.pop()-'0');
                    base *= 10;
                }
                while(rep>0){
                    for(int j=chars.size()-1; j>=0; j--){
                        st.push(chars.get(j));
                    }
                    rep--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}

import java.util.ArrayDeque;
import java.util.Deque;

public class CountCollisionsOnARoad {
    public int countCollisions(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int res = 0;
        Deque<Character> st = new ArrayDeque<>();
        for(int i=0; i<n; ++i){
            char cur = sc[i];
            if(st.isEmpty()){
                st.push(cur);
                continue;
            }
            char top= st.peek();
            if(top==cur){
                st.push(cur);
            }
            if(top=='R' && cur=='L'){
                st.pop();
                res += 2;
                while(!st.isEmpty() && st.peek() =='R'){
                    ++res;
                    st.pop();
                }
                st.push('S');
            }
            if(top=='L' && cur=='R'){
                st.push(cur);
            }
            if(top=='S' && cur=='L'){
                res+=1;
                st.push('S');
            }
            if(top=='S' && cur=='R'){
                st.push(cur);
            }
            if(top=='R' && cur=='S'){
                while(!st.isEmpty() && st.peek() =='R'){
                    ++res;
                    st.pop();
                }
                st.push(cur);
            }
            if(top=='L' && cur=='S'){
                st.push(cur);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountCollisionsOnARoad().countCollisions("RLRSLL"));
    }
}

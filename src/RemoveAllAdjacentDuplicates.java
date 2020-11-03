import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicates {
    // same as one step in zuma game (though just 2 balls not 3 balls), and asteriod collision...
    // reason for stack is mainly "adjacent cancelling out"
    public String removeDuplicates(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            if(st.isEmpty()){
                st.push(s.charAt(i));
            }else if(st.peek() == s.charAt(i)){
                st.pop();
            }else{
                st.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}

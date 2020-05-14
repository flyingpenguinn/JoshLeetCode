import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicates {
    // same as one step in zuma game (though just 2 balls not 3 balls), and asteriod collision...
    // reason for stack is mainly "adjacent cancelling out"
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}

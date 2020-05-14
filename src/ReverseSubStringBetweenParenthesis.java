import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseSubStringBetweenParenthesis {
    public String reverseParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> queue= new ArrayDeque<>();
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if(c==')'){
                while(!stack.isEmpty()){
                    char top = stack.pop();
                    if(top != '(') {
                        queue.offer(top);
                    }else{
                        break;
                    }
                }
                while(!queue.isEmpty()){
                    stack.push(queue.poll());
                }
            }else{
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseSubStringBetweenParenthesis().reverseParentheses("(u(love)i)"));
    }
}

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OnlineStockSpan {
    // mono decrease stack store index
    Deque<Integer> stack= new ArrayDeque<>();
    List<Integer> p=new ArrayList<>();

    public OnlineStockSpan() {

    }

    public int next(int price) {
        while(!stack.isEmpty() && p.get(stack.peek())<=price){
            stack.pop();

        }

        int prev= stack.isEmpty()?-1:stack.peek();
        int cur= p.size();

        stack.push(cur);
        p.add(price);

        return cur-prev;
    }
}

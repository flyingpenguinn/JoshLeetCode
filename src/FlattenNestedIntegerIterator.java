import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class FlattenNestedIntegerIterator {
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    // make sure top is an integer. otherwise keep digging them
    // for non top, we can keep list in the stack and unwrap later
    private Deque<NestedInteger> st = new ArrayDeque<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            st.push(nestedList.get(i));
        }
        dig();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        Integer rt = st.pop().getInteger(); // top must be an integer if has next
        dig();
        return rt;
    }

    @Override
    public boolean hasNext() {
        return !st.isEmpty();
    }

    private void dig() {
        while (!st.isEmpty() && !st.peek().isInteger()) {
            NestedInteger top = st.pop();
            for (int i = top.getList().size() - 1; i >= 0; i--) {
                st.push(top.getList().get(i));
            }
        }
    }
}

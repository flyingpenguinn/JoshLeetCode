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
    // either nint or inn the iterator that has next, or both null
    List<NestedInteger> l;
    int p = -1;
    NestedIterator inn = null;
    Integer nint = null;
    boolean cached = false;

    public NestedIterator(List<NestedInteger> list) {
        l = list;
    }

    @Override
    public Integer next() {
        Integer rt = null;
        if (nint != null) {
            rt = nint;
        } else if (inn != null && inn.hasNext()) {
            rt = inn.next();
        }
        cached = false;
        return rt;
    }

    // point to valid nint or inn. ni or inn.next gives next
    @Override
    public boolean hasNext() {
        if (cached) {
            boolean rt = nint != null || (inn != null && inn.hasNext());
            return rt;
        } else {
            if (inn != null && inn.hasNext()) {
                cached = true;
                return true;
            }
            inn = null;
            nint = null;
            while (p + 1 < l.size()) {
                NestedInteger ni = l.get(p + 1);
                p++;
                if (ni.isInteger()) {
                    nint = ni.getInteger();
                    break;
                } else {
                    inn = new NestedIterator(ni.getList());
                    if (inn.hasNext()) {
                        break;
                    }
                }
            }
            cached = true;
            boolean rt = nint != null || (inn != null && inn.hasNext());
            return rt;
        }
    }
}

class NestedIteratorStack implements Iterator<Integer> {

    Deque<NestedInteger> stack = new ArrayDeque<>();

    public NestedIteratorStack(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return hasNext() ? stack.pop().getInteger() : null;
    }

    // backout the next integer put it on the top of the stack, or it becomes empty
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) {
                break;
            }
            List<NestedInteger> top = stack.pop().getList();
            for (int i = top.size() - 1; i >= 0; i--) {
                stack.push(top.get(i));
            }
        }
        return !stack.isEmpty();
    }
}

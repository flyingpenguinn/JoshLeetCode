import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#331
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true
Example 2:

Input: "1,#"
Output: false
Example 3:

Input: "9,#,#,1"
Output: false
 */
public class VerifyPreorderSerialization {
    // similar to pre order decode
    public boolean isValidSerialization(String s) {
        String[] ss = s.split(",");
        if (ss[0].equals("#")) {
            // only case of # with empty stack
            return ss.length == 1;
        }
        Deque<Integer> st = new ArrayDeque<>();
        Integer last = Integer.valueOf(ss[0]);
        st.offer(last);
        // what's in stack are nodes pending to know their right child
        for (int i = 1; i < ss.length; i++) {
            if (st.isEmpty()) {
                // in case two roots
                return false;
            }
            Integer cur = ss[i].equals("#") ? null : Integer.valueOf(ss[i]);
            if (last != st.peek()) {
                // right child
                st.pop();
            }
            // do nothing if left child
            if (cur != null) {
                st.push(cur);
            }
            last = cur;
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new VerifyPreorderSerialization().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }
}

class VerifyPreorderSerializationNoStack {
    // each non null adds 2 slots. null or number takes one slot
    public boolean isValidSerialization(String preorder) {
        // number of available slots
        int slots = 1;

        for (String node : preorder.split(",")) {
            // one node takes one slot
            --slots;

            // no more slots available
            if (slots < 0) return false;

            // non-empty node creates two children slots
            if (!node.equals("#")) slots += 2;
        }

        // all slots should be used up
        return slots == 0;
    }
}

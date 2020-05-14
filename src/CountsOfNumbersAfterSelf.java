import java.util.*;

public class CountsOfNumbersAfterSelf {

    public static void main(String[] args) {
        //int[] array = {5, 2, 6, 1};
        int[] array = {83, 51, 98, 69, 76, 99, 51};
        System.out.println(new CountsOfNumbersAfterSelf().countSmaller(array));
    }

    class BSTnode {
        int val;
        BSTnode left;
        BSTnode right;
        // need to separate the two
        int count;
        int selfcount;

        public BSTnode(int val) {
            this.val = val;
            this.count = 1;
            this.selfcount = 1;
        }
    }

    BSTnode root = null;

    // do check and insertion in one shot
    // how many are < num in the subtree of node, before this insertion
    private int insert(BSTnode node, int num) {
        if (root == null) {
            root = new BSTnode(num);
            return 0;
        }
        if (node.val == num) {
            node.count++;
            node.selfcount++;
            return getLeftCount(node);
        } else if (node.val < num) {
            node.count++;
            if (node.right == null) {
                node.right = new BSTnode(num);
                return getLeftCount(node) + node.selfcount;
            } else {
                int rightcount = insert(node.right, num);
                return getLeftCount(node) + node.selfcount + rightcount;
            }
        } else {
            node.count++;
            if (node.left == null) {
                node.left = new BSTnode(num);
                return 0;
            } else {
                return insert(node.left, num);
            }
        }
    }

    private int getLeftCount(BSTnode node) {
        return node.left == null ? 0 : node.left.count;
    }


    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> r = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int number = nums[i];
            int count = insert(root, number);
            r.offerFirst(count);
        }
        return r;
    }
}

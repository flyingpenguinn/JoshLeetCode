import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathInZigzagLabeledBinaryTree {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> r = new ArrayList<>();
        while (label >= 1) {
            r.add(label);
            label /= 2;
        }
        Collections.reverse(r);
        int n = r.size();
        // don't flip r.size-1, but flip -2, -4... so i %2 != (n-1) %2
        for (int i = r.size() - 2; i >= 0; i--) {
            if (i % 2 != (n-1) % 2) {
                int sum = (1 << i) + (1 << (i + 1)) - 1;
                r.set(i, sum - r.get(i));
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new PathInZigzagLabeledBinaryTree().pathInZigZagTree(17));
    }
}

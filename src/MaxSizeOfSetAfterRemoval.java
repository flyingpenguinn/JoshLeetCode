import java.util.HashSet;

public class MaxSizeOfSetAfterRemoval {
    public int maximumSetSize(int[] a, int[] b) {
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();
        for (int x : a) {
            s1.add(x);
        }
        for (int x : b) {
            s2.add(x);
        }
        HashSet<Integer> common = new HashSet<>(s1);
        common.retainAll(s2);
        int n = a.length, n1 = s1.size(), n2 = s2.size(), c = common.size();
        int cur = Math.min(n1 - c, n / 2) + Math.min(n2 - c, n / 2) + c;
        return Math.min(n, cur);
    }
}

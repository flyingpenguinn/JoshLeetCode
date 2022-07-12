import java.util.TreeSet;

public class SmallestInfiniteSet {
    private TreeSet<Integer> popped = new TreeSet<>();
    private int cur = 1;

    public SmallestInfiniteSet() {

    }

    public int popSmallest() {
        if (!popped.isEmpty() && popped.first() < cur) {
            return popped.pollFirst();
        } else {
            return cur++;
        }
    }

    public void addBack(int num) {
        if (popped.contains(num) || cur <= num) {
            return;
        }
        popped.add(num);
    }
}

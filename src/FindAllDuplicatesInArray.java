import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllDuplicatesInArray {
    // use nums[i] as a hash table, negative value to indicate it existed
    public List<Integer> findDuplicates(int[] a) {
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            int pos = Math.abs(a[i]) - 1;
            if (a[pos] < 0) {
                // dont set it back otherwise can't tell between 2 times and missing...
                r.add(pos + 1);
            } else {
                a[pos] = -Math.abs(a[pos]);
            }
        }
        return r;
    }


    public static void main(String[] args) {
        int[] array = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(new FindAllDuplicatesInArray().findDuplicates(array));
    }
}

class FindAllDuplicatesSwap {
    // keep swapping nums[i] to where it should be
    public List<Integer> findDuplicates(int[] a) {

        List<Integer> r = new ArrayList<>();
        int i = 0;
        while (i < a.length) {
            int pos = a[i] - 1;
            if (a[i] < 0) {
                i++;
                continue;
            } else if (pos == i) {
                i++;
            } else if (a[pos] == a[i]) {
                r.add(a[i]);
                a[i] = -a[i];
                i++;
            } else {
                swap(a, pos, i);
            }
        }
        return r;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

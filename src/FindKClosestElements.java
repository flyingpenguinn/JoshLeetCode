import java.util.LinkedList;
import java.util.List;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] a, int k, int x) {
        int n = a.length;
        if (a[0] == x && a[n - 1] == x) {
            return toList(a, 0, k - 1);
        }
        if (x < a[0]) {
            return toList(a, 0, k - 1);
        }
        if (x > a[n - 1]) {
            return toList(a, n - k, n - 1);
        }
        int index = binarySearchFoundOrFirstBigger(a, x);
        int lastSmaller = -1;
        int firstBigger = -1;
        int count = 0;
        if (index < n && a[index] == x) {
            // found
            count = 1;
            int i = index - 1;
            while (i >= 0 && a[i] == x && count < k) {
                i--;
                count++;
            }
            // i+1 is the last == number
            if (count == k) {
                return toList(a, i + 1, index);
            }
            lastSmaller = i;
            int j = index + 1;
            while (j < n && a[j] == x && count < k) {
                j++;
                count++;
            }
            // j-1 is the last == number
            if (count == k) {
                return toList(a, i + 1, j - 1);
            }
            firstBigger = j;
        } else {
            lastSmaller = index - 1;
            firstBigger = index;
        }
        int ps = lastSmaller;
        int pg = firstBigger;
        List<Integer> list = toList(a, lastSmaller + 1, firstBigger - 1);
        while (count < k && ps >= 0 && pg < n) {
            if (x - a[ps] <= a[pg] - x) {
                // <= because we favor smaller number
                list.add(0, a[ps]);
                ps--;
            } else {
                list.add(a[pg]);
                pg++;
            }
            count++;
        }
        while (count < k && ps >= 0) {
            list.add(0, a[ps]);
            ps--;
            count++;
        }
        while (count < k && pg < n) {
            list.add(a[pg]);
            pg++;
            count++;
        }
        return list;
    }

    private int binarySearchFoundOrFirstBigger(int[] a, int x) {
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (a[mid] == x) {
                return mid;
            } else if (a[mid] < x) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    private List<Integer> toList(int[] a, int i, int j) {
        LinkedList<Integer> rt = new LinkedList<>();
        for (int k = i; k <= j; k++) {
            rt.add(a[k]);
        }
        return rt;
    }

    public static void main(String[] args) {
        FindKClosestElements fke = new FindKClosestElements();
        int[] a = {1, 2, 3, 4, 5};

        System.out.println(fke.findClosestElements(a, 3, 2));
        System.out.println(fke.findClosestElements(a, 3, 1));
        System.out.println(fke.findClosestElements(a, 3, 3));
        System.out.println(fke.findClosestElements(a, 3, 4));
        System.out.println(fke.findClosestElements(a, 3, 5));
        System.out.println(fke.findClosestElements(a, 3, 0));
        System.out.println(fke.findClosestElements(a, 3, 6));

        System.out.println(fke.findClosestElements(a, 0, 2));
        System.out.println(fke.findClosestElements(a, 1, 2));
        System.out.println(fke.findClosestElements(a, 1, 1));
        System.out.println(fke.findClosestElements(a, 1, 3));
        System.out.println(fke.findClosestElements(a, 1, 4));
        System.out.println(fke.findClosestElements(a, 1, 5));
        System.out.println(fke.findClosestElements(a, 1, 0));
        System.out.println(fke.findClosestElements(a, 1, 6));

        int[] a2 = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(fke.findClosestElements(a2, 1, 3));
        System.out.println(fke.findClosestElements(a2, 2, 3));
        System.out.println(fke.findClosestElements(a2, 3, 3));
        System.out.println(fke.findClosestElements(a2, 4, 3));
        System.out.println(fke.findClosestElements(a2, 5, 3));

    }

}

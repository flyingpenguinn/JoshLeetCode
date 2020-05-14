public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] a) {
        int n = a.length;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            cur = a[i] == 0 ? 0 : cur + 1;
            max = Math.max(max, cur);
        }
        return max;
    }
}

class MaxConsecutiveOnesStartend {
    public int findMaxConsecutiveOnes(int[] a) {
        int n = a.length;
        int start = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            if (i == 0 || a[i - 1] != 1) {
                start = i;
            }
            // no else if here. the start can be the end
            if (i == n - 1 || a[i + 1] != 1) {
                max = Math.max(max, i - start + 1);
            }
        }
        return max;
    }
}

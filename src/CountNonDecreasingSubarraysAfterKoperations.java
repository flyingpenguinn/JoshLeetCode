import base.ArrayUtils;

import java.util.*;

public class CountNonDecreasingSubarraysAfterKoperations {

    public long countNonDecreasingSubarrays(int[] a, int K) {
        int n = a.length;

        // 前缀和
        long[] f = new long[n];
        f[0] = a[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i - 1] + a[i];
        }

        // right[i]：下一个大于等于 a[i] 的元素的位置
        int[] right = new int[n];
        Arrays.fill(right, n);

        // vec[i]：a[i] 是哪些数的“左边最近的更大数”
        List<List<Integer>> vec = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vec.add(new ArrayList<>());
        }

        // 单调栈
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[st.peek()] <= a[i]) {
                right[st.pop()] = i;
            }
            if (!st.isEmpty()) {
                vec.get(st.peek()).add(i);
            }
            st.push(i);
        }


        long ans = 0, now = 0;
        // 双端队列维护单调队列
        Deque<Integer> dq = new ArrayDeque<>();
        // 双指针：i 是子数组的末尾，j 是子数组的开头最左边能到哪里
        for (int i = 0, j = 0; i < n; i++) {
            // 将 a[i] 加入单调队列
            while (!dq.isEmpty() && a[dq.getLast()] <= a[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            now += a[dq.getFirst()] - a[i];

            while (j < i && now > K) {
                // 扣掉被去掉的元素的贡献
                now -= calc(j, i, right, a, f);
                for (int x : vec.get(j)) {
                    // 加上露出来的元素的贡献
                    if (x > i) {
                        break;
                    }
                    now += calc(x, i, right, a, f);
                }
                // 如有必要，从单调队列中去掉 a[j]
                if (dq.getFirst() == j) {
                    dq.removeFirst();
                }
                j++;
            }

            ans += i - j + 1;
        }
        return ans;
    }

    private long calc(int i, int lim, int[] right, int[] a, long[] f) {
        lim = Math.min(lim, right[i] - 1);
        return 1L * a[i] * (lim - i) - (f[lim] - f[i]);
    }

    public static void main(String[] args) {
        System.out.println(new CountNonDecreasingSubarraysAfterKoperations().countNonDecreasingSubarrays(ArrayUtils.read1d("[1000000000,1,1,1,1]"), 1000000000));
    }

}

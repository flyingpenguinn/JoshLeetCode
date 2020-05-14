import java.util.Arrays;


public class ThreeSumWithMultiplicity {
    // O(n+size*size)  in this one possible range of numbers is small
    // idea is to loop numbers themselves
    int[] cnt;

    public int threeSumMulti(int[] a, int t) {
        int size = 101;
        cnt = new int[size];
        for (int ai : a) {
            cnt[ai]++;
        }
        int n = a.length;
        long r = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                // in case target too large
                int k = t - i - j;
                // must enforce an order: i<=j<=k. otherwise we will double count the pairs
                if (k < 0 || k >= size || k < j) {
                    continue;
                }
                if (i == j && i == k) {
                    r += pick3(i);
                } else if (i == j) {
                    r += pick2(i) * cnt[k];
                } else if (j == k) {
                    r += pick2(j) * cnt[i];
                } else {
                    r += cnt[i] * cnt[j] * cnt[k];
                }
            }
        }
        return (int) (r % 1000000007);
    }

    long pick3(int i) {
        long ci = cnt[i];
        if (ci < 3) {
            return 0;
        }
        return ci * (ci - 1L) * (ci - 2) / 6;
    }

    long pick2(int i) {
        long ci = cnt[i];
        if (ci < 2) {
            return 0;
        }
        return ci * (ci - 1L) / 2;
    }
}

class ThreeSumWithMultiSol2 {
    // like traditional 3 sum but a twist on counting. note it's just counting triples not reporting their position so we can sort

    private int MOD = 1000000007;

    public int threeSumMulti(int[] nums, int target) {
        Arrays.sort(nums);
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            int ti = target - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sumjk = nums[j] + nums[k];
                if (sumjk > ti) {
                    k--;
                } else if (sumjk < ti) {
                    j++;

                } else {
                    // note this is different from 3sum smaller because when == we really dont know where to go:
                    // either directly j++ or directly k-- may be bad because of duplciated j or k values
                    if (nums[j] == nums[k]) {
                        int diff = k - j + 1;
                        int delta = diff * (diff - 1) / 2;
                        count += delta;
                        break;
                    }
                    int jv = nums[j];
                    int jcount = 0;
                    while (j < k && nums[j] == jv) {
                        j++;
                        jcount++;
                    }
                    j--;// back from the last ++ value which could be bad
                    int kv = nums[k];
                    int kcount = 0;
                    // here j can == k because the j earlier is not sth we would land on
                    while (j < k && nums[k] == kv) {
                        k--;
                        kcount++;
                    }
                    int delta = jcount * kcount;
                    count += delta;
                }
            }
        }
        return (int) (count % MOD);
    }
}

class ThreeSumWithMultiSol3 {
    // fix j, loop i and count how many ks we have
    public int threeSumMulti(int[] a, int t) {
        int[] cnt = new int[101];
        int n = a.length;
        long r = 0;
        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
                int tk = t - a[j] - a[i];
                if (tk < cnt.length && tk >= 0 && cnt[tk] > 0) {
                    r += cnt[tk];
                }
            }
            cnt[a[j]]++;
        }
        return (int) (r % 1000000007);
    }
}
import java.util.*;

public class NextSpecialPalindromicNumber {
    // in total 2016 nums, just get all of them!
    private static Set<Long> allset = new HashSet<>();
    private static List<Long> all = new ArrayList<>();

    public long specialPalindrome(long n) {
        if (all.isEmpty()) {
            gen();
        }

        return all.get(binary(n));
    }

    // -1 means no odd
    private static int[] mid = {1, 3, 5, 7, 9, -1};
    private static int[] evens = {2, 4, 6, 8};

    private static void gen() {
        List<Integer> cand = new ArrayList<>();
        for (int ei : evens) {
            cand.add(ei);
        }
        for (int mi : mid) {
            List<Integer> ccand = new ArrayList<>(cand);
            ccand.add(mi);
            int[] cnt = new int[10];
            dogen(0, 0, ccand, mi, cnt);
        }
        all = new ArrayList<>(allset);
        Collections.sort(all);
    }

    private static void dogen(int i, int cur, List<Integer> cand, int mid, int[] cnt) {
        if (i == 8) {
            if (mid != -1) {
                ++cnt[mid];
            }
            if (isspecial(cnt)) {
                if (cur != 0) {
                    String v = makevalue(cur, mid);
                    allset.add(Long.parseLong(v));
                } else if (mid != -1) {
                    allset.add((long) mid);
                }
            }
            if (mid != -1) {
                --cnt[mid];
            }
            return;
        }
        dogen(i + 1, cur, cand, mid, cnt);
        for (int ei : cand) {
            if (ei == -1) {
                continue;
            }
            int ncur = cur * 10 + ei;
            cnt[ei] += 2;
            dogen(i + 1, ncur, cand, mid, cnt);
            cnt[ei] -= 2;
        }
    }

    private static String makevalue(int cur, int mid) {

        String sc = String.valueOf(cur);
        String rev = new StringBuilder(sc).reverse().toString();
        String v;
        if (mid == -1) {
            v = sc + rev;
        } else {
            v = sc + mid + rev;
        }
        return v;
    }

    private static boolean isspecial(int[] cnt) {

        for (int k = 0; k < 10; ++k) {
            if (cnt[k] > 0 && cnt[k] != k) {
                return false;
            }
        }
        return true;
    }

    private int binary(long v) {
        int l = 0;
        int u = all.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (all.get(mid) > v) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new NextSpecialPalindromicNumber().specialPalindrome(0));
        System.out.println(new NextSpecialPalindromicNumber().specialPalindrome(2));
    }
}

import java.util.ArrayList;
import java.util.List;

public class MinOperationToMakeArrayKIncreasing {

    public int kIncreasing(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < k; ++i) {
            List<Integer> cur = new ArrayList<>();
            for (int j = i; j < n; j += k) {
                cur.add(a[j]);
            }
            int lcur = lis(cur);
            //   System.out.println(cur+" "+lcur);
            res += cur.size() - lcur;
        }
        return res;
    }

    private int lis(List<Integer> a) {
        List<Integer> list = new ArrayList<>();
        int n = a.size();
        for (int i = 0; i < n; ++i) {
            int k = a.get(i);
            int pos = binary(list, k);
            if (list.size() == pos) {
                list.add(k);
            } else {
                list.set(pos, k);
            }
        }
        return list.size();
    }

    // first >=
    private int binary(List<Integer> list, int k) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) > k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

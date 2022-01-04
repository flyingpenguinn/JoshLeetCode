import base.ArrayUtils;

import java.util.*;

public class MaxEmployeesInvitedToMeeting {
    private int[] st;
    private int[] len;
    private int maxlen = 0;

    public int maximumInvitations(int[] a) {
        int n = a.length;
        st = new int[n];
        len = new int[n];

        for (int i = 0; i < n; ++i) {
            if (st[i] == 0) {
                dfs(a, i, 0, new HashMap<>());
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, len[i]);
        }
        return res;
    }

    private void dfs(int[] a, int i, int index, Map<Integer, Integer> pre) {
        int ne = a[i];
        if (st[ne] == 2) {
            len[i] = 1 + len[ne];
            st[i] = 2;
            return;
        }
        if (pre.containsKey(ne)) {
            int preindex = pre.get(ne);
            int clen = index - preindex + 1;
            len[i] = clen;
            for (int k : pre.keySet()) {
                if (pre.get(k) >= preindex) {
                    len[k] = clen;
                } else {
                    len[k] = pre.size() - pre.get(k);
                }
            }
        } else {
            pre.put(i, index);
            dfs(a, ne, index + 1, pre);
            pre.remove(i);
        }
        st[i] = 2;
    }

    public static void main(String[] args) {
        System.out.println(new MaxEmployeesInvitedToMeeting().maximumInvitations(ArrayUtils.read1d("[3,0,1,4,1]")));
    }
}

import java.util.ArrayList;
import java.util.List;

public class SumOfKMirrorNumbers {
    public long kMirror(int k, int n) {
        int len = 1;
        List<Long> res = new ArrayList<>();
        while (res.size() < n) {
            char[] a = new char[len];
            dfs(a, k, 0, res);
            len++;
        }

        while (res.size() > n) res.remove(res.size() - 1);
        long sum = 0;
        for (Long s : res) {
            sum += s;
        }
        return sum;
    }


    void dfs(char[] a, int k, int index, List<Long> res) {
        int n = a.length;
        if (index >= (n + 1) / 2) {
            String s = new String(a);
            long num10 = Long.parseLong(s, k);
            String s10 = Long.toString(num10);
            if(valid(s10)){
                res.add(num10);
            }
            return;
        }
        for (char i = '0'; i < (char) ('0' + k); i++) {
            if (index == 0 && i == '0') {
                continue;
            }
            a[index] = i;
            a[n - 1 - index] = i;
            dfs(a, k, index + 1, res);
        }

    }

    private boolean valid(String s) {
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}

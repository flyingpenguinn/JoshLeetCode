import java.util.ArrayList;
import java.util.List;

public class FindLoserOfCircularGame {
    public int[] circularGameLosers(int n, int k) {
        int[] count = new int[n];
        int cur = 0;
        int round = 1;
        count[cur] = 1;
        while (true) {
            int next = cur + round * k;

            next %= n;
            //    System.out.println(round+" "+next);
            ++count[next];
            if (count[next] == 2) {
                break;
            }
            ++round;
            cur = next;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (count[i] == 0) {
                list.add(i + 1);
            }
        }
        int[] rres = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            rres[i] = list.get(i);
        }
        return rres;
    }
}

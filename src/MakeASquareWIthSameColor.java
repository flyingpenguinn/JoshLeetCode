import java.util.HashMap;
import java.util.Map;

public class MakeASquareWIthSameColor {
    // at most one, so >=3
    public boolean canMakeSquare(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Map<Character, Integer> vm = new HashMap<>();
                for (int ik = 0; ik < 2 && i + ik < m; ++ik) {
                    for (int jk = 0; jk < 2 && j + jk < n; ++jk) {
                        char v = a[i + ik][j + jk];
                        vm.put(v, vm.getOrDefault(v, 0) + 1);
                    }
                }
                for (char k : vm.keySet()) {
                    if (vm.get(k) >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

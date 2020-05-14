package base;

import java.util.HashSet;
import java.util.Set;

public class TempleOfForever {
    Set<String> seen = new HashSet<>();

    void forever(int x, int y) {
        String code = x + "," + y;
        if (seen.contains(code)) {
            return;
        }
        seen.add(code);
        System.out.println(x + "," + y);
        if (x >= 3) {
            forever(x - 3, y + 2);
        }
        forever(y, x);
    }

    public static void main(String[] args) {
        new TempleOfForever().forever(15, 12);
    }
}

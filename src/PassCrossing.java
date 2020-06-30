
import java.util.HashSet;
import java.util.Set;

/*
LC#1496
Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.



Example 1:



Input: path = "NES"
Output: false
Explanation: Notice that the path doesn't cross any point more than once.
Example 2:



Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.


Constraints:

1 <= path.length <= 10^4
path will only consist of characters in {'N', 'S', 'E', 'W}
 */
public class PassCrossing {
    public boolean isPathCrossing(String path) {
        int px = 0;
        int py = 0;
        Set<String> seen = new HashSet<>();
        seen.add(toCode(px, py));
        for (int i = 0; i < path.length(); i++) {
            char dir = path.charAt(i);
            if (dir == 'N') {
                py++;
            } else if (dir == 'S') {
                py--;
            } else if (dir == 'E') {
                px++;
            } else {
                //w
                px--;
            }
            String sig = toCode(px, py);
            if (!seen.add(sig)) {
                return true;
            }

        }
        return false;
    }

    private String toCode(int x, int y) {
        return x + "," + y;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#1465
Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.



Example 1:



Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
Example 2:



Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9


Constraints:

2 <= h, w <= 10^9
1 <= horizontalCuts.length < min(h, 10^5)
1 <= verticalCuts.length < min(w, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
It is guaranteed that all elements in horizontalCuts are distinct.
It is guaranteed that all elements in verticalCuts are distinct.
 */
public class MaxAreaOfCakeCut {
    int Mod = 1000000007;

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        List<Integer> hs = new ArrayList<>();
        hs.add(0);
        for (int i = 0; i < horizontalCuts.length; i++) {
            hs.add(horizontalCuts[i]);
        }
        hs.add(h);
        List<Integer> ws = new ArrayList<>();
        ws.add(0);
        for (int i = 0; i < verticalCuts.length; i++) {
            ws.add(verticalCuts[i]);
        }
        ws.add(w);
        Collections.sort(hs);
        Collections.sort(ws);
        int maxh = 0;
        for (int i = 1; i < hs.size(); i++) {
            int diff = hs.get(i) - hs.get(i - 1);
            maxh = Math.max(maxh, diff);
        }
        int maxw = 0;
        for (int i = 1; i < ws.size(); i++) {
            int diff = ws.get(i) - ws.get(i - 1);
            maxw = Math.max(maxw, diff);
        }
        long rt = 1L * maxh * maxw;
        return (int) (rt % Mod);
    }
}

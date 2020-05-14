import java.util.*;

/*
LLC#1257
You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region X contains another region Y then X is bigger than Y.

Given two regions region1, region2, find out the smallest region that contains both of them.

If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It's guaranteed the smallest region exists.



Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"


Constraints:

2 <= regions.length <= 10^4
region1 != region2
All strings consist of English letters and spaces with at most 20 letters.
 */
public class SamllestCommonRegion {
    // lowest common ancestor where we have parent link...
    public String findSmallestRegion(List<List<String>> rs, String r1, String r2) {
        Map<String, String> map = new HashMap<>();
        for (List<String> l : rs) {
            for (int i = 1; i < l.size(); i++) {

                map.put(l.get(i), l.get(0));
                // only one direct father
            }
        }
        Set<String> p1 = new HashSet<>();
        p1.add(r1);
        while (map.containsKey(r1)) {
            String pr1 = map.get(r1);
            p1.add(pr1);
            r1 = pr1;
        }
        while (!p1.contains(r2)) {
            r2 = map.get(r2);
        }
        return r2;
    }
}

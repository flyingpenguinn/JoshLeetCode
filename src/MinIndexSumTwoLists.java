import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.
 */
public class MinIndexSumTwoLists {
    public String[] findRestaurant(String[] a, String[] b) {
        Map<String, Integer> bm = new HashMap<>();

        for (int i = 0; i < b.length; i++) {
            bm.put(b[i], i);
        }
        List<String> r = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (bm.containsKey(a[i])) {
                int bi = bm.get(a[i]);
                if (i + bi < min) {
                    min = i + bi;
                    r.clear();
                    r.add(a[i]);
                } else if (i + bi == min) {
                    r.add(a[i]);
                }
            }
        }
        String[] rr = new String[r.size()];
        for (int i = 0; i < rr.length; i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }
}

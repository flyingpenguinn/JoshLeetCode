import java.util.HashMap;
import java.util.Map;

/*
LC#170
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
 */
public class TwoSumIIIDataStructure {

    /**
     * Initialize your data structure here.
     */
    Map<Integer, Integer> map = new HashMap<>();

    public TwoSumIIIDataStructure() {

    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (int k : map.keySet()) {
            int other = value - k;
            if (k == other) {
                if (map.get(k) >= 2) {
                    return true;
                }
            } else {
                if (map.containsKey(other)) {
                    return true;
                }
            }
        }
        return false;
    }
}

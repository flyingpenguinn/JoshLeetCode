import java.util.*;

/*
LC#1436
You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.



Example 1:

Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo"
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
Example 2:

Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are:
"D" -> "B" -> "C" -> "A".
"B" -> "C" -> "A".
"C" -> "A".
"A".
Clearly the destination city is "A".
Example 3:

Input: paths = [["A","Z"]]
Output: "Z"


Constraints:

1 <= paths.length <= 100
paths[i].length == 2
1 <= cityAi.length, cityBi.length <= 10
cityAi != cityBi
All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {
    // basically a set diff problem
    public String destCity(List<List<String>> p) {
        Set<String> good = new HashSet<>();
        Set<String> bad = new HashSet<>();

        for (int i = 0; i < p.size(); i++) {
            List<String> route = p.get(i);
            String dst = route.get(1);
            String source = route.get(0);
            bad.add(source);
            good.remove(source);
            if (!bad.contains(dst)) {
                good.add(dst);
            }
        }
        return good.iterator().next();
    }
}

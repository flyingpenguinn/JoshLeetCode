import java.util.ArrayList;
import java.util.List;

/*
LC#118
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int rows) {
        List<List<Integer>> r = new ArrayList<>();
        if (rows == 0) {
            return r;
        }
        List<Integer> s = new ArrayList<>();
        s.add(1);
        r.add(s);
        while (r.size() < rows) {
            List<Integer> last = r.get(r.size() - 1);
            List<Integer> c = new ArrayList<>();
            c.add(1);
            for (int i = 0; i + 1 < last.size(); i++) {
                c.add(last.get(i) + last.get(i + 1));
            }
            c.add(1);
            r.add(c);
        }
        return r;
    }
}

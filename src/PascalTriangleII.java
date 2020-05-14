import java.util.ArrayList;
import java.util.List;

/*
LC#119
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleII {
    public List<Integer> getRow(int k) {
        List<Integer> r= new ArrayList<>();
        r.add(1);
        k-=1;
        // do in reverse! r[i]+= r[i-1] so can't do forward way
        while(k>=0){
            int n= r.size();
            r.add(1);
            for(int i=n-1;i-1>=0;i--){
                r.set(i,r.get(i)+r.get(i-1));
            }
            k--;
        }
        return r;
    }
}

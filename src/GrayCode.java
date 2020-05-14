import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GrayCode {
    // similar to cracking safe... can dfs the solution but also can be clever... every new code on level n is
    // level n-1 + 1[all level n from the back]
    public List<Integer> grayCode(int n) {
        List<Integer> rs = new ArrayList<Integer>();
        rs.add(0);
        for (int i = 0; i < n; i++) {
            int oldsize = rs.size();
            for (int j = oldsize - 1; j >= 0; j--) {
                // we need to put 11 after 01. what we add at first will be the next of the original size -1
                rs.add(rs.get(j) | 1 << i);
                // keep the originally there just add 1xxx
            }
        }
        return rs;
    }


    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(3));
    }
}
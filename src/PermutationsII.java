import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#47
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
public class PermutationsII {

    // for each position check a map to loop in available elements
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] a) {
        Map<Integer,Integer> m = new HashMap<>();
        for(int i=0; i<a.length;i++){
            m.put(a[i], m.getOrDefault(a[i], 0)+1);
        }
        dfs(a, 0, m, new ArrayList<>());
        return res;
    }

    // iterate each number at each position. the number is sth like
    // first 1/second 1/2/3....this can also be used to solve permutation 1
    private void dfs(int[] a, int i, Map<Integer, Integer> m, List<Integer> cur) {
        if(i==a.length){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int k: m.keySet()){
            if(m.get(k)==0){
                continue;
            }
            cur.add(k);
            m.put(k, m.getOrDefault(k, 0)-1);
            dfs(a, i+1, m, cur);
            m.put(k, m.getOrDefault(k, 0)+1);
            cur.remove(cur.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(ArrayUtils.read1d("1,1,2")));
    }
}

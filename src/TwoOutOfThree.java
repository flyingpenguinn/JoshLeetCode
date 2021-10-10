import java.util.*;

public class TwoOutOfThree {
    private Map<Integer, Set<Integer>> m = new HashMap<>();
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        for(int n1: nums1){
            m.computeIfAbsent(n1, k-> new HashSet<>()).add(1);
        }
        for(int n2: nums2){
            m.computeIfAbsent(n2, k-> new HashSet<>()).add(2);
        }
        for(int n3: nums3){
            m.computeIfAbsent(n3, k-> new HashSet<>()).add(3);
        }
        List<Integer> res = new ArrayList<>();
        for(int ai: m.keySet()){
            if(m.get(ai).size()>=2){
                res.add(ai);
            }
        }
        return res;
    }
}

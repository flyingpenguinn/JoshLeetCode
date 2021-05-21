import java.util.ArrayList;
import java.util.List;

public class ProductOfTwoRunlengthEncodedArray {
    public List<List<Integer>> findRLEArray(int[][] v1, int[][] v2) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<v1.length && j<v2.length){
            int prod = v1[i][0]* v2[j][0];
            int num = Math.min(v1[i][1], v2[j][1]);
            v1[i][1] -= num;
            v2[j][1] -= num;
            if(v1[i][1]==0){
                i++;
            }
            if(v2[j][1]==0){
                j++;
            }
            // both i and j can be increased
            insert(res, prod, num);
        }
        while(i<v1.length){
            insert(res, v1[i][0], v1[i][1]);
            i++;
        }
        while(j<v2.length){
            insert(res, v2[j][0], v2[j][1]);
            j++;
        }
        return res;
    }

    private void insert(List<List<Integer>> res, int prod, int num){
        if(!res.isEmpty() && res.get(res.size()-1).get(0)==prod){
            res.get(res.size()-1).set(1, res.get(res.size()-1).get(1)+ num);
        }else{
            List<Integer> added = new ArrayList<>();
            added.add(prod);
            added.add(num);
            res.add(added);
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckIfOneStringSwap {
    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.equals(s2)){
            return true;
        }
        int n = s1.length();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(s1.charAt(i)!= s2.charAt(i)){
                list.add(i);
            }
        }
        if(list.size()>2 || list.size()==1){
            return false;
        }
        return s1.charAt(list.get(0)) == s2.charAt(list.get(1)) && s2.charAt(list.get(1)) == s1.charAt(list.get(0));
    }
}

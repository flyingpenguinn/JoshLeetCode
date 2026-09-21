import java.util.ArrayList;
import java.util.List;

public class SwapAdjacentLrString {
    // L in t are left to L in s, R are right to s. L/R splits the streak of each other
    public boolean canTransform(String s, String t) {
        int n = s.length();
        String ns = s.replaceAll("X", "");
        String nr = t.replaceAll("X", "");
        if(!ns.equals(nr)){
            return false;
        }
        List<Integer> ls = new ArrayList<>();
        List<Integer> rs = new ArrayList<>();
        for(int i=0; i<n; ++i){
            if(s.charAt(i)=='L'){
                ls.add(i);
            }else if(s.charAt(i)=='R'){
                rs.add(i);
            }
        }
        int li = 0;
        int ri = 0;
        for(int i=0; i<n; ++i){
            if(t.charAt(i)=='L'){
                int spos = ls.get(li++);
                if(i>spos){
                    return false;
                }
            }else if(t.charAt(i)=='R'){
                int spos = rs.get(ri++);
                if(i<spos){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new SwapAdjacentLrString().canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}

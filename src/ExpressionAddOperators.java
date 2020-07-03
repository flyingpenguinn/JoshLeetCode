import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#282
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 */
public class ExpressionAddOperators {
    // trick 1: get a multi cache for better enumeration
    // trick2: handle numbers starting with 0
    // trick3: handle long
    public List<String> addOperators(String s, int t) {
        List<String> r = new ArrayList<>();
        if(s==null){
            return r;
        }
        // s only has digits
        dfs(s, 0, 0, 0, "",t, r);
        return new ArrayList<>(r);
    }

    // at pos i, numbers before realized cur, pending numbers are waiting for decision about the sign before current num
    private void dfs(String s, int i, long cur, long pending, String sol, long t, List<String> r){
        if(i==s.length()){
            if(cur+pending == t){
                r.add(sol);
            }
            return;
        }
        long num = 0;
        for(int j=i; j<s.length(); j++){
            if(s.charAt(i)== '0' && j>i){
                break;
            }
            num = num*10L+ (s.charAt(j)-'0');
            // cur-> + -> num. note the first num is always an implicit +
            if(i==0){
                dfs(s, j+1, 0, num, String.valueOf(num), t, r);
            }else{
                dfs(s, j+1, cur+pending, num, sol+"+"+num, t, r);
                dfs(s, j+1, cur+pending, -num, sol+"-"+num, t, r);
                dfs(s, j+1, cur, pending*num, sol+"*"+num, t, r);
            }
        }
    }

    public static void main(String[] args) {
        //   System.out.println(new ExpressionAddOperators().addOperators("123", 6));
        //  System.out.println(new ExpressionAddOperators().addOperators("92345", 1));
        System.out.println(new ExpressionAddOperators().addOperators("105", 5));
    }
}

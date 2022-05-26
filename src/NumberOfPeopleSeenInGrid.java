import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfPeopleSeenInGrid {
    public int[][] seePeople(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] res = new int[m][n];
        for(int i=0; i<m; ++i){
            Deque<Integer> st = new ArrayDeque<>();
            for(int j=n-1; j>=0; --j){
                while(!st.isEmpty() && st.peek() < a[i][j]){
                    st.pop();
                    ++res[i][j];
                }
                if(!st.isEmpty() ){
                    ++res[i][j];
                }
                if(st.isEmpty() || st.peek() != a[i][j]){
                    st.push(a[i][j]);
                }
            }
        }
        for(int j=0; j<n; ++j){
            Deque<Integer> st = new ArrayDeque<>();
            for(int i=m-1; i>=0; --i){
                while(!st.isEmpty() && st.peek() < a[i][j]){
                    st.pop();
                    ++res[i][j];
                }
                if(!st.isEmpty() ){
                    ++res[i][j];
                }
                if(st.isEmpty() || st.peek() != a[i][j]){
                    st.push(a[i][j]);
                }
            }
        }
        return res;
    }
}

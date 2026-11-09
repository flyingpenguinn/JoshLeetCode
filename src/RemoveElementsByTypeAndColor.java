import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveElementsByTypeAndColor {
    public boolean canRemoveAll(int[] types, int[] colors, int[] groupSize) {
        int n = types.length;
        int m = groupSize.length;
        Deque<int[]> dq = new ArrayDeque<>();
        for(int i=0; i<n; ++i){
            if(dq.isEmpty()){
                dq.push(new int[]{types[i], colors[i], 1});
            }else if(dq.peek()[0]==types[i] && dq.peek()[1] == colors[i]){
                int[] top = dq.pop();
                top[2]+= 1;
                dq.push(top);
            }else{
                dq.push(new int[]{types[i], colors[i], 1});
            }
            if(!dq.isEmpty() && dq.peek()[2]==groupSize[dq.peek()[0]]){
             //   System.out.println("can pop up "+Arrays.toString(dq.peek()));
                dq.pop();
            }
          //  System.out.println("i="+i+ Arrays.toString(dq.peek()));
        }
        return dq.isEmpty();
    }
}

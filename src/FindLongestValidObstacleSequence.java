import java.util.ArrayList;
import java.util.List;

public class FindLongestValidObstacleSequence {
    public int lengthOfLIS(int[] a) {
        List<Integer> r=new ArrayList<>();
        int n=a.length;
        for(int i=0;i<n;i++){
            // first >,or found
            int index= bs(r,a[i]);
            if(index == r.size()){
                r.add(a[i]);
            }else{
                r.set(index,a[i]);
            }
        }
        return r.size();
    }

    int bs(List<Integer> a, int t){
        int l=0;
        int u=a.size()-1;
        while(l<=u){
            int m= l+(u-l)/2;
            if(a.get(m)<=t){
                l = m+1;
            }else{
                u = m-1;
            }
        }
        return l;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        List<Integer> r=new ArrayList<>();
        for(int i=0;i<n;i++){
            // first >,or found
            int index= bs(r,a[i]);
            if(index == r.size()){
                r.add(a[i]);
            }else{
                r.set(index,a[i]);
            }
            res[i] = index+1;
        }
        return res;
    }
}

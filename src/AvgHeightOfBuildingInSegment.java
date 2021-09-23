import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvgHeightOfBuildingInSegment {
    public int[][] averageHeightOfBuildings(int[][] a) {
        int n = a.length;
        List<int[]> lines = new ArrayList<>();
        for(int i=0; i<n; ++i){
            lines.add(new int[]{a[i][0], a[i][2], 0});
            lines.add(new int[]{a[i][1], a[i][2], 1});
        }
        Collections.sort(lines, (x, y)-> {
            return Integer.compare(x[0], y[0]);
        });
        int i = 0;
        int lastx = -1;
        int lastlen = 0;
        int sum = 0;
        int items = 0;
        List<int[]> res = new ArrayList<>();
        while(i<lines.size()){
            int x = lines.get(i)[0];
            int j=i;
            while(j<lines.size() && lines.get(j)[0] == lines.get(i)[0]){
                if(lines.get(j)[2]==0){
                    ++items;
                    sum += lines.get(j)[1];
                }else{
                    --items;
                    sum -= lines.get(j)[1];
                }
                ++j;
            }
            i=j;
            int clen = items==0? 0: sum/items;
            if(clen != lastlen){
                if(lastlen >0){
                    // no ins
                    res.add(new int[]{lastx, x, lastlen});
                }
                lastx = x;
                lastlen = clen;
            }
        }
        int[][] rr = new int[res.size()][3];
        for(i=0; i<res.size(); ++i){
            for(int j=0; j<3; ++j){
                rr[i][j] = res.get(i)[j];
            }
        }
        return rr;
    }
}

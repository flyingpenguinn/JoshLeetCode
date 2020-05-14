import java.util.Arrays;

public class ConnectingCitiesWithMinimumCost {
    int[] parent;
    int[] size;

    //kruskal,just sort, no pq needed!
    public int minimumCost(int n, int[][] c) {
        // start from 1 to n
        parent=new int[n+1];
        size =new int[n+1];

        Arrays.fill(parent,-1);
        Arrays.fill(size,1);
        Arrays.sort(c,(a,b)-> Integer.compare(a[2],b[2]));
        int cost=0;
        for(int i=0;i<c.length;i++){
            int[] top= c[i];
            if(find(top[0])==find(top[1])){
                continue;
            }
            cost += top[2];
            union(top[0],top[1]);
        }
        int roots=0;
        for(int i=1;i<=n;i++){
            if(parent[i]==-1){
                roots++;
            }
        }

        return roots>1?-1:cost;
    }

    int find(int i){

        if(parent[i]==-1){
            return i;
        }
        int rt= find(parent[i]);
        parent[i]=rt;
        return rt;
    }

    void union(int i,int j){
        int r1= find(i);
        int r2= find(j);
        if(size[r1]<=size[r2]){
            parent[r1]=r2;
            size[r2]+= size[r1];
        }else{
            parent[r2]=r1;
            size[r1] += r2;
        }
    }
}

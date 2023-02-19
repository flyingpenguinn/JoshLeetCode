import java.util.Arrays;

public class FindStringWithLcp {
    // aij>0 then they are the same
    public String findTheString(int[][] a) {
        int n = a.length;
        int[] ans = new int[n];
        int c = 0;
        for(int i=0; i<n; ++i){
            if(ans[i]!= 0){
                continue; // we assigned already
            }
            ++c;
            if(c>26){
                return "";
            }
            ans[i] = c;
            for(int j=i+1; j<n; ++j){
                if(a[i][j]>0 && ans[j]==0){
                    ans[j] = c;
                }
            }
        }
        //  System.out.println(Arrays.toString(ans));
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                if(ans[i]==ans[j]){
                    int later = (i+1<n && j+1<n)? a[i+1][j+1]: 0;
                    //  System.out.println("i="+i+" j="+j+" value="+a[i][j]+" later="+later);
                    if(a[i][j] != later+1){
                        return "";
                    }
                }else{
                    if(a[i][j] >0){
                        return "";
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i){
            sb.append((char)(ans[i]-1+'a'));
        }
        return sb.toString();
    }
}

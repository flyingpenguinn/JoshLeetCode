import java.util.*;
/*
LC#943
Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.


Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"


Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
 */
public class FindShortestSuperstring {
    // tsp without fixed start
    // cache match results between i and j dont need to calc over and over
    Map<BitSet,String>[] dp;
    int[][] mlen;
    public String shortestSuperstring(String[] a) {
        int n= a.length;
        mlen= new int[n][n];
        dp=new HashMap[n];
        for(int i=0;i<n;i++){
            dp[i]= new HashMap<>();
            for(int j=0;j<n;j++){
                if(i!=j){
                    mlen[i][j]=merge(a,i,j);
                }
            }
        }

        BitSet st= new BitSet(n);
        String min=null;

        for(int i=0;i<n;i++){
            String cur= tsp(a,i,st);
            if(min==null || min.length()>cur.length()){
                min=cur;
            }
        }
        return min;
    }

    String tsp(String[] a, int i,BitSet st){

        int n= a.length;
        if(st.cardinality()==n-1){
            return a[i];
        }

        String ch= dp[i].get(st);
        if(ch!=null){
            return ch;
        }
        BitSet ns= (BitSet)st.clone();
        ns.set(i);
        String min=null;
        for(int j=0;j<a.length;j++){
            if(ns.get(j)){
                continue;
            }

            String rt= tsp(a,j,ns);
            int cut2 = mlen[i][j];

            String cur= a[i]+rt.substring(cut2+1);
            if(min==null || min.length()>cur.length()){
                min=cur;
            }
        }
        dp[i].put(st,min);
        return min;
    }

    int merge(String[] a, int i, int j){
        String s1=a[i];
        String s2=a[j];
        int k=0;
        int maxk=-1;
        while(k<s2.length()){
            if(k>= s1.length()){
                break;
            }
            if(ends(s1,s2,k)){
                maxk=k;
            }
            k++;
        }

        return maxk;
    }

    boolean ends(String s1,String s2,int j){
        int i=s1.length()-j-1;
        int k=0;
        while(i<s1.length() && k<=j){
            if(s1.charAt(i++) != s2.charAt(k++)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //  String[] strs = {"catg", "ctaagt", "gcta", "ttca", "atgcatc"};
        String[] strs = {"uhozqhxcqmkifljvcie", "epuhozqhxcqmkifljvci", "ugmqnepuhozqhxcqm", "iexdudtvurjkrovrhmpa", "rovrhmpaasblgaosw", "qmkifljvciexdudtv", "zsgtuowskyzphybeugm", "uowskyzphybeugmq", "qhxcqmkifljvciex"};
        // String[] strs = {"ymv", "lpkp", "ajelp", "kpx"};
        System.out.println(new FindShortestSuperstring().shortestSuperstring(strs));
    }
}

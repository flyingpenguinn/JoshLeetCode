import java.util.Arrays;

public class SentenceSimilarityIII {
    public boolean areSentencesSimilar(String s1, String s2) {
        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" ");
        if(ss1.length == ss2.length){
            return Arrays.equals(ss1, ss2);
        }else if(ss1.length > ss2.length){
            return solve(ss1, ss2);
        }else{
            return solve(ss2, ss1);
        }
    }

    // we can insert in s2/j to match s1
    private boolean solve(String[] s1, String[] s2){
        int i = 0;
        int j = 0;
        boolean inserted = false;
        while(i<s1.length && j<s2.length){
            if(s1[i].equals(s2[j])){
                i++;
                j++;
            }else{
                if(inserted){
                    return false;
                }
                int k = i+1;
                while(k<s1.length && !s1[k].equals(s2[j])){
                    k++;
                }
                if(k==s1.length){
                    return false;
                }
                // s1k == s2j
                inserted = true;
                i = k+1;
                j++;
            }
        }
        if(i==s1.length && j==s2.length){
            return true;
        }else if(i<s1.length){
            return inserted? false: true;
        }else{
            return false;
        }
    }
}

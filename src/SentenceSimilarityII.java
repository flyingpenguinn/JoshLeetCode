import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
LC#737
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

 */
public class SentenceSimilarityII {

    // it's now transitive so we need to get all the connected components. either dfs or UF. we can dfs because we only solve components once
    String Nopa= "";
    Map<String,String> parent= new HashMap<>();
    Map<String,Integer> size= new HashMap<>();

    String find(String s){
        String cp= parent.get(s);

        if(cp==null){
            // new one
            parent.put(s,Nopa);
            size.put(s,1);
            return s;
        }else{
            if(cp== Nopa){
                return s;
            }
            String rp= find(cp);
            parent.put(s,rp);

            return rp;
        }
    }

    void union(String s1, String s2){
        String p1= find(s1);
        String p2= find(s2);
        if(p1.equals(p2)){
            // must do this to avoid circular
            return;
        }
        int size1= size.get(p1);
        int size2= size.get(p2);
        if(size1<size2){
            parent.put(p1,p2);
            size.put(p2, size1+size2);
        }else{
            parent.put(p2,p1);
            size.put(p1, size1+size2);
        }
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        int n1= words1.length;
        int n2= words2.length;
        if(n1!=n2){
            return false;
        }
        for(List<String> pi: pairs){
            String pi0= pi.get(0);
            String pi1= pi.get(1);
            union(pi0,pi1);

        }
        for(int i=0;i<n1;i++){
            String w1= words1[i];
            String w2= words2[i];
            if(w1.equals(w2)){
                continue;
            }
            if(find(w1).equals(find(w2))){
                continue;
            }
            return false;
        }
        return true;
    }
}

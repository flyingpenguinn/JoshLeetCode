import java.util.HashSet;

public class NamingACompany {
    //For two words A = a + suffix_a, B = b + suffix_b with different initials a, b, if the suffix of A (suffix_a) appears in both sets, they can't form a valid name. Thus we just need to find out the number of unique suffix from set A and set B .
    public long distinctNames(String[] ideas) {
        HashSet<Integer>[] head = new HashSet[26];
        for(int i=0; i<26; ++i){
            head[i] = new HashSet<>();
        }
        for(String s: ideas){
            String stub = s.substring(1);
            int cind = s.charAt(0)-'a';
            head[cind].add(stub.hashCode());
        }
        long res = 0;
        for(int i=0; i<26; ++i){
            for(int j=i+1; j<26; ++j){
                long p1 = 0;
                for(Integer hashed: head[i]){
                    if(!head[j].contains(hashed)){
                        ++p1;
                    }
                }
                long p2 = 0;
                for(Integer hashed: head[j]){
                    if(!head[i].contains(hashed)){
                        ++p2;
                    }
                }
                res += p1*p2;
            }
        }
        return res*2; // we have check i<j cases, now *2 to cover i>j cases
    }
}

import java.util.*;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] end = new int[26];
        Arrays.fill(end, -1);
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            int cind = c-'a';
            end[cind] = i;
        }
        int curend = end[s.charAt(0)-'a'];
        int curstart = 0;
        List<Integer> r = new ArrayList<>();
        for(int i=1; i<n; i++){
            char c = s.charAt(i);
            int cind = c-'a';
            if(i>curend){
                r.add(curend-curstart+1);
                curend = end[cind];
                curstart = i;
            }else{
                curend = Math.max(curend, end[cind]);
            }
        }
        r.add(curend-curstart+1);
        return r;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new PartitionLabels().partitionLabels("eaaaabaaec"));
        // System.out.println(new PartitionLabels().partitionLabels("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyfysysyysfsffsffysfsffyfssssyysyfssyrqrrrqqrqrqqrrrqrqrrqqqrqrrrqqqrrrddddddddddddddddddddddddddddddddduauaaauaalluluaaulllulllullualulllwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwjpjeejepjepeeejjpepeppeeppepjepepjpepkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzibiiibiiiibiiibbiiibbiibibibiibbiibibiiibixggxgxxcggxggxgxggggcxgxxccgototttotttotottttoottttotottotommmvvmmvmvvvvmmvmvmmmvvmmvmvmvvmmvvvvmmvv"));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}


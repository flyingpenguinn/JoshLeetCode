import java.util.*;

public class PartitionLabels {
    List<Integer> result = new ArrayList<>();
    int[] skip = null;

    public List<Integer> partitionLabels(String s) {
        skip = new int[s.length()];
        int[] posMap = new int[26];  // no need to use hashmap...
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int maxPos = posMap[c - 'a'];
            maxPos = Math.max(maxPos, i);
            posMap[c - 'a'] = maxPos;
        }
        skip[0] = posMap[s.charAt(0) - 'a'];
        for (int i = 1; i < s.length(); i++) {
            skip[i] = Math.max(skip[i - 1], posMap[s.charAt(i) - 'a']);
        }
        iterativePartition(s);
        return result;
    }

    private void iterativePartition(String s) {
        int i = 0;
        int index = 0;
        while (index < s.length()) {

            if (i >= skip[i]) {
                // key insight is we have no point in putting more stuff to left otherwise it won't be "most parts" solution. we could well split it
                int leftSize = i - index + 1;
                result.add(leftSize);
                index = i + 1;
                i = index;
            } else {
                i = skip[i];
            }
        }
    }



    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new PartitionLabels().partitionLabels("eaaaabaaec"));
        // System.out.println(new PartitionLabels().partitionLabels("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyfysysyysfsffsffysfsffyfssssyysyfssyrqrrrqqrqrqqrrrqrqrrqqqrqrrrqqqrrrddddddddddddddddddddddddddddddddduauaaauaalluluaaulllulllullualulllwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwjpjeejepjepeeejjpepeppeeppepjepepjpepkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzibiiibiiiibiiibbiiibbiibibibiibbiibibiiibixggxgxxcggxggxgxggggcxgxxccgototttotttotottttoottttotottotommmvvmmvmvvvvmmvmvmmmvvmmvmvmvvmmvvvvmmvv"));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}


// TLE
class PartitionLabelsDp {

    Map<Integer, Map<Integer, List<Integer>>> cache = new HashMap<>();
    Map<Character, Integer> posMap = new HashMap<>();

    public List<Integer> partitionLabels(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int maxPos = posMap.getOrDefault(c, i);
            maxPos = Math.max(maxPos, i);
            posMap.put(c, maxPos);
        }
        return doPartition(s, 0, s.length() - 1);
    }

    private List<Integer> doPartition(String s, int l, int u) {
        if (l == u) {
            List<Integer> singleList = new ArrayList<>();
            singleList.add(1);
            return singleList;
        }
        if (l > u) {
            return new ArrayList<>();
        }
        List<Integer> cached = cache.getOrDefault(l, new HashMap<>()).get(u);
        if (cached != null) {
            return cached;
        }
        int maxParts = 0;
        List<Integer> maxLeft = null;
        List<Integer> maxRight = null;
        int max = -1;
        for (int i = l; i < u; i++) {
            max = Math.max(max, posMap.get(s.charAt(i)));
            if (i >= max) {
                List<Integer> left = doPartition(s, l, i);
                List<Integer> right = doPartition(s, i + 1, u);
                int parts = left.size() + right.size();
                if (maxParts < parts) {
                    maxLeft = left;
                    maxRight = right;
                }
            } else {
                continue;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (maxLeft != null) {
            maxLeft.addAll(maxRight);
            result = maxLeft;
        } else {
            int len = u - l + 1;
            List<Integer> all = new ArrayList<>();
            all.add(len);
            result = all;
        }
        HashMap<Integer, List<Integer>> input = new HashMap<>();
        input.put(u, result);
        cache.put(l, input);
        return result;
    }
}
import java.util.HashMap;

public class CountPrefixAndSuffixPairsIandII {
    class TrieNode {
        HashMap<Integer, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    public long countPrefixSuffixPairs(String[] words) {
        TrieNode root = new TrieNode();
        long res = 0;
        for (String word : words) {
            TrieNode x = root;
            for (int i = 0, n = word.length(); i < n; ++i) {
                int key = word.charAt(i) * 128 + word.charAt(word.length() - i - 1);
                x = x.children.computeIfAbsent(key, p -> new TrieNode());
                res += x.count;
            }
            x.count++;
        }
        return res;
    }
}

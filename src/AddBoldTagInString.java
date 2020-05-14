import java.util.*;
/*
LC#616
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].l
 */
public class AddBoldTagInString {

    // simpler merge interval: bold[i] indicates whether i should be bold.
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] bold = new int[n+1];
        for(int i=0; i<n;i++){
            for(String d: dict){
                if(s.startsWith(d, i)){
                    for(int j=0; j<d.length();j++){
                        bold[i+j] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=n;i++){
            if( (i<n && bold[i]==1) && (i==0 || bold[i-1]==0)){
                sb.append("<b>");
            }
            if ( (i==n || bold[i]==0) && (i>0 && bold[i-1]==1)){
                sb.append("</b>");
            }
            if(i<n){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] dict = {"uf", "wa", "fa", "mu", "xd", "ty", "po", "mk", "sm", "ga", "bu"};
        long start = System.currentTimeMillis();
        String msg = new AddBoldTagInString().addBoldTag("uflwaifabmusxdttyjpowmkjsmwgambumwqxltxbtquekvuzpexklrocgfnouklguujyletswkmphdzvhenltfcgfkunafuppscvqacsoogmcduzjpmidrkbzdcyinejbjadslhkgjxozuptzjaggotpbumdumvrigwhdhaqpqopknmpocrjuuoyxjaoiusheugocinfylueyqugvzlozhumuvlyisoaznkkxelydvxvwuyosnjtnjlyxiouffpcasjppkjolyccrzyioleriavojrnrqabgnyuwyhxzvgetjqimguymwqqdimdhiulhhjuqqscdfrptvbcpyiihpujyesjxtwfeaahstewqahxttzqbpfzkrowcmmakhzjmaxwmxklkvgfcmughwdfbtcmszjarbadqhrduioyeyldpcgnvrdwliikaiozabgvsthxezcxzlvpkzlnatciaqgwywdhmdjmvwmudsrsvxklcdgyazidtkgkhggoqzqboxbqvmsomwwoxfybkjuvhbiwnodjgwhgwljyfcnazfpraadoqsxxnhgmvmfskbvavwqtddtonpsexvqpkgqgjbwlzdlkmcggrqkvxripcqrsinxyagiimiqcoirpgtehwrrofzxnnpzfklybhfvlfwinuwslivyronozjwtfvhlaazdzvucqferzvxmrliaqgoneehpktqfdohubdcwvwnzksbfftuimkgsonwvacgfvfngngkvboefqwhmruwfheidruvwdgfkuxkkigkuywjoyyxifkfyradqyghmoogoyrmoxmbsyymgxlfjroins", dict);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(msg);
    }
}

class AddBoldTagMergeInterval {
    class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public String addBoldTag(String s, String[] dict) {
        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            for (String d : dict) {

                if (s.startsWith(d, i)) {
                    ranges.add(new Range(i, i + d.length() - 1));
                }

            }
        }
        String str = mergeRange(ranges, s);
        return str;
    }

    private String mergeRange(List<Range> tm, String s) {
        Collections.sort(tm, (a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end));
        StringBuilder sb = new StringBuilder();
        if (tm.isEmpty()) {
            return s;
        }
        Iterator<Range> it = tm.iterator();
        Range first = it.next();
        int laststart = first.start;
        int lastend = first.end;
        int strstart = 0;

        while (it.hasNext()) {
            Range cur = it.next();
            if (cur.start == lastend + 1) {
                lastend = cur.end;// concat
            } else if (cur.start > lastend + 1) {
                handleAppend(s, sb, laststart, lastend, strstart);
                strstart = lastend + 1;
                // must reset lasts here
                laststart = cur.start;
                lastend = cur.end;
            } else if (cur.end >= lastend) {
                lastend = cur.end;
            }
        }
        handleAppend(s, sb, laststart, lastend, strstart);
        sb.append(s.substring(lastend + 1, s.length()));
        return sb.toString();
    }

    private void handleAppend(String s, StringBuilder sb, int laststart, int lastend, int strstart) {
        String append = s.substring(strstart, laststart);
        sb.append(append);
        sb.append("<b>");
        sb.append(s.substring(laststart, lastend + 1));
        sb.append("</b>");
    }
}


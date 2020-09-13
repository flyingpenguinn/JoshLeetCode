import java.util.*;

public class CheckIfStringIsTransformableWithSubstringSorting {
    // we cant move a number before sth that is smaller
    // so loop smaller numbers to see if there is one that is going to
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        List<Integer>[] list = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - '0';
            list[cind].add(i);
        }
        // note the brilliant usage of count to indicate the position of next such char in s
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            int cind = t.charAt(i) - '0';
            int index = count[cind];
            //we need to search the indexth value in s
            if (index >= list[cind].size()) {
                return false;
            }
            int poss = list[cind].get(index);
            // this is the position in s we need to move, from poss to i
            for (int j = 0; j < cind; j++) {
                int indexj = count[j];
                // this is the next j's index that we may want to check if appears before poss. note this position must be after i, because
                // this is the "next" j's index in s
                if (indexj < list[j].size() && list[j].get(indexj) < poss) {
                    // so later in s we have a smaller value that will move to before poss, not good becauseit's not doable...
                    return false;
                }
            }
            count[cind]++;

        }
        return true;
    }

    public static void main(String[] args) {

        //     System.out.println(new CheckIfStringIsTransformableWithSubstringSorting().isTransformable("432513576", "231435567"));
        //  System.out.println(new CheckIfStringIsTransformableWithSubstringSorting().isTransformable("84532", "34852"));
        System.out.println(new CheckIfStringIsTransformableWithSubstringSorting().isTransformable("848188", "818884"));
    }
}

public class FriendsOfAppropriateAge {
    public int numFriendRequests(int[] ages) {
        int[] am = new int[121];
        for (int a : ages) {
            am[a]++;
        }
        int[] accum = new int[121];
        for (int i = 1; i <= 120; i++) {
            accum[i] = accum[i - 1] + am[i];
        }

        int fr = 0;
        for (int i = 0; i < am.length; i++) {
            int c = am[i];
            if (c == 0) {
                continue;
            }
            int low = (int) (0.5 * i + 7 + 1);//int
            // sm for those >= low and <=i-1
            int sm = accum[i - 1] - accum[low - 1];
            // System.out.println(i+".."+sm);
            if (sm > 0) {
                // c*sm because can pair any at i
                fr += c * sm;
            }
            // 6 cant even connect to 6 because it's <10
            if (i >= low) {
                fr += c * (c - 1);
            }

        }
        return fr;

    }
}

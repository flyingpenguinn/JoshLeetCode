public class FindTheIndexOfLargeInteger {
    interface ArrayReader {
        // Compares the sum of arr[l..r] with the sum of arr[x..y]
        // return 1 if sum(arr[l..r]) > sum(arr[x..y])
        // return 0 if sum(arr[l..r]) == sum(arr[x..y])
        // return -1 if sum(arr[l..r]) < sum(arr[x..y])
        public int compareSub(int l, int r, int x, int y);

        // Returns the length of the array
        public int length();
    }

    // make sure the two parts are of equal length
    public int getIndex(ArrayReader reader) {
        int n = reader.length();
        int l = 0;
        int u = n - 1;
        while (l < u) {
            int mid = l + (u - l) / 2;
            int items = u - l + 1;
            if (items % 2 == 1) {
                int comp = reader.compareSub(l, mid - 1, mid + 1, u);
                if (comp == 0) {
                    return mid;
                } else if (comp == 1) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                int comp = reader.compareSub(l, mid, mid + 1, u);
                if (comp == 1) {
                    u = mid;
                } else {
                    l = mid + 1;
                }
                // when it's even we won't get 0
            }


        }
        return l;
    }
}

public class DeleteColumnsMakeSorted {
    public int minDeletionSize(String[] a) {
        int r = a.length;
        if (r == 0) {
            return 0;
        }
        int c = a[0].length();
        int count = 0;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r - 1; i++) {
                if (a[i].charAt(j) > a[i + 1].charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}

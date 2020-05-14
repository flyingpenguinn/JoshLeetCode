public class PeakInexInMountain {
    public int peakIndexInMountainArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (((i == 0) || a[i] > a[i - 1]) && ((i + 1 == a.length) || a[i] > a[i + 1])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,3,2,1};
        System.out.println(new PeakIndexBinarySearch().peakIndexInMountainArray(a));
    }
}

class PeakIndexBinarySearch {
    public int peakIndexInMountainArray(int[] a) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (mid - 1 >= 0 && mid + 1 < a.length && a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {
                return mid;
            } else if (mid - 1 >= 0 && a[mid] >= a[mid - 1]) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l == a.length ? a.length - 1 : l;
    }
}

import java.util.*;

/*
LC#480
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class SlidingWindowMedian {
    // treemap serve as heap
    // can't use map size that's key value pair count not the heap count! need another counter if we want to treat maps as heaps
    private TreeMap<Integer,Integer> q1 = new TreeMap<>(Collections.reverseOrder());
    private TreeMap<Integer,Integer> q2 = new TreeMap<>();
    private int size1 = 0;
    private int size2 = 0;

    public double[] medianSlidingWindow(int[] a, int k) {
        if(a==null || a.length==0 || k<=0 || k > a.length){
            return new double[0];
        }
        int n = a.length;
        double[] r = new double[n-k+1];
        int ri = 0;
        for(int i=0; i<n;i++){
            if (q1.isEmpty() || a[i]<=q1.firstKey()){
                update(q1, a[i], 1);
                size1++;
            }else{
                update(q2, a[i], 1);
                size2++;
            }
            adjust();
            int head = i-k+1;
            if(head>=0){
                if(k%2==0){
                    r[ri++] = (0.0+q1.firstKey()+q2.firstKey())/2.0;
                }else{
                    r[ri++] = q1.firstKey();
                }
                if(a[head] <= q1.firstKey()){
                    update(q1, a[head], -1);
                    size1--;
                }else{
                    update(q2, a[head], -1);
                    size2--;
                }
                adjust();
            }
        }
        return r;
    }

    private void update(TreeMap<Integer,Integer> m, int k, int delta){
        int nv = m.getOrDefault(k,0)+delta;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }


    private void adjust(){
        // q1.size >=q2.size but no more than 1
        if(size1 > size2 + 1){
            int k = q1.firstKey();
            update(q1, k, -1);
            update(q2, k, 1);
            size1--;
            size2++;
        }else if (size2>size1){
            int k = q2.firstKey();
            update(q2, k, -1);
            update(q1, k, 1);
            size2--;
            size1++;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {9, 7, 0, 3, 9, 8, 6, 5, 7, 6};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, 2)));
    }
}
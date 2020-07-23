/*
LC#1095
(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.



Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.


Constraints:

3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
 */
interface MountainArray {
    public int get(int index);

    public int length();
}

public class FindInMountainArray {
    // cache ma.get calls, and make sure we vet the l in the end when finding
    public int findInMountainArray(int t, MountainArray ma) {
        if (ma == null || ma.length() <= 0) {
            return -1;
        }
        int n = ma.length();
        int peakIndex = findPeak(0, n - 1, ma);
        if (peakIndex == -1) {
            return -1;
        }
        int index1 = findFirst(0, peakIndex, t, ma, true);
        if (index1 != -1) {
            return index1;
        }
        int index2 = findFirst(peakIndex + 1, n - 1, t, ma, false);
        return index2;
    }

    private int findPeak(int start, int end, MountainArray ma) {
        int l = start;
        int u = end;

        while (l <= u) {
            int mid = l + (u - l) / 2;
            int midv = ma.get(mid);
            if (mid == start || mid == end) {
                return mid;
            }
            int midm1 = ma.get(mid - 1);
            int midp1 = ma.get(mid + 1);
            if (midm1 < midv && midv > midp1) {
                return mid;
            } else if (midm1 < midv && midv < midp1) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return -1;
    }

    // prev < the next
    private int findFirst(int start, int end, int t, MountainArray ma, boolean inc) {
        int l = start;
        int u = end;
        int n = ma.length();
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int midv = ma.get(mid);
            if (inc) {
                if (midv >= t) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (midv <= t) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        if (l >= 0 && l < n && ma.get(l) == t) {
            return l;
        } else {
            return -1;
        }
    }
}

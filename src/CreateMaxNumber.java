import base.ArrayUtils;

import java.util.*;

/*
LC#321
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
 */
public class CreateMaxNumber {
    // O((m+n)^3).
    // 1. enumerate single list i, k-i numbers
    // 2. merge. note we must look ahead in order to merge
    public int[] maxNumber(int[] a, int[] b, int k) {
        List<Integer> res = null;
        for(int picka = 0; picka<=a.length; ++picka){
            int pickb = k-picka;
            if(pickb<0 || pickb>b.length){
                continue;
            }
            List<Integer> ra= pick(a, picka);
            List<Integer> rb = pick(b, pickb);
            List<Integer> merged = merge(ra, rb);
            if(res == null
                    || compare(merged, 0, res, 0)>0){
                res = merged;
            }
        }
        int[] rr = new int[res.size()];
        for(int i=0; i<res.size(); ++i){
            rr[i] = res.get(i);
        }
        return rr;
    }

    // pick k numbers to make it big. similar to "remove k digits"
    private List<Integer> pick(int[] a, int k){
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<n; ++i){
            while(!st.isEmpty() && st.peek()<a[i] && st.size()+n-i-1>=k){
                st.pop(); // -1 because we are popping up
            }
            if(st.size()<k){
                st.push(a[i]);
            }
        }
        while(!st.isEmpty()){
            res.add(st.pop());
        }
        Collections.reverse(res);
        return res;
    }

    // merge but with the caveat of looking ahead
    private List<Integer> merge(List<Integer> a, List<Integer> b){
        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList<>();
        while(i<a.size() && j<b.size()){
            int cmp = compare(a, i, b, j);
            if(cmp>=0){
                res.add(a.get(i++));
            }else if(cmp<0){
                res.add(b.get(j++));
            }
        }
        while(i<a.size()){
            res.add(a.get(i++));
        }
        while(j<b.size()){
            res.add(b.get(j++));
        }
        return res;
    }

    private int compare(List<Integer> a, int i, List<Integer> b, int j){
        while(i<a.size() && j<b.size()){
            if(a.get(i) > b.get(j)){
                return 1;
            }else if(a.get(i)<b.get(j)){
                return -1;
            }
            ++i;
            ++j;
        }
        if(i==a.size() && j==b.size()){
            return 0;
        }else{
            return i==a.size()? -1: 1;
        }
    }
}


import base.ArrayUtils;

import java.util.Arrays;

/*
LC#927
Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i+1 < j, such that:

A[0], A[1], ..., A[i] is the first part;
A[i+1], A[i+2], ..., A[j-1] is the second part, and
A[j], A[j+1], ..., A[A.length - 1] is the third part.
All three parts have equal binary value.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.



Example 1:

Input: [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: [1,1,0,1,1]
Output: [-1,-1]


Note:

3 <= A.length <= 30000
A[i] == 0 or A[i] == 1
 */
public class ThreeEqualParts {
    // rely on the fact that for the 3 parts to be equal, their 1s must equal.
    // then 0s at the end of the 3rd number decides the number itself, if they can be equal
    // note after counting 0s and 1s, we need to verify they are really equal
    public int[] threeEqualParts(int[] a) {
        int allones = 0;
        int n = a.length;
        int[] bad = new int[] { -1, -1};
        for(int i=0; i<n; i++){
            allones += a[i];
        }
        if(allones%3!= 0){
            return bad;
        }
        if(allones==0){
            return new int[]{0, n-1};
        }
        int t = allones/3;
        int rz = 0;
        for(int i=n-1; i>=0 && a[i]==0; i--){
            rz++;
        }
        int[] res = new int[2];
        int ones = 0;
        int i = 0;
        int times = 0;
        while(i<n && times<2){
            if(a[i]==1){
                ones++;
                if(ones==t){
                    res[times] = i+rz+1;
                    // calculating num2 and 3 starting point. will convert to num1 end later for res[0]
                    times++;
                    ones = 0;
                    i += rz+1;
                }else{
                    i++;
                }
            }else{
                i++;
            }
        }
        if(times<2){
            return bad;
        }
        while(i<n){
            if(a[i]==1){
                ones++;
            }
            i++;
        }
        if(ones==t){
            if(good(res,a)){
                // convert res[0] to num1 end
                return new int[]{res[0]-1, res[1]};
            }
        }
        return bad;
    }

    private boolean good(int[] res, int[] a){
        int n = a.length;
        int i = 0;
        int j = res[0];
        int k = res[1];
        while(i<n && a[i]==0){
            i++;
        }
        while(j<n && a[j]==0){
            j++;
        }
        while(k<n && a[k]==0){
            k++;
        }
        while(i<res[0] && j<res[1] && k<n){
            if(a[i]!= a[j] || a[j] != a[k] || a[k]!= a[i]){
                return false;
            }
            i++;
            j++;
            k++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,0,1"))));
        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,1,1,0"))));

        System.out.println(Arrays.toString(new ThreeEqualParts().threeEqualParts(ArrayUtils.read1d("1,0,0,0,1,0,1,0,0,1,0,0"))));
    }
}

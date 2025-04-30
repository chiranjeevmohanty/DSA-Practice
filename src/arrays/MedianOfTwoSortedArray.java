/*---Approach---*/
/*
 * Problem Statement: (LC: 4)
 *  You are given two sorted arrays `nums1` and `nums2`, and you need to find the **median** of the two sorted arrays.
 *  The overall run time complexity should be **O(log(min(m, n))**, where m and n are the lengths of the two arrays.
 *  The median is the middle value in the combined sorted array. If the total number of elements is even,
 *  then the median is the average of the two middle elements.
 *
 * ---Constraints---
 *  0 <= nums1.length, nums2.length <= 10^5
 *  0 <= nums1[i], nums2[i] <= 10^6
 *  The arrays are already sorted.
 *
 * -> Optimized Approach (Binary Search on Smaller Array):
 *  Time Complexity: O(log(min(m, n))) // Binary search on the smaller array only.
 *  Space Complexity: O(1) // Constant space.
 *
 * - First, we ensure binary search happens on the smaller array by swapping if needed.
 * - We perform a binary search to partition both arrays such that the left half has one more element if the total length is odd.
 * - We choose `i` as the cut in nums1 and compute `j = (m + n + 1)/2 - i` to make sure left and right halves are balanced.
 * - Define:
 *      Aleft  = max of left part from nums1
 *      Aright = min of right part from nums1
 *      Bleft  = max of left part from nums2
 *      Bright = min of right part from nums2
 * - We check the condition:
 *      Aleft <= Bright && Bleft <= Aright
 *   => if true, it means we have a valid partition:
 *      - If total length is odd, return max(Aleft, Bleft)
 *      - If total is even, return (max(Aleft, Bleft) + min(Aright, Bright)) / 2
 * - If Aleft > Bright, we move left (`r = i - 1`), otherwise move right (`l = i + 1`)
 * - This binary search ensures we always get the correct partition with logarithmic complexity.
 */

/*---Corner Cases---*/
/*
 * 1. One empty array
 *    - Input: nums1 = [], nums2 = [1]
 *    - Expected Output: 1.0
 *    - Explanation: Only one element exists, so it's the median.
 *
 * 2. Even total number of elements
 *    - Input: nums1 = [1, 2], nums2 = [3, 4]
 *    - Expected Output: 2.5
 *    - Explanation: Median is average of 2 and 3.
 *
 * 3. Uneven array sizes
 *    - Input: nums1 = [1, 3], nums2 = [2]
 *    - Expected Output: 2.0
 *    - Explanation: Merged = [1, 2, 3], median is 2.
 *
 * 4. Identical elements
 *    - Input: nums1 = [2, 2], nums2 = [2, 2]
 *    - Expected Output: 2.0
 *    - Explanation: All elements are the same, median is 2.
 */


package arrays;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
    }
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        int l = 0;
        int r = n;
        while(l <= r){
            int i = (l + r)/2;
            int j = (n + m + 1)/2 - i;
            int Aleft = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m)? Integer.MAX_VALUE : nums1[i];
            int Bleft = (j == 0)? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n)? Integer.MAX_VALUE : nums2[j];
            if(Aleft <= Bright && Bleft <= Aright){
                if((n + m)%2 == 0){
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright))/2.0;
                }else{
                    return Math.max(Aleft, Bleft);
                }
            }else if(Aleft > Bleft){
                r = i - 1;
            }else{
                l = i + 1;
            }
        }
        return 0;
    }
}

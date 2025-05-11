/*---Approach---*/
/*
 * Problem Statement: (LC:300)
 *  Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * ---Constraints---
 *  1 <= nums.length <= 2500
 *  -10^4 <= nums[i] <= 10^4
 *
 * ---Approaches---
 * ->Most Optimized Approach (Binary search):
 *  Time Complexity: O(nlog(n)) //For each element we are finding the correct place using binary search
 *  Space Complexity: O(n) //For the array list containing the smallest possible tails.
 * - 1st we need to create an ArrayList which will store the smallest possible
 *   last elements of increasing subsequences (not actual subsequences, just their potential ends).
 *
 * - Then we can iterate through the input array one element at a time.
 *
 * - For each element `num`, we check:
 *     → If it's greater than the last element in the ArrayList, we can extend the subsequence.
 *       So, we add `num` at the end of the list.
 *
 *     → If it's not greater, it means it could potentially be the start of a smaller
 *       increasing subsequence. In this case, we find the index in the list where
 *       `num` can replace the first element that is >= `num` (using binary search).
 *       This ensures the list stays optimized for future extensions.
 *
 * - The key idea is: the ArrayList does not store the real LIS, but by keeping the smallest
 *   possible values at each length, it helps us ensure the LIS length is correct.
 *
 * - At the end, the size of the ArrayList will be the length of the Longest Increasing Subsequence.
 */

/*---Corner Cases---*/
/*
 * 1. Strictly Increasing Array
 *    - Input: [1, 2, 3, 4, 5]
 *    - Expected Output: 5
 *    - Explanation: The whole array is already increasing, so the LIS is the array itself.

 * 2. Strictly Decreasing Array
 *    - Input: [5, 4, 3, 2, 1]
 *    - Expected Output: 1
 *    - Explanation: No two elements are in increasing order, so the LIS is any single element.

 * 3. Mixed Increasing and Decreasing
 *    - Input: [10, 9, 2, 5, 3, 7, 101, 18]
 *    - Expected Output: 4
 *    - Explanation: One possible LIS is [2, 3, 7, 101] or [2, 3, 7, 18].

 * 4. All Elements Equal
 *    - Input: [7, 7, 7, 7, 7]
 *    - Expected Output: 1
 *    - Explanation: Since all elements are equal, only one can be picked for an increasing subsequence.

 * 5. Duplicates with Increasing Pattern
 *    - Input: [1, 3, 5, 4, 7, 5, 6]
 *    - Expected Output: 5
 *    - Explanation: One possible LIS is [1, 3, 4, 5, 6] or [1, 3, 4, 5, 7].

 * 6. Long Increasing Then Drop
 *    - Input: [1, 2, 3, 10, 0]
 *    - Expected Output: 4
 *    - Explanation: [1, 2, 3, 10] is an increasing sequence.

 * 7. Zigzag Pattern
 *    - Input: [1, 3, 2, 4, 3, 5]
 *    - Expected Output: 4
 *    - Explanation: [1, 2, 3, 5] is a valid LIS.
 */


package dynamicprogramming;
import java.util.*;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    //using binary search
    static int lengthOfLIS(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int num: nums){
            int ind = Collections.binarySearch(arr, num);
            if(ind < 0){
                ind = -(ind + 1);
            }
            if(ind == arr.size()){
                arr.add(num);
            }else{
                arr.set(ind, num);
            }
        }
        return arr.size();
    }

    //TODO::Using dynamic programming(Top-down/Bottom-up)
}

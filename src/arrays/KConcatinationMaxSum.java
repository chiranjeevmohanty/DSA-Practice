/*---Approach---*/
/*
 * Problem Statement: (LC:1191)
 *  Given an integer array arr and an integer k, modify the array by repeating it k times.
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 * ---Constraints---
 *  1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 * ---Approaches---
 * ->Brute Force (kadane's algorithm):
 *  Time Complexity: O(n^2) //k can be as large as the size of the array.
 *  Space Complexity: O(n^2) //We have to store the replicated array.
 * - As a simple solution we can replicate the array k times after that we can apply kadane's algorithm.
 * - As we can see our n and k value can go up to 10^5 so the kadane's algorithm will also fail here
 *
 * ->Optimized Approach (kadane's algorithm):
 *  Time Complexity: O(n) //we are looping through the array at max 3 times.
 *  Space Complexity: O(n) //we are storing the replicated array in a variable.
 * - Here also we wil apply kadane's algorithm but with little modification.
 * - As we can see that we have to concatenate the array k-times, and then we have get max sum of sub array.
 * - Sub array means the contiguous part then we can say in between we have to consider the whole array.
 * - We can calculate the sum of the array if its -ve then if we will add this to our result it will always reduce the result.
 * - We only add the complete array if its sum is +ve.
 * - This only applies for the middle arrays.
 * - For start and end array we can apply kadane's algo by concatenating these two coz we can remove -ve numbers at the start and end.
 * - If k is 1 simply we can apply and return kadane's on the array.
 */

/*---Corner Cases---*/
/*
 * e.g.
 * 1. Empty Array
 *    - Input: `arr = []`, `k = 5`
 *    - Expected Output: `0`
 *    - Explanation: No elements exist, so maximum subarray sum is 0 regardless of `k`.
 *
 * 2. Single Negative Element
 *    - Input: `arr = [-5]`, `k = 3`
 *    - Expected Output: `0`
 *    - Explanation: All elements are negative, so taking empty subarray (sum 0) is best.
 *
 * 3. Single Positive Element
 *    - Input: `arr = [7]`, `k = 100000`
 *    - Expected Output: `700000`
 *    - Explanation: One positive element repeated 100000 times, so max sum = 7 * 100000.
 *
 * 4. All Zeros
 *    - Input: `arr = [0, 0, 0, 0]`, `k = 10`
 *    - Expected Output: `0`
 *    - Explanation: Sum of any subarray will always be 0.
 *
 * 5. Mixed Positives and Negatives, Total Sum Zero
 *    - Input: `arr = [1, 2, -3, 1, -1]`, `k = 4`
 *    - Expected Output: `4`
 *    - Explanation: Max subarray comes from local parts only; overall total sum = 0, so only use first two concatenations.
 *
 * 6. Very Large Positive Sum
 *    - Input: `arr = [5, 2, 3]`, `k = 100000`
 *    - Expected Output: `(sum of arr) * (k) = (10) * (100000) = 1000000`
 *    - Explanation: Since all elements are positive, max sum is whole array sum multiplied by k.
 *
 * 7. Large Negative Followed by Positive
 *    - Input: `arr = [-1000, 1, 2, 3]`, `k = 3`
 *    - Expected Output: `6`
 *    - Explanation: Best subarray is [1, 2, 3] repeated if needed; initial -1000 is avoided.
 *
 * 8. Alternating Positives and Negatives
 *    - Input: `arr = [2, -1, 2, -1, 2, -1]`, `k = 5`
 *    - Expected Output: `7`
 *    - Explanation: Best subarray picks all positives, managing negatives carefully.
 *
 * 9. k = 1 with Mixed Elements
 *    - Input: `arr = [1, -2, 1, 1, -2, 1]`, `k = 1`
 *    - Expected Output: `2`
 *    - Explanation: Since k = 1, only the original array is used for max subarray sum.
 *
 * 10. k = Very Large, Sum Negative
 *     - Input: `arr = [-1, -2, -3]`, `k = 100000`
 *     - Expected Output: `0`
 *     - Explanation: Array is negative overall, so best to take empty subarray with sum 0.
 */


package arrays;

public class KConcatinationMaxSum {
    public static void main(String[] args) {
        System.out.println(kConcatenationMaxSum(new int[]{1, 2}, 3));
    }
    static int kConcatenationMaxSum(int[] arr, int k) {
        int MOD = 1_000_000_007;
        if (k == 1) return (int)(kadanes(arr) % MOD);
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int[] arr2 = new int[arr.length * 2];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        System.arraycopy(arr, 0, arr2, arr.length, arr.length);
        if (sum <= 0) {
            return (int)(kadanes(arr2) % MOD);
        } else {
            long ans = kadanes(arr2) + (sum * (k - 2));
            return (int)(ans % MOD);
        }
    }

    static long kadanes(int[] nums) {
        long maxSum = 0;
        long curSum = 0;
        for (int num : nums) {
            curSum += num;
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }
}

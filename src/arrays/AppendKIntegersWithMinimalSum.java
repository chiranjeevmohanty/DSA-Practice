/*---Approach---*/
/*
 * Problem Statement: (LC: 2195)
 *  Given an array `nums` of positive integers and an integer `k`, append `k` positive integers
 *  that do **not** appear in `nums` such that the total sum of these appended numbers is **minimized**.
 *  Return that minimum possible sum.
 *
 * ---Constraints---
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *  1 <= k <= 10^8
 *
 * -> Optimized Approach (Sorting + Arithmetic Sum of Missing Integers):
 *  Time Complexity: O(n log n) // Due to sorting the array.
 *  Space Complexity: O(1) // Constant space, we just store a few variables.
 *
 * - First, sort the array so we can process the numbers in increasing order.
 * - We initialize `prev = 0` to track the previous number seen.
 * - Loop through each number in the sorted array:
 *     - Skip duplicates (since duplicates can't give us more missing numbers).
 *     - Calculate the **gap** between `prev` and current `num` using: `gap = num - prev - 1`
 *     - We can pick at most `Math.min(k, gap)` missing numbers in this range.
 *     - Add the sum of these numbers using the arithmetic series formula:
 *         sum = (start + end) * count / 2
 *         where start = prev + 1, end = prev + take
 *     - Subtract the number of used elements from `k`.
 *     - Update `prev = num`
 *     - If k becomes 0, break early.
 *
 * - If `k` is still greater than 0 after processing the array, that means we need to take the next `k` numbers
 *   starting after the last number (prev + 1 to prev + k), and sum them using arithmetic series again.
 *
 * - Finally, return the total accumulated sum.
 */

/*---Corner Cases---*/
/*
 * 1. All small numbers missing
 *    - Input: nums = [5, 6], k = 3
 *    - Expected Output: 6
 *    - Explanation: Smallest 3 missing = [1, 2, 3]; sum = 6
 *
 * 2. Gaps in-between
 *    - Input: nums = [1, 4, 6], k = 3
 *    - Expected Output: 9
 *    - Explanation: Missing = [2, 3, 5]; sum = 10
 *
 * 3. No missing gaps in array
 *    - Input: nums = [1, 2, 3, 4], k = 2
 *    - Expected Output: 9
 *    - Explanation: Append next 2 numbers after 4: [5, 6]; sum = 11
 *
 * 4. Duplicates in input
 *    - Input: nums = [1, 1, 2, 3], k = 2
 *    - Expected Output: 9
 *    - Explanation: Missing = [4, 5]; sum = 9
 */


package arrays;
import java.util.*;

public class AppendKIntegersWithMinimalSum {
    public static void main(String[] args) {
        System.out.println(minimalKSum(new int[]{1, 1, 1, 4}, 6));
    }
    static long minimalKSum(int[] nums, int k) {
        long ans = 0L;
        int prev = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length && k > 0; i++){
            if (nums[i] == prev) continue;
            int gap = nums[i] - prev - 1;
            if(gap >= k){
                ans += sum(prev + 1, prev + k);
                k = 0;
            }else{
                ans += sum(prev + 1, nums[i] - 1);
                k -= gap;
            }
            prev = nums[i];
        }
        if(k > 0){
            ans +=  sum(prev + 1, prev + k);
        }
        return ans;
    }
    static long sum(long l, long r){
        return  (r - l + 1) * (r + l)/2;
    }


    //using hashSet but giving TLE in some case
    static long minimalKSum2(int[] nums, int k) {
        long ans = 0L;
        int numsCount = k;
        HashSet<Integer> set = new HashSet<>(nums.length);
        for(int num: nums){
            set.add(num);
        }
        int start = 1;
        while (numsCount > 0 && start < nums[nums.length - 1]){
            if(!set.contains(start)){
                ans += start;
                numsCount--;
            }
            start++;
        }
        return ans;
    }
}

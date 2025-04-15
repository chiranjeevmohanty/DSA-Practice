/*---Approach---*/
/*
 * Problem Statement: (LC:213)
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * ---Constraints---
 *  1 <= nums.length <= 100
 *  0 <= nums[i] <= 1000
 *
 * ---Approaches---
 * ->Brute Force (recursion):
 *  Time Complexity: O(2 ^ n) //Each recursive function calling two functions inside body.
 *  Space Complexity: O(n) //call stack memory usage
 * - simply we have two options either take the current one and skip previous one or else take next one and skip current one.
 * - But the catch is the array is circular so we cant take both start and end index. so you do the operation twice for (start to end - 1) and (start + 1 to end).
 * - and at the end we take max of this two things.
 *
 * ->Optimized Approach (dynamic programming(top-down memoization)):
 *  Time Complexity: O(n) //we are storing the data in memo array avoiding overlapping calculations.
 *  Space Complexity: O(n) //memo array and call stack
 * - We do same recursive approach but this time we will store our computed values in the memo array.
 *
 * ->Most Optimized Approach (dynamic programming(bottom-up tabulation)):
 *  Time Complexity: O(n) //simple recursion
 *  Space Complexity: O(1) //few variables
 *
 */

/*---Corner Cases---*/
/*
 * 1. Single House
 *    - Input: [5]
 *    - Expected Output: 5
 *    - Explanation: Only one house, so the robber takes the money.
 *
 * 2. Two Houses (Circular)
 *    - Input: [2, 3]
 *    - Expected Output: 3
 *    - Explanation: Since first and last house are adjacent, robber can choose only one. 3 is max.
 *
 * 3. Three Houses
 *    - Input: [2, 3, 2]
 *    - Expected Output: 3
 *    - Explanation: Can't rob both first and last (2 + 2). Max is just robbing 3 in the middle.
 *
 * 4. Multiple Houses
 *    - Input: [1, 2, 3, 1]
 *    - Expected Output: 4
 *    - Explanation: Rob house 1 (2) and house 2 (3) → only valid when skipping either 0 or 3. Best path is 1 + 3 = 4.
 *
 * 5. Larger List
 *    - Input: [2, 7, 9, 3, 1]
 *    - Expected Output: 11
 *    - Explanation: Can’t rob both 2 (index 0) and 1 (last). Best path: rob 9 + 2 = 11.
 *
 * 6. All Same Values
 *    - Input: [5, 5, 5, 5]
 *    - Expected Output: 10
 *    - Explanation: Rob house 0 and 2, or 1 and 3 → both give 10.
 *
 * 7. High Peak in the Middle
 *    - Input: [1, 1, 100, 1, 1]
 *    - Expected Output: 102
 *    - Explanation: Rob house 2 (100) and house 0 (1) or 4 (1). Max = 1 + 100 + 1 = 102, respecting adjacency.
 *
 * 8. Empty Array
 *    - Input: []
 *    - Expected Output: 0
 *    - Explanation: No houses to rob.
 *
 * 9. Long List with Peaks
 *    - Input: [6, 7, 1, 30, 8, 2, 4]
 *    - Expected Output: 41
 *    - Explanation: Best path: 7 + 30 + 4 = 41.
 */

package dynamicprogramming;
import java.util.*;

public class HouseRobberII {
    public static void main(String[] args) {
        System.out.println(rob2(new int[]{2, 3, 2}));
    }
    //top-down approach with memoization
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        int res1 = helper(nums.length - 2, 0, nums, memo);
        Arrays.fill(memo, -1);
        int res2 = helper(nums.length - 1, 1, nums, memo);
        return Math.max(res1,res2);
    }
    public int helper(int n, int start, int[] nums, int[] memo){
        if(n < start) return 0;
        if(memo[n] != -1) return memo[n];
        return memo[n] = Math.max(helper(n - 1, start, nums, memo), nums[n] + helper(n - 2, start, nums, memo));
    }

    //bottom-up tabulation with space optimized
    static int rob2(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] res = new int[2];
        for(int i = 0; i < nums.length - 1; i++){
            int temp = res[1];
            res[1] = Math.max(nums[i] + res[0], res[1]);
            res[0] = temp;
        }
        int[] res2 = new int[2];
        for(int i = 1; i < nums.length; i++){
            int temp = res2[1];
            res2[1] = Math.max(nums[i] + res2[0], res2[1]);
            res2[0] = temp;
        }
        return Math.max(res[1], res2[1]);
    }
}

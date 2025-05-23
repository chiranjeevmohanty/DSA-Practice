package dynamicprogramming;
import java.util.*;

public class PartitionEqualSubSetSum {
    //DP::top-down memoization
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        int n = nums.length;
        for(int num: nums){
            totalSum+=num;
        }
        if(totalSum % 2 == 1) return false;
        int target = totalSum/2;
        int[][] memo = new int[n][target + 1];
        for(int[] arr: memo){
            Arrays.fill(arr, -1);
        }
        return helper(nums, nums.length - 1, target, memo);
    }
    public boolean helper(int[] nums, int ind, int target, int[][] memo){
        if(ind == 0) return nums[ind] == target;
        if(target == 0) return true;

        if(memo[ind][target] != -1) return memo[ind][target] == 1;
        boolean notTake = helper(nums, ind - 1, target, memo);
        boolean take = false;
        if(nums[ind] <= target){
            take = helper(nums, ind - 1, target - nums[ind], memo);
        }
        memo[ind][target] = notTake || take ? 1 : 0;
        return notTake || take;
    }
    //DP::bottom-up tabulation
    public boolean canPartition2(int[] nums) {
        int totalSum = 0;
        int n = nums.length;
        for(int num: nums){
            totalSum+=num;
        }
        if(totalSum % 2 == 1) return false;
        int target = totalSum/2;
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notTake = dp[i - 1][j];
                boolean take = false;
                if (nums[i] <= j) {
                    take = dp[i - 1][j - nums[i]];
                }
                dp[i][j] = take || notTake;
            }
        }
        return dp[n - 1][target];
    }
}

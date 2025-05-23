package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;

public class PartitionTokEqualSum {
    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }
    static boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for(int num: nums) totalSum+=num;
        if(totalSum % k != 0) return false;
        int target = totalSum/k;
        Arrays.sort(nums);
        HashMap<Integer, Boolean> memo = new HashMap<>();
        return helper(nums, 0, target, k, 0, memo);
    }
    static boolean helper(int[] nums, int curSum, int target, int k, int bitmask, HashMap<Integer, Boolean> memo){
        if(k == 1) return true;
        if(curSum == target) {
            return helper(nums, 0, target, k - 1, bitmask, memo);
        }

        if(memo.get(bitmask) != null) return memo.get(bitmask);

        for(int i = 0; i < nums.length; i++){
            if((bitmask & (1 << i)) != 0) continue;
            if(curSum + nums[i] > target) continue;
            if(helper(nums, curSum + nums[i], target, k, bitmask  | (1 << i), memo)){
                memo.put(bitmask, true);
                return true;
            }
        }
        memo.put(bitmask, false);
        return false;
    }
}

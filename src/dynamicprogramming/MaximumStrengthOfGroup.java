/*---Approach---*/
/*
 * Problem Statement: (LC:2708)
 *  You are given a 0-indexed integer array nums representing the score of students in an exam. The teacher would like to form one non-empty group of students with maximal strength, where the strength of a group of students of indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​].
 *  Return the maximum strength of a group the teacher can create.
 *
 * ---Constraints---
 *  1 <= nums.length <= 13
 *  -9 <= nums[i] <= 9
 *
 * ---Approaches---
 * ->Brute Force (recursive):
 *  Time Complexity: O(2 ^ n) //As this is a sub-sequence problem there will be 2^n number of steps to get all the subsequence using recursion.
 *  Space Complexity: O(n) //for the call stack
 * - Simply we will recursively get all the answers, and we will return the max of the answer by taking the element or elase not taking the element.
 * - But the problem with this approach is some corner case will fail here we have to handel those manually.
 *
 * ->Optimized Approach (recursive):
 *  Time Complexity: O(n) //we will loop through the array max once
 *  Space Complexity: O(1) //No extra space required just some constant variables
 * - The intuition behind the solution is which numbers we can neglect and which we have to keep.
 * - And as given in the question the sub-sequence should be non-empty means we have to take at least one element.
 * - If we think care fully we need the multiplication value that means we have to avoid the zeroes.
 * - And max means it should be the multiplied value should be a +ve number. It means we have to take even number of negative values so that it will cancel the negative.
 * - In 1st step we will count the numbers of +ve, -ve and zeroes and the smallest positive number in considering there absolute value. And we will multiply the non-zero numbers in the same loop.
 * - In the next step we will observe the counts if both +ve and -ve are zero then there is only zeroes so we will return zero.
 * - Next case if there is odd number of negative number then we have to drop one number. we can simply divide the whole value with smallest -ve number.
 * - There will be some corner cases as well we have to handle.
 *  1: If there is only one number that means simply we will return that number cox sub-sequence can't be empty.
 *  2: If there is one negative and all zeroes. we have to return zero.
 */

/*---Corner Cases---*/
/*
 * 1. Single Positive Element
 *    - Input: `nums = [7]`
 *    - Expected Output: `7`
 *    - Explanation: Only one positive number; product is the number itself.
 *
 * 2. Single Negative Element
 *    - Input: `nums = [-5]`
 *    - Expected Output: `-5`
 *    - Explanation: Only one negative number; cannot pair to make positive.
 *
 * 3. Single Zero Element
 *    - Input: `nums = [0]`
 *    - Expected Output: `0`
 *    - Explanation: Only zero present; maximum product is zero.
 *
 * 4. All Positive Numbers
 *    - Input: `nums = [2, 3, 5, 7]`
 *    - Expected Output: `210`
 *    - Explanation: Multiplying all positives gives maximum product.
 *
 * 5. All Negative Numbers (Even Count)
 *    - Input: `nums = [-1, -2, -3, -4]`
 *    - Expected Output: `24`
 *    - Explanation: Even number of negatives results in positive product.
 *
 * 6. All Negative Numbers (Odd Count)
 *    - Input: `nums = [-2, -3, -4]`
 *    - Expected Output: `12`
 *    - Explanation: Best to exclude one negative to maximize product.
 *
 * 7. Zeros and Negatives Mixed
 *    - Input: `nums = [-2, 0, -1]`
 *    - Expected Output: `2`
 *    - Explanation: Multiplying `-2` and `-1` gives 2; zero is ignored.
 *
 * 8. Positive and Large Negative
 *    - Input: `nums = [10, -10, 5]`
 *    - Expected Output: `50`
 *    - Explanation: Ignoring `-10` gives maximum product `10 * 5 = 50`.
 *
 * 9. Multiple Zeros
 *    - Input: `nums = [0, -1, 0, -2, 0]`
 *    - Expected Output: `2`
 *    - Explanation: Multiplying `-1` and `-2` gives 2; zeros are ignored.
 *
 * 10. Large Mixed Elements
 *     - Input: `nums = [1, 2, 3, 4, 5, -6, -7, -8, -9, 0, 10, 11, 12]`
 *     - Expected Output: `479001600`
 *     - Explanation: Select numbers carefully to maximize product considering negatives and positives.
 */


package dynamicprogramming;
import java.util.*;

public class MaximumStrengthOfGroup {
    public static void main(String[] args) {
        System.out.println(maxStrength2(new int[]{0, -1}));
    }
    static long maxStrength(int[] nums) {
        long[] memo = new long[nums.length];
        Arrays.fill(memo, -1);
        return helper(nums, 0, 1L, memo);
    }
    //recursion with memoization Note:failing some edge cases
    static long helper(int[] nums, int index, long mul, long[] memo){
        if(index == nums.length) return mul;
        if(memo[index] != -1) return memo[index];
        memo[index] = Math.max(helper(nums, index + 1, mul * nums[index], memo), helper(nums, index + 1, mul, memo));
        return memo[index];
    }

    //iterative
    static long maxStrength2(int[] nums) {
        int negativeCount = 0;
        int positiveCount = 0;
        int zeroCount = 0;
        long mul = 1;
        int min = Integer.MIN_VALUE;
        for(int num: nums){
           if(num < 0){
               min = Math.max(min, num);
               negativeCount++;
               mul *= num;
           }else if(num > 0){
               positiveCount++;
               mul *= num;
           }else{
               zeroCount++;
           }
        }
        if(negativeCount % 2 != 0) {
            mul /= min;
        }
        if(negativeCount == 0 && positiveCount == 0){
            return 0;
        }
        if(negativeCount == 1){
            if(zeroCount > 0 && zeroCount + negativeCount == nums.length){
                return 0;
            }
            if(nums.length == 1){
                return (long)nums[0];
            }
        }
        return mul;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:2967)
 * You are given a 0-indexed integer array nums having length n.
 * You are allowed to perform a special move any number of times (including zero) on nums. In one special move you perform the following steps in order:
 * Choose an index i in the range [0, n - 1], and a positive integer x
 * Add |nums[i] - x| to the total cost.
 * Change the value of nums[i] to x.
 * A palindromic number is a positive integer that remains the same when its digits are reversed. For example, 121, 2552 and 65756 are palindromic numbers whereas 24, 46, 235 are not palindromic numbers.
 * An array is considered equalindromic if all the elements in the array are equal to an integer y, where y is a palindromic number less than 109.
 * Return an integer denoting the minimum possible total cost to make nums equalindromic by performing any number of special moves.
 *
 * ---Constraints---
 *  1 <= n <= 105
 *  1 <= nums[i] <= 109
 *
 * ---Approaches---
 * ->Optimized Approach (Math, Linear search):
 *  Time Complexity: O(nlog(n)) //For the sorting of the array to find the median. Although finding the closest palindrome takes d * k(d = distance from median to the palindrome and k is the length of number).
 *  Space Complexity: O(1) //No extra space required jus some variables and we are sorting the array in place.
 * - 1st we need to find the median of the array coz that will be the perfect candidate to start with.
 * - To find the median we need to sort the array first then we can take median if its an even length array we can take any of the median. lets keep it simple m = nums[nums.length / 2].
 * - To check whether a number is palindrome or not we can create a function to check.
 * - After that if we want to construct a palindromic number that we need to convert each number to a palindromic number so if we combine those it will create a palindromic number.
 * - So we need to find the closest palindromic number that can be smaller or bigger so we have to possibilities here.
 * - To find the palindromic number we are using linear search (Note: You might be thinking if we have the sorted range why we are not using binary search coz in worst case it will traverse all the element of the range).
 * - After finding te two closest palindromic numbers we can run a for loop to calculate the cost by taking absolute value of num - palindromicNumber.
 * - At the end we can return min of the two values and that will be our answer.
 */

/*---Corner Cases---*/
/*
 *
 * 1. Single Element Array (Already Palindromic)
 *    - Input: nums = [121]
 *    - Expected Output: 0
 *    - Explanation: The array already contains a palindromic number; no changes are needed.
 *
 * 2. Single Element Array (Non-Palindromic)
 *    - Input: nums = [123]
 *    - Expected Output: Minimum cost to change 123 to the nearest palindromic number.
 *    - Explanation: Only one element needs to be changed to its nearest palindrome.
 *
 * 3. All Elements Already Palindromic and Identical
 *    - Input: nums = [22, 22, 22]
 *    - Expected Output: 0
 *    - Explanation: All elements are the same palindromic number; no changes are needed.
 *
 * 4. All Elements Already Palindromic but Different
 *    - Input: nums = [11, 22, 33]
 *    - Expected Output: Minimum cost to make all elements the same palindromic number.
 *    - Explanation: Choose a palindromic number and change all elements to it with minimal cost.
 *
 * 5. Mixed Palindromic and Non-Palindromic Elements
 *    - Input: nums = [10, 22, 31]
 *    - Expected Output: Minimum cost to make all elements the same palindromic number.
 *    - Explanation: Convert all elements to a common palindromic number with minimal cost.
 *
 * 6. Large Numbers Close to Upper Bound
 *    - Input: nums = [999999999, 100000000, 123456789]
 *    - Expected Output: Minimum cost to make all elements the same palindromic number.
 *    - Explanation: Handle large numbers and find the optimal palindromic number for minimal cost.
 *
 * 7. Array with Two Elements, One Palindromic
 *    - Input: nums = [121, 130]
 *    - Expected Output: Minimum cost to make both elements the same palindromic number.
 *    - Explanation: Decide whether to change 130 to 121 or both to another palindrome.
 *
 * 8. Array with Two Elements, Both Non-Palindromic
 *    - Input: nums = [123, 456]
 *    - Expected Output: Minimum cost to make both elements the same palindromic number.
 *    - Explanation: Convert both to a common palindromic number with minimal cost.
 *
 * 9. Array with Elements Requiring Large Changes
 *    - Input: nums = [1, 1000000000]
 *    - Expected Output: Minimum cost to make both elements the same palindromic number.
 *    - Explanation: Assess the cost-effectiveness of changing small and large numbers to a common palindrome.
 *
 * 10. Array with Sequential Numbers
 *     - Input: nums = [10, 11, 12, 13, 14]
 *     - Expected Output: Minimum cost to make all elements the same palindromic number.
 *     - Explanation: Determine the optimal palindromic number that minimizes the total change cost.
 */


package arrays;
import java.util.*;

public class MinCostToMakeArrEqualindromic {
    public static void main(String[] args) {
        System.out.println(minimumCost(new int[]{206,215,216,219,220,221}));
    }
    static long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int m = nums[nums.length / 2];
        int palindrome1 = search(m, true);
        int palindrome2 = search(m, false);
        long count1 = 0L;
        long count2 = 0L;
        for(int num : nums){
            count1 += Math.abs(palindrome1 - num);
            count2 += Math.abs(palindrome2 - num);
        }
        return Math.min(count1, count2);
    }
    static int search(int target,boolean searchRight){
        if (searchRight) {
            // Find the first palindrome >= target
            for (int i = target; ; i++) {
                if (checkEqualindromic(String.valueOf(i))) return i;
            }
        } else {
            // Find the last palindrome <= target
            for (int i = target; i >= 0; i--) {
                if (checkEqualindromic(String.valueOf(i))) return i;
            }
        }
        return -1;
    }
    static boolean checkEqualindromic(String s){
        int start = 0;
        int end = s.length() - 1;
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:3020)
 *  You are given an array of positive integers nums.
 * You need to select a subset of nums which satisfies the following condition:
 * You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
 * Return the maximum number of elements in a subset that satisfies these conditions.
 *
 * ---Constraints---
 *  2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * ->Optimized Approach (hashTable, iteration):
 *  Time Complexity: O(n) //We are looping through the array twice.
 *  Space Complexity: O(n) //For the freq map.
 * - So in this problem the intuition is that there should be at least pair of the desired numbers to form the pattern.
 * - As we can see the result will always be odd. coz the middle will be always one there can't be two middles.
 * - In the 1st step we need to store the frequency in the in a frequency map so that we can use it later.
 * - In the next step we will iterate through each item so that we can see which one is forming the longer pattern.
 * - Special case: one thing we need to consider that in case of 1 the odd number of one's can form the pattern coz any power of one is one only.
 * - Next we will go and check for the pattern and store the max length of the pattern.
 * - Then at end we will return the max of odd ones count and the max length of the pattern.
 */

/*---Corner Cases---*/
/*
 * 1. Minimal Case
 *    - Input: `nums = [2, 4]`
 *    - Expected Output: `2`
 *    - Explanation: 2 → 4; basic chain with 2 elements.

 * 2. Perfect Small Chain
 *    - Input: `nums = [2, 4, 16, 4, 2]`
 *    - Expected Output: `5`
 *    - Explanation: 2 → 4 → 16 → 4 → 2 forms full mirror.

 * 3. Chain Broken Midway
 *    - Input: `nums = [2, 4, 5, 16, 4, 2]`
 *    - Expected Output: `5`
 *    - Explanation: Ignore 5; remaining numbers form a beautiful subset.

 * 4. Full Mirror With Large Numbers
 *    - Input: `nums = [2, 4, 16, 256, 65536, 256, 16, 4, 2]`
 *    - Expected Output: `9`
 *    - Explanation: Full mirror structure: 2 → 4 → 16 → 256 → 65536 → 256 → 16 → 4 → 2.

 * 5. Multiple Starting Points (Duplicates)
 *    - Input: `nums = [3, 9, 81, 6561, 81, 9, 3]`
 *    - Expected Output: `7`
 *    - Explanation: 3 → 9 → 81 → 6561 → 81 → 9 → 3 is valid mirror.

 * 6. Reversed Input Order
 *    - Input: `nums = [65536, 256, 16, 4, 2, 4, 16, 256, 65536]`
 *    - Expected Output: `9`
 *    - Explanation: Reorder to match: 2 → 4 → 16 → 256 → 65536 → 256 → 16 → 4 → 2.

 * 7. No Chain Possible
 *    - Input: `nums = [7, 11, 13, 17, 19, 23]`
 *    - Expected Output: `1`
 *    - Explanation: No exponential chain; best is single element.

 * 8. Chain Only Partial
 *    - Input: `nums = [2, 3, 4, 16]`
 *    - Expected Output: `2`
 *    - Explanation: 2 → 4 is valid, 3 does not fit.

 * 9. Max Size Input (Random Values)
 *    - Input: `nums = [1, 2, 3, 4, 5, ..., 100000]`
 *    - Expected Output: `1`
 *    - Explanation: No beautiful chain likely; pick any single element.

 * 10. Max Size Input (Full Chain Possible)
 *     - Input: Array containing many copies of [2, 4, 16, 4, 2]
 *     - Expected Output: Maximum possible beautiful subset size
 *     - Explanation: By duplicating [2,4,16,4,2] pattern many times, we maximize the subset length.
 */


package arrays;
import java.util.*;

public class MaximumNoOfElementInSubset {
    public static void main(String[] args) {
        System.out.println(maximumLength(new int[]{2, 4, 16, 96, 16, 96, 9216, 8, 2, 4, 1, 2}));
    }
    static int maximumLength(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int num: nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxCount = Integer.MIN_VALUE;
        for(int num: nums){
            int count = 0;
            if(num == 1) continue;
            long curVal = num;
            while(freq.get((int)curVal) != null && freq.get((int)curVal) >= 2){
                count+=2;
                curVal = (long)(Math.pow(curVal, 2));
            }
            if(freq.get((int)curVal) != null){
                count++;
            }else{
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }
        int onesCount = 0;
        if(freq.get(1) != null){
            onesCount = freq.get(1) % 2 == 0 ? freq.get(1) - 1 : freq.get(1);
        }
        return Math.max(onesCount, maxCount);
    }
}

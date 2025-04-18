/*---Approach---*/
/*
 * Problem Statement: (LC:2202)
 *  You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.
 * In one move, you can perform either of the following:
 * If the pile is not empty, remove the topmost element of the pile.
 * If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
 * You are also given an integer k, which denotes the total number of moves to be made.
 * Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1.
 *
 * ---Constraints---
 * 1 <= nums.length <= 105
 * 0 <= nums[i], k <= 109
 *
 * ->Optimized Approach (Math, Iteration):
 *  Time Complexity: O(n) //Traversing the array at most once
 *  Space Complexity: O(1) //No extra space required just some variables
 * - Things to notice here is if we can obtain the max at the pile by using strictly k steps. SO we have to use all steps.
 * - And if after k-moves the pile is empty we have to simply return -1. IN this case if it only contains one number and k is not divisible by 2 then we can say we at the end we wil have an empty pile.
 * - In the next step we have traverse the array k - 2 times coz we cant take the k-1th element coz again we have to put the element back to the pile.
 * - After that we will make a condition that if k < nums.length then if after removing k elements the remaining element will be nums[k] if this num is greater than our max then we can say this is the answer.
 * - At the end we will return our answer
 *
 */

/*---Corner Cases---*/
/*
 * 1. Empty Stack
 *    - Input: nums = [], k = 1
 *    - Expected Output: -1
 *    - Explanation: The stack is empty. Any operation keeps it empty, so there's no top element.
 *
 * 2. No Operations Allowed
 *    - Input: nums = [5, 2, 1], k = 0
 *    - Expected Output: 5
 *    - Explanation: No operations allowed, so the top element stays as is.
 *
 * 3. One Operation - Only One Element
 *    - Input: nums = [10], k = 1
 *    - Expected Output: -1
 *    - Explanation: One pop must be performed. The stack becomes empty.
 *
 * 4. One Operation - Multiple Elements
 *    - Input: nums = [3, 2, 1], k = 1
 *    - Expected Output: 2
 *    - Explanation: Remove the top (3), the next top becomes 2.
 *
 * 5. k == nums.length
 *    - Input: nums = [1, 2, 3], k = 3
 *    - Expected Output: 2
 *    - Explanation: Remove all 3 elements in sequence. You can push one back as the final move. Max of first k-1 = max([1,2]) = 2.
 *
 * 6. k > nums.length
 *    - Input: nums = [4, 3], k = 5
 *    - Expected Output: 4
 *    - Explanation: All elements can be removed. One can be pushed back. You can choose the max of the removed values.
 *
 * 7. k == 1, best to pop once
 *    - Input: nums = [1, 100], k = 1
 *    - Expected Output: 100
 *    - Explanation: Pop 1, now 100 is on top.
 *
 * 8. Maximum at index >= k
 *    - Input: nums = [1, 2, 3, 100], k = 2
 *    - Expected Output: 2
 *    - Explanation: Can only access first k elements. Max([1,2]) = 2
 *
 * 9. Pushing previously removed max
 *    - Input: nums = [10, 1, 2], k = 3
 *    - Expected Output: 10
 *    - Explanation: Remove 10, 1, 2 → then push back 10 on top as last move.
 *
 * 10. Stack becomes empty before k operations
 *    - Input: nums = [9], k = 2
 *    - Expected Output: -1
 *    - Explanation: First operation removes 9. Second has no effect. Stack is empty.
 */


package arrays;

public class MaximizeTopMostElementAfterKMoves {
    public int maximumTop(int[] nums, int k) {
        if (nums.length == 1 && k % 2 == 1) return -1;
        int max = 0;
        for (int i = 0; i < Math.min(k - 1 ,nums.length); i++)
            max = Math.max(max, nums[i]);
        if (k < nums.length)
            max = Math.max(max, nums[k]);
        return max;
    }
}

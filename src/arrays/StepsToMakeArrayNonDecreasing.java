/*---Approach---*/
/*
 * Problem Statement: (LC:2289)
 * You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 * Return the number of steps performed until nums becomes a non-decreasing array.
 *
 * ---Constraints---
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *
 * ->Brute force (Iteration):
 *  Time Complexity: O(n^2) //We can go through the array manually and mark the numbers where its violate the rule.
 *  Space Complexity: O(1) //We can modify the current array
 *
 * → Optimized Approach (Monotonic Stack):
 * Time Complexity: O(n)    // Each element is pushed and popped at most once
 * Space Complexity: O(n)   // Stack stores up to n elements in worst case
 *
 * - We need to simulate the deletion steps where in each step, all nums[i] such that nums[i - 1] > nums[i] are removed.
 * - Observation:
 *   → An element can only be removed if there’s a greater element on its left.
 *   → The number of steps to remove an element depends on how many rounds (steps) it takes for the decreasing order to be resolved.
 * - So, we use a monotonic stack to track elements in non-decreasing order,
 *   along with how many steps it took to reach them.
 * - For each element:
 *   → If it’s smaller than the top of the stack, we can’t delete it yet.
 *   → If it’s greater, we pop from the stack and record the maximum steps from previously popped elements (because they'd be deleted before this one).
 * - If the stack is not empty after popping, it means this element needs one more step to be deleted (after previous deletions), so we do: steps + 1.
 * - We track and update the global maximum number of steps during the process.
 * - Finally, return the max number of steps needed to make the array non-decreasing.
 */

/*---Corner Cases---*/
/*
 *
 * 1. Single Element
 *    - Input: [5]
 *    - Expected Output: 0
 *    - Explanation: A single element is trivially non-decreasing, so no steps are needed.
 *
 * 2. Already Non-Decreasing
 *    - Input: [1, 2, 3, 4, 5]
 *    - Expected Output: 0
 *    - Explanation: The array is already sorted in non-decreasing order; no deletions required.
 *
 * 3. Strictly Decreasing
 *    - Input: [5, 4, 3, 2, 1]
 *    - Expected Output: 4
 *    - Explanation: Each element (except the last) violates the condition and would be removed over multiple steps.
 *
 * 4. All Elements Equal
 *    - Input: [3, 3, 3, 3]
 *    - Expected Output: 0
 *    - Explanation: Equal elements satisfy non-decreasing order, no steps required.
 *
 * 5. Alternating Peaks
 *    - Input: [1, 3, 2, 4, 3, 5]
 *    - Expected Output: 2
 *    - Explanation: Elements like 3 -> 2 and 4 -> 3 violate order and require step-by-step deletions.
 *
 * 6. Flat Then Drop
 *    - Input: [7, 7, 7, 7, 6]
 *    - Expected Output: 1
 *    - Explanation: Final element breaks the non-decreasing trend and will be removed in one step.
 *
 * 7. Peak in the Middle
 *    - Input: [1, 2, 8, 3, 4]
 *    - Expected Output: 1
 *    - Explanation: The number 8 is greater than its successor 3 and violates the condition.
 *
 * 8. Long Tail Violation
 *    - Input: [1, 2, 3, 4, 5, 1]
 *    - Expected Output: 1
 *    - Explanation: The last element 1 causes a violation and will be removed after one step.
 *
 * 9. Two Elements (Decreasing)
 *    - Input: [4, 1]
 *    - Expected Output: 1
 *    - Explanation: The second element violates non-decreasing order and must be removed.
 *
 * 10. Large Increasing Followed by Decreasing Tail
 *     - Input: [1, 2, 3, 4, 3, 2, 1]
 *     - Expected Output: 3
 *     - Explanation: The decreasing tail requires multiple steps to stabilize into a non-decreasing array.
 */


package arrays;
import java.util.Arrays;
import java.util.Stack;

public class StepsToMakeArrayNonDecreasing {
    public static void main(String[] args) {
        System.out.println(totalSteps(new int[]{5,3,4,4,7,3,6,11,8,5,11}));

    }

    //[5,3,4,4,7,3,6,11,8,5,11]
    //[5,4,3,2,1]
    static int totalSteps(int[] nums) {
        Stack<int[]> stack = new Stack<>();
        int maxSteps = 0;
        for (int num : nums) {
            int steps = 0;
            while (!stack.isEmpty() && num >= stack.peek()[0]) {
                steps = Math.max(steps, stack.pop()[1]);
            }
            steps = stack.isEmpty() ? 0 : steps + 1;
            maxSteps = Math.max(maxSteps, steps);
            stack.push(new int[]{num, steps});
        }
        System.out.println("stack" + stack);
        return maxSteps;
    }
}

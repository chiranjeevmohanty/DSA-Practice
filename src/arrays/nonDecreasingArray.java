/*---Approach---*/
/*
 * Problem Statement: (LC:665)
 *  Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 *  We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 * ---Constraints---
 *  n == nums.length
 *  1 <= n <= 10^4
 *  -10^5 <= nums[i] <= 10^5
 *
 * ---Approaches---
 * ->Optimized Approach (iteration):
 *  Time Complexity: O(n) //At most we are iterating once.
 *  Space Complexity: O(1) //No extra space required
 * - The problem is quite simple we just need to find the violation and fix it and then we will move through if we found another violation will return false.
 * - 1st step we will create a count which will store the update count.
 * - Then we will go through the array and will fix the violation and we will move forward.
 * - At any case if we found that count is greater than 1 means again one violation came so we will return false.
 * - At the end if the loop terminates normally then there was only one violation so we will return true.
 */

/*---Corner Cases---*/
/*
 * 1. Already Non-Decreasing
 *    - Input: [1, 2, 3, 4, 5]
 *    - Expected Output: true
 *    - Explanation: The array is already non-decreasing.

 * 2. One Modification Needed (middle element)
 *    - Input: [4, 2, 3]
 *    - Expected Output: true
 *    - Explanation: Modify 4 to 2 => [2, 2, 3].

 * 3. One Modification Needed (last pair)
 *    - Input: [3, 4, 2]
 *    - Expected Output: true
 *    - Explanation: Modify 4 to 2 => [3, 2, 2] is not non-decreasing,
 *                  but modifying 2 to 4 => [3, 4, 4] works.

 * 4. Two Modifications Needed
 *    - Input: [3, 4, 2, 1]
 *    - Expected Output: false
 *    - Explanation: Need more than one change to make non-decreasing.

 * 5. Edge Case - Only Two Elements (Decreasing)
 *    - Input: [2, 1]
 *    - Expected Output: true
 *    - Explanation: One modification is enough.

 * 6. Large Same Elements with One Drop
 *    - Input: [5, 5, 5, 3, 5]
 *    - Expected Output: true
 *    - Explanation: Modify 3 to 5 => [5, 5, 5, 5, 5].

 * 7. First Element Too Large
 *    - Input: [10, 1, 2, 3]
 *    - Expected Output: true
 *    - Explanation: Modify 10 to 1 => [1, 1, 2, 3].

 * 8. Already Sorted Except One Element in Middle
 *    - Input: [1, 2, 5, 3, 5]
 *    - Expected Output: true
 *    - Explanation: Modify 5 to 3 or 3 to 5.

 * 9. Complex Case - Impossible with One Change
 *    - Input: [1, 5, 3, 4, 2]
 *    - Expected Output: false
 *    - Explanation: Requires multiple modifications.
 */


package arrays;

public class nonDecreasingArray {
    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4, 2, 3}));
    }
    static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (++count > 1) return false;
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }

    //shorter and straight forward
    public boolean checkPossibility2(int[] N) {
        for (int i = 1, err = 0; i < N.length; i++)
            if (N[i] < N[i-1])
                if (err++ > 0 || (i > 1 && i < N.length - 1 && N[i-2] > N[i] && N[i+1] < N[i-1]))
                    return false;
        return true;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:209)
 *  Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * ---Constraints---
 *  1 <= target <= 10^9
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^4
 *
 * ---Approaches---
 * ->Brute Force (Nested loop):
 *  Time Complexity: O(n ^ 2) //To generate all sub array.
 *  Space Complexity: O(1) //No extra space required
 * - Simply we will generate all sub arrays, and we will see whether it is greater than the target and we will calculate the min of the length.
 *
 * ->Optimized Approach (sliding window):
 *  Time Complexity: O(n) //At max we are visiting all the elements twice.
 *  Space Complexity: O(1) //No extra space required.
 * - Simply we will take two pointers i and j. we will place it on the 0th index.
 * - We will move our j(expanding) pointer until it become greater than or equals to target.
 * - We will move our i(shrinking) pointer until it again less than the target.
 * - And we will keep track of the window size i.e sub array length by taking min which is (j - i + 1)(coz of zero indexing).
 *
 * ->Optimized Approach: follow up (prefix sum and binary search):
 *  Time Complexity: O(nlog(n)) //traversing the prefix sum takes n steps and finding the pair takes log(n) coz we will run binary search on the prefix sum array.
 *  Space Complexity: O(n) //For the prefix sum array.
 * - Here is bit advanced method where we will use suffix sum and binary search together.
 * - At 1st iteration we will create our suffix sum array by taking the sum of all previous computed values.
 * - Then we will start iterating through the array linearly if sum(0 - j) - sum(0 - i) >= target. then we have our one possible answer.
 * - Next we will from current sum from the suffix array we will minus the target, and then we will run binary search on the suffix array to get the lower bound of the remaining number.
 * - We are taking lower bound coz so that the sub arrays will be of minimal size al possible.
 * - And after each iteration if we found a valid pair we will store the min difference in our answer.
 */

/*---Corner Cases---*/
/*
 * 1. Basic Case
 *    - Input: target = 7, nums = [2,3,1,2,4,3]
 *    - Expected Output: 2
 *    - Explanation: Subarray [4,3] sums to 7, length is 2.
 * 2. Entire Array Needed
 *    - Input: target = 15, nums = [1,2,3,4,5]
 *    - Expected Output: 5
 *    - Explanation: Entire array sums to 15.
 * 3. Single Element Matches Target
 *    - Input: target = 4, nums = [1,4,4]
 *    - Expected Output: 1
 *    - Explanation: Single element 4 matches target.
 * 4. No Subarray Meets Target
 *    - Input: target = 100, nums = [1,1,1,1,1,1,1]
 *    - Expected Output: 0
 *    - Explanation: No subarray sums to 100 or more.
 * 5. Multiple Possible Subarrays
 *    - Input: target = 11, nums = [1,2,3,4,5]
 *    - Expected Output: 3
 *    - Explanation: Subarray [3,4,5] sums to 12, minimal length is 3.
 * 6. All Elements Are Target
 *    - Input: target = 5, nums = [5,5,5,5,5]
 *    - Expected Output: 1
 *    - Explanation: Any single element is enough.
 * 7. Long Array with Late Match
 *    - Input: target = 15, nums = [1,1,1,1,1,1,1,1,1,6,9]
 *    - Expected Output: 2
 *    - Explanation: Subarray [6,9] at the end sums to 15.
 * 8. Target is Zero
 *    - Input: target = 0, nums = [1,2,3]
 *    - Expected Output: 1
 *    - Explanation: Any element is ≥ 0, so minimal length is 1.
 * 9. Array Has Large Elements Only
 *    - Input: target = 100, nums = [101,102,103]
 *    - Expected Output: 1
 *    - Explanation: Each element alone is ≥ 100.
 * 10. Empty Array
 *    - Input: target = 5, nums = []
 *    - Expected Output: 0
 *    - Explanation: No elements to form a subarray.
 */


package slidingWindow;
import java.util.*;

public class MinimumSizeSubArraySum {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen2(7, new int[]{2,3,1,2,4,3}));
    }

    //sliding window O(n)
    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int sum = 0;
        int minSize = 100001;
        while (j < n) {
            sum += nums[j];
            while (sum >= target) {
                minSize = Math.min(minSize, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minSize == 100001 ? 0 : minSize;
    }

    //follow up: using suffix sum and  binary search(nlog(n))
    static int minSubArrayLen2(int target, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }
        int minLength = 100001;
        for (int i = 1; i <= nums.length; i++) {
            int required = prefixSum[i] - target;
            int bound = lowerBound(prefixSum, required);
            if (bound != -1) {
                minLength = Math.min(minLength, i - bound);
            }
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(prefixSum));
        return minLength == 100001 ? 0: minLength;
    }

    static int lowerBound(int[] nums, int target) {
        int res = -1;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] <= target) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:2856)
 * Given an integer array num sorted in non-decreasing order.
 * You can perform the following operation any number of times:
 * Choose two indices, i and j, where nums[i] < nums[j].
 * Then, remove the elements at indices i and j from nums. The remaining elements retain their original order, and the array is re-indexed.
 * Return the minimum length of nums after applying the operation zero or more times.
 *
 * ---Constraints---
 *  1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums is sorted in non-decreasing order.
 *
 * ---Approaches---
 * ->Brute Force (hashmap):
 *  Time Complexity: O(n) //we will loop through the array most once
 *  Space Complexity: O(n) //for the hashmap
 * - we need the max frequency coz if that is less than half of the array size then all can be cancelled if its even length array or else we will return 0.
 * - if the max frequency is greater than half of the size of the array then the answer must be maxFreq * 2 - n.
 *
 * ->Optimized Approach (binary search):
 *  Time Complexity: O(log(n)) //binary search
 *  Space Complexity: O(1) //no extra space required
 * - we will do the same thing but the difference is that instead of getting max frequency linearly we will use binary search.
 * - coz if there is an element which frequency is more than half of the size of the array then th middle will be the same element so if we will get the last and first occurrence we can get max frequency.
 */

/*---Corner Cases---*/
/*
 * 1. Single Element
 *    - Input: [1]
 *    - Expected Output: 1
 *    - Explanation: Only one number; no possible pair removals.
 *
 * 2. All Elements Are Unique (Even Length)
 *    - Input: [1, 2, 3, 4]
 *    - Expected Output: 0
 *    - Explanation: Pair (1,2) and (3,4) — completely removable.
 *
 * 3. All Elements Are Unique (Odd Length)
 *    - Input: [1, 2, 3]
 *    - Expected Output: 1
 *    - Explanation: One element will remain unpaired after optimal removals.
 *
 * 4. All Elements Are the Same (Even Count)
 *    - Input: [7, 7, 7, 7]
 *    - Expected Output: 0
 *    - Explanation: Can remove two 7s at a time until nothing remains.
 *
 * 5. All Elements Are the Same (Odd Count)
 *    - Input: [8, 8, 8]
 *    - Expected Output: 1
 *    - Explanation: One 8 will remain after removing pairs.
 *
 * 6. One Element Has More Than Half Frequency
 *    - Input: [1, 1, 1, 2]
 *    - Expected Output: 2
 *    - Explanation: MaxFreq = 3, n = 4 → 2 * maxFreq - n = 6 - 4 = 2
 *
 * 7. Balanced Frequencies
 *    - Input: [5, 5, 6, 6, 7, 7]
 *    - Expected Output: 0
 *    - Explanation: All values have exact pairs.
 *
 * 8. Max Frequency Equals Half the Size
 *    - Input: [9, 9, 1, 2]
 *    - Expected Output: 0
 *    - Explanation: MaxFreq = 2, n = 4 → maxFreq ≤ n/2 → return 0
 *
 * 9. Max Frequency Slightly Greater Than Half
 *    - Input: [4, 4, 4, 1, 2]
 *    - Expected Output: 1
 *    - Explanation: maxFreq = 3, n = 5 → 2 * maxFreq - n = 1
 *
 * 10. Long Sequence, Skewed Frequency
 *    - Input: [2, 2, 2, 2, 3, 3, 3, 4, 5]
 *    - Expected Output: 1
 *    - Explanation: maxFreq = 4, n = 9 → 2 * maxFreq - n = 8 - 9 = -1 → return n % 2 = 1
 */


package arrays;
import java.util.*;

public class MinArrLengthAfterPairRemoval {
    public static void main(String[] args) {
        System.out.println(minLengthAfterRemovals(new ArrayList<>(Arrays.asList(2,2,2,3))));
    }
    //linear approach O(n)
    static int minLengthAfterRemovals2(List<Integer> nums) {
        int maxFreq = 0;
        int n = nums.size();
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num: nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(num));
        }
        if (maxFreq <= n / 2) {
            return n % 2;
        }
        return 2 * maxFreq - n;
    }
    //binary search approach O(log(n))
    static int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int mid = nums.get(n / 2);
        int maxFreq = search(nums, mid, false) - search(nums, mid, true) + 1;

        if (maxFreq <= n / 2) {
            return n % 2;
        }
        return 2 * maxFreq - n;
    }
    static int search(List<Integer> nums, int target, boolean lastOcc){
        int start = 0;
        int end = nums.size() - 1;
        int res = -1;
        while(start <= end){
            int mid = start + (end - start) / 2;;
            if (nums.get(mid) == target) {
                res = mid;
                if (lastOcc) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums.get(mid) > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start + " " + end);
        return res;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:2910)
 *  You are given a collection of numbered balls and instructed to sort them into boxes for a nearly balanced distribution. There are two rules you must follow:
 *  Balls with the same box must have the same value. But, if you have more than one ball with the same number, you can put them in different boxes.
 * The biggest box can only have one more ball than the smallest box.
 * Return the fewest number of boxes to sort these balls following these rules.
 *
 * ---Constraints---
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *
 * ->Optimized Approach (HashTable, Greedy):
 *  Time Complexity: O(n) //We are iterating through the array only three times.
 *  Space Complexity: O(n) //TO store the frequency in the hashmap.
 * - As per the question in a group same number should be there, and the min group size and max group size should have max difference 1.
 * - 1st we need to store the frequency of the balls in a hashmap.
 * - Then we can get the min frequency because that will be the maximum frequency of the minimum group size. coz if it exceeds more than this then we cant fit all same balls in a group.
 * - After that we got our search space of 1 - minFrequency. we will traverse it opposite so that we will get our desired result fast.
 * - Then we will calculate the totalGroups by considering the max group size as minfreq + 1(this will be the max size).
 * - Then the total number of max group will be freq/(minfreq + 1) denotes as a.
 * - And the leftover balls will be freq % (minfreq + 1) denotes as b.
 * - If there is no leftover balls then we can say that its completely divisible by the max group size so we will ad this value to our groupCount.
 * - If there is remainder left and the groupSize - remained is less than or equals to the groupCount then we can say we can borrow one to one balls from the already created groups. so then the total number will be a + 1.
 * - If all the condition unsatisfied then we can't create desired group so we will create a flag, and we will make it false.
 * - At the end if loops terminates normally means all elements are distinct so we will return balls.length;
 * */

/*---Corner Cases---*/
/*
 * e.g.
 * 1. Empty String
 *    - Input: `""`
 *    - Expected Output: `0`
 *    - Explanation: An empty string has no characters, so the longest substring with unique characters is of length 0.
 */

package arrays;
import java.util.*;

public class MinGroupsForValidAssignment {
    public static void main(String[] args) {
        System.out.println(minGroupsForValidAssignment(new int[]{1,1,3,3,1,1,2,2,3,1,3,2}));
    }
    static int minGroupsForValidAssignment(int[] balls) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int minFreq = balls.length;
        for (int ball : balls) {
            freqMap.put(ball, freqMap.getOrDefault(ball, 0) + 1);
        }
        for (int freq : freqMap.values()) {
            minFreq = Math.min(minFreq, freq);
        }
        for (int groupSize = minFreq; groupSize >= 1; groupSize--) {
            int totalGroups = 0;
            boolean isValid = true;
            for (int freq : freqMap.values()) {
                int a = freq / (groupSize + 1);
                int b = freq % (groupSize + 1);

                if (b == 0) {
                    totalGroups += a;
                } else if (groupSize - b <= a) {
                    totalGroups += a + 1;
                } else {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                return totalGroups;
            }
        }
        return balls.length;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:2975)
 * There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal and vertical fences given in arrays hFences and vFences respectively.
 * Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the coordinates (1, vFences[i]) to (m, vFences[i]).
 * Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it is impossible to make a square field.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * ---Constraints---
 *  3 <= m, n <= 10^9
 *  1 <= hFences.length, vFences.length <= 600
 *  1 < hFences[i] < m
 *  1 < vFences[i] < n
 *  hFences and vFences are unique.
 *
 * ---Approaches---
 * ->Optimized Approach (Hashset, sorting):
 *  Time Complexity: O(k ^ 2) //coz we are generating all possible length using fences array.(k is the length of the fences array which is <= 600).
 *  Space Complexity: O(k ^ 2) //we are storing all the possible length in a hashset.
 * - 1st we can think that if we need to make a square out of this grid we need length = breadth.
 * - So one thing is clear from here is we need to find a combination such that comb1 = comb2 and both should be present in the vFences and hFences.
 * - As we know the hFences and vFences does not include the start and end which is 0 , m and n. we can create a arraylist and we can add this values also coz we need these two calculate all possible combinations.
 * - Then we can sort the corresponding array list the reason to sort is to avoid calculations if we won't sort we have to try all n^2 combination we can reduce this by half by sorting the arraylist.
 * - Then we have to create a separate function which returns a hashset of all the length can be make using the values given. We are taking hashset coz there will be duplicates we don't want them.
 * - In the next step we can compare the values, and we can track of max equal length.
 * - Then at the end we can return the length * length that will be the max aria of the square. As it can go beyond int we simply can return module of 10^9+7.
 *
 */

/*---Corner Cases---*/
 /*
 *
 * 1. Minimum Grid Size, No Fence to Remove
 *    - Input: m = 3, n = 3, hFences = [2], vFences = [2]
 *    - Expected Output: 1
 *    - Explanation: After adding borders (1 and 3), the largest square is 1x1.
 *
 * 2. Empty hFences and vFences
 *    - Input: m = 5, n = 5, hFences = [], vFences = []
 *    - Expected Output: 16
 *    - Explanation: The entire grid [1,5] is available; only one big square of size 4x4 can be formed.
 *
 * 3. Only One Fence in Each Direction
 *    - Input: m = 4, n = 4, hFences = [2], vFences = [2]
 *    - Expected Output: 4
 *    - Explanation: After adding borders, squares of size 2x2 are possible in all quadrants.
 *
 * 4. Uneven Grid Dimensions
 *    - Input: m = 10, n = 6, hFences = [2, 5], vFences = [2, 3]
 *    - Expected Output: 4
 *    - Explanation: Best square has side length 2 (from 3-5 and 3-5), area = 4.
 *
 * 5. No Square Possible (No Matching Gaps)
 *    - Input: m = 6, n = 6, hFences = [2, 4], vFences = [1, 3, 5]
 *    - Expected Output: -1
 *    - Explanation: No common gap length exists between horizontal and vertical directions.
 *
 * 6. All Fences Are Evenly Spaced
 *    - Input: m = 9, n = 9, hFences = [3, 6], vFences = [3, 6]
 *    - Expected Output: 9
 *    - Explanation: Square of side length 3 exists multiple times. Area = 9.
 *
 * 7. Longest Square Formed Using Border Fence
 *    - Input: m = 8, n = 8, hFences = [4], vFences = [4]
 *    - Expected Output: 16
 *    - Explanation: Largest square is 4x4 between 4 and 8.
 */



package arrays;
import java.util.*;

public class MaximizeSquareArea {
    public static void main(String[] args) {
        System.out.println(maximizeSquareArea(4, 4, new int[]{2}, new int[]{2, 3}));
    }

    static int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long MOD = 1_000_000_007L;
        if (m == n) return (int) (((m - 1) * (n - 1)) % MOD);
        List<Integer> h = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        h.add(1);
        for (int x : hFences) h.add(x);
        h.add(m);
        v.add(1);
        for (int y : vFences) v.add(y);
        v.add(n);
        Collections.sort(h);
        Collections.sort(v);
        HashSet<Integer> hComb = possibleValues(h);
        HashSet<Integer> vComb = possibleValues(v);
        int max = 0;
        for (Integer num : hComb) {
            if (vComb.contains(num)) max = Math.max(max, num);
        }
        return max == 0 ? -1 : (int) ((max % MOD * max % MOD) % MOD);
    }

    static HashSet<Integer> possibleValues(List<Integer> fences) {
        HashSet<Integer> res = new HashSet<>();
        for (int i = 0; i < fences.size(); i++) {
            for (int j = i + 1; j < fences.size(); j++) {
                res.add(fences.get(j) - fences.get(i));
            }
        }
        return res;
    }

}

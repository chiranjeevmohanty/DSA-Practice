/*---Approach---*/
/*
 * Problem Statement:(LC:5)
 *  e.g. Return the length of the longest substring with unique characters.
 *
 * ---Constraints---
 *  e.g. 0 <= n <= 10 ^ 5 or string contains all small English alphabets
 *
 * ---Approaches---
 * ->Brute Force (technique used #e.g. nested loop, slider window, binary search, etc):
 *  Time Complexity: O(n ^ 2) // details (how?)
 *  Space Complexity: O(n ^ 2) // details (how?)
 * - points
 *
 * ->Optimized Approach (technique used #e.g. nested loop, slider window, binary search, etc):
 *  Time Complexity: O(n) // details (how?)
 *  Space Complexity: O(n) // details (how?)
 * - points
 */

/*---Corner Cases---*/
/*
 * 1. Minimum Input Case (Single Character)
 *    - Input: `"0"`, minJump = 1, maxJump = 1
 *    - Expected Output: `true`
 *    - Explanation: The start index is also the last index, so it's already reachable.
 *
 * 2. No Valid Jumps (All '1's)
 *    - Input: `"0111"`, minJump = 1, maxJump = 2
 *    - Expected Output: `false`
 *    - Explanation: The path is blocked at index 1 (`'1'`), so reaching the end is impossible.
 *
 * 3. Only One Jump Possible
 *    - Input: `"010"`, minJump = 1, maxJump = 1
 *    - Expected Output: `true`
 *    - Explanation: Can jump from index 0 → 1 → 2.
 *
 * 4. Path Blocked in Middle
 *    - Input: `"00100"`, minJump = 2, maxJump = 3
 *    - Expected Output: `false`
 *    - Explanation: The character at index 3 is `'1'`, blocking any further progress.
 *
 * 5. Exact Jumps Required
 *    - Input: `"00000"`, minJump = 2, maxJump = 2
 *    - Expected Output: `true`
 *    - Explanation: Can jump from index 0 → 2 → 4.
 *
 * 6. Multiple Possible Paths (Backtracking Required)
 *    - Input: `"0100010"`, minJump = 2, maxJump = 3
 *    - Expected Output: `true`
 *    - Explanation: Can take different paths to reach the last index.
 *
 * 7. Edge Case: Large Input with All '0's (Max Jump Allowed)
 *    - Input: `"0000000000"`, minJump = 2, maxJump = 3
 *    - Expected Output: `true`
 *    - Explanation: Multiple ways to reach the last index efficiently.
 *
 * 8. Large Input with Blocking '1's
 *    - Input: `"0001000000000001000000000000"`, minJump = 2, maxJump = 5
 *    - Expected Output: `false`
 *    - Explanation: The `'1'` at index 3 and 15 blocks the way.
 *
 * 9. Largest Possible Input (Stress Test)
 *    - Input: `"0" + "0".repeat(1999)`, minJump = 1, maxJump = 1000
 *    - Expected Output: `true`
 *    - Explanation: The jumps can be maximized for efficiency.
 *
 * 10. Unreachable Last Index (Edge Case)
 *    - Input: `"000001"`, minJump = 2, maxJump = 3
 *    - Expected Output: `false`
 *    - Explanation: The last index is unreachable due to jump constraints.
 */


package dynamicprogramming;

public class JumpGameVIII {

    public static void main(String[] args) {
        System.out.println(canReach("011010", 2, 3));
    }

    static boolean canReach(String s, int minJump, int maxJump) {
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, minJump, maxJump, 0, memo);
    }

    //this is valid for small strings 1000 <= s.length <= 2000
    static boolean helper(String s, int minJump, int maxJump, int index, Boolean[] memo){
        if(index == s.length() - 1 && s.charAt(index) != '1'){
            return true;
        }

        if (index >= s.length() || s.charAt(index) == '1') {
            return false;
        }

        if(memo[index] != null){
            return memo[index];
        }

        boolean canReach = false;
        for (int jump = minJump; jump <= maxJump; jump++) {
            int newIndex = index + jump;
            if (newIndex < s.length() && s.charAt(newIndex) == '0') {
                if (helper(s, minJump, maxJump, newIndex, memo)) {
                    canReach = true;
                    break; // Stop early if a valid path is found
                }
            }
        }

        memo[index] = canReach;
        return canReach;
    }

    //TODO:: Have to approach this using BFS
}

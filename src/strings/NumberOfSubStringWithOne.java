/*---Approach---*/
/*
 * Problem Statement: (LC:1513)
 *  Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.
 *
 * ---Constraints---
 *  1 <= s.length <= 105
 *  s[i] is either '0' or '1'
 *
 * ---Approaches---
 * ->Optimized Approach (Math, string traversal):
 *  Time Complexity: O(n) // Traversing the array max once to count the number of ones
 *  Space Complexity: O(1) // NO extra space used
 * - The problem is quite simple we have count all no of substring with no of ones.
 * - So total no of sub string for a consecutive one's is (n(n + 2))/2 where n is the number of consecutive one's.
 * - so we will go through the string and will count the consecutive one's, and we will perform the math operation until it reaches the end.
 * - Note: As the result can be very large we have to return it in 10^9+7 modulo.
 * - Make sure the use long type so that it will give precise answer. after modulus, you can simply return it by type casting it to int.
 */

/*---Corner Cases---*/
/*
 *
 * 1. All zeros
 *    - Input: "0000"
 *    - Expected Output: 0
 *    - Explanation: No '1's, so no valid substrings.
 *
 * 2. All ones
 *    - Input: "1111"
 *    - Expected Output: 10
 *    - Explanation: Substrings are: "1" (4), "11" (3), "111" (2), "1111" (1) → Total = 1+2+3+4 = 10
 *
 * 3. Mixed 1s and 0s
 *    - Input: "10101"
 *    - Expected Output: 3
 *    - Explanation: Substrings are at positions: 0 ("1"), 2 ("1"), 4 ("1")
 *
 * 4. Long consecutive ones
 *    - Input: "0000111110000"
 *    - Expected Output: 15
 *    - Explanation: "11111" → 1+2+3+4+5 = 15
 *
 * 5. Alternating ones and zeros
 *    - Input: "0101010101"
 *    - Expected Output: 5
 *    - Explanation: Five single '1's
 *
 * 6. Ones at beginning and end
 *    - Input: "111000111"
 *    - Expected Output: 6 + 6 = 12
 *    - Explanation: Two groups of three '1's → 1+2+3 = 6 each
 *
 * 7. Very large string, all '1's (Stress test)
 *    - Input: 100000 '1's
 *    - Expected Output: (100000 * 100001 / 2) % 1_000_000_007 = 21_143_231
 *    - Explanation: Huge number of substrings → must handle without overflow
 *
 * 8. Edge case - Empty string
 *    - Input: ""
 *    - Expected Output: 0
 *    - Explanation: No substrings possible.
 *
 * 9. Edge case - Single character
 *    - Input: "1"
 *    - Expected Output: 1
 *    - Input: "0"
 *    - Expected Output: 0
 *
 * 10. Multiple groups of ones
 *     - Input: "1110111"
 *     - Expected Output: (1+2+3) + (1+2+3) = 6 + 6 = 12
 *     - Explanation: Two separate groups of three '1's
 */


package strings;

public class NumberOfSubStringWithOne {
    public int numSub(String s) {
        long MOD = 1_000_000_007;
        int start = 0;
        long answer = 0;
        while(start < s.length()){
            while(start < s.length() && s.charAt(start) == '0') start++;
            int count = 0;
            while(start < s.length() && s.charAt(start) == '1'){
                count++;
                start++;
            }
            long curr = (long) count * (count + 1) / 2;
            answer = (answer + curr) % MOD;
        }
        return (int)(answer % MOD);
    }
}

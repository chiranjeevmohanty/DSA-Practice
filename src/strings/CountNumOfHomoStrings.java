/*---Approach---*/
/*
 * Problem Statement: (LC:1759)
 *  Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.
 * A string is homogenous if all the characters of the string are the same.
 * A substring is a contiguous sequence of characters within a string.
 *
 * ---Constraints---
 *  1 <= s.length <= 105
 * s consists of lowercase letters.
 *
 * ---Approaches---
 * ->Optimized Approach (Math, string traversal):
 *  Time Complexity: O(n) // Traversing the array max once to count the number of ones
 *  Space Complexity: O(1) // NO extra space used
 * - The problem is quite simple we have count all no of substring with no of same character.
 * - So total no of sub string for a consecutive one's is (n(n + 2))/2 where n is the number of consecutive duplicate characters.
 * - so we will go through the string and will count the consecutive duplicate character, and we will perform the math operation until it reaches the end.
 * - Note: As the result can be very large we have to return it in 10^9+7 modulo.
 * - Make sure the use long type so that it will give precise answer. after modulus, you can simply return it by type casting it to int.
 */

/*---Corner Cases---*/
/*
 * 1. All Unique Characters
 *    - Input: "abc"
 *    - Expected Output: 3
 *    - Explanation: Only substrings are "a", "b", "c" — total = 3
 *
 * 2. All Same Characters
 *    - Input: "aaaa"
 *    - Expected Output: 10
 *    - Explanation: Homogenous substrings: "a" (4), "aa" (3), "aaa" (2), "aaaa" (1) → 1+2+3+4 = 10
 *
 * 3. Mixed Groups
 *    - Input: "abbccc"
 *    - Expected Output: 1 ("a") + 3 ("b","bb") + 6 ("c","cc","ccc") = 10
 *    - Explanation: Each homogenous group contributes n*(n+1)/2
 *
 * 4. Alternating Characters
 *    - Input: "ababab"
 *    - Expected Output: 6
 *    - Explanation: Only single-length substrings are homogenous → "a", "b", ...
 *
 * 5. Long Group in the Middle
 *    - Input: "abbbbbbc"
 *    - Expected Output: 1 ("a") + 21 ("b" x 6 = 1+2+3+4+5+6) + 1 ("c") = 23
 *
 * 6. All Characters the Same (Stress Test)
 *    - Input: 100000 'z's
 *    - Expected Output: (100000 * 100001 / 2) % 1_000_000_007 = 21_143_231
 *
 * 7. Single Character
 *    - Input: "x"
 *    - Expected Output: 1
 *
 * 8. Two Large Groups
 *    - Input: 50000 'a's + 50000 'b's
 *    - Expected Output: ((50000 * 50001)/2) * 2 % 1_000_000_007 = 10_571_616
 *
 * 9. Ending with Group
 *     - Input: "aaabbbccc"
 *     - Expected Output: (1+2+3) * 3 = 18
 *     - Explanation: Three groups of 3 → each contributes 6
 */

package strings;

public class CountNumOfHomoStrings {
    public int countHomogenous(String s) {
        long MOD = 1_000_000_007;
        int start = 0;
        long res = 0;
        while(start < s.length()){
            int count = 1;
            while(start < s.length() - 1 && s.charAt(start) == s.charAt(start + 1)){
                count++;
                start++;
            }
            long curr = (long) count * (count + 1) / 2;
            res = (res + curr) % MOD;
            start++;
        }
        return (int)(res % MOD);
    }
}

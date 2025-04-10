/*---Approach---*/
/*
 * Problem Statement:(LC: 1750)
 *  Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following algorithm on the string any number of times:
 * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
 * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
 * The prefix and the suffix should not intersect at any index.
 * The characters from the prefix and suffix must be the same.
 * Delete both the prefix and the suffix.
 * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
 *
 * ---Constraints---
 *  1 <= s.length <= 105
 * s only consists of characters 'a', 'b', and 'c'.
 *
 * ---Approaches---
 * ->Optimized Approach (two-pointers):
 *  Time Complexity: O(n) // single pass in the string
 *  Space Complexity: O(1) // some variables no extra space
 * - the problem is quite simple we have to remove same character from both start and end and have to return the length og remaining string.
 * - We created two pointers one start one end if the character is same then move and at the end just return end - start + 1(coz index starts from 0).
 */

/*---Corner Cases---*/
/*
 *
 * 1. All Unique Characters
 *    - Input: "abcdef"
 *    - Expected Output: 6
 *    - Explanation: All characters are unique, so the entire string is the longest substring.
 *
 * 2. All Repeating Characters
 *    - Input: "aaaaaa"
 *    - Expected Output: 1
 *    - Explanation: Only one unique character, so longest substring without repeating is of length 1.
 *
 * 3. Repeating Characters at the End
 *    - Input: "abcdea"
 *    - Expected Output: 5
 *    - Explanation: Longest substring without repeating is "abcde".
 *
 * 4. Repeating Characters at the Start
 *    - Input: "aabcdef"
 *    - Expected Output: 6
 *    - Explanation: Longest substring without repeating is "abcdef".
 *
 * 5. Repeating Characters in the Middle
 *    - Input: "abcabcbb"
 *    - Expected Output: 3
 *    - Explanation: Longest substring without repeating is "abc".
 *
 * 6. Unicode or Special Characters
 *    - Input: "a!@#a!@"
 *    - Expected Output: 4
 *    - Explanation: Longest substring is "!@#a".
 *
 * 7. Numbers in the String
 *    - Input: "123451234"
 *    - Expected Output: 5
 *    - Explanation: Longest substring is "12345".
 *
 * 8. Case Sensitivity
 *    - Input: "aAbBcC"
 *    - Expected Output: 6
 *    - Explanation: Uppercase and lowercase letters are considered different.
 *
 * 9. Very Long String with Same Character
 *     - Input: String of length 10^5 with all characters as 'a'
 *     - Expected Output: 1
 *     - Explanation: Only one unique character, so longest substring is of length 1.
 */


package strings;

public class MinimumLength {
    public int minimumLength(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end && s.charAt(start) == s.charAt(end)) {
            char curChar = s.charAt(start);
            while (start <= end && s.charAt(start) == curChar) start++;
            while (start <= end && s.charAt(end) == curChar) end--;
        }
        return end - start + 1;
    }
}

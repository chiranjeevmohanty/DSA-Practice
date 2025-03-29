package strings;
import java.util.*;
/*---Approach---*/
/*
 * Problem Statement:
 * Return the length of the longest substring with unique characters.
 *
 * ---Constraints---
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols, and spaces.
 *
 * ---Approaches---
 * Brute Force:
 * - A simple solution would be to generate all substrings of the string and check whether any of them contains repeating characters.
 * - However, this solution is inefficient (O(n^2)) for large strings, as the length can be up to 5 * 10^4, as stated in the constraints.
 *
 * Optimized Approach (sliding window & HashMap):
 * - Using the sliding window technique, we can solve the problem in a single pass.
 * - The approach is simple: we need to loop through each element using the right pointer. The HashMap will keep track of all elements and their indices.
 * - If we haven't encountered the same character before, we continue normally and store the count as `right - left + 1` (since indices start from 0).
 * - If we encounter a duplicate character, we move the left pointer just after the duplicate character to avoid unnecessary calculations.
 */
/*---Corner Cases---*/
/*
 * 1. Empty String
 *    - Input: `""`
 *    - Expected Output: `0`
 *    - Explanation: An empty string has no characters, so the longest substring with unique characters is of length 0.
 *
 * 2. Single Character String
 *    - Input: `"a"`
 *    - Expected Output: `1`
 *    - Explanation: A string with only one character has a longest substring with unique characters of length 1.
 *
 * 3. String with All Unique Characters
 *    - Input: `"abcdef"`
 *    - Expected Output: `6`
 *    - Explanation: The string contains only unique characters, so the entire string is the longest substring with unique characters.
 *
 * 4. String with All Identical Characters
 *    - Input: `"aaaa"`
 *    - Expected Output: `1`
 *    - Explanation: All characters are the same, so the longest substring with unique characters can only have length 1 (any single character).
 *
 * 5. String with Multiple Repeated Characters
 *    - Input: `"abcabcbb"`
 *    - Expected Output: `3`
 *    - Explanation: The longest substring without repeating characters is `"abc"`, which has a length of 3.
 *
 * 6. String with Spaces and Special Characters
 *    - Input: `"abc @123$"`
 *    - Expected Output: `9`
 *    - Explanation: The string includes spaces and special characters, but the entire string has no duplicates, so the length of the longest substring with unique characters is the length of the entire string, which is 9.
 *
 * 7. String with Digits, Letters, and Symbols
 *    - Input: `"a1b2c3d4!"`
 *    - Expected Output: `10`
 *    - Explanation: All characters are unique in this string, including letters, digits, and symbols, so the longest substring with unique characters is the entire string, which has a length of 10.
 *
 * 8. String with Large Input Size (near maximum length)
 *    - Input: A very large string like `"abc...xyz1234567890"` repeated many times.
 *    - Expected Output: Dependent on the input but should be processed efficiently.
 *    - Explanation: For very large strings (up to the constraint limit of 50,000 characters), the algorithm should still run efficiently due to the sliding window (two-pointer) approach.
 *
 * 9. String with Characters in Reverse Order
 *    - Input: `"zyxwvuts"`
 *    - Expected Output: `8`
 *    - Explanation: All characters in the string are unique, so the entire string is the longest substring with unique characters.
 *
 * 10. String with Mixed Case Sensitivity
 *    - Input: `"aA"`
 *    - Expected Output: `2`
 *    - Explanation: The characters `'a'` and `'A'` are treated as unique because they have different cases, so the longest substring is of length 2.
 *
 * 11. String with Multiple Duplicates (but a valid substring exists)
 *    - Input: `"abcabcbb"`
 *    - Expected Output: `3`
 *    - Explanation: The longest substring without repeating characters is `"abc"`, which has length 3, even though other substrings have repeating characters.
 */

public class LongestUniqueSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcacb"));
    }
    static int lengthOfLongestSubstring(String s) {
        int left = 0; //sliding window low index
        int maxLength = 0;
        Map<Character, Integer> ch = new HashMap<>(); // to track the repeating character
        for(int right = 0; right < s.length(); right++){ //sliding window high index
            if(ch.containsKey(s.charAt(right))){
                left = Math.max(ch.get(s.charAt(right)) + 1, left); //to ensure the correct shift of left point
            }
            ch.put(s.charAt(right), right); //adding/updating the key => val
            maxLength = Math.max(maxLength, right - left + 1); // tracking the max length
        }
        return maxLength;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:424)
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * ---Constraints---
 * 1 <= s.length <= 10^5
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 *
 * ---Approaches---
 * ->Optimized Approach (sliding window):
 *  Time Complexity: O(n) //traversing the string at max once.
 *  Space Complexity: O(n) //using hashmap to store the frequency.
 * - The approach is simple as we know contiguous part of string we can use sliding window.
 * - in the window size (end - start + 1) we have to store the most frequent element so that we can replace the elements within them to get our result.
 * - we will store the most frequent element and the result which is the tab size itself if its satisfy the condition.
 * - the condition goes like this (tabSize - maxFrequency) > k then we will shrink our window otherwise we take the max.
 * - at the final step when loop will terminate we will have our answer.
 *
 * ->Things to note:
 * - here as we know that the string only contains english capital letters we can take an array instead.
 */

/*---Corner Cases---*/
/*
 *
 * 1. All Same Characters
 *    - Input: s = "AAAA", k = 2
 *    - Expected Output: 4
 *    - Explanation: Already all characters are same, no replacements needed.
 *
 * 2. All Unique Characters, No Replacements Allowed (k = 0)
 *    - Input: s = "ABCDE", k = 0
 *    - Expected Output: 1
 *    - Explanation: No replacements allowed, so longest valid substring is any one character.
 *
 * 3. All Unique Characters, Enough Replacements
 *    - Input: s = "ABCDE", k = 4
 *    - Expected Output: 5
 *    - Explanation: Replace any 4 characters to match the 5th one.
 *
 * 4. Single Character String
 *    - Input: s = "A", k = 0
 *    - Expected Output: 1
 *    - Explanation: Only one character, already valid.
 *
 * 5. Large `k` with Mixed Characters
 *    - Input: s = "AABABBA", k = 10
 *    - Expected Output: 7
 *    - Explanation: With high k, you can replace freely and make the full string the same.
 *
 * 6. k = 0, and string has some repeated characters
 *    - Input: s = "AABBC", k = 0
 *    - Expected Output: 2
 *    - Explanation: Longest substrings like "AA", "BB" are valid without replacement.
 */



package strings;
import java.util.*;

public class LongestRepeatingCharReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 2));
    }
    static int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        int start = 0;
        int result = 0;
        for(int end = 0; end < s.length(); end++){
            char c = s.charAt(end);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(c));
            if ((end - start + 1) - maxFreq > k) {
                char charAtStart = s.charAt(start);
                freq.put(charAtStart, freq.get(charAtStart) - 1);
                start++;
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:5)
 *  Given a string s, return the longest palindromic substring in s.
 *
 * ---Constraints---
 *  1 <= s.length <= 1000
 *   s consist of only digits and English letters.
 *
 * ---Approaches---
 * ->Brute Force (nested loop):
 *  Time Complexity: O(n ^ 3) // three nested loops
 *  Space Complexity: O(1) // we are not storing anything
 *  - to get all substring it takes O(n ^ 2).
 *  - to check each substring whether its palindrome or not takes O(n) total O(n ^ 3).
 * ->Optimized Approach (expand around center):
 *  Time Complexity: O(n ^ 2) // simple traversing each element and expanding in worst case its n ^ 2.
 *  Space Complexity: O(1) // no extra space just some variables.
 * - 1st we will traverse through single(odd length palindrome) character and in each step we will expand from the center if it matches we will store the start and end.
 * - in the next step we have to consider even length palindrome for this we will take two characters, and we will do the same if two characters are equal.
 * - at the end we can just return the substring with the start and end we have stored in two variables.
 * ->Most Optimized Approach (Manacher’s Algorithm):
 *  Time Complexity: O(n)
 *  Space Complexity: O(1)
 */

/*---Corner Cases---*/
 /*
 * 1. Single Character String
 *    - Input: `"a"`
 *    - Expected Output: `"a"`
 *    - Explanation: A single-character string is always a palindrome.
 *
 * 2. String with All Unique Characters
 *    - Input: `"abcdef"`
 *    - Expected Output: `"a"` (or any single character)
 *    - Explanation: Since there are no repeating characters, the longest palindromic substring is any single character.
 *
 * 3. String with All Identical Characters
 *    - Input: `"aaaaaa"`
 *    - Expected Output: `"aaaaaa"`
 *    - Explanation: The entire string is a palindrome.
 *
 * 4. Even-Length Palindrome
 *    - Input: `"abba"`
 *    - Expected Output: `"abba"`
 *    - Explanation: `"abba"` is a palindrome, and there is no longer one in the string.
 *
 * 5. Odd-Length Palindrome
 *    - Input: `"racecar"`
 *    - Expected Output: `"racecar"`
 *    - Explanation: The entire string is a palindrome.
 *
 * 6. Palindrome in the Middle
 *    - Input: `"xyzracecarmno"`
 *    - Expected Output: `"racecar"`
 *    - Explanation: `"racecar"` is the longest palindrome, even though the full string is not.
 *
 * 7. Palindrome at the Start
 *    - Input: `"madamxyz"`
 *    - Expected Output: `"madam"`
 *    - Explanation: The longest palindrome starts from index 0.
 *
 * 8. Palindrome at the End
 *    - Input: `"xyznoon"`
 *    - Expected Output: `"noon"`
 *    - Explanation: The longest palindrome appears at the end.
 *
 * 9. String with Multiple Palindromes
 *    - Input: `"abcddcbaracecar"`
 *    - Expected Output: `"racecar"`
 *    - Explanation: Even though `"abcddcba"` is a palindrome, `"racecar"` is the longest one.
 *
 * 10. String with Digits
 *    - Input: `"123454321"`
 *    - Expected Output: `"123454321"`
 *    - Explanation: The entire string is a palindrome.
 *
 * 11. Mixed Alphanumeric Palindrome
 *    - Input: `"a1b2b1a"`
 *    - Expected Output: `"a1b2b1a"`
 *    - Explanation: The entire string is a palindrome.
 *
 * 12. Long String with Palindrome in the Middle
 *    - Input: `"abcdefghracecarklmno"`
 *    - Expected Output: `"racecar"`
 *    - Explanation: `"racecar"` is the longest palindrome, even though it's in the middle of the string.
 *
 * 13. String with No Palindrome Longer Than 1
 *    - Input: `"abcd"`
 *    - Expected Output: `"a"` (or `"b"`, `"c"`, `"d"`)
 *    - Explanation: Since there are no repeating characters, the longest palindrome is any single character.
 *
 * 14. Very Long String with a Palindrome
 *    - Input: `"a".repeat(500) + "racecar" + "a".repeat(500)`
 *    - Expected Output: `"a".repeat(500) + "racecar" + "a".repeat(500)`
 *    - Explanation: The entire string is a palindrome.
 */

package strings;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcddcbaracecar"));
    }
    //expand around centers
    static String longestPalindrome(String s) {
        int start = 0;
        int length = 0;
        //for odd length palindromes
        for(int i = 0; i < s.length(); i++){
            int low = i;
            int high = i;
            while(low > 0 && high < s.length() && s.charAt(low) == s.charAt(high)){
                if(length < high - low + 1){
                    length = high - low + 1;
                    start = low;
                }
                    low--;
                    high++;

            }
        }

        //for even length palindromes
        for(int i = 1; i < s.length(); i++){
            int low = i - 1;
            int high = i;
            while(low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)){
                if(length < high - low + 1){
                    length = high - low + 1;
                    start = low;
                }
                low--;
                high++;
            }
        }

        return s.substring(start, start + length);
    }

    /*TODO:: Implement using Manacher’s Algorithm and DP tabulation*/
}
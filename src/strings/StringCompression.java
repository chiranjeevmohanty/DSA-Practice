/*---Approach---*/
/*
 * Problem Statement: (LC:443)
 *  Given an array of characters chars, compress it using the following algorithm:
 *  Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *  If the group's length is 1, append the character to s.
 *  Otherwise, append the character followed by the group's length.
 *  The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *  After you are done modifying the input array, return the new length of the array.
 *  You must write an algorithm that uses only constant extra space.
 *
 * ---Constraints---
 *  1 <= chars.length <= 2000
 *  chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 *
 * ---Approaches---
 * ->Brute Force (new array):
 *  Time Complexity: O(n) // one loop to traverse through the array
 *  Space Complexity: O(n) // we need to store the item and frequency in an arraylist
 * - For this we will create a new arraylist,and we will push the element and frequency while traversing the array.
 * - Then we will modify the original array with the newly created array and will return the length.
 *
 * ->Optimized Approach (in a single pass, two pointer):
 *  Time Complexity: O(n) // single pass
 *  Space Complexity: O(1) // no extra space required
 * - we need a two pointer approach to solve this problem.
 * - 1st we simply iterate through the array using i pointer which will count the element frequency.
 * - The index pointer will be placed to update the frequency of each character in the array this way we can modify the array on place.
 * - At the end we will return index.
 */

/*---Corner Cases---*/

/*
 * 2. All Unique Characters (No Compression)
 *    - Input: `['a', 'b', 'c', 'd']`
 *    - Expected Output: `4`
 *    - Explanation: Since no character repeats, the compressed form remains the same as the original.
 *
 * 3. All Characters are the Same (Maximum Compression)
 *    - Input: `['a', 'a', 'a', 'a', 'a']`
 *    - Expected Output: `2`
 *    - Explanation: "aaaaa" is compressed to "a5", reducing the length to 2.
 *
 * 4. Case Sensitivity Test
 *    - Input: `['a', 'A', 'a', 'A', 'A']`
 *    - Expected Output: `5`
 *    - Explanation: Since 'a' and 'A' are different, compression does not merge them.
 *
 * 5. Digits and Symbols
 *    - Input: `['1', '1', '!', '!', '!']`
 *    - Expected Output: `4`
 *    - Explanation: "11!!!" is compressed to "12!3".
 *
 * 6. Mixed Characters with Varying Counts
 *    - Input: `['a', 'a', 'b', 'b', 'c', 'c', 'c']`
 *    - Expected Output: `6`
 *    - Explanation: "aabbccc" is compressed to "a2b2c3".
 *
 * 7. Longest Input Allowed (Edge Case)
 *    - Input: A string of 2000 'a' characters.
 *    - Expected Output: `4`
 *    - Explanation: "aaaa... (2000 times)" is compressed to "a2000".
 *
 * 8. Alternating Characters
 *    - Input: `['a', 'b', 'a', 'b', 'a', 'b', 'a', 'b']`
 *    - Expected Output: `8`
 *    - Explanation: Since no consecutive characters repeat, compression does not shorten the string.
 *
 * 9. Single Repeating Character at the End
 *    - Input: `['a', 'b', 'c', 'c', 'c']`
 *    - Expected Output: `5`
 *    - Explanation: "abccc" is compressed to "abc3".
 *
 * 10. Numbers with Repeating Digits
 *    - Input: `['1', '1', '1', '2', '2', '3']`
 *    - Expected Output: `6`
 *    - Explanation: "111223" is compressed to "132213".
 */


package strings;

import java.util.Arrays;

public class StringCompression {
    public static void main(String[] args) {
        char[] cr = new char[]{'a','a','b','b','c','c','c'};
        System.out.println(compress(cr));
        System.out.println(Arrays.toString(cr));
    }
    static int compress(char[] chars) {
        int index = 0; //pointer for the current index where we need to update the character and count
        int i = 0; //pointer for traversing and counting the frequency array

        while (i < chars.length) {
            char curCharacter = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == curCharacter) {
                count++;
                i++;
            }
            chars[index++] = curCharacter;
            if (count > 1) {
                //converting count to string so that we can add each number separately e.g. 56 -> "56" -> '5','6'
                String s = String.valueOf(count);
                for (char c : s.toCharArray()) {
                    chars[index++] = c;
                }
            }
        }

        return index;
    }
}

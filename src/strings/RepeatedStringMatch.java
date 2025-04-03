/*---Approach---*/
/*
 * Problem Statement: (LC:686)
 *  Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b to be a substring of a after repeating it, return -1.
 *
 * ---Constraints---
 *  1 <= a.length, b.length <= 104
 *  a and b consist of lowercase English letters.
 *
 * ---Approaches---
 * ->Brute Force (string match, string multiplication):
 *  Time Complexity: O(n * m) // for contains method to check whether b is substring of a.
 *  Space Complexity: O(n) // to store the multiplied string
 * - at 1st we will append a multiple time so that it matches the length of b.
 * - then we will check whether b is a substring of a using contains which takes O(n * m) time.
 * - if its not matching again we will append a one more time and will repeat the same process so that for edge cases it will return correct value.
 *
 * ->Optimized Approach (KMP (Knuth-Morris-Pratt) algorithm):
 *  Time Complexity: O(n + m) // details (how?)
 *  Space Complexity: O(1) // details (how?)
 * - points
 */

/*---Corner Cases---*/
/*
 * * 1. Basic Case - `b` is already in `a`
 *    - Input: a = "abcd", b = "cd"
 *    - Expected Output: 1
 *    - Explanation: "cd" is already present in "abcd", so one repetition is enough.
 *
 * 2. `b` is exactly `a`
 *    - Input: a = "abc", b = "abc"
 *    - Expected Output: 1
 *    - Explanation: `b` is already `a`, so one repetition is enough.
 *
 * 3. `b` is a repeated version of `a`
 *    - Input: a = "abc", b = "abcabc"
 *    - Expected Output: 2
 *    - Explanation: "abcabc" is found in "abc" repeated twice.
 *
 * 4. `b` requires more repetitions of `a`
 *    - Input: a = "abc", b = "abcabcabc"
 *    - Expected Output: 3
 *    - Explanation: "abcabcabc" requires three repetitions of "abc".
 *
 * 5. `b` is not possible even after repetitions
 *    - Input: a = "abc", b = "acb"
 *    - Expected Output: -1
 *    - Explanation: The order of characters in `b` does not match any repetition of `a`.
 *
 * 6. `b` contains extra characters not in `a`
 *    - Input: a = "xyz", b = "xyzz"
 *    - Expected Output: -1
 *    - Explanation: The character 'z' appears twice in `b`, but only once in `a`, so repetition cannot match `b`.
 *
 * 7. Large Input with Exact Match After Multiple Repetitions
 *    - Input: a = "a", b = "aaaaaaaaaa" (10 'a's)
 *    - Expected Output: 10
 *    - Explanation: `b` requires `a` to be repeated 10 times.
 *
 * 8. `b` starts in one repetition and continues in another
 *    - Input: a = "abc", b = "cab"
 *    - Expected Output: 2
 *    - Explanation: "cab" appears at the boundary of "abcabc".
 *
 * 9. `b` is longer than `a` but not a repeatable substring
 *    - Input: a = "abab", b = "ababab"
 *    - Expected Output: 2
 *    - Explanation: "ababab" is found after repeating "abab" twice.
 *
 * 10. `b` contains characters not in `a`
 *    - Input: a = "abc", b = "def"
 *    - Expected Output: -1
 *    - Explanation: The characters in `b` are completely different from `a`.
 *
 * 11. Large Input with Impossible Match
 *    - Input: a = "a".repeat(1000), b = "b".repeat(1000)
 *    - Expected Output: -1
 *    - Explanation: `a` only contains 'a', while `b` only contains 'b', so `b` can never be a substring.
 *
 * 12. Maximum Constraint Case - `b` is a large repetition of `a`
 *    - Input: a = "ab", b = "ab".repeat(500)
 *    - Expected Output: 500
 *    - Explanation: `b` requires `a` to be repeated 500 times.
 */

package strings;

public class RepeatedStringMatch {
    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abc", "cab"));
    }
    static int repeatedStringMatch(String a, String b) {
        StringBuilder repeated = new StringBuilder(a);
        int count = 1;
        while (repeated.length() < b.length()) {
            repeated.append(a);
            count++;
        }
        if (repeated.toString().contains(b)) {
            return count;
        }
        repeated.append(a);
        count++;
        if (repeated.toString().contains(b)) {
            return count;
        }
        return -1;
    }

    //TODO:: write most efficient code using KMP (Knuth-Morris-Pratt) algorithm
}

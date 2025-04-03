/*---Approach---*/
/*
 * Problem Statement: (LC:779)
 *  We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 *
 * ---Constraints---
 *  1 <= n <= 30
 * 1 <= k <= 2n - 1
 *
 * ---Approaches---
 * ->Brute Force (iterative):
 *  Time Complexity: O(2 ^ n) // for each step the string is doubling in size WRT n.
 *  Space Complexity: O(2 ^ n) // to store the string
 * - in this approach iteratively we have to form the string, and then we can return the char at index k after converting it to int.
 *
 * ->Optimized Approach (binary search):
 *  Time Complexity: O(log k) // to search the kth bit
 *  Space Complexity: O(log k) // for the call stack to store recursive function
 * - Base case: When n is 1, S1 → "0", so return 0.
 * - The length of S_n is (2^n - 1), but we only need the midpoint.
 * - Mid is calculated as (1 << (n - 1)) / 2, which gives the exact middle element.
 * - 1st case (k <= mid): k lies in the first half, which is exactly S(n - 1).
 * - The search space is reduced by half, so recursively call kthGrammar(n - 1, k).
 * - 2nd case (k > mid): k lies in the second half, which is an inverted version of the first half.
 * - Convert k into the corresponding index in the first half using (k - mid).
 * - The search space is reduced by half, so recursively call kthGrammar(n - 1, k - mid).
 * - Since the second half is inverted, apply the invert() function before returning the result.
 */

/*---Corner Cases---*/
/*
 * Test Case 1: Smallest Case
 * Input: n = 1, k = 1
 * Expected Output: '0'
 * Explanation: S1 = "0", so the 1st bit is '0'.
 *
 * Test Case 2: Simple Case
 * Input: n = 2, k = 3
 * Expected Output: '1'
 * Explanation: S2 = "011", the 3rd bit is '1'.
 *
 * Test Case 3: Middle of String
 * Input: n = 3, k = 4
 * Expected Output: '1'
 * Explanation: S3 = "0111001", the 4th bit is '1'.
 *
 * Test Case 4: Last Bit of S4
 * Input: n = 4, k = 15
 * Expected Output: '0'
 * Explanation: S4 = "011100110110001", the 15th bit is '0'.
 *
 * Test Case 5: First Bit of Any N
 * Input: n = 5, k = 1
 * Expected Output: '0'
 * Explanation: The first bit in any Sn is always '0'.
 *
 * Test Case 6: Last Bit of S5
 * Input: n = 5, k = 31
 * Expected Output: '0'
 * Explanation: S5 = "0111001101100011011100110110001", last bit is '0'.
 *
 * Test Case 7: Middle Bit of Large N
 * Input: n = 6, k = 32
 * Expected Output: '1'
 * Explanation: Middle bit in S6.
 *
 * Test Case 8: Edge Case - Large K
 * Input: n = 10, k = 512
 * Expected Output: '1' or '0' (Depends on S10 structure)
 */

package recursion;

public class KthSymbolInGrammar {
    public static void main(String[] args) {
        System.out.println(kthGrammar(2, 1));
    }
    static int kthGrammar(int n, int k) {
        // Base Case: If we are at the first row (S1), return 0
        if (n == 1) {
            return 0;
        }

        // Calculate the midpoint of the current sequence
        // Since the length of Sn is (2^(n-1)), the midpoint is (1 << (n - 1)) / 2
        int mid = (1 << (n - 1)) / 2;

        // Case 1: If k is in the first half (k <= mid), the value is the same as in S(n-1)
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        }
        // Case 2: If k is in the second half (k > mid), it's an inverted version of the first half
        else {
            return invert(kthGrammar(n - 1, k - mid));
        }
    }

    // Function to invert a bit: 0 becomes 1, and 1 becomes 0
    static int invert(int c) {
        return (c == 0) ? 1 : 0;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:1969)
 *  You are given a positive integer p. Consider an array nums (1-indexed) that consists of the integers in the inclusive range [1, 2p - 1] in their binary representations. You are allowed to do the following operation any number of times:
 *  Choose two elements x and y from nums.
 *  Choose a bit in x and swap it with its corresponding bit in y. Corresponding bit refers to the bit that is in the same position in the other integer.
 *  For example, if x = 1101 and y = 0011, after swapping the 2nd bit from the right, we have x = 1111 and y = 0001.
 *  Find the minimum non-zero product of nums after performing the above operation any number of times. Return this product modulo 109 + 7.
 *
 * ---Constraints---
 *  1 <= p <= 60
 *
 * ->Optimized Approach (Math, recursion):
 *  Time Complexity: O(n) // details (how?)
 *  Space Complexity: O(n) // details (how?)
 * - There is an underneath pattern we have to understand if you take an example of p = 3 then min possible multiplication is = 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512.
 * - Here we can observe that the range is 1 - 2^p - 1.
 * - and the min result is by applying this formula is m * (m - 1)^(m - 1)/2.
 * - Using this formula we can get our answer but the catch is that the number can be so high that it can cross the range of long.
 * - So here is how we can fix as in the question its given that we should return it in modulo of 10^9+7. So using recursion at each step we can actually get this modulo so the overall result won't go out of range.
 * - One last thing we have to consider that the base can also go so while passing it to power function we can modulo that as well.
 */

/*---Corner Cases---*/
/*
 * 1. Smallest Possible p
 *    - Input: p = 1
 *    - Expected Output: 1
 *    - Explanation: Array = [1, 2]. The only non-zero product possible is 1 × 2 = 2. To minimize, we flip nothing: 2.
 *
 * 2. p = 2 (Small Power of 2)
 *    - Input: p = 2
 *    - Expected Output: 6
 *    - Explanation: Array = [1, 2, 3, 4]. The optimal product after swaps is 3 × 3 × 4 = 36 mod 10^9+7 = 6.
 *
 * 3. Medium Value of p
 *    - Input: p = 3
 *    - Expected Output: 1512
 *    - Explanation: Array has numbers from 1 to 7. Optimal minimal non-zero product is achieved with certain swaps and is precomputed as 1512.
 *
 * 4. Larger p Value
 *    - Input: p = 5
 *    - Expected Output: 202795991 // May vary based on modulus result
 *    - Explanation: For large arrays, using the formula becomes critical:
 *      (2^p - 1) * pow(2^p - 2, 2^(p - 1) - 1) % mod
 *
 * 5. Very Large p (Stress Test)
 *    - Input: p = 60
 *    - Expected Output: Large number mod 1_000_000_007
 *    - Explanation: Tests performance of pow with large exponents and modulo.
 *
 * 6. Check Power of Two Edge Case
 *    - Input: p = 4
 *    - Expected Output: Computed using formula
 *    - Explanation: The array goes from 1 to 15. Ensures correct usage of modulo exponentiation.
 *
 * 7. Random Intermediate Case
 *    - Input: p = 7
 *    - Expected Output: Validated using efficient modulo pow
 *    - Explanation: Random case ensures correctness for average test input size.
 */


package recursion;

public class MinNonZeroProductOfArray {
    public int minNonZeroProduct(int p) {
        long MOD = 1_000_000_007;
        long m = (1L << p) - 1;
        long exp = (m - 1) / 2;
        long base = m - 1;
        long res = pow(base % MOD, exp, MOD);
        return (int) ((res * (m % MOD)) % MOD);
    }

    public long pow(long base, long exponent, long MOD) {
        if (exponent == 0) return 1;
        long half = pow(base, exponent / 2, MOD);
        long result = (half * half) % MOD;
        if (exponent % 2 != 0) {
            result = (result * base) % MOD;
        }
        return result;
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:50)
 *  Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 *
 * ---Constraints---
 *  -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10^4 <= x^n <= 10^4
 *
 * ---Approaches---
 * ->Optimized Approach (recursion):
 *  Time Complexity: O(log n) // recursively we will get the power
 *  Space Complexity: O(log n) // for the call stack
 * - The approach is quite same as the good number the only difference is that it can have both +ve and -ve values for x and n.
 * - Things to notice is only when the n value is -ve it will give decimal value which is 1/x^n.
 * - Simply while passing the argument if n is less than 0 we can change the n to 1/n and x to -x.
 */

/*---Corner Cases---*/
/*
 * 1. Empty Exponent (n = 0)
 *    - Input: x = 2.0, n = 0
 *    - Expected Output: 1.0
 *    - Explanation: Any number raised to the power of 0 is always 1.
 *
 * 2. Positive Exponent
 *    - Input: x = 2.0, n = 10
 *    - Expected Output: 1024.0
 *    - Explanation: 2^10 = 1024.
 *
 * 3. Negative Exponent
 *    - Input: x = 2.0, n = -2
 *    - Expected Output: 0.25
 *    - Explanation: 2^(-2) = 1 / 2^2 = 1 / 4 = 0.25.
 *
 * 4. Fractional Base
 *    - Input: x = 0.5, n = 3
 *    - Expected Output: 0.125
 *    - Explanation: 0.5^3 = 0.5 * 0.5 * 0.5 = 0.125.
 *
 * 5. Large Positive Exponent
 *    - Input: x = 1.00001, n = 1000000
 *    - Expected Output: ~2.718 (Approximately)
 *    - Explanation: This approximates Euler's number e (~2.718) due to repeated multiplications.
 *
 * 6. Large Negative Exponent
 *    - Input: x = 2.0, n = -2147483648
 *    - Expected Output: 0.0
 *    - Explanation: Very small value that underflows to 0 due to precision limits.
 *
 * 7. Base is 1
 *    - Input: x = 1.0, n = 1000
 *    - Expected Output: 1.0
 *    - Explanation: 1 raised to any power is always 1.
 *
 * 8. Base is -1 with Even Exponent
 *    - Input: x = -1.0, n = 100
 *    - Expected Output: 1.0
 *    - Explanation: (-1)^even = 1.
 *
 * 9. Base is -1 with Odd Exponent
 *    - Input: x = -1.0, n = 99
 *    - Expected Output: -1.0
 *    - Explanation: (-1)^odd = -1.
 *
 * 10. Small Base with Large Exponent
 *     - Input: x = 0.00001, n = 2147483647
 *     - Expected Output: 0.0
 *     - Explanation: Very small number raised to a large power results in underflow.
 */

package recursion;

public class PowerOf {
    public static void main(String[] args) {
        System.out.println(myPow(-2, 10));
    }

    static double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }
        return power(x, exp);
    }

    static double power(double x, long n) {
        if (n == 0) return 1.0;
        double half = power(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}

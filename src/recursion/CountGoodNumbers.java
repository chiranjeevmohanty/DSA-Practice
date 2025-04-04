/*---Approach---*/
/*
 * Problem Statement: (LC:1922)
 * A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
 * For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits (5 and 2) at odd positions are prime. However, "3245" is not good because 3 is at an even index but is not even.
 * Given an integer n, return the total number of good digit strings of length n. Since the answer may be large, return it modulo 109 + 7.
 * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
 *
 * ---Constraints---
 *  1 <= n <= 10^15
 *
 * ---Approaches---
 * ->Brute Force (using Math.pow and long datatype):
 *  Time Complexity: O(1) // constant time but the catch is it won't solve the problem
 *  Space Complexity: O(k ^ n) // k is the value options and n is the index options its basically n/2 for even and odd indices.
 * - It seems in the first glance that it will work but no, coz the value of n can be as high as 10 ^ 15.
 * - So this approach will fail to handle large input, although we will return it in modulo of 10^9+7 still we don't have the container to hold the result.
 * - we can think of BIgInt type of there the in build power function will cause TLE.
 * - it will be valid for small numbers though.
 *
 * ->Optimized Approach (recursion):
 *  Time Complexity: O(log(n)) // recursively we will get the power with modulo of 10^9+7
 *  Space Complexity: O(1) // we are storing some variables
 * - Thing to notice that when we are getting the power value why can't we get the modulo value from each step this way we can avoid loss of precision error.
 * - Recursively we can get the power of some number and while getting the result in each step we can get the modulo value.
 */

/*---Corner Cases---*/
/*
 * 1. very big n value
 *    - Input: `1000000000000000`
 *    - Expected Output: `711414395`
 *    - Explanation: If we will check with max n value we will get to know our approach efficiency whether its handling large inputs and outputs.
 */

package recursion;

public class CountGoodNumbers {
    public static void main(String[] args) {
        System.out.println(countGoodNumbers(5));
        System.out.println(myPow(10, 9));
    }
    private static final int MOD = 1000000007;
    static int countGoodNumbers(long n) {
        long even = n / 2;
        long odd = n - even;
        return (int)((myPow(5, odd) * myPow(4, even)) % MOD);
    }

    static long myPow(long x, long n) {
        if (n == 0) return 1;
        long ans = myPow(x, n / 2);
        ans = (ans * ans) % MOD;
        if (n % 2 == 1) {
            ans = (ans * x) % MOD;
        }
        return ans;
    }
}

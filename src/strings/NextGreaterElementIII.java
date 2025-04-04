/*---Approach---*/
/*
 * Problem Statement: (LC:556)
 *  Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 *  Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 * ---Constraints---
 *  1 <= n <= 2^31 - 1
 *
 * ---Approaches---
 * NOTE: in the complexity analysis n represents the number of digits not the actual number as stated in constrains.
 * ->Brute Force (recursion):
 *  Time Complexity: O(n!) // It takes n! time to get all the permutation of the number(n is the number of digits in the number)
 *  Space Complexity: O(n) // for the recursion stack
 * - simply we will generate all permutations of the number, and we will compare and store the smallest permutation which is greater than number itself.
 *
 * ->Optimized Approach (Next Lexicographical Permutation):
 *  Time Complexity: O(n) // one iteration, swap and reverse max we are traversing two times in the number.
 *  Space Complexity: O(n) // For the char array
 * - Thing to notice here is: to get the closest permutation which is greater than the number itself, we can say we have to consider from right as they have less significant.
 * - If the number is completely decreasing then there will be no answer cos it's already the biggest permutation (e.g. 987654321).
 * - 1st we have to find a point where there is a violation. (e.g. 9879321, violation point is 7).
 * - we can run a while loop or for loop to get the point where its violated. The condition should look like this. if(arr[i] < arr[i+1]). then i is our pivot index.
 * - next step would be to search the index which we can swap with the pivot we have.
 * - One thing we can notice if we swap the smallest element which is lying after the pivot we will get close.
 * - After swapping as we know after the pivot its in decreasing order if we will reverse it we will get the closest permutation which is greater than the current no here we got the answer.
 * - As mention in the problem if the permutation values goes out of int value we have to return -1.
 * - To solve this we can simply store the answer in long, and then we can compare it with Integer.MAX_VALUE. if it's greater than this we will return -1 or else we will return number itself by type casting it to int.
 */

/*---Corner Cases---*/
/*
 * 1. Single Digit Number (No Next Greater)
 *    - Input: `n = 7`
 *    - Expected Output: `-1`
 *    - Explanation: A single-digit number has no next greater permutation.
 *
 * 2. Already Largest Permutation
 *    - Input: `n = 4321`
 *    - Expected Output: `-1`
 *    - Explanation: No greater number can be formed, as it's already the largest.
 *
 * 3. Smallest Swap Possible
 *    - Input: `n = 12`
 *    - Expected Output: `21`
 *    - Explanation: The next greater number is obtained by swapping `1` and `2`.
 *
 * 4. Swap in the Middle
 *    - Input: `n = 12543`
 *    - Expected Output: `13245`
 *    - Explanation: Swap `2` and `3`, then rearrange the remaining digits in ascending order.
 *
 * 5. Multiple Repeating Digits
 *    - Input: `n = 115432`
 *    - Expected Output: `121345`
 *    - Explanation: Find the first decreasing number from the right (`1`), swap with `2`, then rearrange.
 *
 * 6. Large Input
 *    - Input: `n = 1999999999`
 *    - Expected Output: `-1`
 *    - Explanation: The next permutation exceeds the 32-bit integer limit.
 *
 * 7. Number with Zeros
 *    - Input: `n = 120`
 *    - Expected Output: `201`
 *    - Explanation: Swap `1` and `2`, then sort the remaining digits.
 *
 * 8. Next Greater with Last Digits Swapped
 *    - Input: `n = 534976`
 *    - Expected Output: `536479`
 *    - Explanation: Swap `4` and `6`, then rearrange to the smallest greater number.
 *
 * 9. Large Number With Middle Swap
 *    - Input: `n = 9876543210`
 *    - Expected Output: `-1`
 *    - Explanation: Already the largest possible number.
 *
 * 10. Random Case
 *    - Input: `n = 230241`
 *    - Expected Output: `230412`
 *    - Explanation: Swap `2` and `4`, then sort the remaining digits.
 */


package strings;

import java.util.Arrays;

public class NextGreaterElementIII {
    public static void main(String[] args) {
        nextGreaterElement(91321);
    }
    static int nextGreaterElement(int n) {
        char[] digits = Integer.toString(n).toCharArray();
        int len = digits.length;
        int i = len-2;
        while(i>=0 && digits[i] >= digits[i+1]){
            i--;
        }
        if(i < 0) return -1;
        int j = len-1;
        while(digits[j] <= digits[i]){
            j--;
        }
        swap(digits,i,j);
        reverse(digits,i+1,len-1);
        long num =Long.parseLong(new String(digits));
        return  (num > Integer.MAX_VALUE) ? -1 : (int)num;
    }
    static void swap(char[] digits,int i,int j){
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
    static void reverse(char[] digits,int i, int j){
        while(i < j){
            swap(digits,i++,j--);
        }
    }
}

/*---Approach---*/
/*
 * Problem Statement: (LC:227)
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * ---Constraints---
 *  1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * ---Approaches---
 *  ->Optimized Approach (Deque):
 *  Time Complexity: O(n) // we are looping through the string most once.
 *  Space Complexity: O(n) // to store the filter values uses a deque.
 * - This problem can be done by using two stacks as well but in single pass if we want to do then we need a deque.
 * - why deque(this has operator precedence and left to right calculation).
 * - In the 1st step if we counter a digit we will store it in global variable names num using while loop.
 * - After that if we counter an operator(* , /) we will remove the operand from the deque and calculate with current operand and push it again the deque.
 * - and if we count (+ , -), in case of plus simply we will push the number in case minus we will push negation of the number.
 * - in end we loop through the deque using remove 1st(left - right calculation) and we will return the result.
 */

/*---Corner Cases---*/
/*
 * 2. Single Number (No operators):
 *    - Input: `"5"`
 *    - Expected Output: `5`
 *    - Explanation: A string with just one number returns the number itself.
 */


        package strings;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorII {
    public static void main(String[] args) {
//        System.out.println(calculate("3 + 2 * 2 * 7"));          // 7
        System.out.println(calculate(" 36/2 "));          // 1
//        System.out.println(calculate(" 3+6 / 2 "));      // 5
    }

    public static int calculate(String s) {
        Deque<Integer> val = new LinkedList<>();
        int i = 0, n = s.length();
        char sign = '+';
        int num = 0;
        while (i < n) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (!Character.isDigit(c) && c != ' ' || i == n - 1) {
                if (sign == '+') {
                    val.addLast(num);
                } else if (sign == '-') {
                    val.addLast(-num);
                } else if (sign == '*') {
                    val.addLast(val.removeLast() * num);
                } else if (sign == '/') {
                    val.addLast(val.removeLast() / num);
                }

                num = 0;
                sign = c;
            }

            i++;
        }

        int result = 0;
        while (!val.isEmpty()) {
            result += val.removeFirst();
        }

        return result;
    }
}

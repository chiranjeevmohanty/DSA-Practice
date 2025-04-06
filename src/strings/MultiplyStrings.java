/*---Approach---*/
/*
 * Problem Statement: (LC:43)
 *  Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *  Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * ---Constraints---
 *  1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *
 * ---Approaches---
 * ->Optimized Approach (Math and nested loop):
 *  Time Complexity: O(n ^ 2) // nested loops
 *  Space Complexity: O(n ^ 2) // to store the multiplied values in each step in an array
 * - As we can see the constraints the number length can be 200 so after multiply its impossible to store the value in any datatype except BigInt but that is also not allowed.
 * - Things to observe (e.g. s1 = "12" s2 = "21" s1 * s2 = (1 * 2) + (1 * 10) + (20 * 2) + (20 * 10) = (2 + 10 + 40 + 200) = 252)
 * - as we can see in the example that at each step the multiplication value is num1[i] * num2[j] + ((i + j) * 10). i = index in num1, j = index in num2.
 * - By using the approach we can simply add the multiplication value of 1 and j in a string builder and after that we can simply append desirable amount of zeroes and the count will be i + j.
 * - And in each step we will store the string builder in an array of StringBuilder.
 * - after that can get the count by looping through the array we have and in each step again we have to store it in a string builder.
 * - At the end we can simply return the result by type casting it to string.
 */

/*---Corner Cases---*/
/*
 * 1. One or both inputs are "0"
 *    - Input: num1 = "0", num2 = "12345"
 *    - Expected Output: "0"
 *    - Explanation: Anything multiplied by zero is zero.
 *
 * 2. Inputs are both "0"
 *    - Input: num1 = "0", num2 = "0"
 *    - Expected Output: "0"
 *    - Explanation: Zero times zero is zero.
 *
 * 3. Multiplying by "1"
 *    - Input: num1 = "1", num2 = "98765"
 *    - Expected Output: "98765"
 *    - Explanation: Any number multiplied by 1 should remain unchanged.
 *
 * 4. Very large numbers
 *    - Input: num1 = "987654321", num2 = "123456789"
 *    - Expected Output: "121932631112635269"
 *    - Explanation: Tests handling of large inputs that would overflow primitive data types.
 *
 * 5. Numbers with leading zeros (should be treated as valid inputs)
 *    - Input: num1 = "0000123", num2 = "0456"
 *    - Expected Output: "56088"
 *    - Explanation: Leading zeros should not affect the result.
 *
 * 6. Multiplying single-digit numbers
 *    - Input: num1 = "9", num2 = "8"
 *    - Expected Output: "72"
 *    - Explanation: Basic multiplication of single digits.
 *
 * 7. Unequal length inputs
 *    - Input: num1 = "2", num2 = "123456789"
 *    - Expected Output: "246913578"
 *    - Explanation: Tests how the algorithm handles vastly different input sizes.
 *
 * 8. Inputs are maximum possible length allowed by constraints
 *    - Input: num1 = 200-digit number, num2 = 200-digit number
 *    - Expected Output: Correct long product string
 *    - Explanation: Stress test for performance and memory efficiency.
 */

package strings;

public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("4568858979875974", "12387897893489678937869736"));
    }
    static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        StringBuilder[] val = new StringBuilder[num1.length() * num2.length()];
        int ind2 = 0;
        for(int i = num1.length() - 1; i >= 0; i--){
            for(int j = num2.length() - 1; j >= 0; j--){
                StringBuilder temp = new StringBuilder();
                int mul = Integer.parseInt(String.valueOf(num1.charAt(i))) * Integer.parseInt(String.valueOf(num2.charAt(j)));
                temp.append(mul);
                temp.append("0".repeat(num1.length() - i - 1 + num2.length() - j - 1));
                val[ind2++] = temp;
            }
        }
        int length = val[val.length - 1].length() - 1;
        StringBuilder res = new StringBuilder();
        int sum = 0;
        for(int i = length; i >= 0; i--){
            sum = sum / 10;
            for(StringBuilder s: val){
                if(!s.isEmpty()){
                    sum += Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
                    s.deleteCharAt(s.length() - 1);
                }
            }
            res.append(sum%10);
        }
        res.append(sum);
        return res.toString().replaceFirst("^0+(?!$)", "");
    }

    //most optimized same complexity n ^ 2
    static String multiply2(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] arr = new int[m + n];
        for (int i = m - 1; i >= 0; --i) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; --j) {
                int b = num2.charAt(j) - '0';
                arr[i + j + 1] += a * b;
            }
        }
        for (int i = arr.length - 1; i > 0; --i) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
        int i = arr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        for (; i < arr.length; ++i) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }
}

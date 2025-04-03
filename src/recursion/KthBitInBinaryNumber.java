/*---Approach---*/
/*
 * Problem Statement: (LC:1545)
 *  Given two positive integers n and k, the binary string Sn is formed as follows:
 * S1 = "0"
 * Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 *
 * ---Constraints---
 *  1 <= n <= 20
 *  1 <= k <= 2n - 1
 *
 * ---Approaches---
 * ->Brute Force (iterative):
 *  Time Complexity: O(2 ^ n) // for each step the string is doubling in size WRT n.
 *  Space Complexity: O(2 ^ n) // to store the string
 * - in this approach iteratively we have to form the string, and then we can return the char at index k.
 *
 * ->Optimized Approach (binary search):
 *  Time Complexity: O(log k) // to search the kth bit
 *  Space Complexity: O(log k) // for the call stack to store recursive function
 * - Things to notice here is that we really don't have to format the entire string simply recursively we can do this instead.
 * - s1 --> 0 , s2 --> 011, s3 --> 0111001
 * - we can se here is that the base case will be when the n is 0 then s1 --> 0;
 * - another thing to notice is the length of the string will always be (2 ^ n) - 1.
 * - and the mid is always constant which is '1'.
 * - How string formatted : s(n) --> s(n - 1) + "1" + invert(reverse(s(n - 1))).
 * - we will calculate the mid like  totalLength / 2 + 1.(which will be exactly the middle element)
 * - 1st case (k < m): k lies in the 1st half which is s(n - 1)
 * - the search space will be half then in recursion call we can call like function(n - 1, k);
 * - 2nd case (k = m): then simply return "1" coz middle is always 1.
 * - 3rd case (k > m): then its lies on the 2nd half.
 * - as 2nd half is reversed so we can get index like mid - (k - mid) - > this will give the exact index
 * - the search space will be half then in recursion call we can call like function(n - 1, mid - (k - mid));
 * - after getting "0" or "1" from the index simply we can invert it and return it, that will be the answer as the 2nd half is inverted.
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

//iterative approach
public class KthBitInBinaryNumber {
    public static void main(String[] args) {
        System.out.println( findKthBitRecursive(3, 5));
    }
    static char findKthBit(int n, int k) {
        String s = getBinaryNumber(n);
        return s.charAt(s.length() - k);
    }
    static String getBinaryNumber(int n){
        StringBuilder res = new StringBuilder("0");
        for(int i = 0; i < n - 1; i++){
            StringBuilder temp = new StringBuilder();
            for(int j = res.length() - 1; j >= 0; j--){
                if(res.charAt(j) == '1'){
                    temp.append('0');
                }else{
                    temp.append('1');
                }
            }
            res.append('1');
            res.append(temp);
        }
        return res.toString();
    }

    static char findKthBitRecursive(int n, int k) {
        return getKthBit(n, k);
    }

    static char getKthBit(int n, int k) {
        if (n == 1) return '0'; //base case as S1 --> 0
        int length = (1 << n) - 1; // 2^n - 1
        int mid = length / 2 + 1;

        if (k == mid) return '1'; // mid will always be "1" so no need to invert it
        if (k < mid) return getKthBit(n - 1, k); // if k is less than mid then search space will be n - 1, which is (2 ^ n - 1) - 1
        return invert(getKthBit(n - 1, mid - (k - mid)));
    }

    static char invert(char c) {
        return (c == '0') ? '1' : '0';
    }

}

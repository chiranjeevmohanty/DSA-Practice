package stack;
import java.util.*;

public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
    static int longestValidParentheses(String s) {
        int n = s.length();
        Stack<Integer> stc = new Stack<>();
        stc.push(-1);
        int max = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if (ch == '(') {
                stc.push(i);
            } else {
                stc.pop();
                if (stc.isEmpty()) {
                    stc.push(i);
                } else {
                    int currentLength = i - stc.peek();
                    max = Math.max(max, currentLength);
                }
            }
        }
        return max;
    }
}

package strings;

public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi("   -042"));
    }
    static int myAtoi(String s) {
        long result = 0;
        boolean leadingSpace = true;
        boolean intSign = true;
        int sign = 1;

        for (char c : s.toCharArray()) {
            if (leadingSpace && c == ' ') continue;
            leadingSpace = false;

            if (intSign && (c == '-' || c == '+')) {
                sign = (c == '-') ? -1 : 1;
                intSign = false;
                continue;
            }

            intSign = false;
            if (c >= '0' && c <= '9') {
                result = result * 10 + (c - '0');
                if (sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && -result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }

        return (int) (sign * result);
    }
}

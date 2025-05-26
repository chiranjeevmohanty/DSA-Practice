package stack;
import java.util.*;

public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
    //using monotonic stack
    static int trap(int[] height) {
        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        int water = 0;

        for (int i = 0; i < n; i++) {
            int curHeight = height[i];

            while (!stack.isEmpty() && curHeight > height[stack.peek()]) {
                int bottomIndex = stack.pop();
                if (stack.isEmpty()) break;

                int leftIndex = stack.peek();
                int width = i - leftIndex - 1;
                int boundedHeight = Math.min(curHeight, height[leftIndex]) - height[bottomIndex];
                if (boundedHeight > 0) {
                    int trappedWater = width * boundedHeight;
                    water += trappedWater;
                }
            }
            stack.push(i);
        }
        return water;
    }
    //using two pointers
    static int trap2(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int water = 0;
        int lMax = 0, rMax = 0;
        while(l < r){
            if(height[l] < height[r]){
                lMax = Math.max(lMax, height[l]);
                if(height[l] < lMax){
                    water += lMax - height[l];
                }
                l++;
            }else{
                rMax = Math.max(rMax, height[r]);
                if(height[r] < rMax){
                    water += rMax - height[r];
                }
                r--;;
            }
        }
        return water;
    }
}

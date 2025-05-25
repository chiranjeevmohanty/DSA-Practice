package arrays;

public class ReverseSubArrayToMaximizeVal {
    public static void main(String[] args) {
        System.out.println(maxValueAfterReverse(new int[]{2,3,1,5,4}));
    }
    static int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int total = 0;
        for(int i = 1; i < n; i++) total+= Math.abs(nums[i] - nums[i - 1]);
        int maxGain = 0;
        for (int i = 1; i < n - 1; i++) {
            int gain = Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]);
            maxGain = Math.max(maxGain, gain);
            int gain2 = Math.abs(nums[n - 1] - nums[i - 1]) - Math.abs(nums[i] - nums[i - 1]);
            maxGain = Math.max(maxGain, gain2);
        }
        int maxMin = Integer.MIN_VALUE;
        int minMax = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            maxMin = Math.max(maxMin, Math.min(x, y));
            minMax = Math.min(minMax, Math.max(x, y));
        }
        maxGain = Math.max(maxGain, 2 * (maxMin - minMax));
        return total + maxGain;
    }
}

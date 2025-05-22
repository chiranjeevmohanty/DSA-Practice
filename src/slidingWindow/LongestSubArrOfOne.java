package slidingWindow;

public class LongestSubArrOfOne {
    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
    }
    static int longestSubarray(int[] nums) {
        int[] freq = new int[2];
        int left = 0, right = 0;
        int maxLength = 0;
        while(right < nums.length){
            if(nums[right] == 1){
                freq[1]++;
            }else{
                freq[0]++;
            }
            while(left <= right && freq[0] > 1){
                if(nums[left] == 1){
                    freq[1]--;
                }else{
                    freq[0]--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength - 1;
    }
}

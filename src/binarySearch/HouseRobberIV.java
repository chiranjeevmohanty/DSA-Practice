package binarySearch;

public class HouseRobberIV {
    public static void main(String[] args) {
        System.out.println(minCapability(new int[]{2, 3, 5, 9}, 2));
    }
    static int minCapability(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num: nums){
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        while(min <= max){
            int mid = min + (max - min)/2;
            if(check(nums, k, mid)){
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }
        return min;
    }
    static boolean check(int[] nums, int k, int min){
        int count = 0;
        int i = 0;

        while (i < nums.length) {
            if (nums[i] <= min) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count >= k;
    }
}

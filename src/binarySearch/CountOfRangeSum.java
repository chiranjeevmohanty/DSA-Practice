package binarySearch;
import java.util.ArrayList;
import java.util.Arrays;

public class CountOfRangeSum {
    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }
    static int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSum = new long[nums.length + 1];
        int i = 1;
        for(int num: nums){
            prefixSum[i] = prefixSum[i - 1] + num;
            i++;
        }
        System.out.println(Arrays.toString(prefixSum));
        return helper(prefixSum, 0, prefixSum.length - 1, lower, upper);
    }
    static int helper(long[] nums, int left, int right, int lower, int upper){
        if (left >= right) {
            return 0;
        }
        int mid = (left + right)/2;
        int count = 0;
        count += helper(nums, left, mid, lower, upper);
        count += helper(nums, mid + 1, right, lower, upper);

        int l = mid + 1, r = mid + 1;
        for(int i = left; i <= mid; i++){
            while(l <= right && nums[l] - nums[i] < lower) l++;
            while (r <= right && nums[r] - nums[i] <= upper) r++;
            count += (r - l);
        }
        merge(nums, left, mid, right);
        return count;
    }
    static void merge(long[] arr, int left, int mid, int right){
        ArrayList<Long> temp = new ArrayList<>(right - left + 1);
        int i = left, j = mid + 1;
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp.add(arr[i++]);
            }else{
                temp.add(arr[j++]);
            }
        }
        while (i <= mid) {
            temp.add(arr[i++]);
        }
        while (j <= right) {
            temp.add(arr[j++]);
        }
        for (int k = 0; k < temp.size(); k++) {
            arr[left + k] = temp.get(k);
        }
    }
}

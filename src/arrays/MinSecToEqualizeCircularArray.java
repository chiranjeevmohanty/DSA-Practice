package arrays;
import java.util.*;

public class MinSecToEqualizeCircularArray {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(2,1,3,3,2,6,7,2,2,9,4,2,1));
        System.out.println(minimumSeconds(arr));
    }
    static int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        HashMap<Integer, List<Integer>> freq = new HashMap<>();
        for(int i = 0; i < n; i++){
            freq.putIfAbsent(nums.get(i), new ArrayList<>());
            freq.get(nums.get(i)).add(i);
        }
        System.out.println(freq);
        int result = Integer.MAX_VALUE;
        for(List<Integer> indices: freq.values()){
            int maxGap = 0;
            for(int i = 0; i < indices.size(); i++){
                int gap = 0;
                if(i == indices.size() - 1){
                    gap = n - indices.get(i) + indices.get(0);
                }else{
                    gap = indices.get(i + 1) - indices.get(i);
                }
                maxGap = Math.max(maxGap, gap);
            }
            result = Math.min(result, maxGap / 2);
        }
        return result;
    }
}

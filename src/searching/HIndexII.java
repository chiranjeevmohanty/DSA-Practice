package searching;

public class HIndexII {
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{0, 1, 3, 5, 6}));
    }
    static int hIndex(int[] citations) {
        int l = 0;
        int r = citations.length - 1;
        int n = citations.length;
        int ans = 0;
        while(l < r){
            int mid = l + (r - l)/2;
            if(citations[mid] >= n - mid){
                r = mid - 1;
                ans = n - mid;
            }else{
                l = mid + 1;
            }
        }
        System.out.println(l + " " + r);
        return ans;
    }
}

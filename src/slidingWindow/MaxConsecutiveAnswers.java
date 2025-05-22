package slidingWindow;

public class MaxConsecutiveAnswers {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("FFFTTFTTFT", 3));
    }
    static int maxConsecutiveAnswers(String answerKey, int k) {
        int[] freq = new int[2];
        int maxLength = 0;
        int left = 0, right = 0;
        while(right < answerKey.length()){
            if(answerKey.charAt(right) == 'T'){
                freq[1]++;
            }else{
                freq[0]++;
            }
            while(left <= right && freq[0] > k && freq[1] > k){
                if(answerKey.charAt(left) == 'T'){
                    freq[1]--;
                }else{
                    freq[0]--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;
    }
}

package stack;
import java.util.*;

public class AsteroidCollision {
    /*public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        for(int asteroid: asteroids){
            if(asteroid > 0){
                stack.push(asteroid);
                continue;
            }
            if(stack.isEmpty()){
                stack.push(asteroid);
            }else{
                if(stack.peek() > 0){
                    if((asteroid * -1) == stack.peek()){
                        stack.pop();
                    }else if((asteroid * -1) > stack.peek()){
                        while(!stack.isEmpty() && (asteroid * -1) > stack.peek() && stack.peek() > 0){
                            stack.pop();
                        }
                        if(stack.isEmpty()){
                            stack.add(asteroid);
                        }else{
                            if(stack.peek() == 1 * asteroid && stack.peek() > 0){
                                stack.pop();
                            }else if(stack.peek() < 1 * asteroid){
                                stack.push(asteroid);
                            }
                        }
                    }
                }else{
                    stack.push(asteroid);
                }
            }
        }
        List<Integer> answer = new ArrayList<>();
        while(!stack.isEmpty()){
            answer.add(stack.pop());
        }
        Collections.reverse(answer);
        int[] ans = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            ans[i] = answer.get(i);
        }
        return ans;
    }*/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop();
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                    destroyed = true;
                    break;
                } else {
                    destroyed = true;
                    break;
                }
            }

            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}

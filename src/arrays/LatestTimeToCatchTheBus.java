package arrays;
import java.util.*;

public class LatestTimeToCatchTheBus {
    public static void main(String[] args) {
        System.out.println(latestTimeCatchTheBus(new int[]{20,30,10}, new int[]{19,13,26,4,25,11,21}, 2));
    }
    static int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);

        HashSet<Integer> passengersSet = new HashSet<>();
        for (int passenger : passengers) {
            passengersSet.add(passenger);
        }

        int i = 0;
        int lastBus = buses[buses.length - 1];
        int remCapacity = capacity;
        for (int bus : buses) {
            remCapacity = capacity;
            while (i < passengers.length && remCapacity > 0 && passengers[i] <= bus) {
                i++;
                remCapacity--;
            }
        }
        if (remCapacity > 0) {
            int time = lastBus;
            while (passengersSet.contains(time)) {
                time--;
            }
            return time;
        }
        int lastBoarded = passengers[i - 1];
        int time = lastBoarded - 1;
        while (passengersSet.contains(time)) {
            time--;
        }
        return time;
    }
}

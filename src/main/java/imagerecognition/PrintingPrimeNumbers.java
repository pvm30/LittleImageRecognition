package imagerecognition;

import java.util.ArrayList;
import java.util.List;

public class PrintingPrimeNumbers {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int n = 2; n < 100; n++) {
            list.add(n);
        }

        // Printing prime numbers
        while (!list.isEmpty()) {
            Integer i = list.remove(0);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) % i == 0) {
                    list.remove(list.get(j));
                }
            }
            System.out.println(i);
        }
    }

}

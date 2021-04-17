package desafios.fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public static List<Integer> fibonacci() {
        ArrayList<Integer> fibonacciList = new ArrayList<>();
        for ( int i = 0; i <= 350; i++ ) {
            if (i > 1) {
                int size = fibonacciList.size();
                int lastNumber = fibonacciList.get(size - 1);
                int penultNumber = fibonacciList.get(size - 2);
                int newNumber = lastNumber + penultNumber;
                fibonacciList.add(newNumber);
                if (newNumber > 350) {
                    break;
                }
            } else {
                fibonacciList.add(i);
            }
        }
        return fibonacciList;
    }

    public static Boolean isFibonacci(Integer number) {
        return fibonacci().contains(number);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci());
        System.out.println(isFibonacci(5));
    }

}

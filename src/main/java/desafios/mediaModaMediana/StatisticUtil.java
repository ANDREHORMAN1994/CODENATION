package desafios.mediaModaMediana;

import java.util.Arrays;

public class StatisticUtil {

    public static int average(int[] elements) {
        return Math.round(Arrays.stream(elements).sum() / elements.length);
    }

    public static int mode(int[] elements) {
        int count = 0;
        int result = 0;
        for (int i : elements) {
            int count2 = 0;
            for (int i2 : elements) {
                if (i == i2) count2 += 1;
                if (count2 > count) {
                    count = count2;
                    result = i;
                }
            }
        }
        return result;
    }

    public static int median(int[] elements) {
        Arrays.sort(elements);
        int tamanho = elements.length;
        int mediana;
        if (tamanho % 2 != 0) {
            mediana = elements[(tamanho - 1) / 2];
            return mediana;
        } else {
            int anterior = elements[tamanho / 2 - 1];
            int proximo = elements[tamanho / 2];
            mediana = (anterior + proximo) / 2;
            return mediana;
        }
    }

    public static void main(String[] args) {
        StatisticUtil metodos = new StatisticUtil();
        int[] mediaTeste = new int[]{1, 2, 3, 4, 5};
        int[] modaTeste = new int[]{1, 1, 1, 1, 2, 2, 3, 3, 3};
        int[] medianaTeste = new int[]{1, 2, 3, 9, 1, 6, 7, 8};
        System.out.println(metodos.average(mediaTeste));
        System.out.println(metodos.mode(modaTeste));
        System.out.println(metodos.median(medianaTeste));
    }

}
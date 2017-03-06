/**
 * Created by chris on 2016-02-17.
 */
public class QuickSort {
    public static int[] sort(int[] a){
        quickSort(a, 0, a.length-1);
        return a;
    }

    private static int[] quickSort(int[] a, int first, int last) {
        if (first < last){
            int pivIndex = partition(a, first, last);
            quickSort(a, first, pivIndex-1);
            quickSort(a, pivIndex+1, last);
        }
        return a;
    }

    private static int partition(int[] a, int first, int last) {
        int pivot = a[first];
        int up = first;
        int down = last;

        do{
            while ((up < last) && (pivot >= a[up])) up++;

            while (pivot < a[down] ) down--;

            if (up < down){
                int tmp = a[up];
                a[up] = a[down];
                a[down] = tmp;
            }
        }while (up < down);

        int tmp = a[first];
        a[first] = a[down];
        a[down] = tmp;

        return down;
    }
}

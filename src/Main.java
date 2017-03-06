import java.math.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        float[] mergeArray = new float[50];
        float[] quickArray = new float[50];


        for (int i = 0; i < 50; i++){
            mergeArray[i] = (float)Math.random();
            quickArray[i] = (float)Math.random();
        }

        MergeSort.sort(mergeArray);
        QuickSort.sort(quickArray);

        System.out.println("MergeArray sorted = " + checkIfSorted(mergeArray));
        System.out.println("QuickArray sorted = " +checkIfSorted(quickArray));




    }

    public static boolean checkIfSorted(float[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }
}

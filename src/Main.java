public class Main {

    public static void main(String[] args) {
        int arrayLength = (int)1E8;                       //length of array
        int iterations = 20;                              //number of iterations to test

        long start = System.nanoTime();

        PerformanceTester.test(arrayLength, 1, new ParallelQuickSort(), iterations, "ParallelQuickSort").saveAsFile();
        PerformanceTester.test(arrayLength, 2, new ParallelQuickSort(), iterations, "ParallelQuickSort").saveAsFile();
        PerformanceTester.test(arrayLength, 4, new ParallelQuickSort(), iterations, "ParallelQuickSort").saveAsFile();
        PerformanceTester.test(arrayLength, 6, new ParallelQuickSort(), iterations, "ParallelQuickSort").saveAsFile();
        PerformanceTester.test(arrayLength, 8, new ParallelQuickSort(), iterations, "ParallelQuickSort").saveAsFile();

        PerformanceTester.test(arrayLength, 1, new ParallelMergeSort(), iterations, "ParallelMergeSort").saveAsFile();
        PerformanceTester.test(arrayLength, 2, new ParallelMergeSort(), iterations, "ParallelMergeSort").saveAsFile();
        PerformanceTester.test(arrayLength, 4, new ParallelMergeSort(), iterations, "ParallelMergeSort").saveAsFile();
        PerformanceTester.test(arrayLength, 6, new ParallelMergeSort(), iterations, "ParallelMergeSort").saveAsFile();
        PerformanceTester.test(arrayLength, 8, new ParallelMergeSort(), iterations, "ParallelMergeSort").saveAsFile();

        PerformanceTester.test(arrayLength, 1, new MergeSort(), iterations, "MergeSort").saveAsFile();
        PerformanceTester.test(arrayLength, 1, new QuickSort(), iterations, "QuickSort").saveAsFile();

        PerformanceTester.test(arrayLength, 1, new JavaParallellSort(), iterations, "JavaParallelSort").saveAsFile();
        PerformanceTester.test(arrayLength, 1, new JavaSort(), iterations, "JavaQuickSort").saveAsFile();

        System.out.println("Tests complete! Time: " + (System.nanoTime()-start));
    }


}

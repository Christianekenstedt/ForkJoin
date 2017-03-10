import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Anton on 2017-03-09.
 */
public class ParallelQuickSort implements SortStrategy{
    @Override
    public float[] sort(float[] a, int cores) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(cores);

        QuickSortTask rootTask = new QuickSortTask(a, 0, a.length-1);

        forkJoinPool.invoke(rootTask);

        try {
            return rootTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return a;
    }


    private class QuickSortTask extends RecursiveTask<float[]> {
        int threshold = 4000;

        float[] start_a;
        int start_first, start_last;

        public QuickSortTask(float[] a, int first, int last){
            this.start_a = a;
            this.start_first = first;
            this.start_last = last;
        }

        @Override
        protected float[] compute() {
            return quickSort(start_a, start_first, start_last);
        }

        private float[] quickSort(float[] a, int first, int last) {
            if (first < last){
                int pivIndex = partition(a, first, last);

                if(last - first < threshold){
                    quickSort(a, first, pivIndex-1);
                    quickSort(a, pivIndex+1, last);
                }else{
                    QuickSortTask worker1 = new QuickSortTask(a, first, pivIndex-1);
                    QuickSortTask worker2 = new QuickSortTask(a, pivIndex+1, last);

                    worker1.fork();
                    worker2.compute();
                    worker1.join();
                }
            }
            return a;
        }

        private int partition(float[] a, int first, int last) {
            float pivot = a[first];
            int up = first;
            int down = last;

            do{
                while ((up < last) && (pivot >= a[up])) up++;

                while (pivot < a[down] ) down--;

                if (up < down){
                    float tmp = a[up];
                    a[up] = a[down];
                    a[down] = tmp;
                }
            }while (up < down);

            float tmp = a[first];
            a[first] = a[down];
            a[down] = tmp;

            return down;
        }
    }
}

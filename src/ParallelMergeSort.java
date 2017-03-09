import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by Christian & Anton 2017-03-09
 */
public class ParallelMergeSort implements SortStrategy{

    public float[] sort(float[] a, int cores){

        ForkJoinPool forkJoinPool = new ForkJoinPool(cores);

        MergeSortTask rootTask = new MergeSortTask(a);

        forkJoinPool.invoke(rootTask);

        try {
            return rootTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

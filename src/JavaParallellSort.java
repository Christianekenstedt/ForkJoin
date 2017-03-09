/**
 * Created by Anton on 2017-03-09.
 */
public class JavaParallellSort implements SortStrategy{

    @Override
    public float[] sort(float[] a, int cores) {
        java.util.Arrays.parallelSort(a);
        return a;
    }
}

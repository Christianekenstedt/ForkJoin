/**
 * Created by Anton on 2017-03-09.
 */
public class JavaSort implements SortStrategy{

    @Override
    public float[] sort(float[] a, int cores) {
        java.util.Arrays.sort(a);
        return a;
    }
}

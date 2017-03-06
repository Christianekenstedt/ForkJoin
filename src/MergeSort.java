import java.util.Arrays;

/**
 * Created by chris on 2016-02-17.
 */
public class MergeSort {
    public static float[] sort(float[] a){

        if (a.length == 1) return a;

        float[] b = new float[a.length/2];
        float[] c = new float[a.length-b.length];

        System.arraycopy(a, 0, b, 0, b.length);
        System.arraycopy(a, b.length, c, 0, c.length);

        sort(b);
        sort(c);

        return merge(b,c,a);
    }

    private static float[] merge(float[] a, float[] b, float[]c){
        int indexa=0, indexb=0, indexc=0;

        while (indexa < a.length && indexb < b.length){
            if (a[indexa] <= b[indexb]){
                c[indexc++] = a[indexa];
                indexa++;
            }else {
                c[indexc++] = b[indexb];
                indexb++;
            }
        }
        while (indexa<a.length){
            c[indexc++]=a[indexa];
            indexa++;
        }
        while (indexb<b.length){
            c[indexc++]=b[indexb];
            indexb++;
        }
        return c;
    }

}

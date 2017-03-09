import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Anton on 2017-03-09.
 */
public class MergeSortTask extends RecursiveTask<float[]> {
    int threshold = 7000;

    float[] a;


    public MergeSortTask(float[] a){
        this.a = a;

    }

    @Override
    protected float[] compute() {
        return sort(a);
    }

    private float[] sort(float[] a){

        if (a.length == 1) return a;

        float[] b = new float[a.length/2];
        float[] c = new float[a.length-b.length];

        System.arraycopy(a, 0, b, 0, b.length);
        System.arraycopy(a, b.length, c, 0, c.length);

        if(b.length<threshold){
            sort(b);
            sort(c);

            return merge(b,c,a);
        }else{
            MergeSortTask worker1 = new MergeSortTask(b);
            MergeSortTask worker2 = new MergeSortTask(c);

            worker1.fork();

            return merge(worker2.compute(), worker1.join(), a);
        }

    }

    private float[] merge(float[] a, float[] b, float[]c){
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
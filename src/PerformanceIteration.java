/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceIteration {

    public PerformanceIteration(long time, boolean sorted){
        this.elapsedTimeNanoSec = time;
        this.sorted = sorted;
    }
    private long elapsedTimeNanoSec;
    private boolean sorted;

    public long getElapsedTimeNanoSec() {
        return elapsedTimeNanoSec;
    }

    public boolean isSorted() {
        return sorted;
    }
}

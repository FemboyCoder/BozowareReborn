package bozoware.client.util.client;

public class TimerUtil {

    private long initTime;

    public TimerUtil() {
        this.initTime = System.currentTimeMillis();
    }
    public long elapsed() {
        return System.currentTimeMillis() - initTime;
    }
    public void reset() {
        this.initTime = System.currentTimeMillis();
    }
    public boolean elapsed(long time) {
        return time >= elapsed();
    }
    public boolean elapsed(long time, boolean reset) {
        boolean elapsed = elapsed(time);
        if(reset && elapsed) {
            reset();
        }
        return elapsed(time);
    }
}

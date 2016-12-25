package eu.jwvl.phonlib.util.numeric;

/**
 * Created by janwillem on 03/12/15.
 */
public class Range {

    private final int startAt;
    private final int endAt;

    private Range(int startAt, int endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Range of(int start, int end) {
        return new Range(start,end);
    }

    public int getStartAt() {
        return startAt;
    }

    public int getEndAt() {
        return endAt;
    }
}

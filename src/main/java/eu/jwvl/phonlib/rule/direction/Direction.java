package eu.jwvl.phonlib.rule.direction;

/**
 * Created by janwillem on 03/12/15.
 */
public enum Direction {
    LEFT(-1), RIGHT(1);
    private final int moveIndex;

    Direction(int moveIndex) {
        this.moveIndex = moveIndex;
    }


}

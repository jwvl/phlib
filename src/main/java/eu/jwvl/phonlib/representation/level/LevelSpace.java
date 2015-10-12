package eu.jwvl.phonlib.representation.level;

/**
 * Created by janwillem on 04/10/15.
 */
public class LevelSpace {
    private final Level[] contents;

    public LevelSpace(Level... contents) {
        this.contents = contents;
    }

    public int size() {
        return contents.length;
    }

    public boolean contains(Level level) {
        return indexOf(level) >= 0;
    }

    public int indexOf(Level level) {
        for (int i=0; i < size(); i++) {
            if (contents[i] == level) {
                return i;
            }
        }
        return -1;
    }

    public boolean areAdjacent(Level first, Level second) {
        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);
        if (firstIndex < 0 || secondIndex < 0) {
            return false;
        }
        return Math.abs(firstIndex - secondIndex) == 1;
    }
}

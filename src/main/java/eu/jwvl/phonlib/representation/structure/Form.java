package eu.jwvl.phonlib.representation.structure;

import eu.jwvl.phonlib.representation.level.Level;

/**
 * Created by janwillem on 02/10/15.
 */
public abstract class Form {
    private final Level level;

    public Form(Level level) {
        this.level = level;
    }
}

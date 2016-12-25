package eu.jwvl.phonlib.representation.structure;

import eu.jwvl.phonlib.representation.level.Level;

/**
 * Created by janwillem on 02/10/15.
 */
public abstract class ComplexForm<F extends Form> extends Form {

    public ComplexForm(Level level) {
        super(level);
    }

    abstract F[] getFormsAsArray();

    abstract int length();
}

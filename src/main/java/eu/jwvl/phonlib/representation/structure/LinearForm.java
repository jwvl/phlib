package eu.jwvl.phonlib.representation.structure;

/**
 * Created by janwillem on 02/10/15.
 */
public abstract class LinearForm<F extends Form> implements ComplexForm<F> {
    F[] forms;


    @Override
    public F[] getFormsAsArray() {
        return forms;
    }
}

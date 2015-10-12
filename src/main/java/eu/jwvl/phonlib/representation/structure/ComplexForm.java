package eu.jwvl.phonlib.representation.structure;

/**
 * Created by janwillem on 02/10/15.
 */
public interface ComplexForm<F extends Form> extends Form {
    public F[] getFormsAsArray();
}
